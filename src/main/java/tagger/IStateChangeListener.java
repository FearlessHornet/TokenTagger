package tagger;

import java.util.List;

public interface IStateChangeListener {
    void onSave(String taggedFilename);
    void onStateChange(List<String> newTags, boolean isUnique, boolean isLast);
}
