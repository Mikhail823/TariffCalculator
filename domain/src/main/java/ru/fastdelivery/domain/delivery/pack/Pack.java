package ru.fastdelivery.domain.delivery.pack;

import ru.fastdelivery.domain.common.dimensions.Length;
import ru.fastdelivery.domain.common.dimensions.OuterDimensions;
import ru.fastdelivery.domain.common.weight.Weight;

import java.math.BigInteger;

/**
 * Упаковка груза
 *
 * @param weight вес товаров в упаковке
 * @param volume габариты упаковки
 */
public record Pack(Weight weight, OuterDimensions volume) {

    private static final Weight maxWeight = new Weight(BigInteger.valueOf(150_000));

    public Pack {
        if (weight.greaterThan(maxWeight)) {
            throw new IllegalArgumentException("Package can't be more than " + maxWeight);
        }
    }
}
