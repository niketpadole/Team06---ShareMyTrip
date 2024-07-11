import React, { useEffect, useState } from "react";
import Layout from "../../Components/Layouts/Layout";
import axios from "axios";
import toast from "react-hot-toast";
import { useAuth } from "../../context/auth";

const ViewAllTransaction = () => {
  const [auth,setAuth] = useAuth();
  const [transaction, setTransaction] = useState([]);
  const fetchTransaction = async() => {
    try {
      const response = await axios.get(`https://api.sharemytrip.xyz/user/passengers/${auth.id}/transactions`);
      if(response.status == 200)
        {
          setTransaction(response.data);
          toast.success("All Transaction Fetched SucessFully");
        }
        else{
          toast(response.data.message);
        }
    } catch (error) {
      console.log(error);
      toast.error("Something went wrong");
    }
  }

  useEffect(() => {
    fetchTransaction();
  },[])
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
            {transaction.map((transaction, index) => (
                <tr key={index}>
                  <td className="border px-4 py-2">{transaction.orderId}</td>
                  <td className="border px-4 py-2">{transaction.passengerRideId}</td>
                  <td className="border px-4 py-2">{transaction.totalFare}</td>
                  <td className="border px-4 py-2">{new Date(transaction.timestamp).toLocaleString()}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>
      </main>
    </Layout>
  );
};

export default ViewAllTransaction;
