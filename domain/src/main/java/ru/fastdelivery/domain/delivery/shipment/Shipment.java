package ru.fastdelivery.domain.delivery.shipment;

import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.dimensions.Length;
import ru.fastdelivery.domain.common.dimensions.OuterDimensions;
import ru.fastdelivery.domain.common.route.Route;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @param packages упаковки в грузе
 * @param currency валюта объявленная для груза
 */
public record Shipment(
        List<Pack> packages,
        Currency currency,
        Route route) {
    public Weight weightAllPackages() {
        return packages.stream()
                .map(Pack::weight)
                .reduce(Weight.zero(), Weight::add);
    }

    public BigDecimal volumeAllPackages(){
        double totalVolume =  packages.stream().
                map(Pack::volume)
                .map(OuterDimensions::cubimetrCalc)
                .reduce(0.0, Double::sum);
        return new BigDecimal(Double.toString(totalVolume)).setScale(4, RoundingMode.HALF_UP);
    }

    public int routeLength(){
        return this.route().distance();
    }
}
