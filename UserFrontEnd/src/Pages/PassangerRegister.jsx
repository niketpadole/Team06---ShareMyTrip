import React, { useState } from "react";
import Layout from "../Components/Layouts/Layout";
import axios from "axios";
import toast from "react-hot-toast";
import { useNavigate } from "react-router-dom";
import { FaEye, FaEyeSlash } from "react-icons/fa";

const PassangerRegister = () => {
  const navigate = useNavigate();

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [mobile, setMobile] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false); // State for toggling password visibility
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [aadhar, setAadhar] = useState("");
  const [miniBio, setMiniBio] = useState("");
  const [errorMessageFirstName, setErrorMessageFirstName] = useState("");
  const [errorMessageLastName, setErrorMessageLastName] = useState("");
  const [errorMessageMobile, setErrorMessageMobile] = useState("");
  const [errorMessageEmail, setErrorMessageEmail] = useState("");
  const [errorMessagePassword, setErrorMessagePassword] = useState("");
  const [errorMessageDOB, setErrorMessageDOB] = useState("");
  const [errorMessageAadhar, setErrorMessageAadhar] = useState("");

  const validate = () => {
    let isValid = true;
    if (!firstName) {
      setErrorMessageFirstName("First Name is required");
      isValid = false;
    }
    else if (!/^[a-zA-Z]+$/.test(firstName)) {
     setErrorMessageFirstName('First Name must be a string');
    }
    if (!lastName) {
      setErrorMessageLastName("Last Name is required");
      isValid = false;
    }
    else if (!/^[a-zA-Z]+$/.test(lastName)) {
      setErrorMessageLastName('Last Name must be a string');
     }
    if (!mobile) {
      setErrorMessageMobile("Mobile number is required");
      isValid = false;
    } else if (!/^[789]\d{9}$/.test(mobile)) {
      setErrorMessageMobile("Mobile number should be 10 digits and start with 7, 8, or 9");
      isValid = false;
    }
    if (!email) {
      setErrorMessageEmail("Email is required");
      isValid = false;
    } else if (!/\S+@\S+\.\S+/.test(email)) {
      setErrorMessageEmail("Email address is invalid");
      isValid = false;
    }
    if (!password) {
      setErrorMessagePassword("Password is required");
      isValid = false;
    } else if (password.length < 6) {
      setErrorMessagePassword("Password must be at least 6 characters");
      isValid = false;
    }
    if (!dateOfBirth) {
      setErrorMessageDOB("Date of Birth is required");
      isValid = false;
    } else {
      const today = new Date();
      const dob = new Date(dateOfBirth);
      if (dob > today) {
        setErrorMessageDOB("Date of Birth cannot be in the future");
        isValid = false;
      } else if (dob < new Date("1947-01-01")) {
        setErrorMessageDOB("Date of Birth cannot be before 1947");
        isValid = false;
      }
    }
    if (!aadhar) {
      setErrorMessageAadhar("Aadhar Card number is required");
      isValid = false;
    } else if (!/^\d{12}$/.test(aadhar)) {
      setErrorMessageAadhar("Aadhar Card number must be 12 digits");
      isValid = false;
    }

    return isValid;
  };

  const register = async (e) => {
    try {
      e.preventDefault();
      const validation = validate();
      if (validation) {
        const response = await axios.post(
          "https://api.sharemytrip.xyz/user/passengers/register",
          {
            firstName,
            lastName,
            mobile,
            email,
            password,
            dateOfBirth,
            "aadharCard": aadhar,
            miniBio,
          }
        );
        if (response.status === 200) {
          toast.success("Passenger Registration Successful", {
            duration: 3000,
          });
          navigate("/log-in/passanger");
        } else {
          toast.error("Registration Failed");
        }
      } else {
        console.log("Validation Failed");
      }
    } catch (error) {
      console.log(error);
      toast.error("Server Error");
    }
  };

  return (
    <Layout>
      <div className="flex justify-center items-center min-h-screen bg-[#fff4f1] px-4">
        <div className="flex flex-col md:flex-row bg-white rounded-lg shadow-lg overflow-hidden w-full">
          <div className="md:flex-1 bg-[#ff6f61] hidden md:block">
            <img
              src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/4968842.jpg"
              alt="Login"
              className="w-full h-full object-cover"
            />
          </div>
          <div className="flex-1 p-6 md:p-10 text-center">
            <h1 className="mb-5 text-[#ff6f61] text-2xl md:text-3xl">
              Passenger Register
            </h1>
            <form onSubmit={register} className="text-left">
              <div className="mb-5">
                <label htmlFor="firstName" className="block mb-2 text-[#333]">
                  First Name
                </label>
                <input
                  type="text"
                  id="firstName"
                  name="firstName"
                  placeholder="John"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                />
                {errorMessageFirstName && (
                  <p className="text-red-500 text-sm">
                    {errorMessageFirstName}
                  </p>
                )}
              </div>
              <div className="mb-5">
                <label htmlFor="lastName" className="block mb-2 text-[#333]">
                  Last Name
                </label>
                <input
                  type="text"
                  id="lastName"
                  name="lastName"
                  placeholder="Rogers"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                />
                {errorMessageLastName && (
                  <p className="text-red-500 text-sm">{errorMessageLastName}</p>
                )}
              </div>
              <div className="mb-5">
                <label htmlFor="mobile" className="block mb-2 text-[#333]">
                  Mobile
                </label>
                <input
                  type="tel"
                  id="mobile"
                  name="mobile"
                  placeholder="9988777655"
                  value={mobile}
                  onChange={(e) => setMobile(e.target.value)}
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                />
                {errorMessageMobile && (
                  <p className="text-red-500 text-sm">{errorMessageMobile}</p>
                )}
              </div>
              <div className="mb-5">
                <label htmlFor="email" className="block mb-2 text-[#333]">
                  Email
                </label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  placeholder="abc@example.com"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                />
                {errorMessageEmail && (
                  <p className="text-red-500 text-sm">{errorMessageEmail}</p>
                )}
              </div>
              <div className="mb-5 text-left relative">
                <label htmlFor="password" className="block mb-2 text-[#333]">
                  Password
                </label>
                <div className="relative">
                  <input
                    type={showPassword ? "text" : "password"}
                    id="password"
                    name="password"
                    placeholder="Enter your password"
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
                {errorMessagePassword && (
                  <p className="text-red-500 text-sm">{errorMessagePassword}</p>
                )}
              </div>
              <div className="mb-5">
                <label htmlFor="dob" className="block mb-2 text-[#333]">
                  Date of Birth
                </label>
                <input
                  type="date"
                  id="dob"
                  name="dob"
                  value={dateOfBirth}
                  onChange={(e) => setDateOfBirth(e.target.value)}
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                  max={new Date().toISOString().split("T")[0]}
                  min="1947-01-01"
                />
                {errorMessageDOB && (
                  <p className="text-red-500 text-sm">{errorMessageDOB}</p>
                )}
              </div>
              <div className="mb-5">
                <label htmlFor="aadhaar" className="block mb-2 text-[#333]">
                  Aadhaar Card
                </label>
                <input
                  type="text"
                  id="aadhaar"
                  name="aadhaar"
                  placeholder="Enter your 12-digit Aadhaar number"
                  value={aadhar}
                  onChange={(e) => setAadhar(e.target.value)}
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                />
                {errorMessageAadhar && (
                  <p className="text-red-500 text-sm">{errorMessageAadhar}</p>
                )}
              </div>
              <div className="mb-5">
                <label htmlFor="miniBio" className="block mb-2 text-[#333]">
                  Mini Bio
                </label>
                <textarea
                  id="miniBio"
                  name="miniBio"
                  rows="3"
                  placeholder="Tell us a little about yourself"
                  value={miniBio}
                  onChange={(e) => setMiniBio(e.target.value)}
                  className="w-full p-2 border border-[#ddd] rounded-md"
                ></textarea>
              </div>
              <button
                type="submit"
                onClick={register}
                className="w-full py-2 px-4 bg-[#ff6f61] text-white font-semibold rounded-md hover:bg-[#e65c50] transition duration-200"
              >
                Register
              </button>
            </form>
          </div>
        </div>
      </div>
    </Layout>
  );
};

export default PassangerRegister;
