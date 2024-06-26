package com.axis.team6.coderiders.sharemytrip.ridematchingservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.BookRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.CreatePublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.PublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.RideDetailsDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PassengerRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PublisherRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.RideDetailsView;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.exception.RideNotFoundException;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository.PassengerRideRepository;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository.PublisherRideRepository;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository.RideDetailsRepository;

@Service

public class RideServiceImpl implements RideService {
	@Autowired
	private PublisherRideRepository publisherRideRepository;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PassengerRideRepository passengerRideRepository;

	@Autowired
	private RideDetailsRepository rideDetailsRepository;

	@Autowired
	private RestTemplate restTemplate;

	private static final String USER_MANAGEMENT_SERVICE_URL = "http://localhost:8089/publishers";
	
	//utility fun
	private RideDetailsDTO convertToDTO(RideDetailsView rideDetailsView) {
		return modelMapper.map(rideDetailsView, RideDetailsDTO.class);
	}

	//utility fun
	private PublisherRideDTO convertToDTO(PublisherRide publisherRide) {
		return modelMapper.map(publisherRide, PublisherRideDTO.class);
	}

	public PublisherRide createPublisherRide(CreatePublisherRideDTO createPublisherRideDTO) {
		PublisherRide publisherRide = new PublisherRide();
		BeanUtils.copyProperties(createPublisherRideDTO, publisherRide);
		return publisherRideRepository.save(publisherRide);
	}

	public PassengerRide bookRide(BookRideDTO bookRideDTO) {
		PassengerRide passengerRide = new PassengerRide();
		BeanUtils.copyProperties(bookRideDTO, passengerRide);
		PublisherRide publisherRide = publisherRideRepository.findById(bookRideDTO.getPublisherRideId())
				.orElseThrow(() -> new RuntimeException("Publisher Ride not found"));
		if (publisherRide.getAvailableSeats() < bookRideDTO.getNoOfPassengers()) {
			throw new RuntimeException("Not enough available seats");
		}
		publisherRide.setAvailableSeats(publisherRide.getAvailableSeats() - bookRideDTO.getNoOfPassengers());
		publisherRideRepository.save(publisherRide);
		return passengerRideRepository.save(passengerRide);
	}

	public List<PublisherRideDTO> viewAvailableRides() {
		List<PublisherRide> rides = publisherRideRepository.findByAvailableSeatsGreaterThan(0);
		return rides.stream().map(ride -> modelMapper.map(ride, PublisherRideDTO.class))
				.collect(Collectors.toList());
	}

	public void cancelRide(Integer passengerRideId) {
		PassengerRide passengerRide = passengerRideRepository.findById(passengerRideId)
				.orElseThrow(() -> new RuntimeException("Passenger Ride not found"));
		PublisherRide publisherRide = publisherRideRepository.findById(passengerRide.getPublisherRideId())
				.orElseThrow(() -> new RuntimeException("Publisher Ride not found"));
		publisherRide.setAvailableSeats(publisherRide.getAvailableSeats() + passengerRide.getNoOfPassengers());
		publisherRideRepository.save(publisherRide);
		passengerRideRepository.deleteById(passengerRideId);
	}

	public void cancelPublishedRide(Integer publisherRideId) {
		PublisherRide publisherRide = publisherRideRepository.findById(publisherRideId)
				.orElseThrow(() -> new RuntimeException("Publisher Ride not found"));
		publisherRideRepository.deleteById(publisherRideId);
		List<PassengerRide> passengerRides = passengerRideRepository.findByPublisherRideId(publisherRideId);
		for (PassengerRide passengerRide : passengerRides) {
			passengerRideRepository.deleteById(passengerRide.getPassengerRideId());
		}
	}

//    @Override
//    public List<PublisherRideDTO> getPassengerRides(int passengerId) {
//        List<PassengerRide> passengerRides = passengerRideRepository.findByPassengerId(passengerId);
//        return passengerRides.stream()
//                .map(pr -> {
//                    PublisherRide publisherRide = publisherRideRepository.findById(pr.getPublisherRideId()).orElse(null);
//                    return modelMapper.map(publisherRide, PublisherRideDTO.class);
//                })
//                .collect(Collectors.toList());
//    }

	@Override
	public List<RideDetailsDTO> getPassengerRidesDetails(int passengerId) {
		List<RideDetailsView> rides = rideDetailsRepository.findRidesByPassengerId(passengerId);
		List<RideDetailsDTO> rideDetailsDTOs = new ArrayList<>();
		for (RideDetailsView ride : rides) {
			rideDetailsDTOs.add(convertToDTO(ride));
		}
		return rideDetailsDTOs;
//        return rides.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
	}


	
	// filter rides according to ToLocation and FromLocation
	@Override
	public List<PublisherRideDTO> getFilteredRides(String fromLocation, String toLocation,Date dateOfJourney) {
		List<PublisherRide> publisherRides = publisherRideRepository.findByFromLocationAndToLocationAndDateOfJourney(fromLocation,
				toLocation,dateOfJourney);
		return publisherRides.stream().map(ride -> modelMapper.map(ride, PublisherRideDTO.class))
				.collect(Collectors.toList());
	}

	// cancel ride by passenger
	public void cancelRide(int passengerRideId) {
		PassengerRide passengerRide = passengerRideRepository.findById(passengerRideId)
				.orElseThrow(() -> new RideNotFoundException("Passenger ride not found with id: " + passengerRideId));
		passengerRideRepository.delete(passengerRide);
	}

	
	@Override
	public List<PublisherRideDTO> getPublisherRidesDetails(int publisherId) {
		List<PublisherRide> publisherRides = publisherRideRepository.findByPublisherId(publisherId);
		List<PublisherRideDTO> rideDetailsDTOs = new ArrayList<>();
		for (PublisherRide ride : publisherRides) {
			rideDetailsDTOs.add(convertToDTO(ride));
		}
		return rideDetailsDTOs;
	}

	@Override
	public List<PublisherRideDTO> getCompletedRidesDetails() {
		List<PublisherRide> publisherRides = publisherRideRepository.findByStatus("COMPLETED").get();
		List<PublisherRideDTO> rideDetailsDTOs = new ArrayList<>();
		for (PublisherRide ride : publisherRides) {
			rideDetailsDTOs.add(convertToDTO(ride));
		}
		return rideDetailsDTOs;
	}

	@Override
	public List<PublisherRideDTO> getCompletedRides(@Valid Integer id) {
	List<PublisherRide> rides=publisherRideRepository.findByStatusAndPublisherId("COMPLETED",id).get();
	List<PublisherRideDTO> rideDetailsDTOs = new ArrayList<>();
	for (PublisherRide ride : rides) {
		rideDetailsDTOs.add(convertToDTO(ride));
	}
	return rideDetailsDTOs;
	}

	@Override
	public String setRideStatus(Integer id) {
		PublisherRide publisherRide=publisherRideRepository.findById(id).get();
		if(publisherRide==null)
		return null;
		else {
			publisherRide.setStatus("ONGOING");
			publisherRideRepository.save(publisherRide);
			List<PassengerRide> passengerRides=passengerRideRepository.findAllByPublisherRideId(id);
			for(PassengerRide p: passengerRides) {
				p.setStatus("ONGOING");
				passengerRideRepository.save(p);
			}
			return "success";
		}
	}

	@Override
	public List<PublisherRideDTO> getNotCompletedRides(@Valid Integer publisherId) {
		List<PublisherRide> rides=publisherRideRepository.findByStatusAndPublisherId("NOT_COMPLETED",publisherId).get();
		//rides.stream().forEach(System.out::println);
		List<PublisherRideDTO> rideDetailsDTOs = new ArrayList<>();
		for (PublisherRide ride : rides) {
			rideDetailsDTOs.add(convertToDTO(ride));
		}
		return rideDetailsDTOs;
//		return rides.stream().map(ride -> modelMapper.map(ride, PublisherRideDTO.class))
//				.collect(Collectors.toList());
	}

	@Override
	public List<PublisherRideDTO> getOngoingRides(@Valid Integer id) {
		List<PublisherRide> rides=publisherRideRepository.findByStatusAndPublisherId("ONGOING",id).get();
		List<PublisherRideDTO> rideDetailsDTOs = new ArrayList<>();
		for (PublisherRide ride : rides) {
			rideDetailsDTOs.add(convertToDTO(ride));
		}
		return rideDetailsDTOs;
	}

	@Override
	public List<RideDetailsDTO> viewPassengers(@Valid Integer publisherRideId) {
			List<RideDetailsView> rides= rideDetailsRepository.findRidesByPublisherId(publisherRideId);
			List<RideDetailsDTO> rideDetailsDTOs = new ArrayList<>();
			for (RideDetailsView ride : rides) {
				rideDetailsDTOs.add(convertToDTO(ride));
			}
			return rideDetailsDTOs;
	}

	@Override
	public List<RideDetailsDTO> viewPastRides(@Valid Integer passengerId) {
		List<RideDetailsView> rides=rideDetailsRepository.findRidesByPassengerPaymentStatus(passengerId);
		List<RideDetailsDTO> rideDetailsDTOs = new ArrayList<>();
		for (RideDetailsView ride : rides) {
			rideDetailsDTOs.add(convertToDTO(ride));
		}
		return rideDetailsDTOs;
	}
	
	@Override
	public List<RideDetailsDTO> getNotCompletedRidesPassenger(@Valid Integer passengerId) {
		List<RideDetailsView> rides=rideDetailsRepository.findByPublisherStatusAndPassengerId("NOT_COMPLETED",passengerId);
		//rides.stream().forEach(System.out::println);
		List<RideDetailsDTO> rideDetailsDTOs = new ArrayList<>();
		for (RideDetailsView ride : rides) {
			rideDetailsDTOs.add(convertToDTO(ride));
		}
		return rideDetailsDTOs;
	}

	@Override
	public List<RideDetailsDTO> getOngoingRidesPassenger(@Valid Integer passengerId) {
		List<RideDetailsView> rides=rideDetailsRepository.findByPublisherStatusAndPassengerId("ONGOING",passengerId);
		//rides.stream().forEach(System.out::println);
		List<RideDetailsDTO> rideDetailsDTOs = new ArrayList<>();
		for (RideDetailsView ride : rides) {
			rideDetailsDTOs.add(convertToDTO(ride));
		}
		return rideDetailsDTOs;
	}

	@Override
	public String setRideStatusPassenger(Integer passengerRideId) {
		PassengerRide ride=passengerRideRepository.findById(passengerRideId).get();
		if(ride==null)
		return null;
		else {
			ride.setPaymentStatus("PAID");
			ride.setStatus("COMPLETED");
			passengerRideRepository.save(ride);
			return "success";
		}
	}

	@Override
	public String setRideStatusEnd(Integer id) {
		PublisherRide publisherRide=publisherRideRepository.findById(id).get();
		if(publisherRide==null)
		return null;
		else {
			publisherRide.setStatus("COMPLETED");
			publisherRideRepository.save(publisherRide);
			List<PassengerRide> passengerRides=passengerRideRepository.findAllByPublisherRideId(id);
			for(PassengerRide p: passengerRides) {
				p.setStatus("COMPLETED");
				passengerRideRepository.save(p);
			}
			return "success";
		}
	}
}
