import React, { useState, useEffect } from "react";
import axios from "axios";
import Layout from "../../Components/Layouts/Layout";
import { useAuth } from "../../context/auth";

const MyRides = () => {
  const [auth, setAuth] = useAuth();
  const [showPassengers, setShowPassengers] = useState(false);
  const [rides, setRides] = useState([]);
  const [filteredRides, setFilteredRides] = useState([]);
  const [passengers, setPassengers] = useState([]);
  const [selectedRide, setSelectedRide] = useState(null);
  const [filterStatus, setFilterStatus] = useState("ALL");
  const [showCancelModal, setShowCancelModal] = useState(false);
  const [rideToCancel, setRideToCancel] = useState(null);

  useEffect(() => {
    // Fetch ride details from the API
    axios
      .get(`https://api.sharemytrip.xyz/user/publishers/${auth.id}/rides`)
      .then((response) => {
        setRides(response.data);
        setFilteredRides(response.data);
      })
      .catch((error) => console.error("Error fetching rides:", error));
  }, [auth.id]);

  const sendEmail = async (passengerEmail) => {
    try {
      await axios.post(
        `https://api.sharemytrip.xyz/email/send-publisher-canceled-notification-passenger?passengerEmail=${encodeURIComponent(
          passengerEmail
        )}`
      );
    } catch (error) {
      console.log(error);
    }
  };

  const handleViewPassengers = (rideId) => {
    setShowPassengers(!showPassengers);
    setSelectedRide(rideId);
    if (!showPassengers) {
      axios
        .get(`https://api.sharemytrip.xyz/user/publishers/${rideId}/passengers`)
        .then((response) => setPassengers(response.data))
        .catch((error) => console.error("Error fetching passengers:", error));
    }
  };

  const handleStartRide = (rideId) => {
    axios
      .put(`https://api.sharemytrip.xyz/user/publishers/${rideId}/start`)
      .then(() => {
        setRides((prevRides) =>
          prevRides.map((ride) =>
            ride.publisherRideId === rideId
              ? { ...ride, status: "ONGOING" }
              : ride
          )
        );
        setFilteredRides((prevRides) =>
          prevRides.map((ride) =>
            ride.publisherRideId === rideId
              ? { ...ride, status: "ONGOING" }
              : ride
          )
        );
      })
      .catch((error) => console.error("Error starting ride:", error));
  };

  const handleEndRide = (rideId) => {
    axios
      .put(`https://api.sharemytrip.xyz/user/publishers/${rideId}/end`)
      .then(() => {
        setRides((prevRides) =>
          prevRides.map((ride) =>
            ride.publisherRideId === rideId
              ? { ...ride, status: "COMPLETED" }
              : ride
          )
        );
        setFilteredRides((prevRides) =>
          prevRides.map((ride) =>
            ride.publisherRideId === rideId
              ? { ...ride, status: "COMPLETED" }
              : ride
          )
        );
      })
      .catch((error) => console.error("Error ending ride:", error));
  };

  const handleCancelRide = async (rideId) => {
    try {
      // Fetch the latest passengers data
      const response = await axios.get(
        `https://api.sharemytrip.xyz/user/publishers/${rideId}/passengers`
      );
      const passengersData = response.data;

      // Send email to publisher
      await axios.post(
        `https://api.sharemytrip.xyz/email/send-publisher-canceled-notification?publisherEmail=${encodeURIComponent(
          auth.email
        )}`
      );

      // Send email to all passengers
      for (const passenger of passengersData) {
        await sendEmail(passenger.passengerEmail);
      }

      // Delete the ride
      await axios.delete(
        `https://api.sharemytrip.xyz/rides/cancel-published/${rideId}`
      );

      // Update the state after deletion
      setRides((prevRides) =>
        prevRides.map((ride) =>
          ride.publisherRideId === rideId
            ? { ...ride, status: "CANCELED" }
            : ride
        )
      );
      setFilteredRides((prevRides) =>
        prevRides.map((ride) =>
          ride.publisherRideId === rideId
            ? { ...ride, status: "CANCELED" }
            : ride
        )
      );
      setShowCancelModal(false);
    } catch (error) {
      console.error("Error canceling ride:", error);
    }
  };

  const handleFilterChange = (status) => {
    setFilterStatus(status);
    if (status === "ALL") {
      setFilteredRides(rides);
    } else {
      setFilteredRides(rides.filter((ride) => ride.status === status));
    }
  };

  const isStartButtonEnabled = (ride) => {
    const currentDateTime = new Date();
    const journeyDateTime = new Date(
      `${ride.dateOfJourney}T${ride.timeOfJourney}`
    );
    return currentDateTime >= journeyDateTime;
  };

  const openCancelModal = (rideId) => {
    setRideToCancel(rideId);
    setShowCancelModal(true);
  };

  const closeCancelModal = () => {
    setShowCancelModal(false);
    setRideToCancel(null);
  };

  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">My Rides</h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-6xl mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Published Rides</h2>
          <div className="mb-4">
            <label className="mr-4">Filter by status:</label>
            <select
              value={filterStatus}
              onChange={(e) => handleFilterChange(e.target.value)}
              className="text-gray-700 bg-white border border-gray-300 rounded-md"
            >
              <option value="ALL">All</option>
              <option value="NOT_COMPLETED">Not Completed</option>
              <option value="ONGOING">Ongoing</option>
              <option value="COMPLETED">Completed</option>
              <option value="CANCELED">Canceled</option>
            </select>
          </div>
          <div className="overflow-x-auto">
            <table className="min-w-full bg-white">
              <thead>
                <tr>
                  <th className="py-2 px-4 bg-gray-200 text-left">From</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">To</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">Date</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">Time</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">
                    Seats Available
                  </th>
                  <th className="py-2 px-4 bg-gray-200 text-left">Fare</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">Status</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">Actions</th>
                </tr>
              </thead>
              <tbody>
                {filteredRides.map((ride) => (
                  <tr key={ride.publisherRideId}>
                    <td className="border px-4 py-2 text-center">
                      {ride.fromLocation}
                    </td>
                    <td className="border px-4 py-2 text-center">
                      {ride.toLocation}
                    </td>
                    <td className="border px-4 py-2 text-center">
                      {ride.dateOfJourney}
                    </td>
                    <td className="border px-4 py-2 text-center">
                      {ride.timeOfJourney}
                    </td>
                    <td className="border px-4 py-2 text-center">
                      {ride.availableSeats}
                    </td>
                    <td className="border px-4 py-2 text-center">
                      {ride.farePerSeat}
                    </td>
                    <td className="border px-4 py-2 text-center">{ride.status}</td>
                    <td className="border px-4 py-2 text-center">
                      <div className="flex justify-center items-center space-x-2">
                        <button
                          onClick={() =>
                            handleViewPassengers(ride.publisherRideId)
                          }
                          className="text-blue-500 hover:text-blue-700"
                        >
                          View
                        </button>
                        {ride.status !== "COMPLETED" &&
                          ride.status !== "CANCELED" && (
                            <>
                              <button
                                onClick={() =>
                                  handleStartRide(ride.publisherRideId)
                                }
                                className={`text-green-500 hover:text-green-700 ${
                                  !isStartButtonEnabled(ride)
                                    ? "opacity-50 cursor-not-allowed"
                                    : ""
                                }`}
                                disabled={!isStartButtonEnabled(ride)}
                              >
                                Start
                              </button>
                              {ride.status === "ONGOING" && (
                                <button
                                  onClick={() =>
                                    handleEndRide(ride.publisherRideId)
                                  }
                                  className="text-red-500 hover:text-red-700"
                                >
                                  End
                                </button>
                              )}
                              {ride.status !== "ONGOING" && (
                                <button
                                  onClick={() =>
                                    openCancelModal(ride.publisherRideId)
                                  }
                                  className="text-yellow-500 hover:text-yellow-700"
                                >
                                  Cancel
                                </button>
                              )}
                            </>
                          )}
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

          {showPassengers && selectedRide && (
            <section className="bg-white p-8 mt-10 rounded-lg shadow-md">
              <h2 className="text-2xl text-red-600 mb-6">Passengers</h2>
              <div className="overflow-x-auto">
                <table className="min-w-full bg-white">
                  <thead>
                    <tr>
                      <th className="py-2 px-4 bg-gray-200 text-center">
                        Passenger Name
                      </th>
                      <th className="py-2 px-4 bg-gray-200 text-center">
                        Mobile Number
                      </th>
                      <th className="py-2 px-4 bg-gray-200 text-center">
                        No. of Seats Booked
                      </th>
                      <th className="py-2 px-4 bg-gray-200 text-center">
                        Payment Status
                      </th>
                      <th className="py-2 px-4 bg-gray-200 text-center">
                        Paid Amount
                      </th>
                    </tr>
                  </thead>
                  <tbody>
                    {passengers.map((passenger, index) => (
                      <tr key={index}>
                        <td className="border px-4 py-2 text-center">
                          {passenger.passengerName}
                        </td>
                        <td className="border px-4 py-2 text-center">
                          {passenger.passengerMobile}
                        </td>
                        <td className="border px-4 py-2 text-center">
                          {passenger.passengerCount}
                        </td>
                        <td className="border px-4 py-2 text-center">
                          {passenger.passengerPaymentStatus}
                        </td>
                        <td className="border px-4 py-2 text-center">
                          {passenger.passengerPaymentStatus === "PAID"
                            ? passenger.fare * passenger.passengerCount
                            : 0}
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </section>
          )}

          {showCancelModal && (
            <div
              id="popup-modal"
              tabIndex="-1"
              className="fixed inset-0 z-50 flex items-center justify-center overflow-y-auto overflow-x-hidden bg-black bg-opacity-50"
            >
              <div className="relative p-4 w-full max-w-md max-h-full">
                <div className="relative bg-white rounded-lg shadow dark:bg-gray-700">
                  <button
                    type="button"
                    className="absolute top-3 end-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
                    onClick={closeCancelModal}
                  >
                    <svg
                      className="w-3 h-3"
                      aria-hidden="true"
                      xmlns="http://www.w3.org/2000/svg"
                      fill="none"
                      viewBox="0 0 14 14"
                    >
                      <path
                        stroke="currentColor"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"
                      />
                    </svg>
                    <span className="sr-only">Close modal</span>
                  </button>
                  <div className="p-4 md:p-5 text-center">
                    <svg
                      className="mx-auto mb-4 text-gray-400 w-12 h-12 dark:text-gray-200"
                      aria-hidden="true"
                      xmlns="http://www.w3.org/2000/svg"
                      fill="none"
                      viewBox="0 0 20 20"
                    >
                      <path
                        stroke="currentColor"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M10 11V6m0 8h.01M19 10a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
                      />
                    </svg>
                    <h3 className="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">
                      Are you sure you want to cancel this ride?
                    </h3>
                    <button
                      type="button"
                      className="text-white bg-red-600 hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 font-medium rounded-lg text-sm inline-flex items-center px-5 py-2.5 text-center"
                      onClick={() => handleCancelRide(rideToCancel)}
                    >
                      Yes, I'm sure
                    </button>
                    <button
                      type="button"
                      className="py-2.5 px-5 ms-3 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
                      onClick={closeCancelModal}
                    >
                      No, cancel
                    </button>
                  </div>
                </div>
              </div>
            </div>
          )}
        </section>
      </main>
    </Layout>
  );
};

export default MyRides;
