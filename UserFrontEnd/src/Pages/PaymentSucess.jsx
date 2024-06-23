import React, { useEffect } from 'react';
import toast from 'react-hot-toast';
import { useNavigate, useSearchParams } from 'react-router-dom';
import Layout from '../Components/Layouts/Layout';

const PaymentSuccess = () => {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();

  const paymentId = searchParams.get('razorpay_payment_id');

  return (
    <Layout>
      <h1>Payment Successful</h1>
      <p>Payment ID: {paymentId}</p>
      <button 
        onClick={() => {toast.success("Payment SucessFull");navigate("/")}}
        className="mt-4 px-4 py-2 bg-blue-600 text-white rounded"
      >
        Go to Home
      </button>
    </Layout>
  );
}

export default PaymentSuccess;
