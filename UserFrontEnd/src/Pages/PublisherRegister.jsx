import React, { useState } from "react";
import Layout from "../Components/Layouts/Layout";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";

const PublisherRegister = () => {
  const navigate = useNavigate();

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [mobile, setMobile] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [drivingLicence, setDrivingLicence] = useState(null);
  const [addhar, setAddhar] = useState(null);
  const [miniBio, setMiniBio] = useState("");
  const [vehicleModelName, setModelName] = useState("");
  const [vehicleNo, setVeichleNumber] = useState("");
  const [errorMessageFirstName, setErrorMessageFirstName] = useState("");
  const [errorMessageLastName, setErrorMessageLastName] = useState("");
  const [errorMessageMobile, setErrorMessageMobile] = useState("");
  const [errorMessageEmail, setErrorMessageEmail] = useState("");
  const [errorMessagePassword, setErrorMessagePassword] = useState("");
  const [errorMessageDOB, setErrorMessageDOB] = useState("");
  const [errorMessageDrivingLicence, setErrorMessageDrivingLicence] =
    useState("");
  const [errorMessageAddhar, setErrorMessageAddhar] = useState("");
  const [errorMessageModelName, setErrorMessageModelName] = useState("");
  const [errorMessageVeichleNumber, setErrorMessageVeichleNumber] =
    useState("");

  const validate = () => {
    let isValid = true;

    if (!firstName) {
      setErrorMessageFirstName("First Name Is Required");
      isValid = false;
    }
    if (!lastName) {
      setErrorMessageLastName("Last Name Is Required");
      isValid = false;
    }
    if (!mobile) {
      setErrorMessageMobile("Mobile Number is Required");
      isValid = false;
    }else if (!/^[789]\d{9}$/.test(mobile)) {
      setErrorMessageMobile("Mobile Number must be 10 digits Should start with 7|8|9");
      isValid = false;
    }
    if (!email) {
      setErrorMessageEmail("Email is Mandatory");
      isValid = false;
    }
    if (!password) {
      setErrorMessagePassword("Password Fields Is Mandatory");
      isValid = false;
    }
    if (!dateOfBirth) {
      setErrorMessageDOB("DOB is Required");
      isValid = false;
    } else {
      const dob = new Date(dateOfBirth);
      const today = new Date();
      const minDOB = new Date("1947-01-01");

      if (dob > today) {
        setErrorMessageDOB("Date of Birth cannot be a future date.");
        isValid = false;
      } else if (dob < minDOB) {
        setErrorMessageDOB("Date of Birth cannot be before 1947.");
        isValid = false;
      } else {
        setErrorMessageDOB("");
      }
    }
    // if (!drivingLicence) {
    //   setErrorMessageDrivingLicence("Document is Required");
    //   isValid = false;
    // }
    // if (!addhar) {
    //   setErrorMessageAddhar("Document is Required");
    //   isValid = false;
    // }

    return isValid;
  };

  const register = async (e) => {
    try {
      e.preventDefault();
      const validation = validate();
      if (validation) {
        const response = await axios.post("http://localhost:8089/user/publishers/register", {
          firstName,
          lastName,
          mobile,
          email,
          password,
          dateOfBirth,
          drivingLicence,
          addhar,
          miniBio,
          vehicleModelName,
          vehicleNo
        });
        if(response.status == 200)
          {
            toast.success("Publisher Registration SucessFull",{
              duration:5000
            });
            navigate("/log-in/publisher")
          }
          else{
            toast.error("Registration Failed",{
              duration:3000
            })
          }
      }
      else{
        toast.error("Validation Failed");
      }
    } catch (error) {
      console.log(error);
      toast.error("Internal Error",{
        duration:3000
      })
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
            <form action="/login" method="post" className="text-left">
              <div className="mb-5">
                <label htmlFor="firstName" className="block mb-2 text-[#333]">
                  First Name
                </label>
                <input
                  type="text"
                  id="firstname"
                  name="firstname"
                  placeholder="Jhon"
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
                  id="lastname"
                  name="lastname"
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
              <div className="mb-5">
                <label htmlFor="password" className="block mb-2 text-[#333]">
                  Password
                </label>
                <input
                  type="password"
                  id="password"
                  name="password"
                  placeholder="Enter your password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                />
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
                  type="file"
                  id="drivingLicence"
                  name="drivingLicence"
                  accept="image/*"
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                  onChange={(e) => setDrivingLicence(e.target.files[0])}
                />
                {errorMessageDrivingLicence && (
                  <p className="text-red-500 text-sm">
                    {errorMessageDrivingLicence}
                  </p>
                )}
              </div>
              <div className="mb-5">
                <label htmlFor="addhar" className="block mb-2 text-[#333]">
                  Aadhar
                </label>
                <input
                  type="file"
                  id="addhar"
                  name="addhar"
                  accept="image/*"
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                  onChange={(e) => setAddhar(e.target.files[0])}
                />
                {errorMessageAddhar && (
                  <p className="text-red-500 text-sm">{errorMessageAddhar}</p>
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
                  placeholder="Enter your vehicle model name"
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
                  placeholder="Enter your vehicle number"
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
