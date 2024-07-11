import { createContext, useContext, useEffect, useState } from "react";
 
const RideContext = createContext();
 
const RideProvider = ({ children }) => {
  const [ridesDetail, setRideDetail] = useState([]);
 
  useEffect(() => {
    let existingRideDetails = localStorage.getItem('rides');
    if (existingRideDetails) {
      setRideDetail(JSON.parse(existingRideDetails));
    }
  }, []);
 
  return (
    <RideContext.Provider value={[ridesDetail, setRideDetail]}>
      {children}
    </RideContext.Provider>
  );
};
 
// Custom Hook
const useRides = () => useContext(RideContext);
 
export { useRides, RideProvider };