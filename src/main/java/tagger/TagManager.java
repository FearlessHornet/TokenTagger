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


}
