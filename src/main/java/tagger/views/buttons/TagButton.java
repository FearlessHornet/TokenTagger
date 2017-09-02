package tagger.views.buttons;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TagButton extends JButton implements ActionListener {
    private static boolean _toggleGroup = false;

    private static ArrayList<TagButton> _group = null;
    private static ArrayList<TagButton> GetGroup() {
        if (_group == null) {
            _group = new ArrayList<>();
        }
        return _group;
    }

    public static void ResetGroup() {
        _group = null;
    }

    public static void SetUniqueToggle(boolean toggleState) {
        _toggleGroup = toggleState;
    }

    public static ArrayList<String> GetChosenTags() {
        ArrayList<String> tags = new ArrayList<>();
        for (TagButton btn : GetGroup()) {
            if (btn.isSelected()) {
                tags.add(btn.getText());
            }
        }
        return tags;
    }

    public TagButton(String label) {
        super(label);

        setSelected(false);
        addActionListener(this);
        setBackground(Color.LIGHT_GRAY);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        setBorder(emptyBorder);
        setFocusable(false);
        setForeground(Color.WHITE);
        GetGroup().add(this);
    }

    @Override
    public void setSelected(boolean state) {
        Color bg = state ? Color.DARK_GRAY : Color.LIGHT_GRAY;
        setBackground(bg);
        super.setSelected(state);
    }

    public void actionPerformed(ActionEvent e) {
        if (!isSelected()) {
            if (_toggleGroup) {
                for (TagButton btn : GetGroup()) {
                    btn.setSelected(false);
                }
            }
        }
        setSelected(!isSelected());
    }
}
