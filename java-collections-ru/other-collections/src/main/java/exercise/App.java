package exercise;

import java.util.*;

class App {

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> firstDict,
                                                        Map<String, Object> secondDict) {
        Set<String> firstSet = new HashSet<>(firstDict.keySet());
        Set<String> secondSet = new HashSet<>(secondDict.keySet());

        Set<String> added = new HashSet<>(secondSet);
        added.removeAll(firstSet);

        Set<String> deleted = new HashSet<>(firstSet);
        deleted.removeAll(secondSet);

        Set<String> intersection = new HashSet<>(firstSet);
        intersection.retainAll(secondSet);

        Set<String> union = new TreeSet<>(firstSet);
        union.addAll(secondSet);

        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        for (String key : union) {
            if (added.contains(key)) {
                result.put(key, "added");
            } else if (deleted.contains(key)) {
                result.put(key, "deleted");
            } else if (intersection.contains(key) && firstDict.get(key).equals(secondDict.get(key))) {
                result.put(key, "unchanged");
            } else {
                result.put(key, "changed");
            }
        }
        return result;
    }
}
