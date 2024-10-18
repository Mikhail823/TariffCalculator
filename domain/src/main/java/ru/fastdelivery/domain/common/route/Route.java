package ru.fastdelivery.domain.common.route;


public record Route(GeoPoint departure, GeoPoint destination) {
    private static final int RADIUS = 6372795;

    public int distance(){
        var delta = destination.getLongitude() - departure.getLatitude();
        var longitudeSine = Math.sin(delta);
        var longitudeCosine = Math.cos(delta);
        var num = Math.sqrt(
                Math.pow(destination.getLatitudeCosine() * longitudeSine, 2) +
                        Math.pow(departure.getLatitudeCosine() * destination.getLatitudeSine() -
                                departure.getLatitudeSine() * destination.getLatitudeCosine() * longitudeCosine, 2)
        );

        var denominator = departure.getLatitudeSine() * destination.getLatitudeSine() +
                departure.getLatitudeCosine() * destination.getLatitudeCosine() * longitudeCosine;
        var angleDist = Math.atan2(num, denominator);
        return (int) Math.round(angleDist * RADIUS / 1000);

    }
}


