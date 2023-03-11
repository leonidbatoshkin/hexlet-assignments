package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> mansList) {
        return mansList.stream()
                .sorted(Comparator.comparing(birthday -> birthday.get("birthday")))
                .filter(gender -> gender.get("gender").equals("male"))
                .map(name -> name.get("name"))
                .collect(Collectors.toList());
    }
}