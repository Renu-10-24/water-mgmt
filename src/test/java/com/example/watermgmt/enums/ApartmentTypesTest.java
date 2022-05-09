package com.example.watermgmt.enums;


import com.example.watermgmt.exception.WaterManagementException;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class ApartmentTypesTest {
	@Test
	public void testApartmentTypes() {
		assertThat(ApartmentType.TWO_BHK.getPersonCount()).isEqualTo(3);
		assertThat(ApartmentType.THREE_BHK.getPersonCount()).isEqualTo(5);
	}

	@Test
	public void testInvalidApartmentType() {
		assertThat( ApartmentType.getApartmentType(2)).isEqualTo(ApartmentType.TWO_BHK);
		assertThat(ApartmentType.getApartmentType(3)).isEqualTo(ApartmentType.THREE_BHK);
//		Assert.assertEquals(ApartmentType.TWO_BHK, ApartmentType.getApartmentType(3));
	}

	@Test
	void testExpectedException() {
		assertThatThrownBy(() -> {
			// Code under test
			ApartmentType.getApartmentType(5);
		}).isInstanceOf(WaterManagementException.class)
				.hasMessageContaining("Invalid APARTMENT TYPE 5");


	}
}
