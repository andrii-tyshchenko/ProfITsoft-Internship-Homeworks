package task3;

import task3.shapes.*;

import java.util.Comparator;
import java.util.List;

public class AppForSortingShapesByVolume {
    public static void main(String[] args) {
        try {
            Shape3D cube = new Cube(5);
            Shape3D cube2 = new Cube(2.5);
            Shape3D cylinder = new Cylinder(1, 10);
            Shape3D ball = new Ball(4);

            List<Shape3D> shapes = List.of(cube, cube2, cylinder, ball);

            List<Shape3D> shapesSortedByVolume = sortShapesByVolume(shapes);

            shapesSortedByVolume.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns list of 3D shapes, ascendant sorted by their volume, from given list.
     * @param shapes - list of 3D shapes (ball, cube, cylinder).
     * @return list of 3D shapes, sorted by their volume (ascendant).
     */
    public static List<Shape3D> sortShapesByVolume(List<Shape3D> shapes) {
        if (shapes == null) {
            throw new NullPointerException("List of shapes must not be null");
        }

        Comparator<Shape3D> comparatorByVolume = Comparator.comparingDouble(Shape3D::calculateVolume);

        return shapes.stream()
                .sorted(comparatorByVolume)
                .toList();
    }
}