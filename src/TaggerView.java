import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaggerView {
    private JPanel _tagPane;
    private JPanel _tokenPane;
    private JFrame _frame;

    TaggerView() {
        _frame = new JFrame();
        _frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container content = _frame.getContentPane();
        content.setPreferredSize(new Dimension(1000, 598));
        content.setLayout(null);
        _frame.setResizable(false);

        _tokenPane = new JPanel();
        _tokenPane.setBounds(0,0,600,600);
        _tokenPane.setLayout(null);

        _tagPane = new JPanel();
        _tagPane.setBounds(600,0,400,600);
        _tagPane.setLayout(new GridLayout(18, 1));
        _tagPane.setBackground(Color.LIGHT_GRAY);
        _tagPane.add(AddTagTitle("TAGS"));

        content.add(_tokenPane);
        content.add(_tagPane);
        _frame.pack();
        _frame.setVisible(true);
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

    public void SetToken(String tokenPath) {
        TokenPanel token = new TokenPanel(tokenPath);
        token.setBounds(50, 45, 500, 500);
        _tokenPane.add(token);
        _frame.setTitle(tokenPath);
        _tokenPane.repaint();
}
}
