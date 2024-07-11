import React, { useState } from "react";
import Layout from "../Components/Layouts/Layout";

const ContactUs = () => {
  const [formData, setFormData] = useState({
    name: "",
    mobileno: "",
    subject: "",
    message: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const mailtoLink = `mailto:sharemytripteam@gmail.com?subject=${encodeURIComponent(
      formData.subject
    )}&body=${encodeURIComponent(
      `Name: ${formData.name}\nMobile No: ${formData.phone}\n\n${formData.message}`
    )}`;

    window.location.href = mailtoLink;
  };

  return (
    <>
      <Layout>
        <main className="py-8 bg-[#fff4f1]">
          <section className="bg-white shadow-md rounded-lg p-8 flex flex-col md:flex-row">
            <div className="md:w-1/2 md:pr-8">
              <h1 className="text-3xl font-bold mb-4 text-gray-800">
                Contact Us
              </h1>
              <p className="mb-8 text-gray-600">
                Have any questions or concerns? We're always ready to help!
                Contact us using the form below or reach out to us directly via
                email or phone.
              </p>
              <form onSubmit={handleSubmit} className="space-y-6">
                <div className="flex flex-col">
                  <label htmlFor="name" className="mb-2 text-gray-600">
                    Name
                  </label>
                  <input
                    type="text"
                    id="name"
                    name="name"
                    className="px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    value={formData.name}
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="flex flex-col">
                  <label htmlFor="phone" className="mb-2 text-gray-600">
                    Mobile Number
                  </label>
                  <input
                    type="tel"
                    id="phone"
                    name="phone"
                    className="px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    value={formData.phone}
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="flex flex-col">
                  <label htmlFor="subject" className="mb-2 text-gray-600">
                    Subject
                  </label>
                  <input
                    type="text"
                    id="subject"
                    name="subject"
                    className="px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    value={formData.subject}
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="flex flex-col">
                  <label htmlFor="message" className="mb-2 text-gray-600">
                    Concern
                  </label>
                  <textarea
                    id="message"
                    name="message"
                    rows="6"
                    className="px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    value={formData.message}
                    onChange={handleChange}
                    required
                  ></textarea>
                </div>
                <button
                  type="submit"
                  className="w-full bg-[#ff6f61] text-white py-2 rounded-lg  transition duration-300"
                >
                  Send Message
                </button>
              </form>
            </div>
            <div className="md:w-1/2 md:pl-8 mt-8 md:mt-0">
              <p className="mb-4 text-gray-600">Phone: 123 456-7890</p>
              <p className="mb-8 text-gray-600">
                Email: sharemytripteam@gmail.com
              </p>
              <div className="map-embed mb-8">
                <iframe
                  src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3770.804198897531!2d72.87765531501696!3d19.076090587089774!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3be7b83b0b8b8b21%3A0x5c49dbd5e6d0b3a4!2sMumbai%2C+Maharashtra%2C+India!5e0!3m2!1sen!2sin!4v1549345472000"
                  width="100%"
                  height="250"
                  frameBorder="0"
                  style={{ border: 0 }}
                  allowFullScreen
                  title="Office Location"
                ></iframe>
              </div>
            </div>
          </section>
        </main>
      </Layout>
    </>
  );
};

export default ContactUs;
