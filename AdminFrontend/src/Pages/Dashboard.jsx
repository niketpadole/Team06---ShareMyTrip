import React from "react";
import Layout from "../Components/Layouts/Layout";

const Dashboard = () => {
  return (
    <Layout>
      <main class="py-10 bg-[#fff4f1]">
        <h1 class="text-4xl text-center text-red-600 mb-10">Admin Dashboard</h1>
        <section class="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
          <h2 class="text-2xl text-red-600 mb-6">Dashboard Overview</h2>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div class="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 class="text-xl text-gray-700 mb-2">Total Customers</h3>
              <p class="text-2xl text-red-600">150</p>
            </div>
            <div class="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 class="text-xl text-gray-700 mb-2">Total Publishers</h3>
              <p class="text-2xl text-red-600">50</p>
            </div>
            <div class="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 class="text-xl text-gray-700 mb-2">Total Passengers</h3>
              <p class="text-2xl text-red-600">100</p>
            </div>
            <div class="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 class="text-xl text-gray-700 mb-2">Total Trips</h3>
              <p class="text-2xl text-red-600">200</p>
            </div>
          </div>
        </section>
      </main>
    </Layout>
  );
};

export default Dashboard;
