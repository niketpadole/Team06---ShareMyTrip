import React, { useState, useEffect } from "react";
import Layout from "../../Components/Layouts/Layout";
import toast from "react-hot-toast";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../context/auth";

const CreateRide = () => {
  const navigate = useNavigate();
  const [auth] = useAuth();
  const [from_location, setFromLocation] = useState("");
  const [to_location, setToLocation] = useState("");
  const [distance, setDistance] = useState("");
  const [journey_hours, setJourneyHour] = useState("");
  const [available_seats, setAvailableSeats] = useState("");
  const [date_of_journey, setDateOfJourney] = useState("");
  const [time_of_journey, setTimeOfJourney] = useState("");
  const [fare_per_seat, setFarePerSeats] = useState("");
  const [initialFare, setInitialFare] = useState(""); // New state to hold the initially fetched fare
  const [description, setDescription] = useState("");
  const [errors, setErrors] = useState({});
  const [showModal, setShowModal] = useState(false);
  const [minTime, setMinTime] = useState("");

  const validate = () => {
    let tempErrors = {};
    if (!from_location) tempErrors.from_location = "From Location is required";
    if (!to_location) tempErrors.to_location = "To Location is required";
    if (!available_seats)
      tempErrors.available_seats = "Available Seats is required";
    if (!date_of_journey)
      tempErrors.date_of_journey = "Date of Journey is required";
    if (!time_of_journey)
      tempErrors.time_of_journey = "Time of Journey is required";
    if (!fare_per_seat) tempErrors.fare_per_seats = "Fare Per Seat is required";
    if (
      parseFloat(fare_per_seat) < parseFloat(initialFare) - 200 ||
      parseFloat(fare_per_seat) > parseFloat(initialFare) + 200
    ) {
      tempErrors.fare_per_seats = `Fare Per Seat must be between ${
        parseFloat(initialFare) - 200
      } and ${parseFloat(initialFare) + 200}`;
    }

    setErrors(tempErrors);
    return Object.keys(tempErrors).length === 0;
  };

  useEffect(() => {
    const fetchDistanceAndFare = async () => {
      if (from_location && to_location) {
        try {
          const distanceResponse = await axios.post(
            "https://api.sharemytrip.xyz/fare/distance",
            {
              fromLocation: from_location,
              toLocation: to_location,
            }
          );
          if (distanceResponse.status === 200) {
            setDistance(distanceResponse.data.distance);
            const fareResponse = await axios.post(
              "https://api.sharemytrip.xyz/fare/calculate",
              {
                fromLocation: from_location,
                toLocation: to_location,
              }
            );
            if (fareResponse.status === 200) {
              setFarePerSeats(fareResponse.data.totalFare);
              setInitialFare(fareResponse.data.totalFare); // Set the initial fare
              const JourneyResponse = await axios.post(
                "https://api.sharemytrip.xyz/fare/calculateJourneyTime",
                {
                  fromLocation: from_location,
                  toLocation: to_location,
                }
              );
              if (JourneyResponse.status == 200) {
                setJourneyHour(JourneyResponse.data);
              }
            }
          }
        } catch (error) {
          console.error("Error fetching distance and fare", error);
        }
      }
    };

    fetchDistanceAndFare();
    // Set minimum time to the current time
    const now = new Date();
    const hours = now.getHours().toString().padStart(2, "0");
    const minutes = now.getMinutes().toString().padStart(2, "0");
    setMinTime(`${hours}:${minutes}`);
  }, [from_location, to_location]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (validate()) {
      setShowModal(true);
    }
  };

  const handleConfirm = async () => {
    try {
      const formattedTime = new Date(`1970-01-01T${time_of_journey}:00`)
        .toTimeString()
        .split(" ")[0];
      const response = await axios.post(
        `https://api.sharemytrip.xyz/user/publishers/${auth.id}/rides`,
        {
          fromLocation: from_location,
          toLocation: to_location,
          distance: distance,
          journeyHours: journey_hours,
          availableSeats: available_seats,
          dateOfJourney: date_of_journey,
          timeOfJourney: formattedTime,
          farePerSeat: fare_per_seat,
          aboutRide: description,
        }
      );
      if (response.status === 200) {
        await axios.post(
          `https://api.sharemytrip.xyz/email/send-publisher-confirmation?publisherEmail=${encodeURIComponent(
            auth.email
          )}`
        );
        toast.success("Ride published successfully!", {
          duration: 3000,
        });
        navigate("/publisher/dashboard");
      }
    } catch (error) {
      console.error("There was an error publishing the ride!", error);
      toast.error("Failed to publish the ride.");
    } finally {
      setShowModal(false);
    }
  };

  const closeModal = () => {
    setShowModal(false);
  };

  return (
    <Layout>
      <div className="mx-auto w-full py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">
          Welcome, {`${auth.firstName} ${auth.lastName}`}
        </h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-lg mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Publish a New Ride</h2>
          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label
                htmlFor="from_location"
                className="block text-gray-700 mb-2"
              >
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
              {errors.from_location && (
                <p className="text-red-500 text-sm">{errors.from_location}</p>
              )}
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
              {errors.to_location && (
                <p className="text-red-500 text-sm">{errors.to_location}</p>
              )}
            </div>
            <div className="mb-4">
              <label htmlFor="distance" className="block text-gray-700 mb-2">
                Distance(in KM)
              </label>
              <input
                type="number"
                step="0.1"
                id="distance"
                name="distance"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={distance}
                readOnly
              />
            </div>
            <div className="mb-4">
              <label
                htmlFor="journey_hours"
                className="block text-gray-700 mb-2"
              >
                Journey Hours
              </label>
              <input
                type="text"
                step="0.1"
                id="journey_hours"
                name="journey_hours"
                className="w-full p-2 border border-gray-300 rounded-md"
                readOnly
                value={journey_hours}
                onChange={(e) => setJourneyHour(e.target.value)}
              />
            </div>
            <div className="mb-4">
              <label
                htmlFor="available_seats"
                className="block text-gray-700 mb-2"
              >
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
              {errors.available_seats && (
                <p className="text-red-500 text-sm">{errors.available_seats}</p>
              )}
            </div>
            <div className="mb-4">
              <label
                htmlFor="date_of_journey"
                className="block text-gray-700 mb-2"
              >
                Date of Journey
              </label>
              <input
                type="date"
                id="date_of_journey"
                name="date_of_journey"
                className="w-full p-2 border border-gray-300 rounded-md"
                min={new Date().toISOString().split("T")[0]}
                value={date_of_journey}
                onChange={(e) => setDateOfJourney(e.target.value)}
              />
              {errors.date_of_journey && (
                <p className="text-red-500 text-sm">{errors.date_of_journey}</p>
              )}
            </div>
            <div className="mb-4">
              <label
                htmlFor="time_of_journey"
                className="block text-gray-700 mb-2"
              >
                Time of Journey
              </label>
              <input
                type="time"
                id="time_of_journey"
                name="time_of_journey"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={time_of_journey}
                min={minTime}
                onChange={(e) => setTimeOfJourney(e.target.value)}
              />
              {errors.time_of_journey && (
                <p className="text-red-500 text-sm">{errors.time_of_journey}</p>
              )}
            </div>
            <div className="mb-4">
              <label
                htmlFor="fare_per_seats"
                className="block text-gray-700 mb-2"
              >
                Fare Per Seat (in Rupees)
              </label>
              <input
                type="number"
                step="0.1"
                id="fare_per_seats"
                name="fare_per_seats"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={fare_per_seat}
                min={parseFloat(initialFare) - 200} // Use initialFare for validation
                max={parseFloat(initialFare) + 200} // Use initialFare for validation
                onChange={(e) => setFarePerSeats(e.target.value)}
              />
              {errors.fare_per_seats && (
                <p className="text-red-500 text-sm">{errors.fare_per_seats}</p>
              )}
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

      {showModal && (
        <div
          id="popup-modal"
          tabIndex="-1"
          className="fixed inset-0 z-50 flex items-center justify-center overflow-y-auto overflow-x-hidden bg-black bg-opacity-50"
        >
          <div className="relative p-4 w-full max-w-md max-h-full">
            <div className="relative bg-white rounded-lg shadow">
              <button
                type="button"
                className="absolute top-3 end-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center"
                onClick={closeModal}
              >
                <svg
                  className="w-3 h-3"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 14 14"
                >
                  <path
                    stroke="currentColor"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"
                  />
                </svg>
                <span className="sr-only">Close modal</span>
              </button>
              <div className="p-4 md:p-5 text-center">
                <svg
                  className="mx-auto mb-4 text-gray-400 w-12 h-12"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 20 20"
                >
                  <path
                    stroke="currentColor"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M10 11V6m0 8h.01M19 10a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
                  />
                </svg>
                <h3 className="mb-5 text-lg font-normal text-gray-500">
                  Are you sure you want to publish this ride?
                </h3>
                <button
                  type="button"
                  className="text-white bg-red-600 hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300  font-medium rounded-lg text-sm inline-flex items-center px-5 py-2.5 text-center"
                  onClick={handleConfirm}
                >
                  Yes, I'm sure
                </button>
                <button
                  type="button"
                  className="py-2.5 px-5 ms-3 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100"
                  onClick={closeModal}
                >
                  No, cancel
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </Layout>
  );
};

export default CreateRide;
