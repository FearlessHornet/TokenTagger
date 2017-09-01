import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button_Progress extends JButton implements ActionListener {
    private boolean _lastStep = false;
    private IProgressListener _controller;

    Button_Progress(IProgressListener listener) {
        super();

        _controller = listener;

        addActionListener(this);
        setBackground(Color.GREEN);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        setBorder(emptyBorder);
        setFocusable(false);

        // Default state
        setState(false);
    }

    public void setState(boolean lastStep) {
        _lastStep = lastStep;
        setText(_lastStep ? "SAVE" : "NEXT");
    }

    public void actionPerformed(ActionEvent e) {
        if (_lastStep) {
            _controller.complete();
            return;
        }
        _controller.progress();
    }
}
