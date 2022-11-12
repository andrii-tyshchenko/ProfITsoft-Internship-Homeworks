package task2;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppForTop5HashTags {
    public static void main(String[] args) {
        List<String> strings = List.of(
                "some text #super #books #cool",
                "abra cadabra #ukraine #love best #cool",
                "string without hashtags",
                "#java #love #love #cool",
                "vote me #instagram #sport",
                "another one string",
                "",
                "lorem ipsum #photo todo #books",
                "tuna fish #cool #happy",
                "#beautiful #style #ukraine",
                "#top #music",
                "lazy dog #top #books",
                "books #books books #top",
                "#sport sport"
        );

        try {
            Map<String, Long> top5HashTags = getTop5HashTags(strings);

            top5HashTags.forEach((key, value) -> System.out.println(String.format("%s: %d time(s)", key, value)));
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns Map of top 5 hashtags (words, which start with "#" sign), found in given list of strings,
     * where key - is the hashtag, value - is a count of appearance.
     * If some hashtag appears in the same string twice or more, it is counted only once for this string.
     * Order of values - descendant, of keys - alphabetical.
     * @param strings - list of strings, which may contain hashtags.
     * @return Map, containing top 5 hashtags (key - hashtag, value - count of its appearance).
     */
    public static Map<String, Long> getTop5HashTags(List<String> strings) {
        if (strings == null) {
            throw new NullPointerException("List of strings must not be null");
        }

        return strings.stream()
                .map(s -> s.split(" "))
                .flatMap(words -> Arrays.stream(words)
                        .distinct())
                .filter(word -> word.startsWith("#") && word.length() > 2)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }
}