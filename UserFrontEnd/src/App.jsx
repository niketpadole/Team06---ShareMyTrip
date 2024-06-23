import React from "react"
import Home from "./Pages/Home"
import About from "./Pages/About"
import PassangerLogin from "./Pages/PassangerLogin"
import PublisherLogin from "./Pages/PublisherLogin"
import { Route, Routes } from "react-router-dom"
import PageNotFound from "./Pages/PageNotFound"
import PassangerRegister from "./Pages/PassangerRegister"
import PublisherRegister from "./Pages/PublisherRegister"
import Services from "./Pages/Services"
import Testinomial from "./Pages/Testinomial"
import ForgotPassword from "./Pages/ForgotPasswordPassanger"
import ForgotPasswordPublisher from "./Pages/ForgotPasswordPublisher"
import CreateRide from "./Pages/Publisher/CreateRide"
import BookRide from "./Pages/Passanger/BookRide"
import PubliserProfile from "../src/Pages/Publisher/PubliserProfile"
import PassangerProfile from "../src/Pages/Passanger/PassangerProfile"
import PublisherDashboard from "./Pages/Publisher/Dashboard"
import PassangerDashboard from "./Pages/Passanger/Dashboard"
import PublisherMyRides from "./Pages/Publisher/MyRides"
import PassangerMyRides from "./Pages/Passanger/MyRides"
import PassangerTransaction from "./Pages/Passanger/ViewAllTransaction";
import PublisherTransaction from "./Pages/Publisher/ViewAllTrasaction";
import PublisherRoute from "./Routes/PublisherRoute"
import PassangerRoute from "./Routes/PassangerRoute"
import PrivacyPolicy from "./Pages/PrivacyPolicy"
import TermsOfUse from "./Pages/TermsOfUse"
import ContactUs from "./Pages/ContactUs"
import PaymentSucess from "./Pages/PaymentSucess"


function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/about" element={<About/>}/>
        <Route path="/services" element={<Services/>}/>
        <Route path="/testimonials" element={<Testinomial/>}/>
        <Route path="/log-in/passanger" element={<PassangerLogin/>}/>
        <Route path="/log-in/publisher" element={<PublisherLogin/>}/>
        <Route path="/register/passanger" element={<PassangerRegister/>}/>
        <Route path="/register/publisher" element={<PublisherRegister/>}/>
        <Route path="/reset-password/passanger" element={<ForgotPassword/>}/>
        <Route path="/reset-password/publisher" element={<ForgotPasswordPublisher/>}/>
        <Route path="/PrivacyPolicy" element={<PrivacyPolicy/>}/>
        <Route path="/TermsOfUse" element={<TermsOfUse/>}/>
        <Route path="/contactUs" element={<ContactUs/>}/>
        <Route path="/paymentSucess" element={<PaymentSucess/>}/>
        {/* Passanger Private Route */}
        <Route path="/passanger" element={<PassangerRoute/>}>
        <Route path="dashboard" element={<PassangerDashboard/>}/>
        <Route path="profile" element={<PassangerProfile/>}/>
        <Route path="myRides" element={<PassangerMyRides/>}/>
        <Route path="book-ride" element={<BookRide/>}/>
        <Route path="transactions" element={<PassangerTransaction/>}/>
        </Route>
        {/* Publisher Private Route */}
        <Route path="/publisher" element={<PublisherRoute/>}>
        <Route path="profile" element={<PubliserProfile/>}/>
        <Route path="create-ride" element={<CreateRide/>}/>
        <Route path="dashboard" element={<PublisherDashboard/>}/>
        <Route path="myRides" element={<PublisherMyRides/>}/>
        <Route path="transactions" element={<PublisherTransaction/>}/>
        </Route>
        <Route path='/*' element={<PageNotFound/>} />
      </Routes>
    </>
  )
}

export default App
