import React from "react";

const Services = () => {
  return (
    <>
      <section id="services" className="bg-[#fff4f1] py-10 text-center">
        <h2 className="text-3xl text-[#ff6f61] mb-6">Our Services</h2>
        <div className="flex flex-col md:flex-row items-center justify-center gap-6">
          <div className="max-w-[600px]">
            <h3 className="text-2xl text-[#333] mb-4">Ride Sharing</h3>
            <p className="text-lg text-[#333]">
              Join a community of drivers and passengers sharing rides to save
              costs and reduce environmental impact. Our ride-sharing service
              connects you with people heading in the same direction, making
              your commute more affordable and social.
            </p>
          </div>
          <img
            src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/20944122.jpg"
            alt="Ride Sharing"
            className="max-w-[500px] rounded-lg"
          />
        </div>
      </section>
    </>
  );
};

export default Services;
