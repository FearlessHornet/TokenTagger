package tagger.views.buttons;

import tagger.IProgressListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ProgressButton extends JButton implements ActionListener {
    private boolean _lastStep = false;
    private IProgressListener _controller;

    public ProgressButton(IProgressListener listener) {
        super();

        _controller = listener;

        addActionListener(this);
        setBackground(Color.GREEN);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        setBorder(emptyBorder);
        setFocusable(false);

        InputMap inputMap = getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        ActionListener keybind = this; // Eww
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        actionMap.put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keybind.actionPerformed(e);
            }
    });

        // Default state
        setState(false);
    }

    public void setState(boolean lastStep) {
        _lastStep = lastStep;
        setText(_lastStep ? "SAVE" : "NEXT");
        requestFocusInWindow();
    }

    public void actionPerformed(ActionEvent e) {
        if (_lastStep) {
            _controller.onComplete();
            return;
        }
        _controller.onProgress();
    }
}
