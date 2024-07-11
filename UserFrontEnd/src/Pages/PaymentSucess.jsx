import React, { useEffect } from "react";
import toast from "react-hot-toast";
import { useNavigate, useSearchParams } from "react-router-dom";
import { useAuth } from "../context/auth";
import axios from "axios";
import { useRides } from "../context/RideDetails";

const PaymentSuccess = () => {
  const [auth, setAuth] = useAuth();
  const [ridesDetail, setRideDetail] = useRides();
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();

  const paymentId = searchParams.get("razorpay_payment_id");

  const onStatusChange = async () => {
    try {
      //localStorage.removeItem('rides');
      // const response = await axios.put(
      //   `http://13.201.203.99:8090/rides/updatePaymentStatus`,
      //   {
      //     passengerRideId: ridesDetail.passengerRideId,
      //     paymentStatus: "PAID",
      // const response = await axios.put(
      //   `http://13.201.203.99:8090/rides/passenger/${ridesDetail.passengerRideId}/paid`
      // );
      const response= await axios.put("https://api.sharemytrip.xyz/user/publishers/update-earnings",{
        publisherId:ridesDetail.publisherId,
        passengerId:ridesDetail.passengerId,
        publisherName:ridesDetail.publisherName,
        passengerName:ridesDetail.passengerName,
        publisherMobile:ridesDetail.publisherMobile,
        fromLocation:ridesDetail.fromLocation,
        toLocation:ridesDetail.toLocation,
        noOfSeats:ridesDetail.noOfSeats,
        date:ridesDetail.date,
        departureTime:ridesDetail.departureTime,
        fare:ridesDetail.fare,
        distance:ridesDetail.distance,
        journeyHours:ridesDetail.journeyHours,
        publisherStatus:ridesDetail.publisherStatus,
        publisherRideId:ridesDetail.publisherRideId,
        passengerRideId:ridesDetail.passengerRideId,
        passengerCount:ridesDetail.passengerCount,
        publisherPaymentStatus:ridesDetail.publisherPaymentStatus,
        passengerPaymentStatus:ridesDetail.passengerPaymentStatus,
        passengerMobile:ridesDetail.passengerMobile,
      })
      
      if (response.status == 200) {
        toast.success("Payment Successful");
        setRideDetail({});
        localStorage.removeItem("rides");
        navigate("/");
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100 p-6">
        <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
          <h1 className="text-2xl font-semibold text-center text-green-600 mb-4">
            Payment Successful
          </h1>
          <p className="text-center text-gray-700 mb-6">
            Payment ID: <span className="font-mono">{paymentId}</span>
          </p>
          <button
            onClick={onStatusChange}
            className="w-full py-2 px-4 bg-[#ff6f61] text-white rounded  transition duration-300"
          >
            Go to Home
          </button>
        </div>
      </div>
    </>
  );
};

export default PaymentSuccess;
