package com.dope.wmsapp.outbound.feature;

import com.dope.wmsapp.common.ApiTest;
import com.dope.wmsapp.outbound.domain.MaterialType;
import com.dope.wmsapp.outbound.domain.PackageMaterialRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterPackagingMaterialTest extends ApiTest {

    @Autowired
    PackageMaterialRepository packageMaterialRepository;

    @Test
    void registerPackagingMaterial() {
        final String name = "name";
        final String code = "code";
        final Long innerWidthInMillimeters = 1000L;
        final Long innerHeightInMillimeters = 1000L;
        final Long innerLengthInMillimeters = 1000L;
        final Long outerWidthInMillimeters = 1000L;
        final Long outerHeightInMillimeters = 1000L;
        final Long outerLengthInMillimeters = 1000L;
        final Long weightInGrams = 1000L;
        final Long maxWeightInGrams = 1000L;
        final MaterialType materialType = MaterialType.CORRUGATED_BOX;
        final RegisterPackageMaterial.Request request = new RegisterPackageMaterial.Request(
                name,
                code,
                innerWidthInMillimeters,
                innerHeightInMillimeters,
                innerLengthInMillimeters,
                outerWidthInMillimeters,
                outerHeightInMillimeters,
                outerLengthInMillimeters,
                weightInGrams,
                maxWeightInGrams,
                materialType
        );
        RestAssured.given().log().all()
                .body(request)
                .contentType("application/json")
                .when().post("/package-materials")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        assertThat(packageMaterialRepository.findAll()).hasSize(1);
    }

}
