import React, { useEffect, useState } from "react";

const Spinner = ({ countdown }) => {
  const [count, setCount] = useState(countdown);

  useEffect(() => {
    const timer = setInterval(() => {
      setCount((prevCount) => prevCount - 1);
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  useEffect(() => {
    if (count === 0) {
      window.location.href = "/log-in/passanger";
    }
  }, [count]);

  return (
    <div>
      <div className="absolute right-1/2 bottom-1/2  transform translate-x-1/2 translate-y-1/2 ">
        <div className="border-t-transparent border-solid animate-spin  rounded-full border-blue-400 border-8 h-64 w-64" />
        <p className="text-blue-400 m-auto text-2xl mt-16">Redirecting You In {count} Sec.....</p>
      </div>
    </div>
  );
};

export default Spinner;
