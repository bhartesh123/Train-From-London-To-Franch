package com.example.TrainFromLondonToFranch.Test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TrainFromLondonToFranch.Test.Entity.TravelDetails;
import com.example.TrainFromLondonToFranch.Test.Entity.UserDetails;
import com.example.TrainFromLondonToFranch.Test.Service.Impl.TravelServiceImpl;

@RestController("/")
public class TrainFromLondonToFranch_Controller {
	
	@Autowired
	private TravelServiceImpl travel;

	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody UserDetails details){
		return ResponseEntity.ok(travel.registerUser(details));
				
	}
	
	@PostMapping("/bookTicket")
	public ResponseEntity<?> addTravel(@RequestBody TravelDetails details, @RequestParam int userId){
		return ResponseEntity.ok(travel.addTrvelDetails(details, userId));
	}
	
	@GetMapping("/getDetails")
	public ResponseEntity<?> getTrain(){
		return ResponseEntity.ok(travel.getTrainDetails());
	}
	
	@GetMapping("/recieptDetail")
	public ResponseEntity<?> detailedReciept(@RequestParam Long ticketId){
		return ResponseEntity.ok(travel.getReceipt(ticketId));
	}
	
	@GetMapping("/usersAndSeats")
	public ResponseEntity<?> getUserAndSeat(@RequestParam String section){
		return ResponseEntity.ok(travel.getUsersAndSeat(section));
	}
	
	@DeleteMapping("/removeUserFromTrain")
	public ResponseEntity<?> removeUserFromTrain(@RequestParam int userId){
		return ResponseEntity.ok(travel.removeUserFromTrain(userId));
	}
	
	@PutMapping("/modifyUserSeat")
	public ResponseEntity<?> modifyUserSeat(@RequestParam int userId, @RequestParam String newSeat){
		
		return ResponseEntity.ok(travel.modifyUserSeat(userId, newSeat));
	}
}
