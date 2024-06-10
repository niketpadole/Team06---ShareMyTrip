import React, { useState } from "react";
import Layout from "../Components/Layouts/Layout";
import toast from "react-hot-toast";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";

const CreateRide = () => {
  const navigate = useNavigate();
  const params = useParams();
  let id = 1;

  const [from_location, setFromLocation] = useState("");
  const [to_location, setToLocation] = useState("");
  const [distance, setDistance] = useState("");
  const [journey_hours, setJourneyHour] = useState("");
  const [available_seats, setAvailableSeats] = useState("");
  const [date_of_journey, setDateOfJourney] = useState("");
  const [time_of_journey, setTimeOfJourney] = useState("");
  const [fare_per_seats, setFarePerSeats] = useState("");
  const [description, setDescription] = useState("");
  const [errors, setErrors] = useState({});

  const validate = () => {
    let tempErrors = {};
    if (!from_location) tempErrors.from_location = "From Location is required";
    if (!to_location) tempErrors.to_location = "To Location is required";
    if (!available_seats) tempErrors.available_seats = "Available Seats is required";
    if (!date_of_journey) tempErrors.date_of_journey = "Date of Journey is required";
    if (!time_of_journey) tempErrors.time_of_journey = "Time of Journey is required";
    if (!fare_per_seats) tempErrors.fare_per_seats = "Fare Per Seat is required";

    setErrors(tempErrors);
    return Object.keys(tempErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (validate()) {
      try {
        const formattedTime = new Date(`1970-01-01T${time_of_journey}:00`).toTimeString().split(' ')[0];//Formated Time
        const response = await axios.post(`http://localhost:8089/publishers/${id}/rides`, {
          fromLocation: from_location,
          toLocation: to_location,
          distance: distance,
          journeyHours: journey_hours,
          availableSeats: available_seats,
          dateOfJourney: date_of_journey,
          timeOfJourney: formattedTime,
          farePerSeat: fare_per_seats,
          aboutRide: description,
        });
        toast.success("Ride published successfully!");
        console.log(response);
      } catch (error) {
        console.error("There was an error publishing the ride!", error);
        toast.error("Failed to publish the ride.");
      }
    }
  };

  return (
    <Layout>
      <div className="mx-auto w-full py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">
          Welcome, [Publisher Name]
        </h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-lg mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Publish a New Ride</h2>
          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label htmlFor="from_location" className="block text-gray-700 mb-2">
                From Location
              </label>
              <input
                type="text"
                id="from_location"
                name="from_location"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={from_location}
                onChange={(e) => setFromLocation(e.target.value)}
              />
              {errors.from_location && <p className="text-red-500 text-sm">{errors.from_location}</p>}
            </div>
            <div className="mb-4">
              <label htmlFor="to_location" className="block text-gray-700 mb-2">
                To Location
              </label>
              <input
                type="text"
                id="to_location"
                name="to_location"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={to_location}
                onChange={(e) => setToLocation(e.target.value)}
              />
              {errors.to_location && <p className="text-red-500 text-sm">{errors.to_location}</p>}
            </div>
            <div className="mb-4">
              <label htmlFor="distance" className="block text-gray-700 mb-2">
                Distance (optional)
              </label>
              <input
                type="number"
                step="0.1"
                id="distance"
                name="distance"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={distance}
                onChange={(e) => setDistance(e.target.value)}
              />
            </div>
            <div className="mb-4">
              <label htmlFor="journey_hours" className="block text-gray-700 mb-2">
                Journey Hours (optional)
              </label>
              <input
                type="number"
                step="0.1"
                id="journey_hours"
                name="journey_hours"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={journey_hours}
                onChange={(e) => setJourneyHour(e.target.value)}
              />
            </div>
            <div className="mb-4">
              <label htmlFor="available_seats" className="block text-gray-700 mb-2">
                Available Seats
              </label>
              <input
                type="number"
                id="available_seats"
                name="available_seats"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={available_seats}
                onChange={(e) => setAvailableSeats(e.target.value)}
              />
              {errors.available_seats && <p className="text-red-500 text-sm">{errors.available_seats}</p>}
            </div>
            <div className="mb-4">
              <label htmlFor="date_of_journey" className="block text-gray-700 mb-2">
                Date of Journey
              </label>
              <input
                type="date"
                id="date_of_journey"
                name="date_of_journey"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={date_of_journey}
                onChange={(e) => setDateOfJourney(e.target.value)}
              />
              {errors.date_of_journey && <p className="text-red-500 text-sm">{errors.date_of_journey}</p>}
            </div>
            <div className="mb-4">
              <label htmlFor="time_of_journey" className="block text-gray-700 mb-2">
                Time of Journey
              </label>
              <input
                type="time"
                id="time_of_journey"
                name="time_of_journey"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={time_of_journey}
                onChange={(e) => setTimeOfJourney(e.target.value)}
              />
              {errors.time_of_journey && <p className="text-red-500 text-sm">{errors.time_of_journey}</p>}
            </div>
            <div className="mb-4">
              <label htmlFor="fare_per_seats" className="block text-gray-700 mb-2">
                Fare Per Seat
              </label>
              <input
                type="number"
                step="0.1"
                id="fare_per_seats"
                name="fare_per_seats"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={fare_per_seats}
                onChange={(e) => setFarePerSeats(e.target.value)}
              />
              {errors.fare_per_seats && <p className="text-red-500 text-sm">{errors.fare_per_seats}</p>}
            </div>
            <div className="mb-6">
              <label htmlFor="about_ride" className="block text-gray-700 mb-2">
                Anything About Your Ride
              </label>
              <textarea
                id="about_ride"
                name="about_ride"
                rows="4"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
              ></textarea>
            </div>
            <button
              type="submit"
              className="w-full bg-red-600 text-white p-2 rounded-md hover:bg-red-700"
            >
              Publish Ride
            </button>
          </form>
        </section>
      </div>
    </Layout>
  );
};

export default CreateRide;
