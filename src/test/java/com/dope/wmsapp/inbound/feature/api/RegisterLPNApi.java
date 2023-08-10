package com.dope.wmsapp.inbound.feature.api;

import com.dope.wmsapp.inbound.feature.RegisterLPN;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class RegisterLPNApi {
    private Long inboundItemNo = 1L;
    private String lpnBarcode = "LPN-0001";
    private LocalDateTime expirationAt = LocalDateTime.now().plusDays(1L);

    public RegisterLPNApi inboundItemNo(Long inboundItemNo) {
        this.inboundItemNo = inboundItemNo;
        return this;
    }

    public RegisterLPNApi lpnBarcode(String lpnBarcode) {
        this.lpnBarcode = lpnBarcode;
        return this;
    }

    public RegisterLPNApi expirationAt(LocalDateTime expirationAt) {
        this.expirationAt = expirationAt;
        return this;
    }

    public void request() {
        RegisterLPN.Request request = new RegisterLPN.Request(
                lpnBarcode,
                expirationAt
        );
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/inbound/inbound-items/{inboundItemNo}/lpn", inboundItemNo)
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
