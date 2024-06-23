import React from "react";
import Layout from "../Components/Layouts/Layout";
import About from "../Pages/About"
import MainContent from "../Pages/MainContent";
import { useAuth } from "../context/auth";
const Home = () => {
  const [auth,setAuth] = useAuth();
  return (
    <Layout>
      <p>{JSON.stringify(auth)}</p>
      <MainContent/>
    </Layout>
  );
};

export default Home;
