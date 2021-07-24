package org.openrsc.editor.gui.dialog;

import org.openrsc.editor.gui.graphics.PlaceEntityDelegate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.util.function.BiConsumer;

public class SelectEntityDialog extends JFrame {

    public SelectEntityDialog(BiConsumer<PlaceEntityDelegate.EntityType, Integer> onComplete) {
        super();

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel entityTypesLabel = new JLabel("Entity Type");
        JComboBox<PlaceEntityDelegate.EntityType> entityTypes = new JComboBox<>(
                PlaceEntityDelegate.EntityType.values()
        );
        entityTypes.setSelectedItem(PlaceEntityDelegate.EntityType.NPC);
        panel.add(entityTypesLabel);
        panel.add(entityTypes);

        JLabel entityIdLabel = new JLabel("Entity Id");
        JTextField entityId = new JTextField(10);
        JButton submit = new JButton("Submit");
        entityId.addActionListener(evt -> {
            try {
                final String text = entityId.getText();
                Integer.parseInt(text);
                submit.setEnabled(true);
            } catch (Exception exception) {
                submit.setEnabled(false);
            }
        });
        panel.add(entityIdLabel);
        panel.add(entityId);

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(evt -> {
            setVisible(false);
        });
        submit.addActionListener(evt -> {
            setVisible(false);
            onComplete.accept(
                    (PlaceEntityDelegate.EntityType) entityTypes.getSelectedItem(),
                    Integer.parseInt(entityId.getText())
            );
        });

        panel.add(cancel);
        panel.add(submit);

        add(panel);
        pack();
        setVisible(true);
    }
}
