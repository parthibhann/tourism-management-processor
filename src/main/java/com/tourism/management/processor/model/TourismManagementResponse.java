package com.tourism.management.processor.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TourismManagementResponse {
	
	private List<BranchDetail> branchDetails;
	private String status;
	private String satusText;
	private List<Messages> messages;

}
