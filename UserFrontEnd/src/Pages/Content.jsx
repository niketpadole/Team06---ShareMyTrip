import React from "react";
import { NavLink } from "react-router-dom";

const Content = () => {
  return (
    <>
      <section className="flex flex-col md:flex-row justify-between items-center p-8 md:p-12 bg-[#fff4f1]">
        <div className="max-w-xl text-center md:text-left">
          <h1 className="text-3xl md:text-5xl text-[#ff6f61]">
            Thinking Of Sharing Your Car?
          </h1>
          <p className="text-lg md:text-xl text-[#333] my-4">
            Over 1000 users do it every day
          </p>
          <p className="text-md md:text-lg text-[#333] mb-6">
            Book your ride instantly with just a few clicks. No more waiting!
            Sit back, relax, and enjoy your journey with ShareMyTrip. Your
            comfort is our priority.
          </p>
          <NavLink
            to="/log-in/publisher"
            className="inline-block px-6 py-3 bg-[#ff6f61] text-white text-lg font-bold rounded-md hover:bg-[#e65a50]"
          >
            Join Now
          </NavLink>
        </div>
        <div className="mt-8 md:mt-0 md:ml-8">
          <img
            src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/8867909.jpg"
            alt="Car Sharing"
            className="w-full h-auto rounded-lg shadow-lg"
          />
        </div>
      </section>
    </>
  );
};

export default Content;
