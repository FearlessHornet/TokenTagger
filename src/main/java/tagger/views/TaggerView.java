package tagger.views;

import tagger.IProgressListener;
import tagger.views.buttons.ProgressButton;
import tagger.views.buttons.TagButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaggerView {
    private static final int MaxTags = 17;
    private static final int GridSize = MaxTags + 1;
    private static final int ButtonHeight = 34;

    private JPanel _tagPane;
    private JPanel _tokenPane;
    private JFrame _frame;

    public TaggerView(IProgressListener stateController) {
        int height = GridSize * ButtonHeight;

        _frame = new JFrame();
        _frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container content = _frame.getContentPane();
        content.setPreferredSize(new Dimension(1000, height));
        content.setLayout(null);
        _frame.setResizable(false);

        // Pane to hold token image
        _tokenPane = new JPanel();
        _tokenPane.setBounds(0,0,600, height);
        _tokenPane.setLayout(null);

        // Bottom onProgress (Next/Save) button
        ProgressButton progress = new ProgressButton(stateController);
        int progressWidth = 3 * ButtonHeight;
        progress.setBounds(600 - progressWidth,height - ButtonHeight, progressWidth, ButtonHeight);
        _tokenPane.add(progress);

        // RHS Tag Button panel
        _tagPane = new JPanel();
        _tagPane.setBounds(600,0,400, height);
        _tagPane.setLayout(new GridLayout(GridSize, 1));
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
        int margin = ButtonHeight;
        int height = (GridSize - 2) * ButtonHeight;

        TokenPanel token = new TokenPanel(tokenPath);
        token.setBounds(margin, margin, 600 - (2 * margin), height);
        _tokenPane.add(token);
        _frame.setTitle(tokenPath);
        _tokenPane.repaint();
    }
}