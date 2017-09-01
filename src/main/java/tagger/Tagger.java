package tagger;

import java.io.IOException;

public class Tagger implements IProgressListener {
    public Tagger() {
        try {
            new TagManager("C:/Tokens/config.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onProgress() {

    }

    public void onComplete() {

    }
}
