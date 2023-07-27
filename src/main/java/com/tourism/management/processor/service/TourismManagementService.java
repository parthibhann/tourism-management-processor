package com.tourism.management.processor.service;

import com.tourism.management.processor.model.BranchDetail;
import com.tourism.management.processor.model.TourismManagementResponse;

public interface TourismManagementService {

	public TourismManagementResponse createDocument(BranchDetail branchDetail);
	
	public TourismManagementResponse retrieveDocuments(String criteria, String criteriaValue);
	
}
