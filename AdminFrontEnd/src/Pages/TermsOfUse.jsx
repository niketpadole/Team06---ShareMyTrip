import React from "react";
import Layout from "../Components/Layouts/Layout";

const TermsOfUse = () => {
  return (
    <Layout>
      <div className="bg-[#f9fafb] min-h-screen py-10">
        <div className="max-w-6xl mx-auto p-10 bg-white shadow-lg rounded-lg">
          <h1 className="text-3xl font-bold mb-5 text-center text-[#ff6f61]">
            Terms of Use
          </h1>
          <p className="mb-4 text-lg text-justify text-[#555]">
            Welcome to ShareMyTrip! These terms and conditions outline the rules
            and regulations for the use of our website and services.
          </p>
          <h2 className="text-2xl font-semibold mt-5 mb-3 text-[#ff6f61]">
            1. Acceptance of Terms
          </h2>
          <p className="mb-4 text-lg text-justify text-[#555]">
            By accessing this website, we assume you accept these terms and
            conditions in full. Do not continue to use ShareMyTrip's website if
            you do not accept all of the terms and conditions stated on this
            page.
          </p>
          <h2 className="text-2xl font-semibold mt-5 mb-3 text-[#ff6f61]">
            2. Services Description
          </h2>
          <p className="mb-4 text-lg text-justify text-[#555]">
            ShareMyTrip provides ride-sharing services which allow users to
            connect with drivers for shared rides. The services are subject to
            the terms and conditions provided here.
          </p>
          <h2 className="text-2xl font-semibold mt-5 mb-3 text-[#ff6f61]">
            3. User Responsibilities
          </h2>
          <p className="mb-4 text-lg text-justify text-[#555]">
            Users are responsible for providing accurate information and
            complying with all applicable laws. Any misuse of the service will
            result in termination of the user's account.
          </p>
          {/* Add more sections as necessary */}
        </div>
      </div>
    </Layout>
  );
};

export default TermsOfUse;
