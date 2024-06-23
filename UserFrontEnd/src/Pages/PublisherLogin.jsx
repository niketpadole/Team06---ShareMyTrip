import React, { useState } from "react";
import Layout from "../Components/Layouts/Layout";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";
import { NavLink } from "react-router-dom";
import { useAuth } from "../context/auth";

const PublisherLogin = () => {
  const navigate = useNavigate();

  const [auth, setAuth] = useAuth();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessageEmail, setErrorMessageEmail] = useState("");
  const [errorMessagePassword, setErrorMessagePassword] = useState("");
  const logIn = async (e) => {
    try {
      e.preventDefault();
      if (!email) {
        setErrorMessageEmail("Email is Mandatory");
        return;
      }
      if (!password) {
        setErrorMessagePassword("Password Fields Is Mandatory");
        return;
      }
      const response = await axios.post(
        "http://localhost:8089/user/publishers/login",
        {
          email,
          password,
        }
      );
      if (response.status == 200) {
        toast.success("Publisher Login SucessFull", {
          duration: 6000,
        });
        console.log(response.data);
        const { publisherId, firstName,lastName, email, mobile, dateOfBirth, userType, token } = response.data;
        setAuth({
          id: publisherId,
          firstName,
          lastName,
          email,
          mobile,
          dateOfBirth,
          userType,
          token,
        });
        localStorage.setItem(
          "auth",
          JSON.stringify({ id: publisherId,firstName,lastName,email, mobile, dateOfBirth, userType, token})
        );
        console.log(response.data);
        navigate("/");
      } else  {
        console.log(response.data);
        toast.error(response.data.message);
      }
    } catch (error) {
      toast.error(error);
      console.log(error);
    }
  };
  return (
    <>
      <Layout>
        <div className="flex justify-center items-center h-screen bg-[#fff4f1] p-4">
          <div className="flex flex-col md:flex-row bg-white rounded-lg shadow-lg overflow-hidden max-w-[900px] w-full">
            <div className="md:flex-1 bg-[#ff6f61] hidden md:block">
              <img
                src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/login.jpg"
                alt="Login"
                className="w-full h-full object-cover"
              />
            </div>
            <div className="flex-1 p-10 text-center">
              <h1 className="mb-5 text-[#ff6f61] text-2xl md:text-3xl">
                Publisher Login
              </h1>
              <form action="/login" method="post">
                <div className="mb-5 text-left">
                  <label htmlFor="email" className="block mb-2 text-[#333]">
                    Email
                  </label>
                  {errorMessageEmail ? (
                    <p className="text-red-500 text-xs mb-2">
                      {errorMessageEmail}
                    </p>
                  ) : (
                    <p></p>
                  )}
                  <input
                    type="email"
                    id="email"
                    name="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                    className="w-full p-2 border border-[#ddd] rounded-md"
                  />
                </div>
                <div className="mb-5 text-left">
                  <label htmlFor="password" className="block mb-2 text-[#333]">
                    Password
                  </label>
                  {errorMessagePassword && (
                    <p className="text-red-500 text-xs mb-2">
                      {errorMessagePassword}
                    </p>
                  )}
                  <input
                    type="password"
                    id="password"
                    name="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                    className="w-full p-2 border border-[#ddd] rounded-md"
                  />
                </div>
                <button
                  type="submit"
                  className="bg-[#ff6f61] text-white p-2.5 rounded-md w-full text-lg hover:bg-[#e65a50]"
                  onClick={logIn}
                >
                  Login
                </button>
              </form>
              <p className="mt-5">
                <NavLink
                  to="/reset-password/publisher"
                  className="text-[#ff6f61]"
                >
                  Forgot Password
                </NavLink>
              </p>
              <p className="mt-5">
                Don't have an account?{" "}
                <NavLink to="/register/publisher" className="text-[#ff6f61]">
                  Register here
                </NavLink>
              </p>
            </div>
          </div>
        </div>
      </Layout>
    </>
  );
};

export default PublisherLogin;
