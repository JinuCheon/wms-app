package com.dope.wmsapp.product.feature;

import com.dope.wmsapp.product.domain.Category;
import com.dope.wmsapp.product.domain.ProductRepository;
import com.dope.wmsapp.product.domain.TemperatureZone;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RegisterProductTest {

    @LocalServerPort
    private int port;

    @Autowired private RegisterProduct registerProduct;
    @Autowired private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        if(RestAssured.UNDEFINED_PORT == RestAssured.port) {
            RestAssured.port = port;
        }
    }

    @Test
    @DisplayName("상품을 등록한다.")
    void registerProduct() {
        //given
        final Long weightInGrams = 1000L;
        final Long widthInMillimeters = 100L;
        final Long heightInMillimeters = 100L;
        final Long lengthInMillimeters = 100L;
        final String name = "name";
        final String code = "code";
        final String description = "description";
        final String brand = "brand";
        final String maker = "maker";
        final String origin = "origin";
        final RegisterProduct.Request request = new RegisterProduct.Request(
                name,
                code,
                description,
                brand,
                maker,
                origin,
                Category.ELECTRONICS,
                TemperatureZone.ROOM_TEMPERATURE,
                weightInGrams, //gram
                widthInMillimeters, //너비 mm
                heightInMillimeters, //높이 mm
                lengthInMillimeters //길이 mm
        );

        //when
//        registerProduct.request(request);
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/products")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        //then
        assertThat(productRepository.findAll()).hasSize(1);
    }

}
