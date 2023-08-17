package com.dope.wmsapp.location.feature;

import com.dope.wmsapp.inbound.domain.LPN;
import com.dope.wmsapp.inbound.domain.LPNRepository;
import com.dope.wmsapp.location.domain.Location;
import com.dope.wmsapp.location.domain.LocationRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AssignInventory {
    private final LocationRepository locationRepository;
    private final LPNRepository lpnRepository;

    @Transactional
    @PostMapping("/locations/assign-inventory")
    public void request(@RequestBody @Valid final Request request) {
        final Location location = locationRepository.getByLocationBarcode(request.locationBarcode);
        final LPN lpn = lpnRepository.getLPNByLPNBarcode(request.locationBarcode);

        location.assignLPN(lpn);
    }

    public record Request(
            @NotNull(message = "로케이션 바코드는 필수입니다.")
            String locationBarcode,
            @NotNull(message = "LPN 바코드는 필수입니다.")
            String lpnBarcode) {
        public Request {
        }
    }
}
