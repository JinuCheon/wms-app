package com.dope.wmsapp.location.feature.api;

import com.dope.wmsapp.common.Scenario;
import com.dope.wmsapp.location.feature.AssignLocationLPN;
import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;

public class AssignLocationLPNApi {
    private String locationBarcode = "A-1-1";
    private String lpnBarcode = "LPN-1";

    public AssignLocationLPNApi withLocationBarcode(final String locationBarcode) {
        this.locationBarcode = locationBarcode;
        return this;
    }

    public AssignLocationLPNApi withLPNBarcode(final String lpnBarcode) {
        this.lpnBarcode = lpnBarcode;
        return this;
    }

    public Scenario request() {
        final AssignLocationLPN.Request request = new AssignLocationLPN.Request(
                locationBarcode,
                lpnBarcode
        );

        RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/locations/assign-lpn")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        return new Scenario();
    }
}