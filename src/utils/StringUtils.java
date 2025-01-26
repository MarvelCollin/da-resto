package utils;

import java.util.List;
import java.util.function.Function;

public class StringUtils {
    public static <T> String joinInitials(List<T> items, Function<T, String> getInitial) {
        StringBuilder result = new StringBuilder();
        for(T item : items) {
            result.append(getInitial.apply(item));
        }
        return result.toString();
    }
}
