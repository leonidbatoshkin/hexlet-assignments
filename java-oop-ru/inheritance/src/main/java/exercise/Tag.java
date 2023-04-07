package exercise;

import java.util.Map;
import java.util.Set;

class Tag {
    protected String tagName;
    protected Map<String, String> attributes;

    public Tag(String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("<" + tagName);
        Set<Map.Entry<String, String>> entries = attributes.entrySet();
        entries.
                forEach(line -> result.append(" ").append(line.getKey()).append("=\"")
                        .append(line.getValue()).append("\""));
        return result.append(">").toString();
    }
}