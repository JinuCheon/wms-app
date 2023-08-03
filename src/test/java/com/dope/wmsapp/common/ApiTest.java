package com.dope.wmsapp.common;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApiTest {

    @LocalServerPort
    private int port;
    @Autowired DatabaseCleaner databaseCleaner;

    @BeforeEach
    void setUp() {
        if(RestAssured.UNDEFINED_PORT == RestAssured.port) {
            RestAssured.port = port;
            databaseCleaner.afterPropertiesSet();
        }
        databaseCleaner.execute();
    }
}
