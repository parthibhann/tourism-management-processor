/**
 * 
 */
package com.tourism.management.processor.entity;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * BranchDetails entity
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document("branchDetails")
public class BranchDetails {

	@Id
	private String branchId;
	private String branchName;
	private String website;	
	private int contactNo;	
	private String email;
	private Map<String, Integer> places;
	private Date auditTimeStamp;
}
