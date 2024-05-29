package com.example.TrainFromLondonToFranch.Test.Service;

import com.example.TrainFromLondonToFranch.Test.Entity.TravelDetails;
import com.example.TrainFromLondonToFranch.Test.Entity.UserDetails;

public interface TraverService {

	public Object registerUser(UserDetails details);
	
	public Object addTrvelDetails(TravelDetails details, int userId);
	
	public Object getReceipt(Long ticketId);
	
	public Object getUsersAndSeat(String section);
	
	public Object removeUserFromTrain(int userId);
	
	public Object modifyUserSeat(int userId, String newSeat);
}
