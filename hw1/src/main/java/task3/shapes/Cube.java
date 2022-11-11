package task3.shapes;

public class Cube implements Shape3D {
    private double side;

    public Cube(double side) {
        if (side <= 0 ) {
            throw new IllegalArgumentException("Shape's dimensions can't be negative or zero");
        }

        this.side = side;
    }

    @Override
    public double calculateVolume() {
        return Math.pow(side, 3);
    }

    @Override
    public String toString() {
        return "Cube{" +
                "side=" + side +
                ", volume=" + calculateVolume() +
                '}';
    }
}