package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import javax.inject.Named;


@Named
@RequiredArgsConstructor

public class TariffCalculateUseCase {
    private final WeightPriceProvider weightPriceProvider;

    public Price calc(Shipment shipment) {
        var weightAllPackagesKg = weightPriceProvider.costPerKg().multiply(shipment.weightAllPackages().kilograms());
        var volumeAllPackages = weightPriceProvider.costPerKubM().multiply(shipment.volumeAllPackages());
        var minimalPrice = weightPriceProvider.minimalPrice();
        return weightAllPackagesKg.max(volumeAllPackages).max(minimalPrice);
    }

    public Price minimalPrice() {
        return weightPriceProvider.minimalPrice();
    }
}
