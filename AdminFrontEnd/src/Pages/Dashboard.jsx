import React from "react";
import Layout from "../Components/Layouts/Layout";
import { NavLink } from "react-router-dom";

const Dashboard = () => {
  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">
          Admin Dashboard
        </h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Dashboard Overview</h2>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <NavLink to="/admin/all-customers">
              <div className="bg-gray-100 p-4 rounded-lg shadow-md">
                <h3 className="text-xl text-gray-700 mb-2">Search Customers</h3>
                {/* <p className="text-2xl text-red-600">150</p> */}
              </div>
            </NavLink>
            <NavLink to="/admin/all-publishers">
              <div className="bg-gray-100 p-4 rounded-lg shadow-md">
                <h3 className="text-xl text-gray-700 mb-2">Total Publishers</h3>
                {/* <p className="text-2xl text-red-600">50</p> */}
              </div>
            </NavLink>
            <NavLink to="/admin/all-passangers">
              <div className="bg-gray-100 p-4 rounded-lg shadow-md">
                <h3 className="text-xl text-gray-700 mb-2">Total Passengers</h3>
                {/* <p className="text-2xl text-red-600">100</p> */}
              </div>
            </NavLink>
            <NavLink to="/admin/payment">
              <div className="bg-gray-100 p-4 rounded-lg shadow-md">
                <h3 className="text-xl text-gray-700 mb-2">Total Trips</h3>
                {/* <p className="text-2xl text-red-600">200</p> */}
              </div>
            </NavLink>
          </div>
        </section>
      </main>
    </Layout>
  );
};

export default Dashboard;
