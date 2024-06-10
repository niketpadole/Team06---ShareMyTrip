import React, { useState, useEffect } from "react";

const Services = () => {
  const services = [
    {
      img: "https://s3.amazonaws.com/niket.in.net/shareMyTrip/20944127.jpg",
      title: "Ride Sharing",
      description:
        "Join a secure community of drivers and passengers sharing rides to save costs and reduce environmental impact.",
    },
    {
      img: "https://s3.amazonaws.com/niket.in.net/shareMyTrip/7197526.jpg",
      title: "Choose your ride",
      description:
        "Select from a variety of rides available and enjoy the flexibility to choose your route and schedule.",
    },
    {
      img: "https://s3.amazonaws.com/niket.in.net/shareMyTrip/20945490.jpg",
      title: "Secure Payments",
      description:
        "Enjoy safe and secure transactions with multiple payment options.",
    },
  ];

  const [currentServiceIndex, setCurrentServiceIndex] = useState(0);

  useEffect(() => {
    const intervalId = setInterval(() => {
      setCurrentServiceIndex((prevIndex) => (prevIndex + 1) % services.length);
    }, 5000);

    return () => clearInterval(intervalId);
  }, [services.length]);

  const currentService = services[currentServiceIndex];

  return (
    <>
      <section id="services" className="bg-[#fff4f1] py-10 text-center">
        <h2 className="text-3xl text-[#ff6f61] mb-6">Our Services</h2>
        <div className="flex flex-col md:flex-row items-center justify-center gap-6">
          <div className="max-w-[600px]">
            <h3 className="text-2xl text-[#333] mb-4">{currentService.title}</h3>
            <p className="text-lg text-[#333]">{currentService.description}</p>
          </div>
          <img
            src={currentService.img}
            alt={currentService.title}
            className="max-w-[500px] rounded-lg"
          />
        </div>
      </section>
    </>
  );
};

export default Services;
