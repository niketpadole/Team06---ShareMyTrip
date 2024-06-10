import React, { useState } from "react";
import Layout from "../Components/Layouts/Layout";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";

const BookRide = () => {



    const [fromLocation, setFromLocation] = useState("");
    const [toLocation, setToLocation] = useState("");
    const [date_of_journey, setDateOfJourney] = useState("");
    const [fromLocationError,setFromLocationError] = useState("");
    const [toLocationError,setToLocationError] = useState("");
    const [dateError,setDateError] = useState("");

    const [publishedRide,setPublishedRide] = useState([]);

    const validate = () => {
        let isValid = true;
        if(!fromLocation)
            {
                setFromLocationError("From Location Required");
                isValid = false;
            }
        if(!toLocation)
            {
                setToLocationError("To Location Required");
                isValid = false;
            }
        if(!date_of_journey)
            {
                setDateOfJourney("Date Of Journey Required");
                isValid = false;
            }
        
        return isValid;
    }
    const handleSearch = async (e) =>{
        try {
            e.preventDefault();
            if(validate())
                {
                    const response = await axios.get(`http://localhost:8089/passengers/rides/filter`,{
                        params:{
                            fromLocation,
                            toLocation
                        }
                    });
                    if(response.status == 200)
                        {
                            toast.success("All Ride Fetched Sucessfully");
                            setPublishedRide(response.data);
                        }
                }
            
        } catch (error) {
            console.log(error);
            toast.error(error);
        }
    }
  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">Book a Ride</h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Search for a Ride</h2>
          <form action="/search_rides" method="get" className="mb-6">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div>
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
                  value={fromLocation}
                  onChange={(e) => setFromLocation(e.target.value)}
                  className="w-full p-2 border border-gray-300 rounded-md"
                  required
                />
              </div>
              <div>
                <label
                  htmlFor="to_location"
                  className="block text-gray-700 mb-2"
                >
                  To Location
                </label>
                <input
                  type="text"
                  id="to_location"
                  name="to_location"
                  value={toLocation}
                  onChange={(e) => setToLocation(e.target.value)}
                  className="w-full p-2 border border-gray-300 rounded-md"
                  required
                />
              </div>
              <div>
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
                  value={date_of_journey}
                  onChange={(e) => setDateOfJourney(e.target.value)}
                  className="w-full p-2 border border-gray-300 rounded-md"
                  required
                />
              </div>
            </div>
            <button
              type="submit"
              className="w-full bg-red-600 text-white p-2 rounded-md hover:bg-red-700 mt-4"
              onClick={handleSearch}
            >
              Search Rides
            </button>
          </form>
          <h2 className="text-2xl text-red-600 mb-6">Available Rides</h2>
          <div className="grid grid-cols-1 gap-6">
            <div className="bg-gray-100 p-4 rounded-lg shadow-md flex">
              <div className="flex-shrink-0 mr-4">
                <img
                  src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/3339154.jpg"
                  alt="Publisher Image"
                  className="w-24 h-24 object-cover rounded-full"
                />
              </div>
              <div>
                <h3 className="text-xl text-gray-700 mb-2">Publisher Name</h3>
                <p className="text-gray-600 mb-1">
                  <strong>From:</strong> New York
                </p>
                <p className="text-gray-600 mb-1">
                  <strong>To:</strong> Boston
                </p>
                <p className="text-gray-600 mb-1">
                  <strong>Date:</strong> 2024-06-10
                </p>
                <p className="text-gray-600 mb-1">
                  <strong>Time:</strong> 10:00 AM
                </p>
                <p className="text-gray-600 mb-1">
                  <strong>Fare per Seat:</strong> $50
                </p>
                <a
                  href="#"
                  className="mt-2 inline-block bg-red-600 text-white px-4 py-2 rounded-md hover:bg-red-700"
                >
                  Book Ride
                </a>
              </div>
            </div>
          </div>
        </section>
      </main>
    </Layout>
  );
};

export default BookRide;
