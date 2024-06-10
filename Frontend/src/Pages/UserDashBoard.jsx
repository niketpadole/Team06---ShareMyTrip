import React, { useState } from "react";
import Layout from "../Components/Layouts/Layout";

const UserDashBoard = () => {
  const [leavingFrom, setLeavingFrom] = useState("");
  const [goingTo, setGoingTo] = useState("");
  const [date, setDate] = useState("");
  const [passengers, setPassengers] = useState();

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(leavingFrom, goingTo, date, passengers);
  };

  return (
    <Layout>
      <main className="w-full mx-auto py-8 px-4">
        <div className="text-center mb-4">
          <img
            src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/3339154.jpg"
            className="w-1/3 h-1/3 m-auto max-w-full"
            alt="Rides at low prices"
          />
          <h2 className="text-2xl font-bold mt-4">
            Your pick of rides at low prices
          </h2>
        </div>
        <div className="flex items-center justify-center">
          <form
            className="md:flex md:items-center border-2 rounded-2xl w-3/4"
            onSubmit={handleSubmit}
          >
            <div className=" w-full md:w-1/2">
              <input
                type="text"
                id="leavingFrom"
                placeholder="Leaving From"
                className="w-full  md:border-r-2 outline-none"
                value={leavingFrom}
                onChange={(event) => setLeavingFrom(event.target.value)}
              />
            </div>
            <div className="w-full md:w-1/2">
              <input
                type="text"
                id="goingTo"
                placeholder="Going To"
                className="w-full md:border-r-2 outline-none"
                value={goingTo}
                onChange={(event) => setGoingTo(event.target.value)}
              />
            </div>
            <div className=" w-full md:w-1/3">
              <input
                type="date"
                id="date"
                placeholder="Today"
                className="w-full md:border-r-2 outline-none"
                value={date}
                onChange={(event) => setDate(event.target.value)}
              />
            </div>
            <div className=" w-full md:w-1/3">
              <input
                type="number"
                id="passengers"
                placeholder="No of Passangers"
                className="w-full md:border-r-2 outline-none"
                value={passengers}
                onChange={(event) =>
                  setPassengers(parseInt(event.target.value))
                }
              />
            </div>
            <div className=" w-full md:w-1/3">
              <button
                className="bg-orange-500 hover:bg-orange-700 text-white p-2 border rounded-2xl w-full md:max-w-48"
                type="submit"
              >
                Search
              </button>
            </div>
          </form>
        </div>
        <div className="md:flex md:flex-wrap md:justify-between md:px-16 mt-8">
          <div className="mb-8 w-full md:w-1/2 lg:w-1/3">
            <h3 className="text-xl font-bold mb-2">
              Your pick of rides at low prices
            </h3>
            <p className="text-gray-600">
              No matter where you're going, by bus or carpool, find the perfect
              ride from our wide range of destinations and routes at low prices.
            </p>
          </div>
          <div className="mb-8 w-full md:w-1/2 lg:w-1/3 px-4">
            <h3 className="text-xl font-bold mb-2">
              Trust who you travel with
            </h3>
            <p className="text-gray-600">
              We take the time to get to know each of our members and bus
              partners. We check reviews, profiles, and IDs, so you know who
              you're traveling with and can book your ride at ease on our secure
              platform.
            </p>
          </div>
          <div className="w-full lg:w-1/3">
            <h3 className="text-xl font-bold mb-2">
              Scroll, click, tap and go!
            </h3>
            <p className="text-gray-600">
              Booking a ride has never been easier! Thanks to our simple app
              powered by great technology, you can book a ride close to you in
              just minutes.
            </p>
          </div>
        </div>
      </main>
    </Layout>
  );
};

export default UserDashBoard;
