package com.dope.wmsapp.location.feature;

import com.dope.wmsapp.common.ApiTest;
import com.dope.wmsapp.location.domain.LocationRepository;
import com.dope.wmsapp.location.domain.StorageType;
import com.dope.wmsapp.location.domain.UsagePurpose;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterLocationTest extends ApiTest {

    @Autowired
    LocationRepository locationRepository;

    @Test
    @DisplayName("로케이션을 등록한다.")
    void registerLocation() {
        final String locationBarcode = "A-1-1";
        final StorageType storageType = StorageType.TOTE;
        final UsagePurpose usagePurpose = UsagePurpose.MOVE;
        final RegisterLocation.Request request = new RegisterLocation.Request(
                locationBarcode,
                storageType,
                usagePurpose
        );

        RestAssured.given().log().all()
                .body(request)
                .contentType("application/json")
                .when()
                .post("http://localhost:8080/locations")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        assertThat(locationRepository.findAll()).hasSize(1);
    }

}
