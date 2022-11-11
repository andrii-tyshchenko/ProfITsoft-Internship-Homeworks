package task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static task2.AppForTop5HashTags.*;

class AppForTop5HashTagsTest {
    private List<String> strings;

    @Test
    @DisplayName("Standard test")
    public void testGetTop5HashTags() {
        strings = List.of(
                "#hashtag #hashtag",
                "  ",
                "-",
                "some #hashtag",
                "#city #guide",
                " #guide",
                "lorem ipsum",
                "in nomine patris #et fili",
                " #rest "
        );

        Map<String, Long> expectedResult = new LinkedHashMap<>();
        expectedResult.put("#guide", 2L);
        expectedResult.put("#hashtag", 2L);
        expectedResult.put("#city", 1L);
        expectedResult.put("#et",  1L);
        expectedResult.put("#rest", 1L);

        assertEquals(expectedResult, getTop5HashTags(strings));
    }

    @Test
    @DisplayName("Test for short hashtag values")
    public void testGetTop5HashTagsForShortValues() {
        strings = List.of(
                "#hashtag",
                "#hashta",
                "#hasht",
                "#hash",
                "#has",
                "#ha",
                "#h",
                "#",
                " ",
                ""
        );

        Map<String, Long> expectedResult = new LinkedHashMap<>();
        expectedResult.put("#ha", 1L);
        expectedResult.put("#has", 1L);
        expectedResult.put("#hash", 1L);
        expectedResult.put("#hasht",  1L);
        expectedResult.put("#hashta", 1L);

        assertEquals(expectedResult, getTop5HashTags(strings));
    }

    @Test
    @DisplayName("Test for null")
    public void testGetTop5HashTagsForFromNull() {
        strings = null;

        Exception actualException = assertThrows(NullPointerException.class, ()-> getTop5HashTags(strings));

        String expectedExceptionMessage = "List of strings must not be null";

        assertEquals(expectedExceptionMessage, actualException.getMessage());
    }
}