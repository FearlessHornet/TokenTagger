import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaggerView {
    private JPanel _tagPane;
    private TokenPanel _token;

    TaggerView() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setPreferredSize(new Dimension(1000, 595));
        content.setLayout(null);
        frame.setResizable(false);

        JPanel tokenPane = new JPanel();
        tokenPane.setBounds(0,0,600,600);
        tokenPane.setLayout(null);

        _token = new TokenPanel("C:\\Tokens\\TP83_HeroicCharacters14\\M_Fop_02_hi.png");
        _token.setBounds(50, 45, 500, 500);
        tokenPane.add(_token);

        _tagPane = new JPanel();
        _tagPane.setBounds(600,0,400,600);
        _tagPane.setLayout(new GridLayout(18, 1));
        _tagPane.setBackground(Color.LIGHT_GRAY);
        _tagPane.add(AddTagTitle("Tags:"));

        content.add(tokenPane);
        content.add(_tagPane);
        frame.pack();
        frame.setVisible(true);
    }

    private JLabel AddTagTitle(String title) {
        JLabel label = new JLabel(title.toUpperCase(), JLabel.CENTER);
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));
        return label;
    }

    public void SetTags(String tagTitle, ArrayList<String> tags, boolean isUnique) {
        _tagPane.removeAll();
        TagButton.ResetGroup();
        _tagPane.add(AddTagTitle(tagTitle));
        for (String tag : tags) {
            _tagPane.add(new TagButton(tag));
        }
        TagButton.SetUniqueToggle(isUnique);
        _tagPane.revalidate();
    }
}
