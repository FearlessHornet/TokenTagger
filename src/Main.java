import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        TaggerView view = new TaggerView();
        view.SetTags("TAGS", new ArrayList<>(Arrays.asList(
                "tag", "tag", "tag", "tag", "tag", "tag", "tag", "tag",
                "tag", "tag", "tag", "tag", "tag", "tag", "tag", "tag", "tag")), true);
    }
}
