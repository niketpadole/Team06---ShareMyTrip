import React from "react";
import Layout from "../Components/Layouts/Layout";

const RejectTrip = () => {
  return (
    <Layout>
<main className="py-10 bg-[#fff4f1]">
  <h1 className="text-4xl text-center text-red-600 mb-10">Rejected Trips</h1>
  <section className="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
    <h2 className="text-2xl text-red-600 mb-6">All Rejected Trips</h2>
    <table className="min-w-full bg-white">
      <thead>
        <tr>
          <th className="py-2 px-4 bg-gray-200">Trip ID</th>
          <th className="py-2 px-4 bg-gray-200">Publisher</th>
          <th className="py-2 px-4 bg-gray-200">Passenger</th>
          <th className="py-2 px-4 bg-gray-200">From</th>
          <th className="py-2 px-4 bg-gray-200">To</th>
          <th className="py-2 px-4 bg-gray-200">Date</th>
          <th className="py-2 px-4 bg-gray-200">Time</th>
          <th className="py-2 px-4 bg-gray-200">Reason</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td className="border px-4 py-2">1</td>
          <td className="border px-4 py-2">Jane Smith</td>
          <td className="border px-4 py-2">John Doe</td>
          <td className="border px-4 py-2">New York</td>
          <td className="border px-4 py-2">Boston</td>
          <td className="border px-4 py-2">2024-06-10</td>
          <td className="border px-4 py-2">10:00 AM</td>
          <td className="border px-4 py-2">Passenger not verified</td>
        </tr>
      </tbody>
    </table>
  </section>
</main>

    </Layout>
  );
};

export default RejectTrip;