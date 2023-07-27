/**
 * 
 */
package com.tourism.management.processor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * BranchDetails Model
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchDetailCSVModel {

	private String branchId;
	private String branchName;
	private String website;	
	private String contactNo;	
	private String email;
	private String places;
}
