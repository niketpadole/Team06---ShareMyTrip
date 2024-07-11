import React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "./Pages/Home";
import Dashboard from "./Pages/Dashboard";
import AllCustomer from "./Pages/AllCustomer";
import AllPublisher from "./Pages/AllPublisher";
import AllPasangers from "./Pages/AllPasangers";
import ApprovedTrip from "./Pages/ApprovedTrips";
import RejectTrip from "./Pages/RejectTrip";
import Review from "./Pages/Review";
import Payment from "./Pages/Payment";
import AdminPrivateRoute from "./Routes/AdminPrivateRoute"
import TermsOfUse from "./Pages/TermsOfUse";
import PrivacyPolicy from "./Pages/PrivacyPolicy";

const App = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/terms-of-use" element={<TermsOfUse/>}/>
        <Route path="/privacy-policy" element={<PrivacyPolicy/>}/>
        <Route path="/admin" element={<AdminPrivateRoute/>}>
          <Route path="dashboard" element={<Dashboard />} />
          <Route path="all-customers" element={<AllCustomer />} />
          <Route path="all-publishers" element={<AllPublisher />} />
          <Route path="all-passangers" element={<AllPasangers />} />
          <Route path="approved-trips" element={<ApprovedTrip />} />
          <Route path="reject-trips" element={<RejectTrip />} />
          <Route path="review" element={<Review />} />
          <Route path="payment" element={<Payment />} />
        </Route>
      </Routes>
    </>
  );
};

export default App;
