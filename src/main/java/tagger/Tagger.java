package tagger;

import tagger.views.buttons.TagButton;

import java.io.IOException;
import java.util.Random;

public class Tagger implements IProgressListener {
    private TagManager _tagManager;
    private StringBuffer _buffer;
    private IStateChangeListener _listener;
    private boolean _unknownAdded = false;

    public Tagger(String configPath) throws IOException {
        _tagManager = new TagManager(configPath);
        _buffer = new StringBuffer();
    }

    public void init(IStateChangeListener listener) {
        _listener = listener;
        updateState();
    }

    private void addTag(String tag) {
        // Handle special tags
        if (tag.contentEquals(TagManager.NoneTag)) {
            return;
        }
        if (tag.contentEquals(TagManager.UnknownTag)) {
            if (_unknownAdded) {
                return;
            }
            _unknownAdded = true;
        }

        // Singletonize the buffer
        if (_buffer.length() < 4) {
            Random rng = new Random();
            _buffer.append("tag0");
            _buffer.append(String.format("%04x", rng.nextInt(0xFFFF)).toUpperCase());
            _buffer.append("[");
        }

        // Do it
        _buffer.append(tag.toLowerCase()).append(",");
    }

    private String saveBuffer() {
        _buffer.deleteCharAt(_buffer.length() - 1);
        _buffer.append("].png");
        String output = _buffer.toString();
        resetState();
        return output;
    }

    private void updateState() {
        _listener.onStateChange(_tagManager.getTags(), _tagManager.isUnique(), _tagManager.isLastTagSet());
    }

    public void resetState() {
        _buffer.setLength(0);
        _unknownAdded = false;
        _tagManager.reset();
        updateState();
    }

    public void onProgress() {
        TagButton.GetChosenTags().forEach(this::addTag);
        _tagManager.moveNext();
        updateState();
    }

    public void onComplete() {
        TagButton.GetChosenTags().forEach(this::addTag);
        String filename = saveBuffer();
        _listener.onSave(filename);
        _tagManager.reset();
        updateState();
    }
}
