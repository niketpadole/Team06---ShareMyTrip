import React from "react";
import Layout from "../Components/Layouts/Layout";

const PrivacyPolicy = () => {
  return (
    <Layout>
    <div className="flex justify-center items-center bg-gray-100 min-h-screen p-5">
      <div className="w-full max-w-3xl p-5 bg-white shadow-md rounded-lg">
        <h1 className="text-4xl font-bold mb-5 text-center text-[#ff6f61]">Privacy Policy</h1>
        <p className="mb-4 text-lg text-gray-700 text-justify">
          Your privacy is important to us. It is ShareMyTrip's policy to respect your privacy regarding any information we may collect from you across our website and services.
        </p>
        <h2 className="text-2xl font-semibold mt-5 mb-3 text-[#ff6f61]">1. Information We Collect</h2>
        <p className="mb-4 text-lg text-gray-700 text-justify">
          We collect information to provide better services to all our users. The information we collect includes personal data, usage data, and cookies.
        </p>
        <h2 className="text-2xl font-semibold mt-5 mb-3 text-[#ff6f61]">2. How We Use Information</h2>
        <p className="mb-4 text-lg text-gray-700 text-justify">
          We use the information we collect to provide, maintain, protect, and improve our services, to develop new ones, and to protect ShareMyTrip and our users.
        </p>
        <h2 className="text-2xl font-semibold mt-5 mb-3 text-[#ff6f61]">3. Information Sharing</h2>
        <p className="mb-4 text-lg text-gray-700 text-justify">
          We do not share personal information with companies, organizations, or individuals outside of ShareMyTrip except in the following cases: with your consent, for external processing, or for legal reasons.
        </p>
      </div>
    </div>
    </Layout>
  );
};

export default PrivacyPolicy;
