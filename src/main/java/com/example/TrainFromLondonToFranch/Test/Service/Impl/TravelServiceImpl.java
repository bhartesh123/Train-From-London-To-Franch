package com.example.TrainFromLondonToFranch.Test.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.TrainFromLondonToFranch.Test.Entity.TrainDetails;
import com.example.TrainFromLondonToFranch.Test.Entity.TrainSeatsAvailability;
import com.example.TrainFromLondonToFranch.Test.Entity.TravelDetails;
import com.example.TrainFromLondonToFranch.Test.Entity.TravelReceipt;
import com.example.TrainFromLondonToFranch.Test.Entity.UserDetails;
import com.example.TrainFromLondonToFranch.Test.Repo.SeatsRepo;
import com.example.TrainFromLondonToFranch.Test.Repo.TrainRepo;
import com.example.TrainFromLondonToFranch.Test.Repo.TravelReceiptRepo;
import com.example.TrainFromLondonToFranch.Test.Repo.TravelRepo;
import com.example.TrainFromLondonToFranch.Test.Repo.UserRepo;
import com.example.TrainFromLondonToFranch.Test.Service.TraverService;

import jakarta.persistence.EntityNotFoundException;

@Service("TraverService")
public class TravelServiceImpl implements TraverService {
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private TravelRepo travelRepo;
	
	@Autowired
	private TrainRepo trainRepo;
	
	@Autowired
	private SeatsRepo seatsRepo;
	
	@Autowired
	private TravelReceiptRepo receiptRepo;
	
	@Override
	public Object registerUser(UserDetails details) {
		try {
			return userRepo.save(details);
		}catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return "Email Id already exists. Please check the user.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error while registering the user.";
		}
	}
	
	@Override
	public Object addTrvelDetails(TravelDetails details, int userId) {
		try {
			UserDetails user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
			boolean userHasBookedTicket = travelRepo.existsByUser(user);
	        if (userHasBookedTicket) {
	            return "User has already booked a ticket.";
	        }
	        
			TrainDetails trainDetails=trainRepo.findByFromLocationAndToLocation(details.getFrom(), details.getTo()).orElseThrow(()-> new RuntimeException("Train Not Found"));
			List<TrainSeatsAvailability> seatsAvailability = seatsRepo.findAvailableSeatsByTrainId(trainDetails.getTrainId());
			if(seatsAvailability.isEmpty()) {
				return "No seat available to Book train from London to Frnach";
			}
			TrainSeatsAvailability trainSeatsAvailability=seatsAvailability.get(0);
			Long generatedId = generate10DigitId();
			System.out.println(generatedId);
			details.setTicketId(generatedId);
			details.setUser(user);
			details.setTrainDetails(trainDetails);
			details.setSeatNo(trainSeatsAvailability.getSeats());
			details.setPrice(trainDetails.getPrice());
			trainSeatsAvailability.setAvailability(false);
			travelRepo.save(details);
			trainSeatsAvailability.setTravelDetails(details);
			seatsRepo.save(trainSeatsAvailability);
			return details;
		} catch (RuntimeException e) {
			e.printStackTrace();
			if(e.getLocalizedMessage().equals("User Not Found") || e.getMessage().equals("User Not Found")) {
				return "User not found. Please check the user Id.";
			}else {
				return "Train not found";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return "Error while booking the Ticket";
		}
	}
	
	@EventListener(ApplicationReadyEvent.class)
	private void allocateSeats() {
		allocateTrainDetails();
		List<TrainDetails> trains= new ArrayList<TrainDetails>();
		//TrainSeatsAvailability seats=new TrainSeatsAvailability();
		trains= trainRepo.findAll();
		List<String> availableSeats = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			availableSeats.add("A"+i);
		}
		for(int i=1; i<=5; i++) {
			availableSeats.add("B"+i);
		}
		trains.forEach(train -> {
            availableSeats.forEach(seat -> {
                TrainSeatsAvailability seats = new TrainSeatsAvailability();
                seats.setTrainDetails(train);
                seats.setAvailability(true);
                seats.setSeats(seat);
                seatsRepo.save(seats);
            });
        });
	}
	private void allocateTrainDetails() {
		TrainDetails trainDetails=new TrainDetails();
		trainDetails.setTrainId(100762L);
		trainDetails.setFrom("London");
		trainDetails.setTo("Franch");
		trainDetails.setTotalSeats(10);
		trainDetails.setPrice(20.00);
		trainRepo.save(trainDetails);
	}

	public Object getTrainDetails() {
		// TODO Auto-generated method stub
		return seatsRepo.findAll();
	}
	
	private Long generate10DigitId() {
        Random random = new Random();
        return random.longs(1000000000L, 10000000000L).findFirst().getAsLong();
    }

	public Object getReceipt(Long ticketId) {
		try {
			TravelDetails travelDetails=travelRepo.findByTicketId(ticketId);
			if(travelDetails == null) {
				throw new RuntimeException("Ticket Id not found.");
			}
			TrainDetails trainDetails = trainRepo.findByTrainId(travelDetails.getTrainDetails().getTrainId());
			if(trainDetails == null) {
				throw new RuntimeException("Train Id not found.");
			}
			UserDetails userDetails = userRepo.findByUserId(travelDetails.getUser().getUserId());
			if(userDetails == null) {
				throw new RuntimeException("User Details not found.");
			}
			TravelReceipt receipt = new TravelReceipt();
			receipt.setFirstName(userDetails.getFirstName());
			receipt.setLastName(userDetails.getLastName());
			receipt.setEmailId(userDetails.getEmailId());
			receipt.setFromStation(travelDetails.getFrom());
			receipt.setToStation(travelDetails.getTo());
			receipt.setPricePaid(travelDetails.getPrice());
			receipt.setSeatNo(travelDetails.getSeatNo());
			receipt.setTicketId(travelDetails.getTicketId());
			return receiptRepo.save(receipt);
			
		}catch (RuntimeException e) {
			// TODO: handle exception'
			if(e.getLocalizedMessage().equals("Ticket Id not found.") || e.getMessage().equals("Ticket Id not found.")) {
				return "Invalid ticket ID. Please check the ticket ID.";
			} else if(e.getLocalizedMessage().equals("Train Id not found.") || e.getMessage().equals("Train Id not found.")) {
				return "Train not found for provided Ticket ID.";
			} else if(e.getLocalizedMessage().equals("User Details not found.") || e.getMessage().equals("User Details not found.")) {
				return "User details not found for provided ticket ID.";
			}else {
				return "Error occured while fecting receipt";
			}
		}
	}

	@Override
	public Object getUsersAndSeat(String section) {
		Map<String, String> list=new HashMap<String, String>();
		List<Object[]> res=travelRepo.findUserAndSeats(section.toUpperCase());
		if(res.isEmpty()) {
			return "No any Bookings for section '"+section.toUpperCase()+"'.";
		}
		 for (Object[] result : res) {
	            String name = (String) result[0];
	            String seat = (String) result[1];
	            list.put(name, seat);
	        }
		 
		return list;
	}

	@Override
	public Object removeUserFromTrain(int userId) {
		try {
			List<TravelDetails> travelDetails = travelRepo.findByUserId(userId);
			if (!travelDetails.isEmpty()) {
				travelDetails.forEach(travle->{
					TrainSeatsAvailability seat = seatsRepo.findByTravelDetails(travle);
		            
		            if (seat != null) {
		                // Update the seat availability
		                seat.setAvailability(true);
		                seat.setTravelDetails(null);  // Remove the association with travel details
		                seatsRepo.save(seat);
		            }
		            
		            // Delete the travel details
		            travelRepo.delete(travle);
					
				});
				return "Removed user from the train.";
			} else {
				throw new EntityNotFoundException("User not found with ID: " + userId);
			}
		} catch (EntityNotFoundException e) {
			// TODO: handle exception
			return e.getMessage();
		}
	}

	@Override
	public Object modifyUserSeat(int userId, String newSeat) {
		try {
			UserDetails user = userRepo.findById(userId)
					.orElseThrow(() -> new EntityNotFoundException("User Not Found"));
			TravelDetails travelDetails = travelRepo.findByUser(user)
					.orElseThrow(() -> new EntityNotFoundException("No booked ticket found for the user"));
			TrainDetails trainDetails = travelDetails.getTrainDetails();
			Optional<TrainSeatsAvailability> newSeatOptional = seatsRepo.findBySeatsAndTrainDetails(newSeat,
					trainDetails);
			if (newSeatOptional.isPresent() && newSeatOptional.get().getAvailability()) {
				// Mark the old seat as available
				TrainSeatsAvailability oldSeat = seatsRepo.findByTravelDetails(travelDetails);
				oldSeat.setAvailability(true);
				oldSeat.setTravelDetails(null);
				seatsRepo.save(oldSeat);

				// Assign the new seat
				TrainSeatsAvailability newSeatAvailability = newSeatOptional.get();
				newSeatAvailability.setAvailability(false);
				newSeatAvailability.setTravelDetails(travelDetails);
				seatsRepo.save(newSeatAvailability);

				// Update travel details
				travelDetails.setSeatNo(newSeat);
				return travelRepo.save(travelDetails);
			} else {
				return "Requested seat is not available";
			} 
		} catch (EntityNotFoundException e) {
			// TODO: handle exception
			if(e.getMessage().equals("User Not Found")) {
				return "No User found.";
			} else if(e.getMessage().equals("No booked ticket found for the user")) {
				return "No any booking details found.";
			} else {
				return "Error occured in finding Entity.";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Error occured while modifying seat.";
		}
	}
}
