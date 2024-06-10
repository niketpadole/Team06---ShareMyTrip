import React from "react"
import Home from "./Pages/Home"
import About from "./Pages/About"
import Login from "./Pages/Login"
import { Route, Routes } from "react-router-dom"
import PageNotFound from "./Pages/PageNotFound"
import PassangerRegister from "./Pages/PassangerRegister"
import PublisherRegister from "./Pages/PublisherRegister"
import Services from "./Pages/Services"
import Testinomial from "./Pages/Testinomial"
import UserDashBoard from "./Pages/UserDashBoard"


function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/about" element={<About/>}/>
        <Route path="/services" element={<Services/>}/>
        <Route path="/testimonials" element={<Testinomial/>}/>
        <Route path="/log-in" element={<Login/>}/>
        <Route path="/register/passanger" element={<PassangerRegister/>}/>
        <Route path="/userDashboard" element={<UserDashBoard/>}/>
        <Route path="/register/publisher" element={<PublisherRegister/>}/>
        <Route path='/*' element={<PageNotFound/>} />
      </Routes>
    </>
  )
}

export default App
