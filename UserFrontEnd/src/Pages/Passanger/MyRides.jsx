import React, { useState, useEffect } from "react";
import axios from "axios";
import Layout from "../../Components/Layouts/Layout";
import { useAuth } from "../../context/auth";
import toast from "react-hot-toast";

const MyRides = () => {
  const [auth, setAuth] = useAuth();
  const [rides, setRides] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selectedRideId, setSelectedRideId] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const onCancel = async () => {
    try {
      const response = await axios.delete(`http://localhost:8089/user/passengers/cancel/${selectedRideId}`);
      if (response.status === 204) {
        toast.success("Ride Canceled Successfully");
        fetchRides();
      } else {
        toast.error("Something Went Wrong");
      }
      setIsModalOpen(false);  // Close the modal after canceling the ride
    } catch (error) {
      console.log(error);
      toast.error("An error occurred");
    }
  };

  const fetchRides = async () => {
    try {
      const response = await axios.get(`http://localhost:8089/user/passengers/${auth.id}/rides`);
      console.log("Response from API:", response.data);
      setRides(response.data);
    } catch (error) {
      console.error("Error fetching rides:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchRides();
  }, [auth.id]);

  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">My Rides</h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Booked Rides</h2>
          <div className="overflow-x-auto">
            {loading ? (
              <p className="text-center">Loading...</p>
            ) : rides.length > 0 ? (
              <table className="min-w-full bg-white">
                <thead>
                  <tr>
                    <th className="py-2 px-4 bg-gray-200">Publisher Name</th>
                    <th className="py-2 px-4 bg-gray-200">Publisher Mobile</th>
                    <th className="py-2 px-4 bg-gray-200">From</th>
                    <th className="py-2 px-4 bg-gray-200">To</th>
                    <th className="py-2 px-4 bg-gray-200">Date</th>
                    <th className="py-2 px-4 bg-gray-200">Time</th>
                    <th className="py-2 px-4 bg-gray-200">Seats</th>
                    <th className="py-2 px-4 bg-gray-200">Fare</th>
                    <th className="py-2 px-4 bg-gray-200">Distance</th>
                    <th className="py-2 px-4 bg-gray-200">Journey Hours</th>
                    <th className="py-2 px-4 bg-gray-200">Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {rides.map((ride, index) => (
                    <tr key={index}>
                      <td className="border px-4 py-2">{ride.publisherName}</td>
                      <td className="border px-4 py-2">{ride.publisherMobile}</td>
                      <td className="border px-4 py-2">{ride.fromLocation}</td>
                      <td className="border px-4 py-2">{ride.toLocation}</td>
                      <td className="border px-4 py-2">{ride.date}</td>
                      <td className="border px-4 py-2">{ride.departureTime}</td>
                      <td className="border px-4 py-2">{ride.passengerCount}</td>
                      <td className="border px-4 py-2">Rs.{ride.fare * ride.passengerCount}</td>
                      <td className="border px-4 py-2">{ride.distance} km</td>
                      <td className="border px-4 py-2">{ride.journeyHours} hrs</td>
                      <td className="border px-4 py-2">
                        <button
                          className="text-red-500 hover:text-red-700 ml-2"
                          onClick={() => {
                            setSelectedRideId(ride.passengerRideId);
                            setIsModalOpen(true);
                          }}
                        >
                          Cancel
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
              <p className="text-center text-red-600">No Rides Booked</p>
            )}
          </div>
        </section>
      </main>

      {/* Modal */}
      {isModalOpen && (
        <div id="popup-modal" className="fixed top-0 right-0 left-0 z-50 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50">
          <div className="relative p-4 bg-white rounded-lg shadow-md max-w-md w-full">
            <button
              type="button"
              className="absolute top-3 right-3 text-gray-400 bg-transparent hover:bg-gray-200 rounded-lg p-1"
              onClick={() => setIsModalOpen(false)}
            >
              <svg className="w-5 h-5" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M6 18L18 6M6 6l12 12" />
              </svg>
            </button>
            <div className="text-center">
              <svg className="mx-auto mb-4 text-gray-400 w-12 h-12" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M6 18L18 6M6 6l12 12" />
              </svg>
              <h3 className="mb-4 text-lg font-semibold text-gray-700">Are you sure you want to cancel this ride?</h3>
              <div className="flex justify-center space-x-4">
                <button
                  type="button"
                  className="px-4 py-2 text-white bg-red-600 rounded-lg hover:bg-red-700"
                  onClick={onCancel}
                >
                  Yes, I'm sure
                </button>
                <button
                  type="button"
                  className="px-4 py-2 text-gray-600 bg-gray-200 rounded-lg hover:bg-gray-300"
                  onClick={() => setIsModalOpen(false)}
                >
                  No, cancel
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </Layout>
  );
};

export default MyRides;
