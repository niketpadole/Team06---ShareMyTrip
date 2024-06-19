import React from "react";
import About from "../Pages/About";
import Services from "../Pages/Services";
import Testinomial from "../Pages/Testinomial";
import Content from "../Pages/Content";

const MainContent = () => {
  return (
    <>
      <Content/>
      <About />
      <Services />
      <Testinomial />
    </>
  );
};

export default MainContent;
