package tagger;

import java.io.File;
import java.util.Stack;

public class TokenManager {
    private static final String BaseDir = "C:/Tokens";
    private Stack<String> _directories;
    private Stack<String> _files;
    private String _dir;

    public TokenManager() {
        _directories = new Stack<String>();
        _files = new Stack<String>();
        File dir = new File(BaseDir);
        String[] dirList = dir.list();
        assert dirList != null;
        for (String tokenPack : dirList) {
            if (tokenPack.matches("^(?:TP|PF)[0-9]+_.*")) {
                _directories.add(tokenPack);
            }
        }
    }

    private void nextTokenPack() {
        assert _directories.size() > 0;
        _dir = _directories.pop();
        File tpDir = new File(BaseDir + "/" + _dir);
        String[] dirList = tpDir.list();
        assert dirList != null;
        for (String file : dirList) {
            if (file.contains(".png") && !file.contains("tag0")) {
                _files.add(file);
            }
        }

        // Validate there's tokens in the stack
        if (_files.size() == 0) {
            nextTokenPack();
        }
    }

    public String nextToken() {
        // Ensure tokens are in the stack
        if (_files.size() == 0) {
            nextTokenPack();
        }
        return getDirectory() + _files.pop();
    }

    public String getDirectory() {
        return BaseDir + "/" + _dir + "/";
    }
}
