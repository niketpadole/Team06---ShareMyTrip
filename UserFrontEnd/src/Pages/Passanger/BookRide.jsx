import React, { useState } from "react";
import Layout from "../../Components/Layouts/Layout";
import axios from "axios";
import toast from "react-hot-toast";
import { useAuth } from "../../context/auth";
import { useNavigate } from "react-router-dom";

const BookRide = () => {
  const navigate = useNavigate();
  const [auth, setAuth] = useAuth();
  const [fromLocation, setFromLocation] = useState("");
  const [toLocation, setToLocation] = useState("");
  const [date_of_journey, setDateOfJourney] = useState("");
  const [fromLocationError, setFromLocationError] = useState("");
  const [toLocationError, setToLocationError] = useState("");
  const [dateError, setDateError] = useState("");
  const [seats, setSeats] = useState({});
  const [publishedRide, setPublishedRide] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [rideToBook, setRideToBook] = useState(null);
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);

  const validate = () => {
    let isValid = true;
    if (!fromLocation) {
      setFromLocationError("From Location Required");
      isValid = false;
    }
    if (!toLocation) {
      setToLocationError("To Location Required");
      isValid = false;
    }
    if (!date_of_journey) {
      setDateError("Date Of Journey Required");
      isValid = false;
    }

    return isValid;
  };

  const handleSearch = async (e) => {
    try {
      e.preventDefault();
      if (validate()) {
        const formattedDate = new Date(date_of_journey).toISOString().split('T')[0];
        const response = await axios.get(
          `https://api.sharemytrip.xyz/user/passengers/rides/filter`,
          {
            params: {
              fromLocation,
              toLocation,
              dateOfJourney: formattedDate
            },
          }
        );
        if (response.status === 200) {
          const availableRides = response.data.filter(ride => ride.availableSeats > 0 && ride.status !== "ONGOING" && ride.status !== "COMPLETED");
          toast.success("All Rides Fetched Successfully");
          console.log(availableRides);
          setPublishedRide(availableRides);
        }
      }
    } catch (error) {
      console.log(error);
      toast.error("Something Went Wrong");
    }
  };

  const handleBookRide = async () => {
    const rideSeats = seats[rideToBook.publisherRideId] || 1;
    if (rideSeats > rideToBook.availableSeats) {
      toast.error("Number of seats exceeds available seats");
      return;
    }
    const publisherRideId = rideToBook.publisherRideId;
    try {
      const response = await axios.post(`https://api.sharemytrip.xyz/user/passengers/${auth.id}/book`, {
        passengerId: auth.id,
        publisherRideId: publisherRideId,
        noOfPassengers: rideSeats
      });
      if (response.status === 200) {
        await axios.post(`https://api.sharemytrip.xyz/email/send-passenger-booked-confirmation?passengerEmail=${encodeURIComponent(auth.email)}`)
        toast.success("Ride Booked Successfully");
        setShowModal(false);
        setShowConfirmationModal(true);
      } else {
        toast.error("Something went wrong");
      }
    } catch (error) {
      console.log(error);
      toast.error(error.message);
    } finally {
      closeModal();
    }
  };

  const onBookClick = (ride) => {
    setRideToBook(ride);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
    setRideToBook(null);
  };

  const incrementSeats = (rideId, maxSeats) => {
    setSeats((prevSeats) => ({
      ...prevSeats,
      [rideId]: (prevSeats[rideId] || 1) < maxSeats ? (prevSeats[rideId] || 1) + 1 : maxSeats,
    }));
  };

  const decrementSeats = (rideId) => {
    setSeats((prevSeats) => ({
      ...prevSeats,
      [rideId]: (prevSeats[rideId] || 1) > 1 ? (prevSeats[rideId] || 1) - 1 : 1,
    }));
  };

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
                {fromLocationError && (
                  <p className="text-red-500 text-sm">{fromLocationError}</p>
                )}
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
                {toLocationError && (
                  <p className="text-red-500 text-sm">{toLocationError}</p>
                )}
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
                {dateError && (
                  <p className="text-red-500 text-sm">{dateError}</p>
                )}
                <input
                  type="date"
                  id="date_of_journey"
                  name="date_of_journey"
                  min={new Date().toISOString().split("T")[0]}
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
          {publishedRide.length === 0 ? (
            <h2 className="text-2xl text-red-600 mb-6">No Rides Available</h2>
          ) : (
            <h2 className="text-2xl text-red-600 mb-6">Available Rides</h2>
          )}
          <div className="grid grid-cols-1 gap-6">
            {publishedRide.map((ride, index) => (
              <div
                key={index}
                className="bg-gray-100 p-4 rounded-lg shadow-md flex flex-col md:flex-row items-start md:items-center"
              >
                <div className="flex-shrink-0 mb-4 md:mb-0 md:mr-4">
                  <img
                    src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/3339154.jpg"
                    alt="Publisher Image"
                    className="w-24 h-24 object-cover rounded-full"
                  />
                </div>
                <div className="flex flex-col mr-0">
                  <h3 className="text-xl text-gray-700 mb-2">
                    {ride.publisherName}
                  </h3>
                  <p className="text-gray-600 mb-1">
                    <strong>From:</strong> {ride.fromLocation}
                  </p>
                  <p className="text-gray-600 mb-1">
                    <strong>To:</strong> {ride.toLocation}
                  </p>
                  <p className="text-gray-600 mb-1">
                    <strong>Date:</strong> {ride.dateOfJourney}
                  </p>
                  <p className="text-gray-600 mb-1">
                    <strong>Time:</strong> {ride.timeOfJourney}
                  </p>
                  <p className="text-gray-600 mb-1">
                    <strong>Fare per Seat:</strong> Rs.{ride.farePerSeat}
                  </p>
                  <p className="text-gray-600 mb-1">
                    <strong>Available Seats:</strong> {ride.availableSeats}
                  </p>
                  <div className="flex items-center mt-2">
                    <button
                      className="px-2 py-1 bg-gray-300 rounded-l-md"
                      onClick={() => decrementSeats(ride.publisherRideId)}
                      disabled={(seats[ride.publisherRideId] || 1) <= 1}
                    >
                      -
                    </button>
                    <input
                      type="text"
                      value={seats[ride.publisherRideId] || 1}
                      readOnly
                      className="w-12 text-center border-t border-b border-gray-300"
                    />
                    <button
                      className="px-2 py-1 bg-gray-300 rounded-r-md"
                      onClick={() => incrementSeats(ride.publisherRideId, ride.availableSeats)}
                      disabled={(seats[ride.publisherRideId] || 1) >= ride.availableSeats}
                    >
                      +
                    </button>
                  </div>
                  <button
                    onClick={() => onBookClick(ride)}
                    className="mt-4 md:mt-2 inline-block bg-red-600 text-white px-4 py-2 rounded-md hover:bg-red-700"
                  >
                    Book Ride
                  </button>
                </div>
              </div>
            ))}
          </div>
        </section>

        {showModal && (
          <div id="popup-modal" tabIndex="-1" className="fixed inset-0 z-50 flex items-center justify-center overflow-y-auto overflow-x-hidden bg-black bg-opacity-50">
            <div className="relative p-4 w-full max-w-md max-h-full">
              <div className="relative bg-white rounded-lg shadow dark:bg-gray-700">
                <button type="button" className="absolute top-3 end-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center d" onClick={closeModal}>
                  <svg className="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
                  </svg>
                  <span className="sr-only">Close modal</span>
                </button>
                <div className="p-4 md:p-5 text-center">
                  <svg className="mx-auto mb-4 text-gray-400 w-12 h-12 dark:text-gray-200" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 11V6m0 8h.01M19 10a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"/>
                  </svg>
                  <h3 className="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">Are you sure you want to book this ride?</h3>
                  <button type="button" className="text-white bg-red-600 hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300  font-medium rounded-lg text-sm inline-flex items-center px-5 py-2.5 text-center" onClick={handleBookRide}>
                    Yes, I'm sure
                  </button>
                  <button type="button" className="py-2.5 px-5 ms-3 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100" onClick={closeModal}>
                    No, cancel
                  </button>
                </div>
              </div>
            </div>
          </div>
        )}

        {showConfirmationModal && (
          <div id="confirmation-modal" tabIndex="-1" className="fixed inset-0 z-50 flex items-center justify-center overflow-y-auto overflow-x-hidden bg-black bg-opacity-50">
            <div className="relative p-4 w-full max-w-md max-h-full">
              <div className="relative bg-white rounded-lg shadow">
                <button type="button" className="absolute top-3 end-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center" onClick={() => setShowConfirmationModal(false)}>
                  <svg className="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
                  </svg>
                  <span className="sr-only">Close modal</span>
                </button>
                <div className="p-4 md:p-5 text-center">
                  <h3 className="mb-5 text-lg font-normal text-gray-500">Ride booked successfully!</h3>
                  <button type="button" className="text-white bg-red-600 hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300 font-medium rounded-lg text-sm inline-flex items-center px-5 py-2.5 text-center" onClick={() => navigate("/passanger/myRides")}>
                    My Rides
                  </button>
                  <button type="button" className="py-2.5 px-5 ms-3 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100" onClick={() => navigate("/")}>
                    Home
                  </button>
                </div>
              </div>
            </div>
          </div>
        )}
      </main>
    </Layout>
  );
};

export default BookRide;
