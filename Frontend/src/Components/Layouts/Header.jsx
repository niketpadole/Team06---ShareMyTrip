import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import { HashLink } from "react-router-hash-link";

const Header = () => {
  const [dropDown, setDropDown] = useState(false);
  const [registerDropDown, setRegisterDropDown] = useState(false);
  const [loginDropDown, setLoginDropDown] = useState(false);

  const toggleRegisterDropDown = () => {
    setRegisterDropDown(!registerDropDown);
  };

  const toggleLoginDropDown = () => {
    setLoginDropDown(!loginDropDown);
  };

  const toggleDropDown = () => {
    setDropDown(!dropDown);
  };

  return (
    <>
      <header className="bg-[#ff6f61]">
        <nav className="container mx-auto px-6 py-3">
          <div className="flex items-center justify-between">
            <div className="text-white font-bold text-xl">
              <img src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/ShareMyTrip+Logo.png" className="h-20 mr-8 hover:scale-150"/>
            </div>
            <div className="hidden md:block">
              <ul className="flex items-center space-x-8">
                <li>
                  <NavLink to="/" className="text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]">
                    Home
                  </NavLink>
                </li>
                <li>
                  <HashLink to="#about" className="text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]">
                    About
                  </HashLink>
                </li>
                <li>
                  <HashLink to="#services" className="text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]">
                    Services
                  </HashLink>
                </li>
                <li>
                  <HashLink to="#testimonials" className="text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]">
                    Testimonials
                  </HashLink>
                </li>
                <li className="relative">
                  <button
                    onClick={toggleLoginDropDown}
                    className="block px-4 py-2 text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                  >
                    Log-In
                  </button>
                  <div
                    className={`absolute top-full left-0 bg-white rounded-lg shadow-md mt-2 w-48 ${loginDropDown ? "block" : "hidden"}`}
                  >
                    <NavLink
                      to="/log-in/passanger"
                      className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                    >
                      Passanger
                    </NavLink>
                    <NavLink
                      to="/log-in/publisher"
                      className="block px-4 py-2 text-gray-800 hover:bg-gray-200"
                    >
                      Publisher
                    </NavLink>
                  </div>
                </li>
                <li className="relative">
                  <button
                    onClick={toggleRegisterDropDown}
                    className="block px-4 py-2 text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                  >
                    Register
                  </button>
                  <div
                    className={`absolute top-full left-0 bg-white rounded-lg shadow-md mt-2 w-48 ${registerDropDown ? "block" : "hidden"}`}
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
                  x-show="!showMenu"
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
              <li>
                <NavLink
                  to="/log-in"
                  className="py-2 text-white hover:text-[#6f61ff] active:text-[#6f61ff]"
                >
                  Log-In
                </NavLink>
              </li>
              <li className="relative">
                <button
                  onClick={toggleRegisterDropDown}
                  className="block px-4 py-2 text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                >
                  Register
                </button>
                <div
                  className={`absolute top-full left-0 bg-white rounded-lg shadow-md mt-2 w-48 ${registerDropDown ? "block" : "hidden"}`}
                >
                  <NavLink
                    to="/register/passenger"
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
