package ru.fastdelivery.domain.delivery.shipment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.dimensions.Length;
import ru.fastdelivery.domain.common.dimensions.OuterDimensions;
import ru.fastdelivery.domain.common.route.GeoPoint;
import ru.fastdelivery.domain.common.route.Route;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShipmentTest {

    private static Shipment shipment;
    @Test
    void whenSummarizingWeightOfAllPackages_thenReturnSum() {
        var weight1 = new Weight(BigInteger.TEN);
        var weight2 = new Weight(BigInteger.ONE);
        var dimension1 = new OuterDimensions(new Length(1400), new Length(932), new Length(879));
        var dimension2 = new OuterDimensions(new Length(1123), new Length(753), new Length(650));
        var packages = List.of(new Pack(weight1, dimension1), new Pack(weight2, dimension2));

        var route = Mockito.mock(Route.class);
        var shipment = new Shipment(packages, new CurrencyFactory(code -> true).create("RUB"), route);
        var massOfShipment = shipment.weightAllPackages();

        assertThat(massOfShipment.weightGrams()).isEqualByComparingTo(BigInteger.valueOf(11));
    }


}