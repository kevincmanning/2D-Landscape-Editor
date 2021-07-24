package org.openrsc.editor.gui.dialog;

import org.openrsc.editor.model.configuration.StrokePathConfiguration;
import org.openrsc.editor.model.definition.WallType;

import javax.swing.JFrame;
import java.awt.HeadlessException;
import java.util.List;
import java.util.function.Consumer;

public class StrokePathDialog extends JFrame {
    public StrokePathDialog(Consumer<StrokePathConfiguration> onComplete) throws HeadlessException {
        new SelectWallDialog(
                List.of(WallType.WALL, WallType.DOOR_FRAME),
                (wallDef) -> onComplete.accept(
                        StrokePathConfiguration.builder()
                                .wallDefinition(wallDef)
                                .build()
                )
        );
    }
}
