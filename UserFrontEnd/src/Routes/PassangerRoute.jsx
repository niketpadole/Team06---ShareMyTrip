import React, { useEffect, useState } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/auth';
import Spinner from '../Components/UI/Spinner';

const PassangerRoute = () => {
  const [auth, setAuth] = useAuth();
  const navigate = useNavigate();
  // const [loading, setLoading] = useState(true);

  useEffect(() => {
    const checkAuth = () => {
      if (auth.token == null && auth.userType.toUpperCase() !== "PASSENGER") {
        navigate("/log-in/passanger");
        // setLoading(true);
        // setTimeout(() => {
        // }, 5000);
      }
    };

    checkAuth();
  }, [auth]);

  // if (loading) {
  //   return <Spinner countdown={5} />;
  // }

  return <Outlet />;
};

export default PassangerRoute;
