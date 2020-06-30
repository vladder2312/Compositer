package models;

import javafx.scene.canvas.Canvas;

public interface CompositerInterface {
    void draw(Canvas canvas, ARect rect, double rotation);
    ARect getPosition();
    void setPosition(ARect rect);
    boolean isDraw();
    void loadTextures();
    void setDraw(boolean d);
}
