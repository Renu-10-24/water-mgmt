package com.example.watermgmt.enums;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.watermgmt.exception.WaterManagementException;

public enum ApartmentType {
	
	TWO_BHK(2, 3), THREE_BHK(3, 5);
	static Map<Integer,ApartmentType> aprtTypesMap = null;
	static {
		Stream<ApartmentType> aprtStream = Stream.of(ApartmentType.values());
		aprtTypesMap = aprtStream.collect(Collectors.toMap(a->a.getType(), a->a));
	}
	private int type, personCount;

	ApartmentType(int type, int personCount) {
		this.type = type;
		this.personCount = personCount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	static ApartmentType getApartmentType(int type) throws WaterManagementException {
		return Optional.ofNullable(aprtTypesMap.get(type)).orElseThrow(()->new WaterManagementException("Invalid APARTMENT TYPE "+type));
	}

}
