package ru.fastdelivery.domain.common.dimensions;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record OuterDimensions(Length length,
                              Length width,
                              Length height) {


    public Double cubimetrCalc(){
        double value = length.volumeCalculator().value()
                * width.volumeCalculator().value() * height.volumeCalculator().value() / 1_000_000_000d;
        return new BigDecimal(Double.toString(value)).setScale(4, RoundingMode.HALF_UP).doubleValue();
    }
}
