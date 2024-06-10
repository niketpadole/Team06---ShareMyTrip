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
import CreateRide from "./Pages/CreateRide"
import BookRide from "./Pages/BookRide"
import PubliserProfile from "./Pages/PubliserProfile"
import PassangerProfile from "./Pages/PassangerProfile"
import PublisherDashboard from "./Pages/Publisher/Dashboard"
import PassangerDashboard from "./Pages/Passanger/Dashboard"
import PublisherMyRides from "./Pages/Publisher/MyRides"
import PassangerMyRides from "./Pages/Passanger/MyRides"
import PassangerTransaction from "./Pages/Passanger/ViewAllTransaction";
import PublisherTransaction from "./Pages/Publisher/ViewAllTrasaction";


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
        {/* <Route path="/userDashboard" element={<UserDashBoard/>}/> */}
        <Route path="/register/publisher" element={<PublisherRegister/>}/>
        <Route path="/reset-password/passanger" element={<ForgotPassword/>}/>
        <Route path="/reset-password/publisher" element={<ForgotPasswordPublisher/>}/>
        <Route path="/create-ride" element={<CreateRide/>}/>
        <Route path="/book-ride" element={<BookRide/>}/>
        <Route path="/publisher-profile" element={<PubliserProfile/>}/>
        <Route path="/passanger-profile" element={<PassangerProfile/>}/>
        <Route path="/publisher-dashboard" element={<PublisherDashboard/>}/>
        <Route path="/publisher-myRides" element={<PublisherMyRides/>}/>
        <Route path="/publisher-transactions" element={<PublisherTransaction/>}/>
        <Route path="/passanger-dashboard" element={<PassangerDashboard/>}/>
        <Route path="/passanger-myRides" element={<PassangerMyRides/>}/>
        <Route path="/passanger-transactions" element={<PassangerTransaction/>}/>
        <Route path='/*' element={<PageNotFound/>} />
      </Routes>
    </>
  )
}

export default App
