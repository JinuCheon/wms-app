package com.dope.wmsapp.outbound.feature.api;

import com.dope.wmsapp.common.Scenario;
import com.dope.wmsapp.outbound.domain.MaterialType;
import com.dope.wmsapp.outbound.feature.RegisterPackageMaterial;
import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;

public class RegisterPackageMaterialApi {
    private String name = "name";
    private String code = "code";
    private Long innerWidthInMillimeters = 1000L;
    private Long innerHeightInMillimeters = 1000L;
    private Long innerLengthInMillimeters = 1000L;
    private Long outerWidthInMillimeters = 1000L;
    private Long outerHeightInMillimeters = 1000L;
    private Long outerLengthInMillimeters = 1000L;
    private Long weightInGrams = 1000L;
    private Long maxWeightInGrams = 1000L;
    private MaterialType materialType = MaterialType.CORRUGATED_BOX;

    public RegisterPackageMaterialApi name(final String name) {
        this.name = name;
        return this;
    }

    public RegisterPackageMaterialApi code(final String code) {
        this.code = code;
        return this;
    }

    public RegisterPackageMaterialApi innerWidthInMillimeters(final Long innerWidthInMillimeters) {
        this.innerWidthInMillimeters = innerWidthInMillimeters;
        return this;
    }

    public RegisterPackageMaterialApi innerHeightInMillimeters(final Long innerHeightInMillimeters) {
        this.innerHeightInMillimeters = innerHeightInMillimeters;
        return this;
    }

    public RegisterPackageMaterialApi innerLengthInMillimeters(final Long innerLengthInMillimeters) {
        this.innerLengthInMillimeters = innerLengthInMillimeters;
        return this;
    }

    public RegisterPackageMaterialApi outerWidthInMillimeters(final Long outerWidthInMillimeters) {
        this.outerWidthInMillimeters = outerWidthInMillimeters;
        return this;
    }

    public RegisterPackageMaterialApi outerHeightInMillimeters(final Long outerHeightInMillimeters) {
        this.outerHeightInMillimeters = outerHeightInMillimeters;
        return this;
    }

    public RegisterPackageMaterialApi outerLengthInMillimeters(final Long outerLengthInMillimeters) {
        this.outerLengthInMillimeters = outerLengthInMillimeters;
        return this;
    }

    public RegisterPackageMaterialApi weightInGrams(final Long weightInGrams) {
        this.weightInGrams = weightInGrams;
        return this;
    }

    public RegisterPackageMaterialApi maxWeightInGrams(final Long maxWeightInGrams) {
        this.maxWeightInGrams = maxWeightInGrams;
        return this;
    }

    public RegisterPackageMaterialApi materialType(final MaterialType materialType) {
        this.materialType = materialType;
        return this;
    }

    public Scenario request() {
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

        return new Scenario();
    }
}
