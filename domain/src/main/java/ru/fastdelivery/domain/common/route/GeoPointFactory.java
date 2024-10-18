package ru.fastdelivery.domain.common.route;

import lombok.RequiredArgsConstructor;

/**
 * Создание точки по географическим координатам с проверками исходных данных
 */
@RequiredArgsConstructor
public class GeoPointFactory{

    private final GeoPointProvider geoPointProvider;
    private final static double PI = 3.14159265358979;

    public GeoPoint create(double latitude, double longitude){
        if (geoPointProvider.isLatitudeThePointLimits(latitude)){
            throw new IllegalArgumentException("Latitude value is out of limits or not set!");
        }

        if (geoPointProvider.isLongitudeThePointLimits(longitude)){
            throw new IllegalArgumentException("Longitude value is out of limits or not set!");
        }

        return new GeoPoint(radians(latitude), radians(longitude));
    }

    public double radians(double coord){
        return coord * PI / 180;
    }

}
