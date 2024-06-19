import React from 'react'
import Layout from '../Components/Layouts/Layout'

const PageNotFound = () => {
  return (
    <>
      <Layout>
      <main className="place-items-center sm:py-32 lg:px-8 grid min-h-full px-6 py-24 bg-white">
        <div className="text-center">
          <p className="text-5xl font-semibold text-red-600">404</p>
          <h1 className="sm:text-5xl mt-4 text-3xl font-bold tracking-tight text-gray-900">
            Page not found
          </h1>
          <p className="mt-6 text-base leading-7 text-gray-600">
            Sorry, we couldn’t find the page you’re looking for.
          </p>
          <div className="gap-x-6 flex items-center justify-center mt-10">
            <a
              href="/"
              className="rounded-md bg-indigo-600 px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
            >
              Go back home
            </a>
            <a href="/contact-us" className="text-sm font-semibold text-gray-900">
              Contact support <span aria-hidden="true">&rarr;</span>
            </a>
          </div>
        </div>
      </main>
      </Layout>
    </>
  )
}

export default PageNotFound
