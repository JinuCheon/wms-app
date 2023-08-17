package com.dope.wmsapp.location.feature;

import com.dope.wmsapp.inbound.domain.LPN;
import com.dope.wmsapp.inbound.domain.LPNRepository;
import com.dope.wmsapp.location.domain.Location;
import com.dope.wmsapp.location.domain.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Component
class AssignLocationLPN {
    private final LocationRepository locationRepository;
    private final LPNRepository lpnRepository;

    @Transactional
    public void request(final Request request) {
        final Location location = locationRepository.getByLocationBarcode(request.locationBarcode);
        final LPN lpn = lpnRepository.getLPNByLPNBarcode(request.locationBarcode);

        location.assignLPN(lpn);
    }

    public record Request(String locationBarcode, String lpnBarcode) {
        public Request {
            Assert.hasText(locationBarcode, "locationBarcode must not be empty");
            Assert.hasText(lpnBarcode, "lpnBarcode must not be empty");
        }
    }
}
