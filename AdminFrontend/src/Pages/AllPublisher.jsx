import React from 'react'
import Layout from '../Components/Layouts/Layout'

const AllPublisher = () => {
  return (
    <Layout>
    <main class="py-10 bg-[#fff4f1]">
        <h1 class="text-4xl text-center text-red-600 mb-10">Publisher Details</h1>
        <section class="bg-white p-8 rounded-lg shadow-md max-w-4xl mx-auto">
            <h2 class="text-2xl text-red-600 mb-6">All Publishers</h2>
            <table class="min-w-full bg-white">
                <thead>
                    <tr>
                        <th class="py-2 px-4 bg-gray-200">ID</th>
                        <th class="py-2 px-4 bg-gray-200">Full Name</th>
                        <th class="py-2 px-4 bg-gray-200">Email</th>
                        <th class="py-2 px-4 bg-gray-200">Mobile</th>
                        <th class="py-2 px-4 bg-gray-200">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="border px-4 py-2">1</td>
                        <td class="border px-4 py-2">Jane Smith</td>
                        <td class="border px-4 py-2">jane.smith@example.com</td>
                        <td class="border px-4 py-2">9876543210</td>
                        <td class="border px-4 py-2">
                            <a href="#" class="text-blue-500 hover:text-blue-700">Edit</a>
                            <a href="#" class="text-red-500 hover:text-red-700 ml-2">Delete</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </section>
    </main>
    </Layout>
  )
}

export default AllPublisher