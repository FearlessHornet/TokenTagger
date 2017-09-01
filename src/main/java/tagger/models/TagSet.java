package tagger.models;

import javax.json.JsonObject;
import javax.json.JsonString;
import java.util.ArrayList;
import java.util.List;

public class TagSet {
    private ArrayList<String> _tags;
    private boolean _unknownOption;
    private boolean _uniqueOption;

    public static TagSet FromJson(JsonObject json) {
        boolean unique = json.getBoolean("unique");
        boolean unknown = json.getBoolean("unknown");
        List<JsonString> rawTags = json.getJsonArray("tags").getValuesAs(JsonString.class);
        ArrayList<String> tags = new ArrayList<>();
        rawTags.forEach(rawTag -> tags.add(rawTag.getString()));
        return new TagSet(tags, unique, unknown);
    }

    private TagSet(ArrayList<String> tags, boolean unique, boolean unknown) {
        _tags = tags;
        _uniqueOption = unique;
        _unknownOption = unknown;
    }

    public boolean isUnique() {
        return _uniqueOption;
    }

    public boolean hasUnknown() {
        return _unknownOption;
    }

    public ArrayList<String> getTags() {
        return _tags;
    }

    getTags
}
