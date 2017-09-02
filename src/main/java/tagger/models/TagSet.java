package tagger.models;

import javax.json.JsonObject;
import javax.json.JsonString;
import java.util.ArrayList;
import java.util.List;

public class TagSet {
    private ArrayList<String> _tags;
    private boolean _noneOption;
    private boolean _unknownOption;
    private boolean _uniqueOption;

    public static TagSet FromJson(JsonObject json) {
        boolean unique = json.getBoolean("unique");
        boolean unknown = json.getBoolean("unknown");
        boolean none = json.getBoolean("none");

        List<JsonString> rawTags = json.getJsonArray("tags").getValuesAs(JsonString.class);
        ArrayList<String> tags = new ArrayList<>();
        rawTags.forEach(rawTag -> tags.add(rawTag.getString()));

        return new TagSet(tags, unique, unknown, none);
    }

    private TagSet(ArrayList<String> tags, boolean unique, boolean unknown, boolean none) {
        _tags = tags;
        _uniqueOption = unique;
        _unknownOption = unknown;
        _noneOption = none;
    }

    public boolean isUnique() {
        return _uniqueOption;
    }

    public boolean hasUnknown() {
        return _unknownOption;
    }

    public boolean hasNone() {
        return _noneOption;
    }

    public ArrayList<String> getTags() {
        return _tags;
    }
}
