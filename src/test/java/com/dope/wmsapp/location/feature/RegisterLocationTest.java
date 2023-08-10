package com.dope.wmsapp.location.feature;

import groovyjarjarantlr4.v4.gui.TreeViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

class RegisterLocationTest {

    private RegisterLocation registerLocation;

    @BeforeEach
    void setUp() {
        registerLocation = new RegisterLocation();
    }

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
        registerLocation.request(request);


    }
    private class RegisterLocation {
        private LocationRepository locationRepository;

        public void request(final Request request) {
            final Location location = request.toDomain();
            locationRepository.save(location);
        }

        public record Request(String locationBarcode,
                              StorageType storageType,
                              UsagePurpose usagePurpose) {
            public Request {
                Assert.hasText(locationBarcode, "로케이션 바코드는 필수입니다.");
                Assert.notNull(storageType, "로케이션 타입은 필수입니다.");
                Assert.notNull(usagePurpose, "보관 목적은 필수입니다.");
            }

            public Location toDomain() {
                return new Location(
                        locationBarcode,
                        storageType,
                        usagePurpose
                );
            }
        }
    }

    enum StorageType {
        TOTE("토트 바구니");
        private final String description;

        StorageType(String description) {
            this.description = description;
        }
    }

    enum UsagePurpose {
        MOVE("이동 목적");
        private final String description;

        UsagePurpose(String description) {
            this.description = description;
        }
    }

    private static class Location {
        private final String locationBarcode;
        private final StorageType storageType;
        private final UsagePurpose usagePurpose;
        private Long locationNo;

        public Location(final String locationBarcode, final StorageType storageType, final UsagePurpose usagePurpose) {
            validateConstructor(locationBarcode, storageType, usagePurpose);
            this.locationBarcode = locationBarcode;
            this.storageType = storageType;
            this.usagePurpose = usagePurpose;
        }

        private static void validateConstructor(final String locationBarcode, final StorageType storageType, final UsagePurpose usagePurpose) {
            Assert.notNull(locationBarcode, "로케이션 바코드는 필수입니다.");
            Assert.notNull(storageType, "로케이션 타입은 필수입니다.");
            Assert.notNull(usagePurpose, "보관 목적은 필수입니다.");
        }

        public void assignNo(final Long locationNo) {
            this.locationNo = locationNo;
        }

        public Long getLocationNo() {
            return locationNo;
        }
    }

    private class LocationRepository {
        private final Map<Long, Location> locations = new HashMap<>();
        private Long sequence = 1L;

        public void save(final Location location) {
            location.assignNo(sequence++);
            locations.put(location.getLocationNo(), location);
        }
    }
}
