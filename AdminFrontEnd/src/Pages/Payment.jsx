import React, { useState, useEffect } from "react";
import Layout from "../Components/Layouts/Layout";
import axios from "axios";

const Payment = () => {
  const [rides, setRides] = useState([]);
  const [selectedRide, setSelectedRide] = useState(null);
  const [reservedSeats, setReservedSeats] = useState(0);
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("https://api.sharemytrip.xyz/rides/completed");
        if (response.status === 200) {
          setRides(response.data);
        }
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);

  const handleViewDetails = async (ride) => {
    try {
      const responseDetailsRide = await axios.get(`https://api.sharemytrip.xyz/user/publishers/${ride.publisherRideId}/passengers`);
      const passengerData = responseDetailsRide.data;
      const totalReservedSeats = passengerData.reduce((acc, passenger) => acc + passenger.passengerCount, 0);
      setReservedSeats(totalReservedSeats);
    } catch (error) {
      console.log(error);
    }

    setSelectedRide(ride);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
    setSelectedRide(null);
    setReservedSeats(0);
  };

  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">Total Trips</h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-6xl mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Payment Transactions</h2>
          <div className="overflow-x-auto">
            <table className="min-w-full bg-white">
              <thead>
                <tr>
                  <th className="py-2 px-4 bg-gray-200 text-left">Publisher ID</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">Trip ID</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">From Location</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">To Location</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">Fare Per Seat</th>
                  <th className="py-2 px-4 bg-gray-200 text-left">Action</th>
                </tr>
              </thead>
              <tbody>
                {rides.map((ride) => (
                  <tr key={ride.publisherRideId}>
                    <td className="border px-4 py-2 text-center">{ride.publisherId}</td>
                    <td className="border px-4 py-2 text-center">{ride.publisherRideId}</td>
                    <td className="border px-4 py-2 text-center">{ride.fromLocation}</td>
                    <td className="border px-4 py-2 text-center">{ride.toLocation}</td>
                    <td className="border px-4 py-2 text-center">Rs.{ride.farePerSeat}</td>
                    <td className="border px-4 py-2 text-center">
                      <button
                        onClick={() => handleViewDetails(ride)}
                        className="text-blue-500 hover:text-blue-700"
                      >
                        View
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </section>

        {showModal && selectedRide && (
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
                  onClick={closeModal}
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
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="2"
                      d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"
                    />
                  </svg>
                  <span className="sr-only">Close modal</span>
                </button>
                <div className="p-4 md:p-5 text-center">
                  <h3 className="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">
                    Ride Details
                  </h3>
                  <p><strong>Publisher ID:</strong> {selectedRide.publisherId}</p>
                  <p><strong>Trip ID:</strong> {selectedRide.publisherRideId}</p>
                  <p><strong>From Location:</strong> {selectedRide.fromLocation}</p>
                  <p><strong>To Location:</strong> {selectedRide.toLocation}</p>
                  <p><strong>Distance:</strong> {selectedRide.distance} km</p>
                  <p><strong>Journey Hours:</strong> {selectedRide.journeyHours}</p>
                  <p><strong>Available Seats:</strong> {selectedRide.availableSeats}</p>
                  <p><strong>Reserved Seats:</strong> {reservedSeats}</p>
                  <p><strong>Date of Journey:</strong> {selectedRide.dateOfJourney}</p>
                  <p><strong>Time of Journey:</strong> {selectedRide.timeOfJourney}</p>
                  <p><strong>Fare Per Seat:</strong> Rs.{selectedRide.farePerSeat}</p>
                  <p><strong>Payment Status:</strong> {selectedRide.paymentStatus}</p>
                  <p><strong>Total Earnings:</strong> Rs.{reservedSeats * selectedRide.farePerSeat}</p>
                  <button
                    type="button"
                    className="mt-4 text-white bg-red-600 hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 font-medium rounded-lg text-sm inline-flex items-center px-5 py-2.5 text-center"
                    onClick={closeModal}
                  >
                    Close
                  </button>
                </div>
              </div>
            </div>
          </div>
        )}
      </main>
    </Layout>
  );
};

export default Payment;
