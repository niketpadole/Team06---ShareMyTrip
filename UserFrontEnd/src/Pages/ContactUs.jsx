import React from "react";
import Layout from "../Components/Layouts/Layout";

const ContactUs = () => {
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
              <form action="#" method="POST" className="space-y-6">
                <div className="flex flex-col">
                  <label htmlFor="name" className="mb-2 text-gray-600">
                    Name
                  </label>
                  <input
                    type="text"
                    id="name"
                    name="name"
                    className="px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    required
                  />
                </div>
                <div className="flex flex-col">
                  <label htmlFor="email" className="mb-2 text-gray-600">
                    Email
                  </label>
                  <input
                    type="email"
                    id="email"
                    name="email"
                    className="px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
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
                    required
                  />
                </div>
                <div className="flex flex-col">
                  <label htmlFor="message" className="mb-2 text-gray-600">
                    Message
                  </label>
                  <textarea
                    id="message"
                    name="message"
                    rows="6"
                    className="px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    required
                  ></textarea>
                </div>
                <button
                  type="submit"
                  className="w-full bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition duration-300"
                >
                  Send Message
                </button>
              </form>
            </div>
            <div className="md:w-1/2 md:pl-8 mt-8 md:mt-0">
              <h2 className="text-2xl font-bold mb-4 text-gray-800">
                Our Office
              </h2>
              <p className="mb-4 text-gray-600">
                1234 ShareMyTrip Avenue
                <br />
                Suite 100
                <br />
                San Francisco, CA 94103
              </p>
              <p className="mb-4 text-gray-600">Phone: (123) 456-7890</p>
              <p className="mb-8 text-gray-600">
                Email: support@sharemytrip.com
              </p>
              <div className="map-embed mb-8">
                <iframe
                  src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3153.019609653074!2d-122.40240938468207!3d37.77492957975969!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8085808c7b0d8b33%3A0x7e5ff3d44c1a05e1!2sSan+Francisco%2C+CA%2C+USA!5e0!3m2!1sen!2sin!4v1549345472000"
                  width="100%"
                  height="250"
                  frameBorder="0"
                  style={{ border: 0 }}
                  allowFullScreen
                  title="Office Location"
                ></iframe>
              </div>
              <img
                src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/office.jpg"
                alt="Office"
                className="rounded-lg shadow-lg"
              />
            </div>
          </section>
        </main>
      </Layout>
    </>
  );
};

export default ContactUs;
