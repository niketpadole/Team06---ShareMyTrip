import React from "react";
import { NavLink } from "react-router-dom";
import { HashLink } from "react-router-hash-link";

const Footer = () => {
  return (
    <>
      <footer className="bg-[#ff6f61] text-white py-5 text-center">
        <div className="max-w-6xl mx-auto px-5 flex flex-col items-center">
          <ul className="list-none p-0 flex flex-wrap justify-center mb-5">
            <li className="m-2.5">
              <HashLink
                className="text-white no-underline font-bold transition-colors duration-300 hover:text-red-100"
                to="#about"
              >
                About
              </HashLink>
            </li>
            <li className="m-2.5">
              <NavLink
                className="text-white no-underline font-bold transition-colors duration-300 hover:text-red-100"
                to="/TermsOfUse"
              >
                Terms of Use
              </NavLink>
            </li>
            <li className="m-2.5">
              <NavLink
                className="text-white no-underline font-bold transition-colors duration-300 hover:text-red-100"
                to="/PrivacyPolicy"
              >
                Privacy Policy
              </NavLink>
            </li>
            <li className="m-2.5">
              <NavLink
                className="text-white no-underline font-bold transition-colors duration-300 hover:text-red-100"
                to="/contactUs"
              >
                Contact Us
              </NavLink>
            </li>
          </ul>
          <img
            src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/ShareMyTrip+Logo.png"
            alt="Logo"
            className="w-48 mb-5"
          />
          <p className="text-sm">
            © 2024{" "}
            <a
              className="text-white no-underline font-bold hover:text-red-100"
              href="#"
            >
              ShareMyTrip
            </a>
            . All Rights Reserved. Built by Code Riders Team
          </p>
        </div>
      </footer>
    </>
  );
};

export default Footer;
