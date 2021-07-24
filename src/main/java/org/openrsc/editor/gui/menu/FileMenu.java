package org.openrsc.editor.gui.menu;

import org.openrsc.editor.Actions;
import org.openrsc.editor.SelectSection;
import org.openrsc.editor.gui.GuiUtils;
import smile.swing.FileChooser;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import java.io.File;
import java.util.function.Consumer;

public class FileMenu extends BaseMenu {

    public FileMenu(
            Consumer<File> onOpenLandscape,
            Runnable onOpenSection,
            Runnable onSaveLandscape,
            Runnable onRevertLandscape,
            Runnable onExit
    ) {
        super("File");

        JMenuItem openLandscape = new JMenuItem();
        openLandscape.setText("Open Landscape");
        openLandscape.addActionListener(evt -> {
            final JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Locate Landscape.orsc");
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                onOpenLandscape.accept(fc.getSelectedFile());
                SelectSection ss = new SelectSection();
                ss.setVisible(true);
            }
        });
        add(openLandscape);

        JMenuItem openSection = new JMenuItem();
        openSection.setText("Open Section");
        openSection.addActionListener(GuiUtils.fromRunnable(onOpenSection));
        add(openSection);

        JMenuItem saveLandscape = new JMenuItem();
        saveLandscape.setText("Save Landscape");
        saveLandscape.addActionListener(GuiUtils.fromRunnable(onSaveLandscape));
        add(saveLandscape);

        JMenuItem revertLandscape = new JMenuItem();
        revertLandscape.setText("Revert Landscape");
        revertLandscape.addActionListener(GuiUtils.fromRunnable(onRevertLandscape));
        add(revertLandscape);

        addSeparator();

        JMenuItem openDataDir = new JMenuItem();
        openDataDir.setText("Open Data Directory");
        openDataDir.addActionListener(evt -> {
            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(FileChooser.DIRECTORIES_ONLY);
            fc.setDialogTitle("Locate Landscape.orsc");
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                Actions.onOpenDataDir(fc.getSelectedFile());
                SelectSection ss = new SelectSection();
                ss.setVisible(true);
            }
        });
        add(openDataDir);

        addSeparator();

        JMenuItem exit = new JMenuItem();
        exit.setText("Exit");
        exit.addActionListener(GuiUtils.fromRunnable(onExit));
        add(exit);
    }
}
