package com.tourism.management.processor.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tourism.management.processor.mapper.TourismManagementCsvTypeMapper;
import com.tourism.management.processor.model.BranchDetail;
import com.tourism.management.processor.model.TourismManagementResponse;
import com.tourism.management.processor.service.TourismManagementTrackerService;
import com.tourism.management.processor.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TourismManagementWriter implements ItemWriter<BranchDetail>{

	@Autowired
	TourismManagementTrackerService tourismManagementTrackerService;
	
	@Autowired
	TourismManagementCsvTypeMapper tourismManagementCsvTypeMapper;
	
	@Override
	public void write(List<? extends BranchDetail> branchDetails) throws Exception {

		log.info("TourismManagementWriter >> Start");
				
		log.info("TourismManagementWriter >> {}",JsonUtil.convertToString(branchDetails));
		
		for (BranchDetail branchDetail : branchDetails) {
			log.info("TourismManagementWriter >> Before Api Call >> {}",JsonUtil.convertToString(tourismManagementCsvTypeMapper.mapBranchDetailsToBranchDetailRequest(branchDetail)));
			TourismManagementResponse tourismManagementResponse = tourismManagementTrackerService.addPlace(tourismManagementCsvTypeMapper.mapBranchDetailsToBranchDetailRequest(branchDetail));
			
			log.info("TourismManagementResponse >> {} ",JsonUtil.convertToString(tourismManagementResponse));
		}
		
		log.info("TourismManagementWriter >> End");
		
	}


}
