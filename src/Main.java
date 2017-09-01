import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Controller_Token controller = new Controller_Token();
        View_Tagger view = new View_Tagger(null);
        view.SetTags("TAGS", new ArrayList<>(Arrays.asList(
                "tag", "tag", "tag", "tag", "tag", "tag", "tag", "tag",
                "tag", "tag", "tag", "tag", "tag", "tag", "tag", "tag", "tag")), true);
        view.SetToken("C:/Tokens/bounds.png");
    }
}
