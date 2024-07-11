import React, { useState, useEffect } from "react";
import axios from "axios";
import Layout from "../../Components/Layouts/Layout";
import { useAuth } from "../../context/auth";
import { useRides } from "../../context/RideDetails";
import toast from "react-hot-toast";

const MyRides = () => {
  const [auth, setAuth] = useAuth();
  const [ridesDetail, setRideDetail] = useRides();
  const [rides, setRides] = useState([]);
  const [filteredRides, setFilteredRides] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selectedRide, setSelectedRide] = useState(null);
  const [isCancelModalOpen, setIsCancelModalOpen] = useState(false);
  const [isPaymentModalOpen, setIsPaymentModalOpen] = useState(false);
  const [paymentLink, setPaymentLink] = useState("");
  const [filterStatus, setFilterStatus] = useState("ALL");

  // Pagination states
  const [currentPage, setCurrentPage] = useState(1);
  const ridesPerPage = 6;

  const onCancel = async () => {
    try {
      const response = await axios.delete(
        `https://api.sharemytrip.xyz/user/passengers/cancel/${selectedRide.passengerRideId}`
      );
      if (response.status === 204) {
        sendPublisherEmail(selectedRide.publisherEmail);
        await axios.post(
          `https://api.sharemytrip.xyz/email/send-passenger-canceled-notification?passengerEmail=${encodeURIComponent(
            auth.email
          )}`
        );
        toast.success("Ride Canceled Successfully");
        fetchRides();
      } else {
        toast.error("Something Went Wrong");
      }
      setIsCancelModalOpen(false);
    } catch (error) {
      console.log(error);
      toast.error("An error occurred");
    }
  };

  const onPayNow = async (ride) => {
    const payload = {
      orderId: `order_${ride.publisherRideId}-${ride.passengerRideId}`,
      timestamp: new Date().toISOString(),
      passengerId: auth.id,
      passengerRideId: ride.passengerRideId,
      publisherId: ride.publisherId,
      publisherRideId: ride.publisherRideId,
      publisherName: ride.publisherName,
      publisherMobile: ride.publisherMobile,
      fromLocation: ride.fromLocation,
      toLocation: ride.toLocation,
      dateOfJourney: ride.date,
      noOfSeats: ride.passengerCount,
      date: new Date().toISOString().split("T")[0],
      departureTime: ride.departureTime,
      fare: parseInt(ride.fare * ride.passengerCount),
      distance: ride.distance,
      journeyHours: ride.journeyHours,
      publisherStatus: ride.publisherStatus,
      passengerName: ride.passengerName,
      passengerEmail: auth.email,
      publisherPaymentStatus: "PENDING",
      passengerPaymentStatus: ride.passengerPaymentStatus,
      passengerMobile: ride.passengerMobile,
      passengerCount: ride.passengerCount,
    };

    try {
      const response = await axios.post(
        "https://api.sharemytrip.xyz/user/passengers/pay",
        payload
      );
      if (response.status === 200) {
        console.log("Payment link received:", response.data);
        setPaymentLink(response.data);
        setIsPaymentModalOpen(true);
        localStorage.setItem("rides", JSON.stringify(payload)); // store in localStorage
        setRideDetail([...ridesDetail, ride]); // store in rideDetail Context
      } else {
        toast.error("Payment Failed");
      }
    } catch (error) {
      console.error("Error processing payment:", error);
      // toast.error("An error occurred during payment");
    }
  };

  const fetchRides = async () => {
    try {
      const response = await axios.get(
        `https://api.sharemytrip.xyz/user/passengers/${auth.id}/rides`
      );
      console.log("Response from API:", response.data);
      setRides(response.data);
      setFilteredRides(response.data);
    } catch (error) {
      console.error("Error fetching rides:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchRides();
  }, [auth.id]);

  const handleFilterChange = (status) => {
    setFilterStatus(status);
    if (status === "ALL") {
      setFilteredRides(rides);
    } else {
      setFilteredRides(rides.filter((ride) => ride.publisherStatus === status));
    }
  };

  const handlePaymentConfirmation = () => {
    setIsPaymentModalOpen(false);
    if (paymentLink) {
      window.location.href = paymentLink;
    } else {
      toast.error("Invalid payment link");
    }
  };

  // Pagination logic
  const indexOfLastRide = currentPage * ridesPerPage;
  const indexOfFirstRide = indexOfLastRide - ridesPerPage;
  const currentRides = filteredRides.slice(indexOfFirstRide, indexOfLastRide);
  const totalPages = Math.ceil(filteredRides.length / ridesPerPage);

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const sendPublisherEmail = async(publisherEmail) =>{
    try {
      axios.post(`https://api.sharemytrip.xyz/email/send-pasenger-canceled-notification-publisher?publisherEmail=${encodeURIComponent(
        publisherEmail)}`)
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">My Rides</h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Booked Rides</h2>
          <div className="mb-4">
            <label className="mr-4">Filter by status:</label>
            <select
              value={filterStatus}
              onChange={(e) => handleFilterChange(e.target.value)}
              className="text-gray-700 bg-white border border-gray-300 rounded-md"
            >
              <option value="ALL">All</option>
              <option value="ONGOING" selected>
                Ongoing
              </option>
              <option value="COMPLETED">Completed</option>
              <option value="NOT_COMPLETED">Not Completed</option>
            </select>
          </div>
          {loading ? (
            <p className="text-center">Loading...</p>
          ) : currentRides.length > 0 ? (
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
              {currentRides.map((ride, index) => (
                <div key={index} className="bg-white rounded-lg shadow-md p-6">
                  <h3 className="text-xl font-bold text-red-600 mb-2">
                    {ride.publisherName}
                  </h3>
                  <p className="text-gray-600">
                    <strong>Mobile:</strong> {ride.publisherMobile}
                  </p>
                  <p className="text-gray-600">
                    <strong>From:</strong> {ride.fromLocation}
                  </p>
                  <p className="text-gray-600">
                    <strong>To:</strong> {ride.toLocation}
                  </p>
                  <p className="text-gray-600">
                    <strong>Date:</strong> {ride.date}
                  </p>
                  <p className="text-gray-600">
                    <strong>Time:</strong> {ride.departureTime}
                  </p>
                  <p className="text-gray-600">
                    <strong>Seats:</strong> {ride.passengerCount}
                  </p>
                  <p className="text-gray-600">
                    <strong>Fare:</strong> Rs.{ride.fare * ride.passengerCount}
                  </p>
                  <p className="text-gray-600">
                    <strong>Distance:</strong> {ride.distance} km
                  </p>
                  <p className="text-gray-600">
                    <strong>Journey Hours:</strong> {ride.journeyHours} hrs
                  </p>
                  <p className="text-gray-600">
                    <strong>Ride Status:</strong> {ride.publisherStatus}
                  </p>
                  <p className="text-gray-600">
                    <strong>Payment Status:</strong>{" "}
                    {ride.passengerPaymentStatus}
                  </p>

                  {ride.publisherStatus === "NOT_COMPLETED" && (
                    <button
                      className="mt-4 text-red-500 hover:text-red-700"
                      onClick={() => {
                        setSelectedRide(ride);
                        setIsCancelModalOpen(true);
                      }}
                    >
                      Cancel
                    </button>
                  )}

                  {ride.publisherStatus === "COMPLETED" &&
                    ride.passengerPaymentStatus !== "PAID" && (
                      <button
                        className="mt-4 text-green-500 hover:text-green-700 ml-4"
                        onClick={() => onPayNow(ride)}
                      >
                        Pay Now
                      </button>
                    )}

                  {ride.publisherStatus === "COMPLETED" &&
                    ride.passengerPaymentStatus === "PAID" && (
                      <p className="text-green-600 font-bold mt-4">
                        Payment Successful
                      </p>
                    )}
                </div>
              ))}
            </div>
          ) : (
            <p className="text-center text-red-600">No Rides Booked</p>
          )}

          {/* Pagination Controls */}
          <div className="mt-8 flex justify-center">
            {Array.from({ length: totalPages }, (_, index) => (
              <button
                key={index}
                className={`mx-1 px-3 py-1 rounded-md ${
                  currentPage === index + 1
                    ? "bg-red-600 text-white"
                    : "bg-white text-gray-700 border border-gray-300"
                }`}
                onClick={() => handlePageChange(index + 1)}
              >
                {index + 1}
              </button>
            ))}
          </div>
        </section>
      </main>

      {/* Cancel Modal */}
      {isCancelModalOpen && (
        <div
          id="cancel-modal"
          className="fixed top-0 right-0 left-0 z-50 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50"
        >
          <div className="relative p-4 bg-white rounded-lg shadow-md max-w-md w-full">
            <button
              type="button"
              className="absolute top-3 right-3 text-gray-400 bg-transparent hover:bg-gray-200 rounded-lg p-1"
              onClick={() => setIsCancelModalOpen(false)}
            >
              <svg
                className="w-5 h-5"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M6 18L18 6M6 6l12 12"
                />
              </svg>
            </button>
            <div className="text-center">
              <svg
                className="mx-auto mb-4 text-gray-400 w-12 h-12"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M6 18L18 6M6 6l12 12"
                />
              </svg>
              <h3 className="mb-4 text-lg font-semibold text-gray-700">
                Are you sure you want to cancel this ride?
              </h3>
              <div className="flex justify-center space-x-4">
                <button
                  type="button"
                  className="px-4 py-2 text-white bg-red-600 rounded-lg hover:bg-red-700"
                  onClick={onCancel}
                >
                  Yes, I'm sure
                </button>
                <button
                  type="button"
                  className="px-4 py-2 text-gray-600 bg-gray-200 rounded-lg hover:bg-gray-300"
                  onClick={() => setIsCancelModalOpen(false)}
                >
                  No, cancel
                </button>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* Payment Modal */}
      {isPaymentModalOpen && (
        <div
          id="payment-modal"
          className="fixed top-0 right-0 left-0 z-50 flex items-center justify-center w-full h-full bg-gray-900 bg-opacity-50"
        >
          <div className="relative p-4 bg-white rounded-lg shadow-md max-w-md w-full">
            <button
              type="button"
              className="absolute top-3 right-3 text-gray-400 bg-transparent hover:bg-gray-200 rounded-lg p-1"
              onClick={() => setIsPaymentModalOpen(false)}
            >
              <svg
                className="w-5 h-5"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M6 18L18 6M6 6l12 12"
                />
              </svg>
            </button>
            <div className="text-center">
              <svg
                className="mx-auto mb-4 text-gray-400 w-12 h-12"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M6 18L18 6M6 6l12 12"
                />
              </svg>
              <h3 className="mb-4 text-lg font-semibold text-gray-700">
                Are you sure you want to proceed with the payment?
              </h3>
              <div className="flex justify-center space-x-4">
                <button
                  type="button"
                  className="px-4 py-2 text-white bg-green-600 rounded-lg hover:bg-green-700"
                  onClick={handlePaymentConfirmation}
                >
                  Yes, proceed
                </button>
                <button
                  type="button"
                  className="px-4 py-2 text-gray-600 bg-gray-200 rounded-lg hover:bg-gray-300"
                  onClick={() => setIsPaymentModalOpen(false)}
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

export default MyRides;
