import React from "react";
import Layout from "../Components/Layouts/Layout";

const ForgotPassword = () => {
  return (
      <Layout>
        <main className="flex justify-center items-center h-screen bg-[#fff4f1]">
          <div className="bg-white rounded-lg shadow-md w-full max-w-4xl flex overflow-hidden">
            <div className="hidden md:block w-1/2">
              <img
                src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/login.jpg"
                alt="Forgot Password Image"
                className="h-full w-full object-cover"
              />
            </div>
            <div className="w-full md:w-1/2 p-8">
              <h1 className="text-3xl text-center text-red-600 mb-6">
                Forgot Password
              </h1>
              <form action="/forgot_password" method="post">
                <div className="mb-4">
                  <label htmlFor="email" className="block text-gray-700 mb-2">
                    Email
                  </label>
                  <input
                    type="email"
                    id="email"
                    name="email"
                    className="w-full p-2 border border-gray-300 rounded-md"
                    required
                  />
                </div>
                <button
                  type="submit"
                  className="w-full bg-red-600 text-white p-2 rounded-md hover:bg-red-700"
                >
                  Reset Password
                </button>
              </form>
              <p className="text-center mt-4">
                Remembered your password?{" "}
                <a
                  href="login.html"
                  className="text-red-600 hover:text-red-700"
                >
                  Login here
                </a>
              </p>
            </div>
          </div>
        </main>
        has context menu
      </Layout>
  );
};

export default ForgotPassword;
