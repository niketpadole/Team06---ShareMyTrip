import React, { useEffect, useState } from "react";

const Spinner = ({ countdown }) => {
  const initialCount = parseInt(sessionStorage.getItem("countdown")) || countdown;
  const [count, setCount] = useState(initialCount);

  useEffect(() => {
    sessionStorage.setItem("countdown", count);

    const timer = setInterval(() => {
      setCount((prevCount) => {
        const newCount = prevCount - 1;
        sessionStorage.setItem("countdown", newCount);
        return newCount;
      });
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  useEffect(() => {
    if (count === 0) {
      sessionStorage.removeItem("countdown");
      window.location.href = "/";
    }
  }, [count]);

  return (
    <div>
      <div className="absolute right-1/2 bottom-1/2 transform translate-x-1/2 translate-y-1/2">
        <div className="border-t-transparent border-solid animate-spin rounded-full border-blue-400 border-8 h-64 w-64" />
        <p className="text-blue-400 m-auto text-2xl mt-16">Redirecting You In {count} Sec.....</p>
      </div>
    </div>
  );
};

export default Spinner;