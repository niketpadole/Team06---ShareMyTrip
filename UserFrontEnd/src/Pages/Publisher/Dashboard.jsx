import React from "react";
import Layout from "../../Components/Layouts/Layout";

const Dashboard = () => {
  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">
          Publisher Dashboard
        </h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Dashboard Overview</h2>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div className="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 className="text-xl text-gray-700 mb-2">
                Total Rides Published
              </h3>
              <p className="text-2xl text-red-600">10</p>
            </div>
            <div className="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 className="text-xl text-gray-700 mb-2">Total Earnings</h3>
              <p className="text-2xl text-red-600">Rs.1500</p>
            </div>
            <div className="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 className="text-xl text-gray-700 mb-2">Total Passengers</h3>
              <p className="text-2xl text-red-600">25</p>
            </div>
            <div className="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 className="text-xl text-gray-700 mb-2">Average Rating</h3>
              <p className="text-2xl text-red-600">4.8</p>
            </div>
          </div>
        </section>
      </main>
    </Layout>
  );
};

export default Dashboard;
