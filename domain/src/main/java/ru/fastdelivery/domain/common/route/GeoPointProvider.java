package ru.fastdelivery.domain.common.route;

public interface GeoPointProvider {
    boolean isLongitudeThePointLimits(double longitude);
    boolean isLatitudeThePointLimits(double latitude);
}
