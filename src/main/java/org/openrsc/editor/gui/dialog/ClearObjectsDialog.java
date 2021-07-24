package org.openrsc.editor.gui.dialog;

import org.openrsc.editor.event.action.ClearObjectsAction;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class ClearObjectsDialog extends JFrame {
    private static final ClearObjectsAction.Type DEFAULT_OBJ_TYPE = ClearObjectsAction.Type.BOUNDARY;

    public ClearObjectsDialog(Consumer<ClearObjectsAction.Type> onComplete) throws HeadlessException {
        super();

        AtomicReference<ClearObjectsAction.Type> type = new AtomicReference<>(DEFAULT_OBJ_TYPE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel gameObjectTypesLabel = new JLabel("Game Object Type");
        JComboBox<ClearObjectsAction.Type> gameObjectTypes = new JComboBox<>(ClearObjectsAction.Type.values());
        gameObjectTypes.addActionListener(evt -> type.set((ClearObjectsAction.Type) gameObjectTypes.getSelectedItem()));
        gameObjectTypes.setSelectedItem(DEFAULT_OBJ_TYPE);
        panel.add(gameObjectTypesLabel);
        panel.add(gameObjectTypes);

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(evt -> {
            setVisible(false);
        });
        JButton submit = new JButton("Submit");
        submit.addActionListener(evt -> {
            setVisible(false);
            onComplete.accept(type.get());
        });

        panel.add(cancel);
        panel.add(submit);

        add(panel);
        pack();
        setVisible(true);
    }
}
