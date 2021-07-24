package org.openrsc.editor.gui.graphics;

import org.openrsc.editor.Util;
import org.openrsc.editor.model.EditorTool;
import org.openrsc.editor.model.Tile;
import org.openrsc.editor.model.data.GameObjectLoc;
import org.openrsc.editor.model.data.ItemLoc;
import org.openrsc.editor.model.data.NpcLoc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class InspectToolDelegate extends ToolDelegate {
    private final EditorCanvas editorCanvas;

    private Point hoverPoint = null;

    public InspectToolDelegate(EditorCanvas editorCanvas) {
        super(EditorTool.INSPECT);
        this.editorCanvas = editorCanvas;
    }

    @Override
    public void render(Graphics2D g) {
        Optional.ofNullable(hoverPoint).ifPresent(gridPoint -> {
            Tile currentTile = editorCanvas.getTileByGridCoords(
                    gridPoint.x, gridPoint.y
            );
            editorCanvas.drawTileBorder(currentTile);

            int boxOriginX = currentTile.getX() + EditorCanvas.TILE_SIZE;
            int boxOriginY = currentTile.getY() + EditorCanvas.TILE_SIZE;
            final int boxWidth = 200;
            if (boxOriginX + boxWidth > EditorCanvas.GRID_PIXEL_SIZE) {
                boxOriginX -= boxWidth + EditorCanvas.TILE_SIZE;
            }
            final int boxHeight = 325;
            if (boxOriginY + boxHeight > EditorCanvas.GRID_PIXEL_SIZE) {
                boxOriginY -= boxHeight + EditorCanvas.TILE_SIZE;
            }
            Rectangle infoBox = new Rectangle(
                    boxOriginX,
                    boxOriginY,
                    boxWidth,
                    boxHeight
            );
            g.setColor(Color.BLACK);
            g.fillRect(infoBox.x, infoBox.y, infoBox.width, infoBox.height);

            g.setColor(Color.WHITE);
            g.drawRect(infoBox.x, infoBox.y, infoBox.width, infoBox.height);

            int x = infoBox.x + 4;
            int y = infoBox.y + 17;
            g.drawString("Id: " + currentTile.getID(), x, y);
            y += 17;
            final Point rscCoords = Util.getRSCCoords(currentTile);
            g.drawString("RSC Coords: (" + (int) rscCoords.getX() + ", " + (int) rscCoords.getY() + ")", x, y);
            y += 17;
            g.drawString("Texture: " + currentTile.getGroundTextureInt(), x, y);
            y += 17;
            g.drawString("Elevation: " + currentTile.getGroundElevationInt(), x, y);
            y += 17;
            g.drawString("Overlay: " + currentTile.getGroundOverlayInt(), x, y);
            y += 17;
            g.drawString("Roof Texture: " + currentTile.getRoofTextureInt(), x, y);
            y += 17;
            g.drawString("Diagonal Wall: " + currentTile.getDiagonalWalls(), x, y);
            y += 17;
            g.drawString("North Wall: " + currentTile.getTopBorderWallInt(), x, y);
            y += 17;
            g.drawString("East Wall: " + currentTile.getRightBorderWallInt(), x, y);
            y += 17;

            final GameObjectLoc sceneryLoc = Util.sceneryLocationMap.get(rscCoords);
            if (sceneryLoc != null) {
                g.drawString("Scenery", x, y);
                y += 17;
                g.drawString("  Id: " + sceneryLoc.getId(), x, y);
                y += 17;
                g.drawString("  Direction: " + sceneryLoc.getDirection(), x, y);
                y += 17;
            }

            final GameObjectLoc boundaryLoc = Util.boundaryLocsMap.get(rscCoords);
            if (boundaryLoc != null) {
                g.drawString("Boundary", x, y);
                y += 17;
                g.drawString("  Id: " + boundaryLoc.getId(), x, y);
                y += 17;
                g.drawString("  Direction: " + boundaryLoc.getDirection(), x, y);
                y += 17;
            }

            NpcLoc npcLoc = Util.npcLocationMap.get(rscCoords);
            if (npcLoc != null) {
                g.drawString("NPC", x, y);
                y += 17;
                g.drawString("  Id: " + npcLoc.getId(), x, y);
                y += 17;
            }

            ItemLoc itemLoc = Util.itemLocationMap.get(rscCoords);
            if (itemLoc != null) {
                g.drawString("Item", x, y);
                y += 17;
                g.drawString("  Id: " + itemLoc.getId(), x, y);
                y += 17;
                g.drawString("  Amount: " + itemLoc.getAmount(), x, y);
                y += 17;
                g.drawString("  Respawn: " + itemLoc.getRespawn(), x, y);
            }
        });
    }

    @Override
    public void onToolMount() {

    }

    @Override
    public void onToolUnmount() {
        hoverPoint = null;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        hoverPoint = Util.mouseCoordsToGridCoords(mouseEvent.getPoint());
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
        hoverPoint = Util.mouseCoordsToGridCoords(mouseEvent.getPoint());
    }
}
