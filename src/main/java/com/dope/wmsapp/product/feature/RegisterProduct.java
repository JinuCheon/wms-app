package com.dope.wmsapp.product.feature;

import com.dope.wmsapp.product.domain.Category;
import com.dope.wmsapp.product.domain.Product;
import com.dope.wmsapp.product.domain.ProductRepository;
import com.dope.wmsapp.product.domain.ProductSize;
import com.dope.wmsapp.product.domain.TemperatureZone;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class RegisterProduct {
    private final ProductRepository productRepository;

    public RegisterProduct(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void request(@RequestBody final Request request) {
        validateProductCodeExists(request.code);
        Product product = request.toDomain();
        productRepository.save(product);
    }

    private void validateProductCodeExists(final String code) {
        productRepository.findAll().stream()
                .filter(product -> product.getCode().equals(code))
                .findFirst()
                .ifPresent(product -> {
                    throw new IllegalArgumentException("이미 등록된 상품입니다.");
                });
    }

    public record Request(
            @NotBlank(message = "상품명은 필수입니다.")
            String name,
            @NotBlank(message = "상품코드는 필수입니다.")
            String code,
            @NotBlank(message = "상품설명은 필수입니다.")
            String description,
            @NotBlank(message = "브랜드는 필수입니다.")
            String brand,
            @NotBlank(message = "제조사는 필수입니다.")
            String maker,
            @NotBlank(message = "원산지는 필수입니다.")
            String origin,
            @NotBlank(message = "카테고리는 필수입니다.")
            Category category,
            @NotBlank(message = "온도대는 필수입니다.")
            TemperatureZone temperatureZone,
            @NotBlank(message = "무게는 필수입니다.")
            @Min(value = 0, message = "무게는 0보다 커야합니다.")
            Long weightInGrams,
            @NotBlank(message = "상품사이즈는 필수입니다.")
            @Min(value = 0, message = "너비는 0보다 커야합니다.")
            Long widthInMillimeters,
            @NotBlank(message = "상품사이즈는 필수입니다.")
            @Min(value = 0, message = "높이는 0보다 커야합니다.")
            Long heightInMillimeters,
            @NotBlank(message = "상품사이즈는 필수입니다.")
            @Min(value = 0, message = "길이는 0보다 커야합니다.")
            Long lengthInMillimeters) {
        public Request {
            Assert.hasText(name, "상품명은 필수입니다.");
            Assert.hasText(code, "상품코드는 필수입니다.");
            Assert.hasText(description, "상품설명은 필수입니다.");
            Assert.hasText(brand, "브랜드는 필수입니다.");
            Assert.hasText(maker, "제조사는 필수입니다.");
            Assert.hasText(origin, "원산지는 필수입니다.");
            Assert.notNull(category, "카테고리는 필수입니다.");
            Assert.notNull(temperatureZone, "온도대는 필수입니다.");
            Assert.notNull(weightInGrams, "무게는 필수입니다.");
            if (0 > weightInGrams) {
                throw new IllegalArgumentException("무게는 0보다 커야합니다.");
            }
            Assert.notNull(widthInMillimeters, "너비는 필수입니다.");
            if (0 > widthInMillimeters) {
                throw new IllegalArgumentException("너비는 0보다 커야합니다.");
            }
            Assert.notNull(heightInMillimeters, "높이는 필수입니다.");
            if (0 > heightInMillimeters) {
                throw new IllegalArgumentException("세로길이는 0보다 커야합니다.");
            }
            Assert.notNull(lengthInMillimeters, "세로길이는 필수입니다.");
            if (0 > lengthInMillimeters) {
                throw new IllegalArgumentException("가로길이는 0보다 커야합니다.");
            }

        }

        public Product toDomain() {
            return new Product(
                    name,
                    code,
                    description,
                    brand,
                    maker,
                    origin,
                    category,
                    temperatureZone,
                    weightInGrams,
                    new ProductSize(widthInMillimeters, heightInMillimeters, lengthInMillimeters)
            );
        }

    }
}
