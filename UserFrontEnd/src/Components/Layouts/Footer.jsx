import React from "react";

const Footer = () => {
  return (
    <>
      <footer className="bg-[#ff6f61] text-white py-5 text-center">
        <div className="max-w-6xl mx-auto px-5 flex flex-col items-center">
          <ul className="list-none p-0 flex flex-wrap justify-center mb-5">
            <li className="m-2.5">
              <a
                className="text-white no-underline font-bold transition-colors duration-300 hover:text-red-100"
                href="#"
              >
                About
              </a>
            </li>
            <li className="m-2.5">
              <a
                className="text-white no-underline font-bold transition-colors duration-300 hover:text-red-100"
                href="#"
              >
                Pricing
              </a>
            </li>
            <li className="m-2.5">
              <a
                className="text-white no-underline font-bold transition-colors duration-300 hover:text-red-100"
                href="#"
              >
                Terms of Use
              </a>
            </li>
            <li className="m-2.5">
              <a
                className="text-white no-underline font-bold transition-colors duration-300 hover:text-red-100"
                href="#"
              >
                Privacy Policy
              </a>
            </li>
            <li className="m-2.5">
              <a
                className="text-white no-underline font-bold transition-colors duration-300 hover:text-red-100"
                href="#"
              >
                Careers
              </a>
            </li>
            <li className="m-2.5">
              <a
                className="text-white no-underline font-bold transition-colors duration-300 hover:text-red-100"
                href="#"
              >
                Blog
              </a>
            </li>
            <li className="m-2.5">
              <a
                className="text-white no-underline font-bold transition-colors duration-300 hover:text-red-100"
                href="#"
              >
                Contact Us
              </a>
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
