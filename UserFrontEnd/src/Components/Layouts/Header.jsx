import React, { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { HashLink } from "react-router-hash-link";
import { useAuth } from "../../context/auth";
import { FaUserCircle } from "react-icons/fa";
import { useRides } from "../../context/RideDetails";

const Header = () => {
  const navigate = useNavigate();
  const [auth, setAuth] = useAuth();
  const [ridesDetail, setRideDetail]= useRides();
  const [dropDown, setDropDown] = useState(false);
  const [registerDropDown, setRegisterDropDown] = useState(false);
  const [loginDropDown, setLoginDropDown] = useState(false);
  const [profileDropDown, setProfileDropDown] = useState(false);

  const toggleRegisterDropDown = () => {
    setRegisterDropDown(!registerDropDown);
  };

  const toggleLoginDropDown = () => {
    setLoginDropDown(!loginDropDown);
  };

  const toggleDropDown = () => {
    setDropDown(!dropDown);
  };

  const toggleProfileDropDown = () => {
    setProfileDropDown(!profileDropDown);
  };

  const handleLogout = () => {
    setAuth({
      id: null,
      email: "",
      mobile: "",
      dateOfBirth: "",
      userType: "",
      token: "",
    });
    localStorage.removeItem("auth");
    setRideDetail({});
    localStorage.removeItem("rides");
    navigate("/");
  };

  return (
    <>
      <header className="bg-[#ff6f61]">
        <nav className="container mx-auto px-6 py-3">
          <div className="flex items-center justify-between">
            <div className="text-white font-bold text-xl">
              <img
                src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/ShareMyTrip+Logo.png"
                className="h-20 mr-8 hover:scale-150"
              />
            </div>
            <div className="hidden md:block">
              <ul className="flex items-center space-x-8">
                <li>
                  <NavLink
                    to="/"
                    className="text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                  >
                    Home
                  </NavLink>
                </li>
                <li>
                  <HashLink
                    to="#about"
                    className="text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                  >
                    About
                  </HashLink>
                </li>
                <li>
                  <HashLink
                    to="#services"
                    className="text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                  >
                    Services
                  </HashLink>
                </li>
                <li>
                  <HashLink
                    to="#testimonials"
                    className="text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                  >
                    Testimonials
                  </HashLink>
                </li>
                {auth.userType === "PASSENGER" && (
                  <li>
                    <NavLink
                      to="/passanger/book-ride"
                      className="text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                    >
                      Book Ride
                    </NavLink>
                  </li>
                )}
                {auth.userType === "PUBLISHER" && (
                  <li>
                    <NavLink
                      to="/publisher/create-ride"
                      className="text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                    >
                      Create Ride
                    </NavLink>
                  </li>
                )}
                {auth.token ? (
                  <li className="relative">
                    <button
                      onClick={toggleProfileDropDown}
                      className="flex items-center text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                    >
                      <FaUserCircle className="mr-2" /> Profile
                    </button>
                    <div
                      className={`absolute top-full right-0 bg-white rounded-lg shadow-md mt-2 w-48 ${
                        profileDropDown ? "block" : "hidden"
                      }`}
                    >
                      {auth.userType === "PUBLISHER" ? (
                        <>
                          <NavLink
                            to="/publisher/dashboard"
                            className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                          >
                            Dashboard
                          </NavLink>
                          <NavLink
                            to="/publisher/myRides"
                            className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                          >
                            My Rides
                          </NavLink>
                          {/* <NavLink
                            to="/publisher/transactions"
                            className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                          >
                            View Transactions
                          </NavLink> */}
                          <NavLink
                            to="/publisher/profile"
                            className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                          >
                            Profile
                          </NavLink>
                        </>
                      ) : (
                        <>
                          <NavLink
                            to="/passanger/dashboard"
                            className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                          >
                            Dashboard
                          </NavLink>
                          <NavLink
                            to="/passanger/myRides"
                            className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                          >
                            My Rides
                          </NavLink>
                          <NavLink
                            to="/passanger/transactions"
                            className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                          >
                            View Transactions
                          </NavLink>
                          <NavLink
                            to="/passanger/profile"
                            className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                          >
                            Profile
                          </NavLink>
                        </>
                      )}
                      <button
                        onClick={handleLogout}
                        className="block w-full text-left px-4 py-2 text-gray-800 hover:bg-gray-200"
                      >
                        Logout
                      </button>
                    </div>
                  </li>
                ) : (
                  <li className="relative">
                    <button
                      onClick={toggleLoginDropDown}
                      className="block px-4 py-2 text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                    >
                      Log-In
                    </button>
                    <div
                      className={`absolute top-full left-0 bg-white rounded-lg shadow-md mt-2 w-48 ${
                        loginDropDown ? "block" : "hidden"
                      }`}
                    >
                      <NavLink
                        to="/log-in/passanger"
                        className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                      >
                        Passenger
                      </NavLink>
                      <NavLink
                        to="/log-in/publisher"
                        className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                      >
                        Publisher
                      </NavLink>
                    </div>
                  </li>
                )}
                <li className="relative">
                  <button
                    onClick={toggleRegisterDropDown}
                    className={`${
                      auth.token
                        ? "hidden"
                        : "block px-4 py-2 text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                    }`}
                  >
                    Register
                  </button>
                  <div
                    className={`absolute top-full left-0 bg-white rounded-lg shadow-md mt-2 w-48 ${
                      registerDropDown ? "block" : "hidden"
                    }`}
                  >
                    <NavLink
                      to="/register/passanger"
                      className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                    >
                      Passenger
                    </NavLink>
                    <NavLink
                      to="/register/publisher"
                      className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                    >
                      Publisher
                    </NavLink>
                  </div>
                </li>
              </ul>
            </div>
            <div className="md:hidden">
              <button
                className="outline-none mobile-menu-button"
                onClick={toggleDropDown}
              >
                <svg
                  className="w-6 h-6 text-white"
                  fill="none"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth={2}
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path d="M4 6h16M4 12h16M4 18h16" />
                </svg>
              </button>
            </div>
          </div>
          <div
            className={`mobile-menu ${dropDown ? "block" : "hidden"} md:hidden`}
          >
            <ul className="mt-4 space-y-4">
              <li>
                <NavLink
                  to="/"
                  className="block px-4 py-2 text-white hover:text-[#6f61ff] active:text-[#6f61ff]"
                >
                  Home
                </NavLink>
              </li>
              <li>
                <NavLink
                  to="/about"
                  className="block px-4 py-2 text-white hover:text-[#6f61ff] active:text-[#6f61ff]"
                >
                  About
                </NavLink>
              </li>
              <li>
                <NavLink
                  to="/services"
                  className="block px-4 py-2 text-white hover:text-[#6f61ff] active:text-[#6f61ff]"
                >
                  Services
                </NavLink>
              </li>
              <li>
                <NavLink
                  to="/testimonials"
                  className="block px-4 py-2 text-white hover:text-[#6f61ff] active:text-[#6f61ff]"
                >
                  Testimonials
                </NavLink>
              </li>
              {auth.userType === "PASSENGER" && (
                <li>
                  <NavLink
                    to="/passanger/book-ride"
                    className="block px-4 py-2 text-white hover:text-[#6f61ff] active:text-[#6f61ff]"
                  >
                    Book Ride
                  </NavLink>
                </li>
              )}
              {auth.userType === "PUBLISHER" && (
                <li>
                  <NavLink
                    to="/publisher/create-ride"
                    className="block px-4 py-2 text-white hover:text-[#6f61ff] active:text-[#6f61ff]"
                  >
                    Create Ride
                  </NavLink>
                </li>
              )}
              {auth.token ? (
                <li className="relative">
                  <button
                    onClick={toggleProfileDropDown}
                    className="flex items-center text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                  >
                    <FaUserCircle className="mr-2" /> Profile
                  </button>
                  <div
                    className={`absolute top-full right-0 bg-white rounded-lg shadow-md mt-2 w-48 ${
                      profileDropDown ? "block" : "hidden"
                    }`}
                  >
                    {auth.userType === "PUBLISHER" ? (
                      <>
                        <NavLink
                          to="/publisher/dashboard"
                          className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                        >
                          Dashboard
                        </NavLink>
                        <NavLink
                          to="/publisher/myRides"
                          className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                        >
                          My Rides
                        </NavLink>
                        {/* <NavLink
                          to="/publisher/transactions"
                          className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                        >
                          View Transactions
                        </NavLink> */}
                        <NavLink
                          to="/publisher/profile"
                          className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                        >
                          Profile
                        </NavLink>
                      </>
                    ) : (
                      <>
                        <NavLink
                          to="/passenger/dashboard"
                          className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                        >
                          Dashboard
                        </NavLink>
                        <NavLink
                          to="/passanger/myRides"
                          className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                        >
                          My Rides
                        </NavLink>
                        <NavLink
                          to="/passanger/transactions"
                          className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                        >
                          View Transactions
                        </NavLink>
                        <NavLink
                          to="/passanger/profile"
                          className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                        >
                          Profile
                        </NavLink>
                      </>
                    )}
                    <button
                      onClick={handleLogout}
                      className="block w-full text-left px-4 py-2 text-gray-800 hover:bg-gray-200"
                    >
                      Logout
                    </button>
                  </div>
                </li>
              ) : (
                <li className="relative">
                  <button
                    onClick={toggleLoginDropDown}
                    className="block px-4 py-2 text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                  >
                    Log-In
                  </button>
                  <div
                    className={`absolute top-full left-0 bg-white rounded-lg shadow-md mt-2 w-48 ${
                      loginDropDown ? "block" : "hidden"
                    }`}
                  >
                    <NavLink
                      to="/log-in/passenger"
                      className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                    >
                      Passenger
                    </NavLink>
                    <NavLink
                      to="/log-in/publisher"
                      className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                    >
                      Publisher
                    </NavLink>
                  </div>
                </li>
              )}
              <li className="relative">
                <button
                  onClick={toggleRegisterDropDown}
                  className={`${
                    auth.token
                      ? "hidden"
                      : "block px-4 py-2 text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                  }`}
                >
                  Register
                </button>
                <div
                  className={`absolute top-full left-0 bg-white rounded-lg shadow-md mt-2 w-48 ${
                    registerDropDown ? "block" : "hidden"
                  }`}
                >
                  <NavLink
                    to="/register/passanger"
                    className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                  >
                    Passenger
                  </NavLink>
                  <NavLink
                    to="/register/publisher"
                    className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                  >
                    Publisher
                  </NavLink>
                </div>
              </li>
            </ul>
          </div>
        </nav>
      </header>
    </>
  );
};

export default Header;
