import React, { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { useAuth } from "../../Context/auth";

const Header = () => {
  const [auth, setAuth] = useAuth();
  const [dropDown, setDropDown] = useState(false);
  const navigate = useNavigate();

  const toggleDropDown = () => {
    setDropDown(!dropDown);
  };

  const handleLogout = () => {
    setAuth({});
    localStorage.removeItem("auth");
    navigate("/");
  };

  return (
    <>
      <header className="bg-[#ff6f61]">
        <nav className="container mx-auto px-6 py-3">
          <div className="flex items-center justify-between">
            <div className="text-white font-bold text-xl">
              <img
                src="https://s3.amazonaws.com/niket.in.net/shareMyTrip/ShareMyTrip+Logo.png"
                className="h-20 mr-8 hover:scale-150"
              />
            </div>
            <div className="hidden md:block">
              <ul className="flex items-center space-x-8">
                {auth.token ? (
                  <>
                    <li>
                      <NavLink
                        to="/admin/dashboard"
                        className="text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                      >
                        Home
                      </NavLink>
                    </li>
                    <li>
                      <button
                        onClick={handleLogout}
                        className="text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                      >
                        Logout
                      </button>
                    </li>
                  </>
                ) : (
                  <li>
                    <NavLink
                      to="/"
                      className="text-white text-lg hover:text-[#6f61ff] active:text-[#6f61ff]"
                    >
                      Login
                    </NavLink>
                  </li>
                )}
              </ul>
            </div>
            <div className="md:hidden">
              <button
                className="outline-none mobile-menu-button"
                onClick={toggleDropDown}
              >
                <svg
                  className="w-6 h-6 text-white"
                  x-show="!showMenu"
                  fill="none"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth={2}
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path d="M4 6h16M4 12h16M4 18h16" />
                </svg>
              </button>
            </div>
          </div>
          <div
            className={`mobile-menu ${dropDown ? "block" : "hidden"} md:hidden`}
          >
            <ul className="mt-4 space-y-4">
              {auth.token ? (
                <>
                  <li>
                    <NavLink
                      to="/admin/dashboard"
                      className="block px-4 py-2 text-white hover:text-[#6f61ff] active:text-[#6f61ff]"
                    >
                      Home
                    </NavLink>
                  </li>
                  <li>
                    <button
                      onClick={handleLogout}
                      className="block px-4 py-2 text-white hover:text-[#6f61ff] active:text-[#6f61ff]"
                    >
                      Logout
                    </button>
                  </li>
                </>
              ) : (
                <li>
                  <NavLink
                    to="/login"
                    className="block px-4 py-2 text-white hover:text-[#6f61ff] active:text-[#6f61ff]"
                  >
                    Login
                  </NavLink>
                </li>
              )}
            </ul>
          </div>
        </nav>
      </header>
    </>
  );
};

export default Header;
