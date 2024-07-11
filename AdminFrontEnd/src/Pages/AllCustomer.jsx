import React, { useState } from "react";
import Layout from "../Components/Layouts/Layout";
import axios from "axios";

const AllCustomer = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [customers, setCustomers] = useState([]);

  const handleSearch = async () => {
    try {
      const response = await axios.get(`https://api.sharemytrip.xyz/user/admins/email/${searchTerm}`);
      if (response.data) {
        console.log(response.data);
        setCustomers(response.data);
      } else {
        setCustomers([]);
      }
    } catch (error) {
      console.error("Error fetching customer data:", error);
      setCustomers([]);
    }
  };

  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">
          Customer Details
        </h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">All Customers</h2>
          <div className="flex mb-6">
            <input
              type="text"
              className="border border-gray-300 p-2 flex-grow rounded-l-lg"
              placeholder="Search customers..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
            <button
              onClick={handleSearch}
              className="bg-blue-500 text-white px-4 py-2 rounded-r-lg"
            >
              Search
            </button>
          </div>
          <table className="min-w-full bg-white">
            <thead>
              <tr>
                <th className="py-2 px-4 bg-gray-200">ID</th>
                <th className="py-2 px-4 bg-gray-200">Full Name</th>
                <th className="py-2 px-4 bg-gray-200">Email</th>
                <th className="py-2 px-4 bg-gray-200">Mobile</th>
{/*                 <th className="py-2 px-4 bg-gray-200">Actions</th> */}
              </tr>
            </thead>
            <tbody>
              {customers.length > 0 ? (
                customers.map((customer, index) => (
                  customer && (
                    <tr key={index}>
                      <td className="border px-4 py-2">{customer.passengerId || customer.publisherId}</td>
                      <td className="border px-4 py-2">{`${customer.firstName} ${customer.lastName}`}</td>
                      <td className="border px-4 py-2">{customer.email}</td>
                      <td className="border px-4 py-2">{customer.mobile}</td>
{/*                       <td className="border px-4 py-2">
                        <a href="#" className="text-red-500 hover:text-red-700 ml-2">
                          Block
                        </a>
                      </td> */}
                    </tr>
                  )
                ))
              ) : (
                <tr>
                  <td colSpan="5" className="text-center p-4">
                    No customers found.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </section>
      </main>
    </Layout>
  );
};

export default AllCustomer;
