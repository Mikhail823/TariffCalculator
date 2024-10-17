package ru.fastdelivery.domain.common.dimensions;

public record Length(Integer value) {

    public static final int ROUNDING_UP = 50;

    public Length {
        if (!isLengthChecked(value)){
            throw new IllegalArgumentException("The dimensions of the package" +
                    " do not correspond to the permissible values.");
        }
    }

    public static boolean isLengthChecked(Integer length){
        return length >= 0 && length <= 1500;
    }

    private Integer roundUp(Integer value) {
        return value % ROUNDING_UP == 0 ? value : (value / ROUNDING_UP + 1) * ROUNDING_UP;
    }

    public Length volumeCalculator(){
        return new Length(roundUp(this.value));
    }

}
