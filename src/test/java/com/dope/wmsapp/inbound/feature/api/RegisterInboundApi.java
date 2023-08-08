package com.dope.wmsapp.inbound.feature.api;

import com.dope.wmsapp.common.Scenario;
import com.dope.wmsapp.inbound.feature.RegisterInbound;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class RegisterInboundApi {
    private String title = "title";
    private String discription = "discription";
    private LocalDateTime orderRequestedAt = LocalDateTime.now();
    private LocalDateTime estimatedArrivalAt = LocalDateTime.now().plusDays(1);
    private List<RegisterInbound.Request.Item> inboundItems = List.of(
            new RegisterInbound.Request.Item(
                    1L,
                    1L,
                    1500L,
                    "description"
            ));

    public RegisterInboundApi title(String title) {
        this.title = title;
        return this;
    }

    public RegisterInboundApi discription(String discription) {
        this.discription = discription;
        return this;
    }

    public RegisterInboundApi orderRequestedAt(LocalDateTime orderRequestedAt) {
        this.orderRequestedAt = orderRequestedAt;
        return this;
    }

    public RegisterInboundApi estimatedArrivalAt(LocalDateTime estimatedArrivalAt) {
        this.estimatedArrivalAt = estimatedArrivalAt;
        return this;
    }

    public RegisterInboundApi inboundItems(RegisterInbound.Request.Item... inboundItems) {
        this.inboundItems = List.of(inboundItems);
        return this;
    }

    public Scenario request() {
        RegisterInbound.Request request = new RegisterInbound.Request(
                title,
                discription,
                orderRequestedAt,
                estimatedArrivalAt,
                inboundItems
        );

        // 입고 요청에 대한 정보 레코드. 동 시점에 들어오는 입고들의 정보를 가지고 있음.

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/inbounds")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        return new Scenario();
    }
}
