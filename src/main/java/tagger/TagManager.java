package tagger;

import tagger.models.TagSet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.*;

public class TagManager {
    private ArrayList<TagSet> _tags;
    private int _index = 0;

    static final String UnknownTag = "Unkown";
    static final String NoneTag = "(None)";

    TagManager(String configPath) throws IOException {
        // JSON setup
        File fConfig = new File(configPath);
        FileInputStream fsConfig = new FileInputStream(fConfig);
        JsonReader reader = Json.createReader(fsConfig);

        // Load tag set data
        JsonObject rawJson = reader.readObject();
        List<JsonObject> rawTagSets = rawJson.getJsonArray("tag_sets").getValuesAs(JsonObject.class);

        // Parse tag sets
        _tags = new ArrayList<>();
        rawTagSets.forEach(rawTag -> _tags.add(TagSet.FromJson(rawTag)));
    }

    void reset() {
        _index = 0;
    }

    void moveNext() {
       _index++;
       assert _index < _tags.size();
    }

    ArrayList<String> getTags() {
        ArrayList<String> tags = new ArrayList<>();
        TagSet model = _tags.get(_index);
        if (model.hasUnknown()) {
            tags.add(UnknownTag);
        }
        if (model.hasNone()) {
            tags.add(NoneTag);
        }
        tags.addAll(model.getTags());
        return tags;
    }

    boolean isLastTagSet() {
        return _index + 1 >= _tags.size();
    }

    boolean isUnique() {
        return _tags.get(_index).isUnique();
    }
}
