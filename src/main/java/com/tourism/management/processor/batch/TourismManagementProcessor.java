package com.tourism.management.processor.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tourism.management.processor.mapper.TourismManagementCsvTypeMapper;
import com.tourism.management.processor.model.BranchDetail;
import com.tourism.management.processor.model.BranchDetailCSVModel;
import com.tourism.management.processor.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TourismManagementProcessor implements ItemProcessor<BranchDetailCSVModel,BranchDetail> {

	@Autowired
	TourismManagementCsvTypeMapper tourismManagementCsvTypeMapper;
	
	@Override
	public BranchDetail process(BranchDetailCSVModel item) throws Exception {
		log.info("TourismManagementProcessor >> process", JsonUtil.convertToString(item) );
		
		return tourismManagementCsvTypeMapper.mapBranchDetailCSVModelToBranchDetail(item);
	}

}
