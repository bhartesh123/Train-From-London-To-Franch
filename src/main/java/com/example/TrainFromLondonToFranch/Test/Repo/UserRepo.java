package com.example.TrainFromLondonToFranch.Test.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.TrainFromLondonToFranch.Test.Entity.UserDetails;

@Repository
public interface UserRepo extends JpaRepository<UserDetails, Integer>{

	@Query("select u from UserDetails u WHERE u.userId = :userId")
	UserDetails findByUserId(@Param("userId") int userId);

}
