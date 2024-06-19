import React, { useEffect, useState } from "react";
import axios from "axios";
import Layout from "../Components/Layouts/Layout";
import toast from "react-hot-toast";

const AllPublisher = () => {
  const [publishers, setPublishers] = useState([]);

  const allPublishers = async () => {
    try {
      const response = await axios.get("http://localhost:8089/user/admins/publishers");
      if (response.status === 200) {
        setPublishers(response.data);
        toast.success("All Publishers Fetched Successfully");
      }
    } catch (error) {
      console.log(error);
      toast.error("Failed to fetch publishers");
    }
  };

  useEffect(() => {
    allPublishers();
  }, []);

  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">Publisher Details</h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">All Publishers</h2>
          <table className="min-w-full bg-white">
            <thead>
              <tr>
                <th className="py-2 px-4 bg-gray-200">ID</th>
                <th className="py-2 px-4 bg-gray-200">Full Name</th>
                <th className="py-2 px-4 bg-gray-200">Email</th>
                <th className="py-2 px-4 bg-gray-200">Mobile</th>
                <th className="py-2 px-4 bg-gray-200">Actions</th>
              </tr>
            </thead>
            <tbody>
              {publishers.map((publisher) => (
                <tr key={publisher.publisherId}>
                  <td className="border px-4 py-2">{publisher.publisherId}</td>
                  <td className="border px-4 py-2">{`${publisher.firstName} ${publisher.lastName}`}</td>
                  <td className="border px-4 py-2">{publisher.email}</td>
                  <td className="border px-4 py-2">{publisher.mobile}</td>
                  <td className="border px-4 py-2">
                    <a href="#" className="text-blue-500 hover:text-blue-700">
                      Edit
                    </a>
                    <a href="#" className="text-red-500 hover:text-red-700 ml-2">
                      Delete
                    </a>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>
      </main>
    </Layout>
  );
};

export default AllPublisher;
