package com.tourism.management.processor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourism.management.processor.model.TourismManagementRequest;
import com.tourism.management.processor.model.TourismManagementResponse;
import com.tourism.management.processor.repository.TourismManagementApiClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TourismManagementTrackerServiceImpl implements TourismManagementTrackerService {
	
	@Autowired
	TourismManagementApiClient tourismManagementApiClient;

	@Override
	public  TourismManagementResponse addPlace(TourismManagementRequest tourismManagementRequest) {
		log.info("addPlace >> Start");

		TourismManagementResponse tourismManagementResponse = null;
		try {
			tourismManagementResponse = tourismManagementApiClient.addNewPlace(tourismManagementRequest);			
		}catch(Exception ex) {
			log.error("Exception while add new profile", ex);
		}
		
		log.info("addPlace >> End >> {}", tourismManagementResponse);
		return tourismManagementResponse;
	}

}
