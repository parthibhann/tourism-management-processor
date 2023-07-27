package com.tourism.management.processor.resources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourism.management.processor.model.TourismManagementResponse;
import com.tourism.management.processor.service.TourismManagementService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("tourism/")
@Slf4j
public class TourismManagementBatchResource {
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	
	@Autowired
	TourismManagementService tourismManagementService;
	
	@GetMapping("load/csv")
	public BatchStatus loadCsvData() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		Map<String, JobParameter> parameters = new HashMap<>();
		parameters.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters = new JobParameters(parameters);
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		
		while(jobExecution.isRunning()) {
			log.info("...");
		}
		
		return jobExecution.getStatus();
	}
	
	@GetMapping("{criteria}/{criteriaValue}")
	public TourismManagementResponse retrieveDetails(@PathVariable("criteria") String criteria, @PathVariable("criteriaValue") String criteriaValue) {
		log.info("retrieveDetails , {} , {} ",criteria, criteriaValue);
		return tourismManagementService.retrieveDocuments(criteria, criteriaValue);
	}

}
