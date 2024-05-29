package com.example.TrainFromLondonToFranch.Test.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TrainFromLondonToFranch.Test.Entity.TravelReceipt;

@Repository
public interface TravelReceiptRepo extends JpaRepository<TravelReceipt, Integer>{

}
