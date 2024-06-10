import React, { useState } from "react";
import Layout from "../Components/Layouts/Layout";

const PublisherRegister = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [mobile, setMobile] = useState();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [drivingLicence, setDrivingLicence] = useState(null);
  const [addhar, setAddhar] = useState(null);
  const [miniBio, setMiniBio] = useState("");
  const [modelName, setModelName] = useState("");
  const [veichleNumber, setVeichleNumber] = useState();
  const [errorMessageFirstName, setErrorMessageFirstName] = useState("");
  const [errorMessageLastName, setErrorMessageLastName] = useState("");
  const [errorMessageMobile, setErrorMessageMobile] = useState("");
  const [errorMessageEmail, setErrorMessageEmail] = useState("");
  const [errorMessagePassword, setErrorMessagePassword] = useState("");
  const [errorMessageDOB, setErrorMessageDOB] = useState("");
  const [errorMessageDrivingLicence, setErrorMessageDrivingLicence] = useState("");
  const [errorMessageAddhar, setErrorMessageAddhar] = useState("");
  const [errorMessageModelName, setErrorMessageModeName] = useState("");
  const [errorMessageVeichleNumber, setErrorMessageVeichleNumber] =
    useState("");

  const register = (e) => {
    e.preventDefault();
    if (!firstName) {
      setErrorMessageFirstName("First Name Is Required");
    }
    if (!lastName) {
      setErrorMessageLastName("Last Name Is Required");
    }
    if (!mobile) {
      setErrorMessageMobile("Mobile Number is Required");
    }
    if (!email) {
      setErrorMessageEmail("Email is Mandatory");
      return;
    }
    if (!password) {
      setErrorMessagePassword("Password Fields Is Mandatory");
      return;
    }
    if (!dateOfBirth) {
      setErrorMessageDOB("DOB is Required");
      const dob = new Date(dateOfBirth);
      const today = new Date();
      let age = today.getFullYear() - dob.getFullYear();
      let monthDiff = today.getMonth() - dob.getMonth();
      if (
        monthDiff < 0 ||
        (monthDiff === 0 && today.getDate() < dob.getDate())
      ) {
        age--;
      }

      if (age < 18) {
        setErrorMessageDOB("You must be at least 18 years old to register.");
      }
    }
    if(!drivingLicence)
      {
        setErrorMessageDrivingLicence("Document is Required");
      }
      if(!addhar)
        {
          setErrorMessageAddhar("Document is Required");
        }

    const registerUser = {
      firstName: firstName,
      lastName: lastName,
      mobile: mobile,
      email: email,
      password: password,
      dateOfBirth: dateOfBirth,
      drivingLicence: drivingLicence,
      addhar: addhar,
      miniBio: miniBio,
    };
    console.log(registerUser);
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
              Register
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
                />
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
              </div>
              <div className="mb-5">
                <label htmlFor="adhar" className="block mb-2 text-[#333]">
                  Aadhaar Card
                </label>
                <input
                  type="file"
                  id="aadhaar"
                  name="aadhaar"
                  accept="image/*"
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                  onChange={(e) => setAddhar(e.target.files[0])}
                />
              </div>
              <div className="mb-5">
                <label htmlFor="miniBio" className="block mb-2 text-[#333]">
                  Mini Bio
                </label>
                <textarea
                  id="miniBio"
                  name="miniBio"
                  placeholder="I don't like smoking"
                  value={miniBio}
                  onChange={(e) => setMiniBio(e.target.value)}
                  className="w-full h-20 p-2 border border-[#ddd] rounded-md resize-none"
                />
              </div>
              <div className="mb-5">
                <label htmlFor="modelName" className="block mb-2 text-[#333]">
                  Veichle Model Name
                </label>
                <input
                  type="text"
                  id="modelname"
                  name="modelname"
                  placeholder="Hundai Creta"
                  value={modelName}
                  onChange={(e) => setModelName(e.target.value)}
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                />
              </div>
              <div className="mb-5">
                <label
                  htmlFor="veichleNumber"
                  className="block mb-2 text-[#333]"
                >
                  Veichle Number
                </label>
                <input
                  type="text"
                  id="veichleNumber"
                  name="veichleNumber"
                  placeholder="AP04A"
                  value={veichleNumber}
                  onChange={(e) => setVeichleNumber(e.target.value)}
                  required
                  className="w-full p-2 border border-[#ddd] rounded-md"
                />
              </div>
              <button
                type="submit"
                className="bg-[#ff6f61] text-white p-2.5 rounded-md w-full text-lg hover:bg-[#e65a50]"
                onClick={register}
              >
                Register
              </button>
            </form>
            <p className="mt-5">
              Already Registered?{" "}
              <a href="login.html" className="text-[#ff6f61]">
                Log In here
              </a>
            </p>
          </div>
        </div>
      </div>
    </Layout>
  );
};

export default PublisherRegister;
