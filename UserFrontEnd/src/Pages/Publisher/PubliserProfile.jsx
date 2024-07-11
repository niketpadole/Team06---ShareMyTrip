import React, { useEffect, useState } from 'react';
import Layout from '../../Components/Layouts/Layout';
import axios from 'axios';
import { useAuth } from '../../context/auth';
import toast from 'react-hot-toast';

const PublisherProfile = () => {
  const [auth, setAuth] = useAuth();
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [mobile, setMobile] = useState('');
  const [dateOfBirth, setDateOfBirth] = useState('');
  const [drivingLicense, setDrivingLicense] = useState('');
  const [aadharCard, setAadharCard] = useState('');
  const [miniBio, setMiniBio] = useState('');
  const [vehicleModelName, setVehicleModelName] = useState('');
  const [vehicleNumber, setVehicleNumber] = useState('');
  const [errors, setErrors] = useState({});

  const fetchProfileData = async (publisherId) => {
    try {
      const response = await axios.get(`https://api.sharemytrip.xyz/user/publishers/${publisherId}`);
      const profile = response.data;

      // Updating state with fetched data
      setFirstName(profile.firstName || '');
      setLastName(profile.lastName || '');
      setEmail(profile.email || '');
      setMobile(profile.mobile || '');
      setDateOfBirth(profile.dateOfBirth || '');
      setDrivingLicense(profile.drivingLicense || '');
      setAadharCard(profile.aadharCard || '');
      setMiniBio(profile.miniBio || '');
      setVehicleModelName(profile.vehicleModelName || '');
      setVehicleNumber(profile.vehicleNo || '');
    } catch (error) {
      console.error('Error fetching profile data:', error);
    }
  };

  useEffect(() => {
    if (auth.id) fetchProfileData(auth.id);
  }, [auth.id]);

  const validate = () => {
    let tempErrors = {};

    if (!firstName) {
      tempErrors.firstName = 'First Name is required';
    } else if (!/^[a-zA-Z]+$/.test(firstName)) {
      tempErrors.firstName = 'First Name must be a string';
    }

    if (!lastName) {
      tempErrors.lastName = 'Last Name is required';
    } else if (!/^[a-zA-Z]+$/.test(lastName)) {
      tempErrors.lastName = 'Last Name must be a string';
    }

    if (!mobile) {
      tempErrors.mobile = 'Mobile is required';
    } else if (!/^[789]\d{9}$/.test(mobile)) {
      tempErrors.mobile = 'Mobile must be 10 digits and start with 7, 8, or 9';
    }

    if (!vehicleModelName) {
      tempErrors.vehicleModelName = 'Vehicle Model Name is required';
    } else if (!/^[a-zA-Z\s]+$/.test(vehicleModelName)) {
      tempErrors.vehicleModelName = 'Vehicle Model Name must be a string';
    }

    if (!vehicleNumber) {
      tempErrors.vehicleNumber = 'Vehicle Number is required';
    } else if (!/^[A-Z]{2}\d{2}[A-Z]{2}\d{4}$/.test(vehicleNumber)) {
      tempErrors.vehicleNumber = 'Vehicle Number must be in the format MH56BY2345';
    }

    setErrors(tempErrors);
    return Object.keys(tempErrors).length === 0;
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (!validate()) return;

    const updatedProfile = {
      firstName: firstName,
      lastName: lastName,
      email: email,
      mobile: mobile,
      dateOfBirth: dateOfBirth,
      aadharCard: aadharCard,
      drivingLicense: drivingLicense,
      miniBio: miniBio,
      vehicleModelName: vehicleModelName,
      vehicleNo: vehicleNumber,
    };

    try {
      const response = await axios.put(`https://api.sharemytrip.xyz/user/publishers/${auth.id}`, updatedProfile);
      console.log('Profile updated successfully:', response.data);
      if (response.status === 200) {
        toast.success("Profile Updated Successfully");
      } else {
        toast.error("Profile Updation Failed");
      }
    } catch (error) {
      console.error('Error updating profile:', error);
    }
  };

  return (
    <Layout>
      <main className="mx-auto py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">Welcome {`${auth.firstName} ${auth.lastName}`}</h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-lg mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Profile Information</h2>
          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label htmlFor="first_name" className="block text-gray-700 mb-2">First Name</label>
              <input
                type="text"
                id="first_name"
                name="first_name"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
                required
              />
              {errors.firstName && <p className="text-red-500 text-sm">{errors.firstName}</p>}
            </div>
            <div className="mb-4">
              <label htmlFor="last_name" className="block text-gray-700 mb-2">Last Name</label>
              <input
                type="text"
                id="last_name"
                name="last_name"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
                required
              />
              {errors.lastName && <p className="text-red-500 text-sm">{errors.lastName}</p>}
            </div>
            <div className="mb-4">
              <label htmlFor="email" className="block text-gray-700 mb-2">Email</label>
              <input
                type="email"
                id="email"
                name="email"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                readOnly
              />
            </div>
            <div className="mb-4">
              <label htmlFor="mobile" className="block text-gray-700 mb-2">Mobile</label>
              <input
                type="tel"
                id="mobile"
                name="mobile"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={mobile}
                onChange={(e) => setMobile(e.target.value)}
                required
              />
              {errors.mobile && <p className="text-red-500 text-sm">{errors.mobile}</p>}
            </div>
            <div className="mb-4">
              <label htmlFor="date_of_birth" className="block text-gray-700 mb-2">Date of Birth</label>
              <input
                type="date"
                id="date_of_birth"
                name="date_of_birth"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={dateOfBirth}
                onChange={(e) => setDateOfBirth(e.target.value)}
                readOnly
              />
            </div>
            <div className="mb-4">
              <label htmlFor="driving_license" className="block text-gray-700 mb-2">Driving License</label>
              <input
                type="text"
                id="driving_license"
                name="driving_license"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={drivingLicense}
                onChange={(e) => setDrivingLicense(e.target.value)}
                readOnly
              />
            </div>
            <div className="mb-4">
              <label htmlFor="aadhar_card" className="block text-gray-700 mb-2">Aadhar Card</label>
              <input
                type="text"
                id="aadhar_card"
                name="aadhar_card"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={aadharCard}
                onChange={(e) => setAadharCard(e.target.value)}
                readOnly
              />
            </div>
            <div className="mb-4">
              <label htmlFor="mini_bio" className="block text-gray-700 mb-2">Mini Bio</label>
              <textarea
                id="mini_bio"
                name="mini_bio"
                rows="4"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={miniBio}
                onChange={(e) => setMiniBio(e.target.value)}
              />
            </div>
            <div className="mb-4">
              <label htmlFor="vehicle_model_name" className="block text-gray-700 mb-2">Vehicle Model Name</label>
              <input
                type="text"
                id="vehicle_model_name"
                name="vehicle_model_name"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={vehicleModelName}
                onChange={(e) => setVehicleModelName(e.target.value)}
                required
              />
              {errors.vehicleModelName && <p className="text-red-500 text-sm">{errors.vehicleModelName}</p>}
            </div>
            <div className="mb-6">
              <label htmlFor="vehicle_no" className="block text-gray-700 mb-2">Vehicle Number</label>
              <input
                type="text"
                id="vehicle_no"
                name="vehicle_no"
                className="w-full p-2 border border-gray-300 rounded-md"
                value={vehicleNumber}
                onChange={(e) => setVehicleNumber(e.target.value)}
                required
              />
              {errors.vehicleNumber && <p className="text-red-500 text-sm">{errors.vehicleNumber}</p>}
            </div>
            <button type="submit" className="w-full bg-red-600 text-white p-2 rounded-md hover:bg-red-700">Update Profile</button>
          </form>
        </section>
      </main>
    </Layout>
  );
};

export default PublisherProfile;
