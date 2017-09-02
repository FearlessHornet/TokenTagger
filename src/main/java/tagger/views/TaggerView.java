package tagger.views;

import tagger.IProgressListener;
import tagger.ISkipListener;
import tagger.views.buttons.ProgressButton;
import tagger.views.buttons.TagButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TaggerView implements ActionListener {
    private static final int MaxTags = 17;
    private static final int GridSize = MaxTags + 1;
    private static final int ButtonHeight = 34;

    private JPanel _tagPane;
    private JPanel _tokenPane;
    private JFrame _frame;
    private ProgressButton _progress;
    private ISkipListener _skipListener;
    private TokenPanel _token;

    public TaggerView(IProgressListener stateController, ISkipListener skipListener) {
        int height = GridSize * ButtonHeight;
        _skipListener = skipListener;

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

        // Bottom right button, onProgress (Next/Save)
        _progress = new ProgressButton(stateController);
        int progressWidth = 3 * ButtonHeight;
        _progress.setBounds(600 - progressWidth,height - ButtonHeight, progressWidth, ButtonHeight);
        _tokenPane.add(_progress);

        // Bottom left button, onSkip (Skip)
        JButton skip = new JButton("SKIP");
        skip.addActionListener(this);
        skip.setBackground(Color.YELLOW);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        skip.setBorder(emptyBorder);
        skip.setFocusable(false);
        skip.setBounds(0,height - ButtonHeight, progressWidth, ButtonHeight);
        _tokenPane.add(skip);

        // RHS Tag Button panel
        _tagPane = new JPanel();
        _tagPane.setBounds(600,0,400, height);
        _tagPane.setLayout(new GridLayout(GridSize, 1));
        _tagPane.setBackground(Color.LIGHT_GRAY);
        _tagPane.add(addTagTitle("TAGS"));

        content.add(_tokenPane);
        content.add(_tagPane);
        _frame.pack();
        _frame.setVisible(true);
    }

    private JLabel addTagTitle(String title) {
        JLabel label = new JLabel(title.toUpperCase(), JLabel.CENTER);
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));
        return label;
    }

    public void setTags(String tagTitle, ArrayList<String> tags, boolean isUnique) {
        _tagPane.removeAll();
        TagButton.ResetGroup();
        _tagPane.add(addTagTitle(tagTitle));
        for (String tag : tags) {
            _tagPane.add(new TagButton(tag));
        }
        TagButton.SetUniqueToggle(isUnique);
        _tagPane.revalidate();
        _tagPane.repaint();
    }

    public void setToken(String tokenPath) {
        int margin = ButtonHeight;
        int height = (GridSize - 2) * ButtonHeight;

        if (_token != null) {
            _tokenPane.remove(_token);
        }

        _token = new TokenPanel(tokenPath);
        _token.setBounds(margin, margin, 600 - (2 * margin), height);
        _tokenPane.add(_token);
        _frame.setTitle(tokenPath);
        _tokenPane.repaint();
    }

    public void setLastState(boolean isLast) {
        _progress.setState(isLast);
    }

    public void actionPerformed(ActionEvent e) {
        _skipListener.onSkip();
    }
}