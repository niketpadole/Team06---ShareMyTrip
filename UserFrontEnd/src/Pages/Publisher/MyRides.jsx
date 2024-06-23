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

  useEffect(() => {
    // Fetch ride details from the API
    axios
      .get(`http://localhost:8089/user/publishers/${auth.id}/rides`)
      .then((response) => {
        setRides(response.data);
        setFilteredRides(response.data);
      })
      .catch((error) => console.error("Error fetching rides:", error));
  }, [auth.id]);

  const handleViewPassengers = (rideId) => {
    setShowPassengers(!showPassengers);
    setSelectedRide(rideId);
    if (!showPassengers) {
      axios
        .get(`http://localhost:8089/user/publishers/${rideId}/passengers`)
        .then((response) => setPassengers(response.data))
        .catch((error) => console.error("Error fetching passengers:", error));
    }
  };

  const handleStartRide = (rideId) => {
    axios
      .put(`http://localhost:8089/user/publishers/${rideId}/start`)
      .then(() => {
        setRides((prevRides) =>
          prevRides.map((ride) =>
            ride.publisherRideId === rideId ? { ...ride, status: "ONGOING" } : ride
          )
        );
        setFilteredRides((prevRides) =>
          prevRides.map((ride) =>
            ride.publisherRideId === rideId ? { ...ride, status: "ONGOING" } : ride
          )
        );
      })
      .catch((error) => console.error("Error starting ride:", error));
  };

  const handleEndRide = (rideId) => {
    axios
      .put(`http://localhost:8089/user/publishers/${rideId}/end`)
      .then(() => {
        setRides((prevRides) =>
          prevRides.map((ride) =>
            ride.publisherRideId === rideId ? { ...ride, status: "COMPLETED" } : ride
          )
        );
        setFilteredRides((prevRides) =>
          prevRides.map((ride) =>
            ride.publisherRideId === rideId ? { ...ride, status: "COMPLETED" } : ride
          )
        );
      })
      .catch((error) => console.error("Error ending ride:", error));
  };

  const handleFilterChange = (status) => {
    setFilterStatus(status);
    if (status === "ALL") {
      setFilteredRides(rides);
    } else {
      setFilteredRides(rides.filter((ride) => ride.status === status));
    }
  };

  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">My Rides</h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
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
                  <th className="py-2 px-4 bg-gray-200 text-left">Seats</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">Fare</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">Status</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">Actions</th>
                </tr>
              </thead>
              <tbody>
                {filteredRides.map((ride) => (
                  <tr key={ride.publisherRideId}>
                    <td className="border px-4 py-2">{ride.fromLocation}</td>
                    <td className="border px-4 py-2">{ride.toLocation}</td>
                    <td className="border px-4 py-2">{ride.dateOfJourney}</td>
                    <td className="border px-4 py-2">{ride.timeOfJourney}</td>
                    <td className="border px-4 py-2">{ride.availableSeats}</td>
                    <td className="border px-4 py-2">${ride.farePerSeat}</td>
                    <td className="border px-4 py-2">{ride.status}</td>
                    <td className="border px-4 py-2 flex space-x-2">
                      <button
                        onClick={() => handleViewPassengers(ride.publisherRideId)}
                        className="text-blue-500 hover:text-blue-700"
                      >
                        View
                      </button>
                      {ride.status !== "COMPLETED" && (
                        <>
                          <button
                            onClick={() => handleStartRide(ride.publisherRideId)}
                            className="text-green-500 hover:text-green-700"
                          >
                            Start
                          </button>
                          {ride.status === "ONGOING" && (
                            <button
                              onClick={() => handleEndRide(ride.publisherRideId)}
                              className="text-red-500 hover:text-red-700"
                            >
                              End
                            </button>
                          )}
                          {/* <button className="text-red-500 hover:text-red-700">
                            Delete
                          </button> */}
                        </>
                      )}
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
                      <th className="py-2 px-4 bg-gray-200 text-left">Passenger Name</th>
                      <th className="py-2 px-4 bg-gray-200 text-left">Mobile Number</th>
                      <th className="py-2 px-4 bg-gray-200 text-left">No. of Seats Booked</th>
                      <th className="py-2 px-4 bg-gray-200 text-left">Payment Status</th>
                    </tr>
                  </thead>
                  <tbody>
                    {passengers.map((passenger, index) => (
                      <tr key={index}>
                        <td className="border px-4 py-2">{passenger.passengerName}</td>
                        <td className="border px-4 py-2">{passenger.passengerMobile}</td>
                        <td className="border px-4 py-2">{passenger.passengerCount}</td>
                        <td className="border px-4 py-2">{passenger.passengerPaymentStatus}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </section>
          )}
        </section>
      </main>
    </Layout>
  );
};

export default MyRides;
