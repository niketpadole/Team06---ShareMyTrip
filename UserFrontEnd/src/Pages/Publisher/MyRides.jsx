import React, { useState } from "react";
import Layout from "../../Components/Layouts/Layout";

const MyRides = () => {
  const [showPassengers, setShowPassengers] = useState(false);
  const [rideStatus, setRideStatus] = useState("Not Started");

  const handleViewPassengers = () => {
    setShowPassengers(!showPassengers);
  };

  const handleStartRide = () => {
    setRideStatus("Started");
  };

  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">My Rides</h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Published Rides</h2>
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
                <tr>
                  <td className="border px-4 py-2">New York</td>
                  <td className="border px-4 py-2">Boston</td>
                  <td className="border px-4 py-2">2024-06-10</td>
                  <td className="border px-4 py-2">10:00 AM</td>
                  <td className="border px-4 py-2">3</td>
                  <td className="border px-4 py-2">$50</td>
                  <td className="border px-4 py-2">{rideStatus}</td>
                  <td className="border px-4 py-2 flex space-x-2">
                    <button
                      onClick={handleViewPassengers}
                      className="text-blue-500 hover:text-blue-700"
                    >
                      View
                    </button>
                  </td>
                  <td className="border px-4 py-2 flex space-x-2">
                    <button
                      onClick={handleStartRide}
                      className="text-green-500 hover:text-green-700"
                    >
                      Start
                    </button>
                  </td>
                  <td className="border px-4 py-2 flex space-x-2">
                    <button className="text-red-500 hover:text-red-700">
                      Delete
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

          {showPassengers && (
            <section className="bg-white p-8 mt-10 rounded-lg shadow-md">
              <h2 className="text-2xl text-red-600 mb-6">Passengers</h2>
              <div className="overflow-x-auto">
                <table className="min-w-full bg-white">
                  <thead>
                    <tr>
                      <th className="py-2 px-4 bg-gray-200 text-left">
                        Passenger Name
                      </th>
                      <th className="py-2 px-4 bg-gray-200 text-left">
                        Mobile Number
                      </th>
                      <th className="py-2 px-4 bg-gray-200 text-left">
                        No. of Seats Booked
                      </th>
                      <th className="py-2 px-4 bg-gray-200 text-left">
                        Payment Status
                      </th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td className="border px-4 py-2">John Doe</td>
                      <td className="border px-4 py-2">123-456-7890</td>
                      <td className="border px-4 py-2">2</td>
                      <td className="border px-4 py-2">Paid</td>
                    </tr>
                    <tr>
                      <td className="border px-4 py-2">Jane Smith</td>
                      <td className="border px-4 py-2">987-654-3210</td>
                      <td className="border px-4 py-2">1</td>
                      <td className="border px-4 py-2">Pending</td>
                    </tr>
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
