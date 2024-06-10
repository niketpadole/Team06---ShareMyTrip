import React, { useState } from "react";
import Layout from "../Components/Layouts/Layout";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessageEmail,setErrorMessageEmail] = useState("");
  const [errorMessagePassword,setErrorMessagePassword] = useState("");

  const logIn = (e) => {
    e.preventDefault();
    if(!email)
      {
        setErrorMessageEmail("Email is Mandatory");
        return;
      }
    if(!password)
      {
        setErrorMessagePassword("Password Fields Is Mandatory");
        return;
      }
    console.log(email, password);
  };
  return (
    <Layout>
      <div className="flex justify-center items-center h-screen bg-[#fff4f1] p-4">
        <div className="flex flex-col md:flex-row bg-white rounded-lg shadow-lg overflow-hidden max-w-[900px] w-full">
          <div className="md:flex-1 bg-[#ff6f61] hidden md:block">
            <img
              src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/4968842.jpg"
              alt="Login"
              className="w-full h-full object-cover"
            />
          </div>
          <div className="flex-1 p-10 text-center">
            <h1 className="mb-5 text-[#ff6f61] text-2xl md:text-3xl">Login</h1>
            <form action="/login" method="post">
              <div className="mb-5 text-left">
                <label htmlFor="email" className="block mb-2 text-[#333]">
                  Email
                </label>
                {errorMessageEmail ? (<p className="text-red-500 text-xs mb-2">{errorMessageEmail}</p>):(<p></p>)}
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
                {errorMessagePassword && (<p className="text-red-500 text-xs mb-2">{errorMessagePassword}</p>)}
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
              <a href="register.html" className="text-[#ff6f61]">
                Forgot Password
              </a>
            </p>
            <p className="mt-5">
              Don't have an account?{" "}
              <a href="register.html" className="text-[#ff6f61]">
                Register here
              </a>
            </p>
          </div>
        </div>
      </div>
    </Layout>
  );
};

export default Login;
