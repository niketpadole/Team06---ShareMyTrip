import axios from "axios";
import { useState, useEffect, useContext, createContext } from "react";

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState({
    id: null,
    firstName: "",
    lastName: "",
    email: "",
    mobile: "",
    dateOfBirth: "",
    userType: "",
    totalEarnings:"",
    token: "",
  });

   //default axios
  //  axios.defaults.headers.common["Authorization"] = auth?.token;
  useEffect(() => {
    const data = localStorage.getItem("auth");
    if (data) {
      const parsedData = JSON.parse(data);
      setAuth({
        id: parsedData.id,
        firstName: parsedData.firstName,
        lastName: parsedData.lastName,
        email: parsedData.email,
        mobile: parsedData.mobile,
        dateOfBirth: parsedData.dateOfBirth,
        userType: parsedData.userType,
        totalEarnings:parsedData.totalEarnings,
        token: parsedData.token,
      });
      axios.defaults.headers.common["Authorization"] = parsedData.token;
    }
  }, []);



  return (
    <AuthContext.Provider value={[auth, setAuth]}>
      {children}
    </AuthContext.Provider>
  );
};

const useAuth = () => useContext(AuthContext);

export { useAuth, AuthProvider };
