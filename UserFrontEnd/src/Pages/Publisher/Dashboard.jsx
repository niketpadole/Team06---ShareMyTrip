import React, { useEffect, useState } from "react";
import Layout from "../../Components/Layouts/Layout";
import { useAuth } from "../../context/auth";
import { NavLink } from "react-router-dom";
import axios from "axios";


const Dashboard = () => {
  const [auth, setAuth] = useAuth([]);
  const [rides,setRides] = useState([]);
  useEffect(() => {
    // Fetch ride details from the API
    axios
      .get(`https://api.sharemytrip.xyz/user/publishers/${auth.id}/rides`)
      .then((response) => {
        setRides(response.data);
      })
      .catch((error) => console.error("Error fetching rides:", error));
  }, [auth.id]);
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
              <NavLink
                to="/publisher/myRides"
                className="text-xl text-gray-700 mb-2"
              >
                Total Rides Published
              </NavLink>
              <p className="text-2xl text-red-600">{rides.length}</p>
            </div>
            <div className="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 className="text-xl text-gray-700 mb-2">Total Earnings</h3>
              <p className="text-2xl text-red-600">{auth.totalEarnings}</p>
            </div>
            {/* <div className="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 className="text-xl text-gray-700 mb-2">Total Passengers</h3>
              <p className="text-2xl text-red-600">25</p>
            </div>
            <div className="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 className="text-xl text-gray-700 mb-2">Average Rating</h3>
              <p className="text-2xl text-red-600">4.8</p>
            </div> */}
          </div>
        </section>
      </main>
    </Layout>
  );
};

export default Dashboard;
