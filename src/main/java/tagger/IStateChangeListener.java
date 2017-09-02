package tagger;

import java.util.ArrayList;

public interface IStateChangeListener {
    void onSave(String taggedFilename);
    void onStateChange(ArrayList<String> newTags, boolean isUnique, boolean isLast);
}
