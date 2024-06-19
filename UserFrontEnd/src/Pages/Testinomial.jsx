import React, { useEffect, useState } from "react";

const Testinomial = () => {
  const [testimonialIndex, setTestimonialIndex] = useState(0);

  const testimonials = [
    {
      text: '"ShareMyTrip has made my daily commute so much easier and more affordable. Highly recommend it!" - Vinay Sharma',
    },
    {
      text: '"A great way to meet new people and save money on travel expenses. I love using ShareMyTrip for weekend getaways!" - Abhsihek Tripati',
    },
    {
      text: '"Fantastic service! I was able to rent a car for my business trip at a fraction of the cost of traditional rental services." - Sarah Gill',
    },
  ];

  useEffect(() => {
    const intervalId = setInterval(() => {
      setTestimonialIndex((prevIndex) => (prevIndex + 1) % testimonials.length);
    }, 5000);

    return () => clearInterval(intervalId);
  }, [testimonials.length]);
  return (
    <>
      <section id="testimonials" className="bg-[#fff4f1] py-10 text-center">
        <h2 className="text-3xl text-[#ff6f61] mb-6">Testimonials</h2>
        <div className="flex flex-col items-center justify-center">
          <div className="max-w-[600px]">
            <p className="italic text-lg text-[#333]">
              {testimonials[testimonialIndex].text}
            </p>
          </div>
        </div>
      </section>
    </>
  );
};

export default Testinomial;
