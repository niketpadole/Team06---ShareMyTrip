import React, { useState } from "react";
import Layout from "../Components/Layouts/Layout";
import axios from "axios";
import toast from "react-hot-toast";
import { useNavigate } from "react-router-dom";
import { NavLink } from "react-router-dom";
import { useAuth } from "../context/auth";
import { FaEye, FaEyeSlash } from "react-icons/fa";

const Login = () => {
  const navigate = useNavigate();
  const [auth, setAuth] = useAuth();
  const [userEmail, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false); // State for toggling password visibility
  const [errorMessageEmail, setErrorMessageEmail] = useState("");
  const [errorMessagePassword, setErrorMessagePassword] = useState("");

  const logIn = async (e) => {
    e.preventDefault();
    setErrorMessageEmail("");
    setErrorMessagePassword("");
    if (!userEmail) {
      setErrorMessageEmail("Email is Mandatory");
      return;
    }
    if (!password) {
      setErrorMessagePassword("Password Field Is Mandatory");
      return;
    }
    if(password.length < 6){ 
        setErrorMessagePassword("Password Must Be 8 digit");
        return;
      }

    try {
      const response = await axios.post(`https://api.sharemytrip.xyz/user/passengers/login`, {
        email: userEmail,
        password
      });
      if (response.status === 200) {
        toast.success("Passenger Login Successful", {
          duration: 6000
        });
        const { passengerId, firstName, lastName, email, mobile, dateOfBirth, userType, token} = response.data;
        setAuth({
          id: passengerId,
          firstName,
          lastName,
          email,
          mobile,
          dateOfBirth,
          userType,
          token
        });
        localStorage.setItem("auth", JSON.stringify({ id: passengerId, firstName, lastName, email, mobile, dateOfBirth, userType, token }));
        console.log(response.data);
        console.log(auth);
        navigate("/");
      } else {
        console.log(response.data);
        toast.error(response.data.message);
      }
    } catch (error) {
      toast.error("Invalid Credentials");
      console.log(error);
    }
  };

  return (
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
            <h1 className="mb-5 text-[#ff6f61] text-2xl md:text-3xl">Passenger Login</h1>
            <form onSubmit={logIn}>
              <div className="mb-5 text-left">
                <label htmlFor="email" className="block mb-2 text-[#333]">
                  Email
                </label>
                {errorMessageEmail && (
                  <p className="text-red-500 text-xs mb-2">
                    {errorMessageEmail}
                  </p>
                )}
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={userEmail}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                />
              </div>
              <div className="mb-5 text-left relative">
                <label htmlFor="password" className="block mb-2 text-[#333]">
                  Password
                </label>
                {errorMessagePassword && (
                  <p className="text-red-500 text-xs mb-2">
                    {errorMessagePassword}
                  </p>
                )}
                <div className="relative">
                  <input
                    type={showPassword ? "text" : "password"}
                    id="password"
                    name="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                    className="w-full p-2 border border-[#ddd] rounded-md pr-10"
                  />
                  <span
                    onClick={() => setShowPassword(!showPassword)}
                    className="absolute inset-y-0 right-0 pr-3 flex items-center cursor-pointer text-[#333]"
                  >
                    {showPassword ? <FaEyeSlash /> : <FaEye />}
                  </span>
                </div>
              </div>
              <button
                type="submit"
                className="bg-[#ff6f61] text-white p-2.5 rounded-md w-full text-lg hover:bg-[#e65a50]"
              >
                Login
              </button>
            </form>
            <p className="mt-5">
              <NavLink to="/reset-password/passanger" className="text-[#ff6f61]">
                Forgot Password
              </NavLink>
            </p>
            <p className="mt-5">
              Don't have an account?{" "}
              <NavLink to="/register/passanger" className="text-[#ff6f61]">
                Register here
              </NavLink>
            </p>
          </div>
        </div>
      </div>
    </Layout>
  );
};

export default Login;
