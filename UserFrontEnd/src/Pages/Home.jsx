import React from "react";
import Layout from "../Components/Layouts/Layout";
import About from "../Pages/About"
import MainContent from "../Pages/MainContent";
import { useAuth } from "../context/auth";
import { useRides } from "../context/RideDetails";
const Home = () => {
  const [auth,setAuth] = useAuth();
  const [ridesDetail, setRideDetail] = useRides();
  return (
    <Layout>
      {/* <p>{JSON.stringify(auth)}</p>
      <p>{JSON.stringify(ridesDetail)}</p> */}
      <MainContent/>
    </Layout>
  );
};

export default Home;
