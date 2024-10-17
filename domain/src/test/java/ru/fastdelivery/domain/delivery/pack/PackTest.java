package ru.fastdelivery.domain.delivery.pack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.dimensions.Length;
import ru.fastdelivery.domain.common.dimensions.OuterDimensions;
import ru.fastdelivery.domain.common.weight.Weight;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PackTest {

    @Test
    void whenWeightMoreThanMaxWeight_thenThrowException() {
        var weight = new Weight(BigInteger.valueOf(150_001));
        var dimensions =
                new OuterDimensions(new Length(456), new Length(1250), new Length(150));
        assertThatThrownBy(() -> new Pack(weight, dimensions))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWeightLessThanMaxWeight_thenObjectCreated() {
        var dimensions =
                new OuterDimensions(new Length(1400), new Length(1250), new Length(150));
        var actual = new Pack(new Weight(BigInteger.valueOf(1_000)), dimensions);
        assertThat(actual.weight()).isEqualTo(new Weight(BigInteger.valueOf(1_000)));
    }

    @Test
    void whenTheDimensionsOfThePackageAreNotAcceptable(){
        assertThatThrownBy(()-> new Length(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()-> new Length(1501)).isInstanceOf(IllegalArgumentException.class);
    }
}