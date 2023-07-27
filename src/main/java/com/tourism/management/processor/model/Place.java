/**
 * 
 */
package com.tourism.management.processor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Welcome
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class Place {
	
	private String name;
	private int rate;
	

}
