import React, { useEffect, useState } from 'react';
import Layout from '../Components/Layouts/Layout';

const PassengerProfile = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [mobile, setMobile] = useState('');
  const [dateOfBirth, setDateOfBirth] = useState('');
  const [aadharCard, setAadharCard] = useState('');
  const [miniBio, setMiniBio] = useState('');

  const fetchProfileData = async () => {
    try {
      // Simulating fetching profile data
      const profile = {
        firstName: 'John',
        lastName: 'Doe',
        email: 'john.doe@example.com',
        mobile: '1234567890',
        dateOfBirth: '1990-01-01',
        aadharCard: '1234-5678-9012',
        miniBio: 'A short bio about John Doe.'
      };

      // Updating state with fetched data
      setFirstName(profile.firstName);
      setLastName(profile.lastName);
      setEmail(profile.email);
      setMobile(profile.mobile);
      setDateOfBirth(profile.dateOfBirth);
      setAadharCard(profile.aadharCard);
      setMiniBio(profile.miniBio);
    } catch (error) {
      console.error('Error fetching profile data:', error);
    }
  };

  useEffect(() => {
    fetchProfileData();
  }, []);

  return (
    <Layout>
      <main className="py-10 bg-[#fff4f1]">
        <h1 className="text-4xl text-center text-red-600 mb-10">Passenger Profile</h1>
        <section className="bg-white p-8 rounded-lg shadow-md max-w-lg mx-auto">
          <h2 className="text-2xl text-red-600 mb-6">Profile Information</h2>
          <form action="/update_profile" method="post">
            <div className="mb-4">
              <label htmlFor="first_name" className="block text-gray-700 mb-2">First Name</label>
              <input type="text" id="first_name" name="first_name" className="w-full p-2 border border-gray-300 rounded-md" value={firstName} onChange={(e) => setFirstName(e.target.value)} required />
            </div>
            <div className="mb-4">
              <label htmlFor="last_name" className="block text-gray-700 mb-2">Last Name</label>
              <input type="text" id="last_name" name="last_name" className="w-full p-2 border border-gray-300 rounded-md" value={lastName} onChange={(e) => setLastName(e.target.value)} required />
            </div>
            <div className="mb-4">
              <label htmlFor="email" className="block text-gray-700 mb-2">Email</label>
              <input type="email" id="email" name="email" className="w-full p-2 border border-gray-300 rounded-md" value={email} onChange={(e) => setEmail(e.target.value)} required />
            </div>
            <div className="mb-4">
              <label htmlFor="mobile" className="block text-gray-700 mb-2">Mobile</label>
              <input type="tel" id="mobile" name="mobile" className="w-full p-2 border border-gray-300 rounded-md" value={mobile} onChange={(e) => setMobile(e.target.value)} required />
            </div>
            <div className="mb-4">
              <label htmlFor="date_of_birth" className="block text-gray-700 mb-2">Date of Birth</label>
              <input type="date" id="date_of_birth" name="date_of_birth" className="w-full p-2 border border-gray-300 rounded-md" value={dateOfBirth} onChange={(e) => setDateOfBirth(e.target.value)} required />
            </div>
            <div className="mb-4">
              <label htmlFor="aadhar_card" className="block text-gray-700 mb-2">Aadhar Card</label>
              <input type="text" id="aadhar_card" name="aadhar_card" className="w-full p-2 border border-gray-300 rounded-md" value={aadharCard} onChange={(e) => setAadharCard(e.target.value)} required />
            </div>
            <div className="mb-4">
              <label htmlFor="mini_bio" className="block text-gray-700 mb-2">Mini Bio</label>
              <textarea id="mini_bio" name="mini_bio" rows={4} className="w-full p-2 border border-gray-300 rounded-md" value={miniBio} onChange={(e) => setMiniBio(e.target.value)} />
            </div>
            <button type="submit" className="w-full bg-red-600 text-white p-2 rounded-md hover:bg-red-700">Update Profile</button>
          </form>
        </section>
      </main>
    </Layout>
  );
};

export default PassengerProfile;