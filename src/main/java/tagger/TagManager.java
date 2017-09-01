package tagger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.json.*;

public class TagManager {
    private JsonObject _json;

    TagManager(String configPath) throws IOException {
        File fConfig = new File(configPath);
        FileInputStream fsConfig = new FileInputStream(fConfig);
        JsonReader reader = Json.createReader(fsConfig);
        _json = reader.readObject();
        System.out.println(_json.toString());
    }


}
