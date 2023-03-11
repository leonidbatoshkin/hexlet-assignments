package exercise;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> mansList) {
        return mansList.stream()
                .filter(gender -> gender.get("gender").equals("male"))
                .sorted((birthday1, birthday2) -> {
                    LocalDate date1 = LocalDate.parse(birthday1.get("birthday"));
                    LocalDate date2 = LocalDate.parse(birthday2.get("birthday"));
                    return date1.compareTo(date2);
                })
                .map(name -> name.get("name"))
                .collect(Collectors.toList());
    }
}