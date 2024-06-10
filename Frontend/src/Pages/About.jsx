import React from "react";

const About = () => {
  return (
    <>
      <section id="about" className="bg-[#fff4f1] py-10 text-center">
        <h2 className="text-3xl text-[#ff6f61] mb-6">About Us</h2>
        <div className="flex flex-col md:flex-row items-center justify-center gap-6">
          <img
            src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/7197526.jpg"
            alt="About Us"
            className="max-w-[500px] rounded-lg"
          />
          <div className="max-w-[600px]">
            <p className="text-lg text-[#333]">
              ShareMyTrip is a leading car-sharing platform that connects
              drivers with passengers who need a ride. Our mission is to make
              travel more affordable, convenient, and environmentally friendly.
              We believe in creating a community where people can share rides,
              reduce their carbon footprint, and make new friends along the way.
            </p>
          </div>
        </div>
      </section>
    </>
  );
};

export default About;
