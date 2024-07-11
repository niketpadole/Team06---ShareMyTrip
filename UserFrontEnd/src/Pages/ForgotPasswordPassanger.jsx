import React, { useState } from "react";
import Layout from "../Components/Layouts/Layout";
import axios from "axios";
import toast from "react-hot-toast";
import { useNavigate } from "react-router-dom";
import {NavLink} from "react-router-dom"

const ForgotPassword = () => {
  const navigate= useNavigate();
  const [otpSent, setOtpSent] = useState(false);
  const [otpVerified, setOtpVerified] = useState(false);
  const [otp, setOtp] = useState("");
  const [email, setEmail] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const handleSendOtp = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`https://api.sharemytrip.xyz/user/passengers/reset-password/${email}`);
      if (response.status === 200) {
        toast.success("OTP sent successfully");
        setOtpSent(true);
      } else {
        toast.error("User not found");
      }
    } catch (error) {
      console.error("Error sending OTP", error);
      toast.error("Error sending OTP");
    }
  };

  const handleVerifyOtp = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`https://api.sharemytrip.xyz/user/passengers/verify-otp/${email}/${otp}`);
      if (response.data === "OTP verified successfully") {
        toast.success("OTP verified successfully");
        setOtpVerified(true);
      } else {
        toast.error("Invalid OTP");
      }
    } catch (error) {
      console.error("Error verifying OTP", error);
      toast.error("Error verifying OTP");
    }
  };

  const handleResetPassword = async (e) => {
    e.preventDefault();
    if (newPassword !== confirmPassword) {
      setPasswordError("Passwords do not match");
      return;
    }
    try {
      const response = await axios.put('https://api.sharemytrip.xyz/user/passengers/update-password', null, {
        params: {
          email: email,
          newPassword: newPassword
        },
        headers: {
          'Content-Type': 'application/json',
        }
      });
      if (response.status === 200) {
        toast.success("Password updated successfully");
        setShowModal(false);
        navigate("/log-in/passanger");
      } else {
        toast.error("Something went wrong");
      }
    } catch (error) {
      console.error("Error resetting password", error);
      toast.error("Error resetting password");
    }
  };

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
            <h1 className="text-3xl text-center text-red-600 mb-6">Forgot Password</h1>
            {!otpSent && (
              <form onSubmit={handleSendOtp}>
                <div className="mb-4">
                  <label htmlFor="email" className="block text-gray-700 mb-2">
                    Email
                  </label>
                  <input
                    type="email"
                    id="email"
                    name="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className="w-full p-2 border border-gray-300 rounded-md"
                    required
                  />
                </div>
                <button
                  type="submit"
                  className="w-full bg-red-600 text-white p-2 rounded-md hover:bg-red-700"
                >
                  Send OTP
                </button>
              </form>
            )}
            {otpSent && !otpVerified && (
              <form onSubmit={handleVerifyOtp}>
                <div className="mb-4">
                  <label htmlFor="otp" className="block text-gray-700 mb-2">
                    OTP
                  </label>
                  <input
                    type="number"
                    id="otp"
                    name="otp"
                    value={otp}
                    onChange={(e) => setOtp(e.target.value)}
                    className="w-full p-2 border border-gray-300 rounded-md"
                    required
                  />
                </div>
                <button
                  type="submit"
                  className="w-full bg-red-600 text-white p-2 rounded-md hover:bg-red-700"
                >
                  Verify OTP
                </button>
              </form>
            )}
            {otpVerified && (
              <button
                onClick={() => setShowModal(true)}
                className="mt-4 w-full bg-[#ff6f61] text-white p-2 rounded-md "
              >
                Reset Password
              </button>
            )}
            <p className="text-center mt-4">
              Remembered your password?{" "}
              <NavLink to="/log-in/passanger" className="text-red-600 hover:text-red-700">
                Login here
              </NavLink>
            </p>
          </div>
        </div>
      </main>

      {showModal && (
        <div
          id="authentication-modal"
          tabIndex="-1"
          aria-hidden="true"
          className="fixed top-0 left-0 right-0 z-50 flex justify-center items-center w-full h-full bg-black bg-opacity-50"
        >
          <div className="relative p-4 w-full max-w-md max-h-full">
            <div className="relative bg-white rounded-lg shadow dark:bg-gray-700">
              <div className="flex items-center justify-between p-4 md:p-5 border-b rounded-t">
                <h3 className="text-xl font-semibold text-gray-900">
                  Reset Your Password
                </h3>
                <button
                  type="button"
                  className="end-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center"
                  onClick={() => setShowModal(false)}
                >
                  <svg
                    className="w-3 h-3"
                    aria-hidden="true"
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 14 14"
                  >
                    <path
                      stroke="currentColor"
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="2"
                      d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"
                    />
                  </svg>
                  <span className="sr-only">Close modal</span>
                </button>
              </div>
              <div className="p-4 md:p-5">
                <form onSubmit={handleResetPassword} className="space-y-4">
                  <div>
                    <label
                      htmlFor="newPassword"
                      className="block mb-2 text-sm font-medium text-gray-900"
                    >
                      New Password
                    </label>
                    <input
                      type="password"
                      name="newPassword"
                      id="newPassword"
                      value={newPassword}
                      onChange={(e) => setNewPassword(e.target.value)}
                      className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                      required
                    />
                  </div>
                  <div>
                    <label
                      htmlFor="confirmPassword"
                      className="block mb-2 text-sm font-medium text-gray-900"
                    >
                      Confirm Password
                    </label>
                    <input
                      type="password"
                      name="confirmPassword"
                      id="confirmPassword"
                      value={confirmPassword}
                      onChange={(e) => setConfirmPassword(e.target.value)}
                      className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                      required
                    />
                  </div>
                  {passwordError && (
                    <p className="text-red-500 text-sm">{passwordError}</p>
                  )}
                  <button
                    type="submit"
                    className="w-full text-white bg-[#ff6f61]  focus:ring-4 focus:outline-none  font-medium rounded-lg text-sm px-5 py-2.5 text-center "
                  >
                    Reset Password
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      )}
    </Layout>
  );
};

export default ForgotPassword;
