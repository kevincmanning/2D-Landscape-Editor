package org.openrsc.editor.gui.graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openrsc.editor.Util;
import org.openrsc.editor.gui.dialog.SelectEntityDialog;
import org.openrsc.editor.model.EditorTool;
import org.openrsc.editor.model.Tile;
import org.openrsc.editor.model.data.GameObjectLoc;
import org.openrsc.editor.model.data.ItemLoc;
import org.openrsc.editor.model.data.NpcLoc;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class PlaceEntityDelegate extends ToolDelegate {
    private final EditorCanvas editorCanvas;

    private Tile hoverTile;

    private Integer entityId;
    private EntityType type;
    private Tile npcMin;
    private Tile npcMax;

    public PlaceEntityDelegate(EditorCanvas editorCanvas) {
        super(EditorTool.PLACE_ENTITY);
        this.editorCanvas = editorCanvas;
    }

    @Override
    public void render(Graphics2D g) {
        if (hoverTile != null) {
            Color hoverColor = Color.YELLOW;
            if (type == EntityType.NPC) {
                g.setColor(Color.WHITE);
                if (npcMin == null) {
                    hoverColor = Color.GREEN;
                    g.drawString("NPC min", hoverTile.getX() + 25, hoverTile.getY());
                } else if (npcMax == null) {
                    hoverColor = Color.RED;
                    g.drawString("NPC max", hoverTile.getX() + 25, hoverTile.getY());
                }
            }
            editorCanvas.drawTileBorder(hoverTile, hoverColor);
            if (npcMin != null) {
                editorCanvas.drawTileBorder(npcMin, Color.GREEN);
            }
            if (npcMax != null) {
                editorCanvas.drawTileBorder(npcMax, Color.RED);
            }
        }
    }

    @Override
    public void onToolMount() {
        this.type = null;
        this.npcMin = null;
        this.npcMax = null;
        this.entityId = null;
        new SelectEntityDialog(
                (type, entityId) -> {
                    this.type = type;
                    this.entityId = entityId;
                }
        );
    }

    @Override
    public void onToolUnmount() {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Tile clickedTile = editorCanvas.getTileByGridCoords(
                Util.mouseCoordsToGridCoords(mouseEvent.getPoint())
        );
        final Point clickedTileRSCCoords = clickedTile.getRSCCoords();
        if (type == EntityType.NPC) {
            if (npcMin == null) {
                npcMin = clickedTile;
            } else if (npcMax == null) {
                npcMax = clickedTile;
                Point min = npcMin.getRSCCoords();
                Point max = npcMax.getRSCCoords();
                Point actualMin = new Point(
                        (int) Math.min(min.getX(), max.getX()),
                        (int) Math.min(min.getY(), max.getY())
                );
                Point actualMax = new Point(
                        (int) Math.max(min.getX(), max.getX()),
                        (int) Math.max(min.getY(), max.getY())
                );
                npcMin = editorCanvas.getTileByGridCoords(Util.gridPointFromRSCCoords(actualMin));
                npcMax = editorCanvas.getTileByGridCoords(Util.gridPointFromRSCCoords(actualMax));
            } else {
                Util.npcLocationMap.put(
                        clickedTileRSCCoords,
                        NpcLoc.builder()
                                .id(entityId)
                                .min(org.openrsc.editor.model.data.Point.fromAWT(npcMin.getRSCCoords()))
                                .max(org.openrsc.editor.model.data.Point.fromAWT(npcMax.getRSCCoords()))
                                .start(org.openrsc.editor.model.data.Point.fromAWT(clickedTileRSCCoords))
                                .build()
                );
            }
        } else if (type == EntityType.BOUNDARY || type == EntityType.SCENERY) {
            try {
                Integer direction = Integer.parseInt(JOptionPane.showInputDialog("Enter object direction:"));
                GameObjectLoc loc =
                        GameObjectLoc.builder()
                                .id(entityId)
                                .location(org.openrsc.editor.model.data.Point.fromAWT(clickedTileRSCCoords))
                                .direction(direction)
                                .build();
                if (type == EntityType.BOUNDARY) {
                    Util.boundaryLocsMap.put(
                            clickedTileRSCCoords,
                            loc
                    );
                } else if (type == EntityType.SCENERY) {
                    Util.sceneryLocationMap.put(
                            clickedTileRSCCoords,
                            loc
                    );
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Direction must be an integer");
            }
        } else if (type == EntityType.ITEM) {
            try {
                Integer amount = Integer.parseInt(JOptionPane.showInputDialog("Enter amount: "));
                Integer respawnTime = Integer.parseInt(JOptionPane.showInputDialog("Enter respawn time: "));

                ItemLoc itemLoc = ItemLoc.builder()
                        .amount(amount)
                        .id(entityId)
                        .location(org.openrsc.editor.model.data.Point.fromAWT(clickedTileRSCCoords))
                        .respawn(respawnTime)
                        .build();
                Util.itemLocationMap.put(clickedTileRSCCoords, itemLoc);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Amount and Respawn Time must be an integers");
            }
        }
        Util.sectorModified = true;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point mouseCoords = mouseEvent.getPoint();
        Point tileGridPos = Util.mouseCoordsToGridCoords(mouseCoords);
        Tile tile = editorCanvas.getTileByGridCoords(tileGridPos);
        hoverTile = tile;
    }

    @AllArgsConstructor
    @Getter
    public enum EntityType {
        SCENERY("Scenery"),
        BOUNDARY("Boundary"),
        ITEM("Item"),
        NPC("Npc");

        private final String label;

        @Override
        public String toString() {
            return label;
        }
    }
}
