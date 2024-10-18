package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;


@Named
@RequiredArgsConstructor

public class TariffCalculateUseCase {
    private final WeightPriceProvider weightPriceProvider;
    private static final int MIN_ROUTE = 450;

    public Price calc(Shipment shipment) {
        var weightAllPackagesKg = weightPriceProvider.costPerKg().multiply(shipment.weightAllPackages().kilograms());
        var volumeAllPackages = weightPriceProvider.costPerKubM().multiply(shipment.volumeAllPackages());
        var minimalPrice = weightPriceProvider.minimalPrice();
        var basePrice = weightAllPackagesKg.max(volumeAllPackages).max(minimalPrice);
        return routePrice(shipment.routeLength(), basePrice);
    }

    Price routePrice(int routeLength, Price price) {
        if (routeLength > MIN_ROUTE) {
            var amount = price.amount()
                    .multiply(BigDecimal.valueOf((double) routeLength / MIN_ROUTE))
                    .setScale(2, RoundingMode.CEILING);
            price = new Price(amount, price.currency());
        }
        return price;
    }

    public Price minimalPrice() {
        return weightPriceProvider.minimalPrice();
    }
}
