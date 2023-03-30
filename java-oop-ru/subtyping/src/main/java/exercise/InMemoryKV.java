package exercise;

import java.util.Map;
import java.util.HashMap;

class InMemoryKV implements KeyValueStorage {
    private final HashMap<String, String> hm;

    public InMemoryKV(Map<String, String> map) {
        this.hm = new HashMap<>(map);
    }

    @Override
    public void set(String key, String value) {
        hm.put(key, value);
    }

    @Override
    public void unset(String key) {
        hm.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return hm.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return hm;
    }
}
