package com.example.TrainFromLondonToFranch.Test.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.TrainFromLondonToFranch.Test.Entity.TrainDetails;

@Repository
public interface TrainRepo extends JpaRepository<TrainDetails, Long>{
	@Query("SELECT t FROM TrainDetails t WHERE UPPER(t.from) = UPPER(:from) AND UPPER(t.to) = UPPER(:to)")	
	 Optional<TrainDetails> findByFromLocationAndToLocation(@Param("from")String from, @Param("to") String to);
	
	@Query("SELECT t FROM TrainDetails t WHERE t.trainId=:trainId")
	TrainDetails findByTrainId(@Param("trainId")Long trainId);
}
