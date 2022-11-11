package task3.shapes;

public class Ball implements Shape3D {
    private double radius;

    public Ball(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Shape dimensions can't be negative or zero");
        }

        this.radius = radius;
    }

    @Override
    public double calculateVolume() {
        return 4d / 3 * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public String toString() {
        return "Ball{" +
                "radius=" + radius +
                ", volume=" + calculateVolume() +
                '}';
    }
}