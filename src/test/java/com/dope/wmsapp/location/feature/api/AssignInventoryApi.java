package com.dope.wmsapp.location.feature.api;

import com.dope.wmsapp.common.Scenario;
import com.dope.wmsapp.location.feature.AssignInventory;
import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;

public class AssignInventoryApi {
    private String locationBarcode = "A-1-1";
    private String lpnBarcode = "LPN-1";

    public AssignInventoryApi withLocationBarcode(final String locationBarcode) {
        this.locationBarcode = locationBarcode;
        return this;
    }

    public AssignInventoryApi withLPNBarcode(final String lpnBarcode) {
        this.lpnBarcode = lpnBarcode;
        return this;
    }

    public Scenario request() {
        final AssignInventory.Request request = new AssignInventory.Request(
                locationBarcode,
                lpnBarcode
        );

        RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/locations/assign-inventory")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        return new Scenario();
    }
}