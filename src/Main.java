import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        TokenController controller = new TokenController();
        TaggerView view = new TaggerView();
        view.SetTags("TAGS", new ArrayList<>(Arrays.asList(
                "tag", "tag", "tag", "tag", "tag", "tag", "tag", "tag",
                "tag", "tag", "tag", "tag", "tag", "tag", "tag", "tag", "tag")), true);
        view.SetToken("C:/Tokens/TP83_HeroicCharacters14/M_Fop_02_hi.png");
    }
}
