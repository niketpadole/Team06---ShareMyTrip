import React from "react";
import Layout from "../Components/Layouts/Layout";

const Payment = () => {
  return (
    <Layout>
      <main class="py-10 bg-[#fff4f1]">
        <h1 class="text-4xl text-center text-red-600 mb-10">Admin Payments</h1>
        <section class="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
          <h2 class="text-2xl text-red-600 mb-6">Payment Transactions</h2>
          <table class="min-w-full bg-white">
            <thead>
              <tr>
                <th class="py-2 px-4 bg-gray-200">Payment ID</th>
                <th class="py-2 px-4 bg-gray-200">Trip ID</th>
                <th class="py-2 px-4 bg-gray-200">Publisher ID</th>
                <th class="py-2 px-4 bg-gray-200">Passenger ID</th>
                <th class="py-2 px-4 bg-gray-200">Amount</th>
                <th class="py-2 px-4 bg-gray-200">Commission</th>
                <th class="py-2 px-4 bg-gray-200">Timestamp</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="border px-4 py-2">1</td>
                <td class="border px-4 py-2">101</td>
                <td class="border px-4 py-2">201</td>
                <td class="border px-4 py-2">301</td>
                <td class="border px-4 py-2">Rs.50</td>
                <td class="border px-4 py-2">Rs.4.95</td>
                <td class="border px-4 py-2">2024-06-10 10:00:00</td>
              </tr>
            </tbody>
          </table>
        </section>
      </main>
    </Layout>
  );
};

export default Payment;
