package dev.as.carfinder.car;

import dev.as.carfinder.bodytype.BodyType;
import dev.as.carfinder.brand.Brand;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecifications {

    public static Specification<Car> hasBrand(Long brandId) {
        return (root, query, cb) -> brandId == null ? null : cb.equal(root.get("brand").get("id"), brandId);
    }

    public static Specification<Car> hasBodyType(Long bodyTypeId) {
        return (root, query, cb) -> bodyTypeId == null ? null : cb.equal(root.get("bodyType").get("id"), bodyTypeId);
    }

    public static Specification<Car> priceBetween(Double minPrice, Double maxPrice) {
        return (root, query, cb) -> {
            if (minPrice != null && maxPrice != null) return cb.between(root.get("price"), minPrice, maxPrice);
            if (minPrice != null) return cb.greaterThanOrEqualTo(root.get("price"), minPrice);
            if (maxPrice != null) return cb.lessThanOrEqualTo(root.get("price"), maxPrice);
            return null;
        };
    }

    public static Specification<Car> locationLike(String location) {
        return (root, query, cb) -> location == null ? null : cb.like(cb.lower(root.get("location")), "%" + location.toLowerCase() + "%");
    }

    public static Specification<Car> driveTypeEquals(String driveType) {
        return (root, query, cb) -> driveType == null ? null : cb.equal(cb.lower(root.get("driveType")), driveType.toLowerCase());
    }
    public static Specification<Car> conditionEquals(String condition) {
        return (root, query, cb) ->
                condition == null ? null : cb.equal(cb.lower(root.get("condition")), condition.toLowerCase());
    }

}

