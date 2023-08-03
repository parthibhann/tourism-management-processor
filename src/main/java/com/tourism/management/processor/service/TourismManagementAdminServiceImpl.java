package com.tourism.management.processor.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourism.management.processor.config.TourismManagementProcessorException;
import com.tourism.management.processor.entity.BranchDetails;
import com.tourism.management.processor.mapper.TourismManagementCsvTypeMapper;
import com.tourism.management.processor.model.BranchDetail;
import com.tourism.management.processor.model.TourismManagementResponse;
import com.tourism.management.processor.repository.TourismManagementRepository;
import com.tourism.management.processor.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TourismManagementAdminServiceImpl implements TourismManagementService {

	@Autowired
	TourismManagementRepository tourismManagementRepository;

	@Autowired
	TourismManagementCsvTypeMapper tourismManagementCsvTypeMapper;

	public TourismManagementResponse createDocument(BranchDetail branchDetail) {

		BranchDetails branchDetails = tourismManagementCsvTypeMapper.branchDetailToBranchDetails(branchDetail);

		BranchDetails branchDetailsRes = tourismManagementRepository.save(branchDetails);

		log.info("branchDetailsRes >> " + JsonUtil.convertToString(branchDetailsRes));

		List<BranchDetail> branchDetails2 = tourismManagementCsvTypeMapper
				.branchDetailsResToBranchDetail(Arrays.asList(branchDetailsRes));

		return tourismManagementCsvTypeMapper.branchDetailToTourismManagementResponse(branchDetails2, "SUCCESS", "Place updated successfully", null);
	}

	@Override
	public TourismManagementResponse retrieveDocuments(String criteria, String criteriaValue) {

		log.info("retrieveDocuments >> {} >> {} ", criteria, criteriaValue);

		List<BranchDetails> branchDetails = null;

		switch (criteria) {

		case "branchName":
			branchDetails = tourismManagementRepository.findByBranchNameRegex(criteriaValue);
			break;
		case "branchId":
			branchDetails = tourismManagementRepository.findByBranchId(criteriaValue);
			break;
		case "id":
			Optional<BranchDetails> branchDetailsOptional = tourismManagementRepository.findById(criteriaValue);
			branchDetails = Arrays.asList(branchDetailsOptional.get());
			break;
		case "ALL":
			branchDetails = tourismManagementRepository.findAll();
			break;
		default:
			throw new TourismManagementProcessorException("Search supports By NAME, ASSOCIATEID or SKILL");
		}

		List<BranchDetail> branchDetailList = tourismManagementCsvTypeMapper.branchDetailsResToBranchDetail(branchDetails);		

		return tourismManagementCsvTypeMapper.branchDetailToTourismManagementResponse(branchDetailList, "SUCCESS", "Document retrieved successfully", null);
	}
	
}
