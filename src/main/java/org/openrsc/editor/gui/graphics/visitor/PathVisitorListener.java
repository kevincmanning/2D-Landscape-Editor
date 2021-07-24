package org.openrsc.editor.gui.graphics.visitor;

import org.openrsc.editor.model.Tile;

import java.util.List;

public abstract class PathVisitorListener {
    protected void onNorthWallVisited(Tile tile) {
    }

    protected void onEastWallVisited(Tile tile) {
    }

    protected void onForwardDiagonalVisited(Tile tile) {
    }

    protected void onBackwardDiagonalVisited(Tile tile) {
    }

    protected void onFillTileVisited(Tile tile) {
    }

    protected void onComplete(List<Tile> visited) {
    }

}
