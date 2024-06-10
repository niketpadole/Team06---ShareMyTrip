import React from 'react'
import Layout from '../Components/Layouts/Layout'

const Review = () => {
  return (
    <Layout>
      <main class="py-10 bg-[#fff4f1]">
        <h1 class="text-4xl text-center text-red-600 mb-10">Reviews</h1>
        <section class="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
            <h2 class="text-2xl text-red-600 mb-6">All Reviews</h2>
            <table class="min-w-full bg-white">
                <thead>
                    <tr>
                        <th class="py-2 px-4 bg-gray-200">Review ID</th>
                        <th class="py-2 px-4 bg-gray-200">Publisher</th>
                        <th class="py-2 px-4 bg-gray-200">Passenger</th>
                        <th class="py-2 px-4 bg-gray-200">Publisher Rating</th>
                        <th class="py-2 px-4 bg-gray-200">Publisher Review</th>
                        <th class="py-2 px-4 bg-gray-200">Passenger Rating</th>
                        <th class="py-2 px-4 bg-gray-200">Passenger Review</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="border px-4 py-2">1</td>
                        <td class="border px-4 py-2">Jane Smith</td>
                        <td class="border px-4 py-2">John Doe</td>
                        <td class="border px-4 py-2">5</td>
                        <td class="border px-4 py-2">Great ride!</td>
                        <td class="border px-4 py-2">4</td>
                        <td class="border px-4 py-2">Very good experience.</td>
                    </tr>
                </tbody>
            </table>
        </section>
    </main>
    </Layout>
  )
}

export default Review
