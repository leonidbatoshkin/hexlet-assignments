package exercise;

import java.util.Map;

class Circle {
    private int radius;

    public Circle(Point point, int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (this.radius < 0) {
            throw new NegativeRadiusException("The radius can't be lesser than 0.");
        }
        return Math.pow(radius, 2) * Math.PI;
    }
}
