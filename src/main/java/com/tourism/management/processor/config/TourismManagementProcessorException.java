package com.tourism.management.processor.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TourismManagementProcessorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3486923923464716300L;

	public String message;
	
}
