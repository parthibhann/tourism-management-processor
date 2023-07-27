package com.tourism.management.processor.service;

import com.tourism.management.processor.model.TourismManagementRequest;
import com.tourism.management.processor.model.TourismManagementResponse;

public interface TourismManagementTrackerService {

	public TourismManagementResponse addPlace(TourismManagementRequest tourismManagementRequest);
	
}
