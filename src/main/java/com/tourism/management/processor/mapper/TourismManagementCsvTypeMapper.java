package com.tourism.management.processor.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tourism.management.processor.entity.BranchDetails;
import com.tourism.management.processor.model.BranchDetail;
import com.tourism.management.processor.model.BranchDetailCSVModel;
import com.tourism.management.processor.model.Messages;
import com.tourism.management.processor.model.Place;
import com.tourism.management.processor.model.TourismManagementRequest;
import com.tourism.management.processor.model.TourismManagementResponse;

@Component
public class TourismManagementCsvTypeMapper {

	static Map<String, String> PLANVALMAP = new HashMap<>();

	static {
		PLANVALMAP.put("1", "ANDAMAN");
		PLANVALMAP.put("2", "THAILAND");
		PLANVALMAP.put("3", "DUBAI");
		PLANVALMAP.put("4", "SINGAPORE");
		PLANVALMAP.put("5", "MALASIYA");
	}

	public static String planValueMap(String id) {
		return PLANVALMAP.get(id);
	}

	public BranchDetail mapBranchDetailCSVModelToBranchDetail(BranchDetailCSVModel item) {
		if (null == item) {
			return null;
		}
		return BranchDetail.builder().branchName(item.getBranchName()).website(item.getWebsite())
				.contactNo(Integer.parseInt(item.getContactNo())).email(item.getEmail()).places(placeMapper(item.getPlaces())).build();
	}

	private List<Place> placeMapper(String value) {
		List<Place> places = new ArrayList<>();
		String[] placeRates = value.split("\\|");
		for (String placeRate : placeRates) {
			String[] placeRateVal = placeRate.split("-");
			places.add(mapPlace(TourismManagementCsvTypeMapper.planValueMap(placeRateVal[0]), placeRateVal[1]));
		}
		return places;
	}

	private Place mapPlace(String name, String price) {
		if (name == null && price == null) {
			return null;
		}
		Place place = new Place();
		place.setName(name);
		if (price != null) {
			place.setRate(Integer.parseInt(price));
		}
		return place;
	}

	public TourismManagementRequest mapBranchDetailsToBranchDetailRequest(BranchDetail branchDetail) {

		return TourismManagementRequest.builder().branchDetail(branchDetail).build();
	}

	public BranchDetails branchDetailToBranchDetails(BranchDetail branchDetail) {
		if (null == branchDetail) {
			return null;
		}
		return BranchDetails.builder().branchName(branchDetail.getBranchName()).website(branchDetail.getWebsite())
				.contactNo(branchDetail.getContactNo()).email(branchDetail.getEmail())
				.places(preparePlaceMap(branchDetail.getPlaces())).build();

	}

	private Map<String, Integer> preparePlaceMap(List<Place> places) {

		return places.stream().collect(Collectors.toMap(Place::getName, Place::getRate));
	}

	public List<BranchDetail> branchDetailsResToBranchDetail(List<BranchDetails> branchDetails) {
		
		return branchDetails.stream().map(branchDetail -> {
			return BranchDetail.builder().branchName(branchDetail.getBranchName()).website(branchDetail.getWebsite())
			.contactNo(branchDetail.getContactNo()).email(branchDetail.getEmail())
			.places(prepareMapToPlace(branchDetail.getPlaces())).build();
		}).collect(Collectors.toList());
	}

	private List<Place> prepareMapToPlace(Map<String, Integer> places) {
		
		return places.entrySet().stream().map(entryMap -> {
			return Place.builder().name(entryMap.getKey()).rate(entryMap.getValue()).build();
		}).collect(Collectors.toList());
	}

	public TourismManagementResponse branchDetailToTourismManagementResponse(List<BranchDetail> branchDetails2,
			String status, String satusText, List<Messages> messages) {

		return TourismManagementResponse.builder().branchDetails(branchDetails2).status(status).satusText(satusText)
				.messages(messages).build();
	}
}
