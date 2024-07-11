import React, { useEffect, useState } from "react";
import Layout from "../../Components/Layouts/Layout";
import axios from "axios";
import { toast } from 'react-hot-toast';
import { useAuth } from "../../context/auth";

const Dashboard = () => {
  const [auth,setAuth] = useAuth();
  const [transaction, setTransaction] = useState([]);
  const [totalAmount, setTotalAmount] = useState(0);

  const fetchTransaction = async () => {
    try {
      const response = await axios.get(`https://api.sharemytrip.xyz/user/passengers/${auth.id}/transactions`);
      if (response.status === 200) {
        setTransaction(response.data);
        const total = response.data.reduce((sum, txn) => sum + txn.totalFare, 0);
        setTotalAmount(total);
        toast.success("All Transactions Fetched Successfully");
      } else {
        toast(response.data.message);
      }
    } catch (error) {
      console.log(error);
      toast.error("Something went wrong");
    }
  };

  useEffect(() => {
    fetchTransaction();
  }, []);

  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">Passenger Dashboard</h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Dashboard Overview</h2>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div className="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 className="text-xl text-gray-700 mb-2">Total Rides Completed</h3>
              <p className="text-2xl text-red-600">{transaction.length}</p>
            </div>
            <div className="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 className="text-xl text-gray-700 mb-2">Total Payments</h3>
              <p className="text-2xl text-red-600">Rs. {totalAmount}</p>
            </div>
            {/* <div className="bg-gray-100 p-4 rounded-lg shadow-md">
              <h3 className="text-xl text-gray-700 mb-2">Average Rating</h3>
              <p className="text-2xl text-red-600">4.7</p>
            </div> */}
          </div>
        </section>
      </main>
    </Layout>
  );
};

export default Dashboard;
