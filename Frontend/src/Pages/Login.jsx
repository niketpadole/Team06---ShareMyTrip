import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";
import { NavLink } from "react-router-dom";
import { useAuth } from "../Context/auth";

const Login = () => {
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
      const response = await axios.post("http://localhost:8089/user/admins/login", {
        email,
        password,
      });
      if (response.status === 200) {
        toast.success("Admin Login Successful", {
          duration: 3000,
        });
        const {adminId, adminFullName, email, token} = response.data;
        setAuth({
          adminId,
           adminFullName,
            email,
           token
        });
        localStorage.setItem(
          "auth",
          JSON.stringify({ adminId, adminFullName, email, token })
        );
        navigate("/admin/dashboard");
      } else {
        toast.error("Login Failed");
      }
    } catch (error) {
      toast.error("An error occurred");
      console.log(error);
    }
  };

  return (
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
          <h1 className="mb-5 text-[#ff6f61] text-2xl md:text-3xl">Admin Login</h1>
          <form onSubmit={logIn}>
            <div className="mb-5 text-left">
              <label htmlFor="email" className="block mb-2 text-[#333]">
                Email
              </label>
              {errorMessageEmail && (
                <p className="text-red-500 text-xs mb-2">{errorMessageEmail}</p>
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
                <p className="text-red-500 text-xs mb-2">{errorMessagePassword}</p>
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
            >
              Login
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
