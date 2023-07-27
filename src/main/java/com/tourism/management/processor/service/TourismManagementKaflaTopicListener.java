package com.tourism.management.processor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.tourism.management.processor.model.BranchDetail;
import com.tourism.management.processor.model.TourismManagementResponse;
import com.tourism.management.processor.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@KafkaListener(id = "${kafka.group.id}", topics = {"${kafka.topic.admin}", "${kafka.topic.update}"},containerFactory = "kafkaListenerContainerFactory")
public class TourismManagementKaflaTopicListener {
	
	@Autowired
	TourismManagementService tourismManagementService;

	@KafkaHandler
	public void listenPlaceTopicGroup(BranchDetail branchDetail,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		log.info("listenPlaceTopicGroup >> partition >> {} >> {} ", partition, JsonUtil.convertToString(branchDetail));
		TourismManagementResponse tourismManagementResponse = tourismManagementService.createDocument(branchDetail);
		log.info("listenSillAdminTopicGroup >> tourismManagementResponse >> {} ", JsonUtil.convertToString(tourismManagementResponse));
	}
	
	@KafkaHandler(isDefault = true)
    public void unknown(Object object) {
		log.info("Unkown type received: {} ", JsonUtil.convertToString(object));
    }
}
