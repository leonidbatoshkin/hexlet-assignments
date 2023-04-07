package exercise;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class PairedTag extends Tag {
    String tagBody;
    List<Tag> tagList;

    public PairedTag(String tagName, Map<String, String> attributes, String tagBody, List<Tag> tagList) {
        super(tagName, attributes);
        this.tagBody = tagBody;
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        tagList
                .forEach(tag -> result.append(tag.toString()));
        result.append(tagBody).append("</").append(tagName).append(">");
        return result.toString();
    }
}
