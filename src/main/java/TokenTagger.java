import tagger.IStateChangeListener;
import tagger.Tagger;
import tagger.TokenManager;
import tagger.views.TaggerView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TokenTagger implements IStateChangeListener {
    private TokenManager _tokenManager;
    private Tagger _tagger;
    private TaggerView _view;
    private String _currentTokenPath;

    public static void main(String[] args) throws IOException {
        new TokenTagger("C:/Tokens/config.json");
    }

    private TokenTagger(String configPath) throws IOException {
        _tokenManager = new TokenManager();
        _tagger = new Tagger(configPath);
        _view = new TaggerView(_tagger);

        changeToken();
        _tagger.init(this);
    }

    private void changeToken() {
        _currentTokenPath = _tokenManager.nextToken();
        _view.SetToken(_currentTokenPath);
    }

    public void onSave(String taggedFilename) {
        File token = new File(_currentTokenPath);
        String newPath = _tokenManager.getDirectory() + taggedFilename;
        boolean success = token.renameTo(new File(newPath));
        if (!success) {
            System.out.println("Failed to rename token");
        }
        changeToken();
    }

    public void onStateChange(ArrayList<String> newTags, boolean isUnique, boolean isLast) {
        _view.SetTags(isUnique ? "PICK ONE" : "PICK SOME", newTags, isUnique);
    }
}
