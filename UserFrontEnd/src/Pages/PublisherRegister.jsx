import React, { useState } from "react";
import Layout from "../Components/Layouts/Layout";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";
import { FaEye, FaEyeSlash } from "react-icons/fa";

const PublisherRegister = () => {
  const navigate = useNavigate();

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [mobile, setMobile] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [drivingLicence, setDrivingLicence] = useState("");
  const [aadhar, setAadhar] = useState("");
  const [miniBio, setMiniBio] = useState("");
  const [vehicleModelName, setModelName] = useState("");
  const [vehicleNo, setVeichleNumber] = useState("");
  const [errorMessageFirstName, setErrorMessageFirstName] = useState("");
  const [errorMessageLastName, setErrorMessageLastName] = useState("");
  const [errorMessageMobile, setErrorMessageMobile] = useState("");
  const [errorMessageEmail, setErrorMessageEmail] = useState("");
  const [errorMessagePassword, setErrorMessagePassword] = useState("");
  const [errorMessageDOB, setErrorMessageDOB] = useState("");
  const [errorMessageDrivingLicence, setErrorMessageDrivingLicence] = useState("");
  const [errorMessageAadhar, setErrorMessageAadhar] = useState("");
  const [errorMessageModelName, setErrorMessageModelName] = useState("");
  const [errorMessageVeichleNumber, setErrorMessageVehicleNumber] = useState("");

  const validate = () => {
    let isValid = true;

    if (!firstName) {
      setErrorMessageFirstName("First Name is required");
      isValid = false;
    } else if (!/^[a-zA-Z]+$/.test(firstName)) {
      setErrorMessageFirstName('First Name must be a string');
      isValid = false;
    } else {
      setErrorMessageFirstName("");
    }

    if (!lastName) {
      setErrorMessageLastName("Last Name is required");
      isValid = false;
    } else if (!/^[a-zA-Z]+$/.test(lastName)) {
      setErrorMessageLastName('Last Name must be a string');
      isValid = false;
    } else {
      setErrorMessageLastName("");
    }

    if (!mobile) {
      setErrorMessageMobile("Mobile Number is Required");
      isValid = false;
    } else if (!/^[789]\d{9}$/.test(mobile)) {
      setErrorMessageMobile("Mobile Number must be 10 digits and start with 7, 8, or 9");
      isValid = false;
    } else {
      setErrorMessageMobile("");
    }

    if (!email) {
      setErrorMessageEmail("Email is Mandatory");
      isValid = false;
    } else if (!/\S+@\S+\.\S+/.test(email)) {
      setErrorMessageEmail("Email address is invalid");
      isValid = false;
    } else {
      setErrorMessageEmail("");
    }

    if (!password) {
      setErrorMessagePassword("Password is Mandatory");
      isValid = false;
    } else if (password.length < 6) {
      setErrorMessagePassword("Password must be at least 6 characters");
      isValid = false;
    } else {
      setErrorMessagePassword("");
    }

    if (!dateOfBirth) {
      setErrorMessageDOB("DOB is Required");
      isValid = false;
    } else {
      const dob = new Date(dateOfBirth);
      const today = new Date();
      const minDOB = new Date("1947-01-01");
      const ageDiff = today.getFullYear() - dob.getFullYear();
      const ageMonth = today.getMonth() - dob.getMonth();
      const ageDay = today.getDate() - dob.getDate();

      if (dob > today) {
        setErrorMessageDOB("Date of Birth cannot be a future date.");
        isValid = false;
      } else if (dob < minDOB) {
        setErrorMessageDOB("Date of Birth cannot be before 1947.");
        isValid = false;
      } else if (ageDiff < 18 || (ageDiff === 18 && (ageMonth < 0 || (ageMonth === 0 && ageDay < 0)))) {
        setErrorMessageDOB("You must be at least 18 years old.");
        isValid = false;
      } else {
        setErrorMessageDOB("");
      }
    }

    if (!drivingLicence) {
      setErrorMessageDrivingLicence("Driving Licence is Required");
      isValid = false;
    } else if (drivingLicence.length !== 15) {
      setErrorMessageDrivingLicence("Driving Licence must be 15 characters");
      isValid = false;
    } else {
      setErrorMessageDrivingLicence("");
    }

    if (!aadhar) {
      setErrorMessageAadhar("Aadhar Card number is required");
      isValid = false;
    } else if (!/^\d{12}$/.test(aadhar)) {
      setErrorMessageAadhar("Aadhar Card number must be 12 digits");
      isValid = false;
    } else {
      setErrorMessageAadhar("");
    }

    if (!vehicleModelName) {
      setErrorMessageModelName("Model Name is required");
      isValid = false;
    } else {
      setErrorMessageModelName("");
    }

    if (!vehicleNo) {
      setErrorMessageVehicleNumber("Vehicle Number is required");
      isValid = false;
    } else if (!/^[A-Z]{2}\d{2}[A-Z]{2}\d{4}$/.test(vehicleNo)) {
      setErrorMessageVehicleNumber("Vehicle Number must be in the format MH56BY2345");
      isValid = false;
    } else {
      setErrorMessageVehicleNumber("");
    }

    return isValid;
  };

  const register = async (e) => {
    try {
      e.preventDefault();
      const validation = validate();
      if (validation) {
        const response = await axios.post("https://api.sharemytrip.xyz/user/publishers/register", {
          firstName,
          lastName,
          mobile,
          email,
          password,
          dateOfBirth,
          "drivingLicense": drivingLicence,
          "aadharCard": aadhar,
          miniBio,
          vehicleModelName,
          vehicleNo
        });
        if (response.status === 200) {
          toast.success("Publisher Registration Successful", {
            duration: 5000
          });
          navigate("/log-in/publisher");
        } else {
          toast.error("Registration Failed", {
            duration: 3000
          });
        }
      } else {
        toast.error("Validation Failed");
      }
    } catch (error) {
      console.log(error);
      toast.error("Internal Error", {
        duration: 3000
      });
    }
  };

  const maxDate = new Date().toISOString().split("T")[0];

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
              Publisher Register
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
                  max={maxDate}
                  min="1947-01-01"
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                />
                {errorMessageDOB && (
                  <p className="text-red-500 text-sm">{errorMessageDOB}</p>
                )}
              </div>
              <div className="mb-5">
                <label
                  htmlFor="drivingLicence"
                  className="block mb-2 text-[#333]"
                >
                  Driving License
                </label>
                <input
                  type="text"
                  id="drivingLicence"
                  name="drivingLicence"
                  placeholder="Enter your 15-digit Driving License number"
                  value={drivingLicence}
                  onChange={(e) => setDrivingLicence(e.target.value)}
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                />
                {errorMessageDrivingLicence && (
                  <p className="text-red-500 text-sm">
                    {errorMessageDrivingLicence}
                  </p>
                )}
              </div>
              <div className="mb-5">
                <label htmlFor="aadhaar" className="block mb-2 text-[#333]">
                  Aadhar Card
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
                  placeholder="Write a short bio"
                  value={miniBio}
                  onChange={(e) => setMiniBio(e.target.value)}
                  className="w-full p-2 border border-[#ddd] rounded-md"
                ></textarea>
              </div>
              <div className="mb-5">
                <label htmlFor="modelName" className="block mb-2 text-[#333]">
                  Model Name
                </label>
                <input
                  type="text"
                  id="modelName"
                  name="modelName"
                  placeholder="Enter your vehicle model name Ex:Suzuki Dzire"
                  value={vehicleModelName}
                  required
                  onChange={(e) => setModelName(e.target.value)}
                  className="w-full p-2 border border-[#ddd] rounded-md"
                />
                {errorMessageModelName && (
                  <p className="text-red-500 text-sm">
                    {errorMessageModelName}
                  </p>
                )}
              </div>
              <div className="mb-5">
                <label
                  htmlFor="veichleNumber"
                  className="block mb-2 text-[#333]"
                >
                  Vehicle Number
                </label>
                <input
                  type="text"
                  id="veichleNumber"
                  name="veichleNumber"
                  placeholder="Enter your vehicle number Ex:AP04GT5678"
                  value={vehicleNo}
                  required
                  onChange={(e) => setVeichleNumber(e.target.value)}
                  className="w-full p-2 border border-[#ddd] rounded-md"
                />
                {errorMessageVeichleNumber && (
                  <p className="text-red-500 text-sm">
                    {errorMessageVeichleNumber}
                  </p>
                )}
              </div>
              <button
                onClick={register}
                className="bg-[#ff6f61] text-white py-2 px-4 rounded-md hover:bg-[#ff3b2e]"
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

export default PublisherRegister;
