package com.example.TrainFromLondonToFranch.Test.Repo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.TrainFromLondonToFranch.Test.Entity.TravelDetails;
import com.example.TrainFromLondonToFranch.Test.Entity.UserDetails;

@Repository
public interface TravelRepo extends JpaRepository<TravelDetails, Integer>{
	
	@Query("SELECT t FROM TravelDetails t WHERE t.ticketId = :ticketId")
	TravelDetails findByTicketId(@Param("ticketId")Long ticketId);
	
	boolean existsByUser(UserDetails user);

	@Query("SELECT CONCAT(u.firstName, ' ', u.lastName) AS name, a.seats " +
	           "FROM TravelDetails t " +
	           "JOIN t.user u  " +
	           "JOIN TrainSeatsAvailability a ON t.ticketId = a.travelDetails.ticketId " +
	           "WHERE a.seats LIKE CONCAT(:section, '%') "
	           + "ORDER BY a.seats ASC")
	List<Object[]> findUserAndSeats(@Param("section")String section);

	@Query("select t from TravelDetails t where t.user.userId = :userId")
	List<TravelDetails> findByUserId(@Param("userId") int userId);
	
	Optional<TravelDetails> findByUser(UserDetails user);

}
