package com.tourism.management.processor.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tourism.management.processor.entity.BranchDetails;


@Repository
public interface TourismManagementRepository extends MongoRepository<BranchDetails, String> {

	@Query(value = "{'branchName': {$regex : ?0, $options: 'i'}}")
	List<BranchDetails> findByBranchNameRegex(String branchName);

	@Query("{branchId:'?0'}")
	List<BranchDetails> findByBranchId(String branchId);
	
}
