import React, { useEffect, useState } from 'react'
import Layout from '../Components/Layouts/Layout';

const PubliserProfile = () => {
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
    const profileData = () => {
        try {
            console.log(firstName,lastName,email,mobile,dateOfBirth,aadharCard,setMiniBio)
        } catch (error) {
            console.log(error);
        }
      }
    
      useEffect(() => {
        profileData();
      },[])
  return (
    <Layout>
      <main class="mx-auto py-10 bg-[#fff4f1]">
        <h1 class="text-4xl text-center text-red-600 mb-10">Publisher Profile</h1>
        <section class="bg-white p-8 rounded-lg shadow-md max-w-lg mx-auto">
            <h2 class="text-2xl text-red-600 mb-6">Profile Information</h2>
            <form action="/update_profile" method="post">
                <div class="mb-4">
                    <label for="first_name" class="block text-gray-700 mb-2">First Name</label>
                    <input type="text" id="first_name" name="first_name" class="w-full p-2 border border-gray-300 rounded-md" value={firstName} onChange={(e) => setFirstName(e.target.value)} required/>
                </div>
                <div class="mb-4">
                    <label for="last_name" class="block text-gray-700 mb-2">Last Name</label>
                    <input type="text" id="last_name" name="last_name" class="w-full p-2 border border-gray-300 rounded-md" 
                    value={lastName} onChange={(e) => setLastName(e.target.value)} required/>
                </div>
                <div class="mb-4">
                    <label for="email" class="block text-gray-700 mb-2">Email</label>
                    <input type="email" id="email" name="email" class="w-full p-2 border border-gray-300 rounded-md"
                    value={email} onChange={(e) => setEmail(e.target.value)} required/>
                </div>
                <div class="mb-4">
                    <label for="mobile" class="block text-gray-700 mb-2">Mobile</label>
                    <input type="tel" id="mobile" name="mobile" class="w-full p-2 border border-gray-300 rounded-md"
                    value={mobile} onChange={(e) => setMobile(e.target.value)} required/>
                </div>
                <div class="mb-4">
                    <label for="date_of_birth" class="block text-gray-700 mb-2">Date of Birth</label>
                    <input type="date" id="date_of_birth" name="date_of_birth" class="w-full p-2 border border-gray-300 rounded-md" value={dateOfBirth} onChange={(e) => setDateOfBirth(e.target.value)} required/>
                </div>
                <div class="mb-4">
                    <label for="driving_license" class="block text-gray-700 mb-2">Driving License</label>
                    <input type="text" id="driving_license" name="driving_license" class="w-full p-2 border border-gray-300 rounded-md" value={drivingLicense} onChange={(e) => setDrivingLicense(e.target.value)} required/>
                </div>
                <div class="mb-4">
                    <label for="aadhar_card" class="block text-gray-700 mb-2">Aadhar Card</label>
                    <input type="text" id="aadhar_card" name="aadhar_card" class="w-full p-2 border border-gray-300 rounded-md" 
                    value={aadharCard} onChange={(e) => setAadharCard(e.target.value)} required/>
                </div>
                <div class="mb-4">
                    <label for="mini_bio" class="block text-gray-700 mb-2">Mini Bio</label>
                    <textarea id="mini_bio" name="mini_bio" rows="4" class="w-full p-2 border border-gray-300 rounded-md" value={miniBio} onChange={(e) => setMiniBio(e.target.value)}>[Mini Bio]</textarea>
                </div>
                <div class="mb-4">
                    <label for="vehicle_model_name" class="block text-gray-700 mb-2">Vehicle Model Name</label>
                    <input type="text" id="vehicle_model_name" name="vehicle_model_name" class="w-full p-2 border border-gray-300 rounded-md" value={vehicleModelName} onChange={(e) => setVehicleModelName(e.target.value)} required/>
                </div>
                <div class="mb-6">
                    <label for="vehicle_no" class="block text-gray-700 mb-2">Vehicle Number</label>
                    <input type="text" id="vehicle_no" name="vehicle_no" class="w-full p-2 border border-gray-300 rounded-md" value={vehicleNumber} onChange={(e) => setVehicleNumber(e.target.value)} required/>
                </div>
                <button type="submit" class="w-full bg-red-600 text-white p-2 rounded-md hover:bg-red-700">Update Profile</button>
            </form>
        </section>
    </main>
    </Layout>
  )
}

export default PubliserProfile
