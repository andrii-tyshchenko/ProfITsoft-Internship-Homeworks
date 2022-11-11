package task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task3.shapes.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static task3.AppForSortingShapesByVolume.*;

class AppForSortingShapesByVolumeTest {
    private List<Shape3D> shapes;

    @Test
    @DisplayName("Standard test")
    public void testSortShapesByVolume() {
        Cube cube = new Cube(3);
        Cylinder cylinder = new Cylinder(1, 2);
        Ball ball = new Ball(5);

        shapes = List.of(cube, cylinder, ball);

        List<Shape3D> expectedResult = List.of(cylinder, cube, ball);

        assertEquals(expectedResult, sortShapesByVolume(shapes));
    }

    @Test
    @DisplayName("Test for null")
    public void testSortShapesByVolumeForNull() {
        shapes = null;

        Exception actualException = assertThrows(NullPointerException.class, () -> sortShapesByVolume(shapes));

        String expectedExceptionMessage = "List of shapes must not be null";

        assertEquals(expectedExceptionMessage, actualException.getMessage());
    }
}