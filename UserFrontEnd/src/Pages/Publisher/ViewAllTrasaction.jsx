import React from "react";
import Layout from "../../Components/Layouts/Layout";

const ViewAllTrasaction = () => {
  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">
          All Transactions
        </h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Transaction History</h2>
          <table className="min-w-full bg-white">
            <thead>
              <tr>
                <th className="py-2 px-4 bg-gray-200">Payment ID</th>
                <th className="py-2 px-4 bg-gray-200">Trip ID</th>
                <th className="py-2 px-4 bg-gray-200">Amount</th>
                <th className="py-2 px-4 bg-gray-200">Timestamp</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td className="border px-4 py-2">1</td>
                <td className="border px-4 py-2">101</td>
                <td className="border px-4 py-2">$50</td>
                <td className="border px-4 py-2">2024-06-10 10:00:00</td>
              </tr>
            </tbody>
          </table>
        </section>
      </main>
    </Layout>
  );
};

export default ViewAllTrasaction;
