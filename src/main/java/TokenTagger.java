import tagger.Tagger;
import tagger.TokenManager;
import tagger.views.TaggerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TokenTagger {
    public static void main(String[] args) throws IOException {
        TokenManager tokenManager = new TokenManager();
        TaggerView view = new TaggerView(new Tagger("C:/Tokens/config.json"));
        view.SetTags("TAGS", new ArrayList<>(Arrays.asList(
                "tag", "tag", "tag", "tag", "tag", "tag", "tag", "tag",
                "tag", "tag", "tag", "tag", "tag", "tag", "tag", "tag", "tag")), true);
        view.SetToken("C:/Tokens/bounds.png");
    }
}
