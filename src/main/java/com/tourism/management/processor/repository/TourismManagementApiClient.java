package com.tourism.management.processor.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tourism.management.processor.model.TourismManagementRequest;
import com.tourism.management.processor.model.TourismManagementResponse;

@FeignClient(value = "${feign.client.tourism.management.api}", url = "${feign.client.url.tourism.management.api}")
public interface TourismManagementApiClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/tourism/api/v1/branch")
	public TourismManagementResponse addNewPlace(TourismManagementRequest tourismManagementRequest);

}
