package com.example.TrainFromLondonToFranch.Test.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.TrainFromLondonToFranch.Test.Entity.TrainDetails;
import com.example.TrainFromLondonToFranch.Test.Entity.TrainSeatsAvailability;
import com.example.TrainFromLondonToFranch.Test.Entity.TravelDetails;

@Repository
public interface SeatsRepo extends JpaRepository<TrainSeatsAvailability, Integer>{
	@Query("select a from TrainSeatsAvailability a where a.trainDetails.trainId = :trainId AND a.availability = true")
	 List<TrainSeatsAvailability> findAvailableSeatsByTrainId(@Param("trainId")Long trainId);

	TrainSeatsAvailability findByTravelDetails(TravelDetails travelDetails1);
	
    Optional<TrainSeatsAvailability> findBySeatsAndTrainDetails(String seats, TrainDetails trainDetails);
}
