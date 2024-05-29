package com.example.TrainFromLondonToFranch.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TrainFromLondonToFranch.Test.Entity.UserDetails;
import com.example.TrainFromLondonToFranch.Test.Service.Impl.TravelServiceImpl;

@SpringBootTest
class TestApplicationTests {

	@Test
	void train() {
		UserDetails userDetails = new UserDetails(1, "Bhartesh", "Surwashi", "bharteshsurwashi@gmail.com");
		TravelServiceImpl serviceImpl = Mockito.mock(TravelServiceImpl.class);
		when(serviceImpl.registerUser(userDetails)).thenReturn(userDetails);
		
		UserDetails details = (UserDetails) serviceImpl.registerUser(userDetails);
		
		assertEquals("Bhartesh", details.getFirstName());
		assertEquals("Surwashi", details.getLastName());
		assertEquals("bharteshsurwashi@gmail.com", details.getEmailId());
	}

}
