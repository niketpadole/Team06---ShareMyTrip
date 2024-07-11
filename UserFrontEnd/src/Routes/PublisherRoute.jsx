import React, { useEffect, useState } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/auth';
import Spinner from '../Components/UI/Spinner';

const PublisherRoute = () => {
  const [auth, setAuth] = useAuth();
  const navigate = useNavigate();
  // const [loading, setLoading] = useState(true);

  useEffect(() => {
    const checkAuth = () => {
      if (auth.token == null && auth.userType.toUpperCase() !== "PUBLISHER") {
        navigate("/log-in/publisher");
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

export default PublisherRoute;
