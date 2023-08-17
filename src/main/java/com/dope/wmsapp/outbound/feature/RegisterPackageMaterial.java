package com.dope.wmsapp.outbound.feature;

import com.dope.wmsapp.outbound.domain.MaterialType;
import com.dope.wmsapp.outbound.domain.PackageMaterialRepository;
import com.dope.wmsapp.outbound.domain.PackagingMaterial;
import com.dope.wmsapp.outbound.domain.PackagingMaterialDimension;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class RegisterPackageMaterial {

    private final PackageMaterialRepository packagingMaterialRepository;

    @PostMapping("/package-materials")
    @ResponseStatus(HttpStatus.CREATED)
    public void request(@RequestBody final Request request) {
        final PackagingMaterial packagingMaterial = request.toDomain();
        packagingMaterialRepository.save(packagingMaterial);
    }

    record Request(
            @NotNull(message = "name must not be empty")
            String name,
            @NotNull(message = "code must not be empty")
            String code,
            @NotNull(message = "innerWidthInMillimeters must not be null")
            @Min(value = 1, message = "innerWidthInMillimeters must be greater than 1")
            Long innerWidthInMillimeters,
            @NotNull(message = "innerHeightInMillimeters must not be null")
            @Min(value = 1, message = "innerHeightInMillimeters must be greater than 1")
            Long innerHeightInMillimeters,
            @NotNull(message = "innerLengthInMillimeters must not be null")
            @Min(value = 1, message = "innerLengthInMillimeters must be greater than 1")
            Long innerLengthInMillimeters,
            @NotNull(message = "outerWidthInMillimeters must not be null")
            @Min(value = 1, message = "outerWidthInMillimeters must be greater than 1")
            Long outerWidthInMillimeters,
            @NotNull(message = "outerHeightInMillimeters must not be null")
            @Min(value = 1, message = "outerHeightInMillimeters must be greater than 1")
            Long outerHeightInMillimeters,
            @NotNull(message = "outerLengthInMillimeters must not be null")
            @Min(value = 1, message = "outerLengthInMillimeters must be greater than 1")
            Long outerLengthInMillimeters,
            @NotNull(message = "weightInGrams must not be null")
            @Min(value = 1, message = "weightInGrams must be greater than 1")
            Long weightInGrams,
            @NotNull(message = "maxWeightInGrams must not be null")
            @Min(value = 1, message = "maxWeightInGrams must be greater than 1")
            Long maxWeightInGrams,
            @NotNull(message = "materialType must not be null")
            MaterialType materialType) {

        public PackagingMaterial toDomain() {
            return new PackagingMaterial(
                    name,
                    code,
                    new PackagingMaterialDimension(
                            innerWidthInMillimeters,
                            innerHeightInMillimeters,
                            innerLengthInMillimeters,
                            outerWidthInMillimeters,
                            outerHeightInMillimeters,
                            outerLengthInMillimeters
                    ),
                    weightInGrams,
                    maxWeightInGrams,
                    materialType
            );
        }

    }


}
