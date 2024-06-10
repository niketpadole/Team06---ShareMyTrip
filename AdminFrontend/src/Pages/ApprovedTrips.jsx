import React from 'react'
import Layout from '../Components/Layouts/Layout'

const ApprovedTrips = () => {
  return (
    <Layout>
            <main class="py-10 bg-[#fff4f1]">
        <h1 class="text-4xl text-center text-red-600 mb-10">Approved Trips</h1>
        <section class="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
          <h2 class="text-2xl text-red-600 mb-6">All Approved Trips</h2>
          <table class="min-w-full bg-white">
            <thead>
              <tr>
                <th class="py-2 px-4 bg-gray-200">Trip ID</th>
                <th class="py-2 px-4 bg-gray-200">Publisher</th>
                <th class="py-2 px-4 bg-gray-200">Passenger</th>
                <th class="py-2 px-4 bg-gray-200">From</th>
                <th class="py-2 px-4 bg-gray-200">To</th>
                <th class="py-2 px-4 bg-gray-200">Date</th>
                <th class="py-2 px-4 bg-gray-200">Time</th>
                <th class="py-2 px-4 bg-gray-200">Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="border px-4 py-2">1</td>
                <td class="border px-4 py-2">Jane Smith</td>
                <td class="border px-4 py-2">John Doe</td>
                <td class="border px-4 py-2">New York</td>
                <td class="border px-4 py-2">Boston</td>
                <td class="border px-4 py-2">2024-06-10</td>
                <td class="border px-4 py-2">10:00 AM</td>
                <td class="border px-4 py-2">
                  <a href="#" class="text-blue-500 hover:text-blue-700">
                    View
                  </a>
                  <a href="#" class="text-red-500 hover:text-red-700 ml-2">
                    Cancel
                  </a>
                </td>
              </tr>
            </tbody>
          </table>
        </section>
      </main>
    </Layout>
  )
}

export default ApprovedTrips
