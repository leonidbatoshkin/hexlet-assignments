package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class App {
    public static void swapKeyValue(KeyValueStorage db) {
        for (var key : db.toMap().keySet()) {
            var tempValue = db.get(key, "default");
            db.unset(key);
            db.set(tempValue, key);
        }
    }
}
