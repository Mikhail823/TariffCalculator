package ru.fastdelivery.properties.provider;


import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.fastdelivery.domain.common.route.GeoPointProvider;

@Configuration
@ConfigurationProperties("coordinates")
@Setter
public class GeoPointProperties implements GeoPointProvider {
    private double latitudeMinValue;
    private double latitudeMaxValue;
    private double longitudeMinValue;
    private double longitudeMaxValue;

    @Override
    public boolean isLongitudeThePointLimits(double longitude) {
        return longitude < longitudeMinValue || longitude > longitudeMaxValue;
    }

    @Override
    public boolean isLatitudeThePointLimits(double latitude) {
        return latitude < latitudeMinValue || latitude > latitudeMaxValue;
    }
}
