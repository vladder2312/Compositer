package models;

import com.sun.prism.Graphics;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Compositer implements CompositerInterface{
    public boolean isDrawed = true;
    protected ArrayList<CompositerInterface> object;
    public ARect position;

    public Compositer(){
        object = new ArrayList<CompositerInterface>();
    }

    public void addChild(CompositerInterface c){
        object.add(c);
    }

    @Override
    public void draw(Canvas canvas, ARect rect, double rotation) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setTransform(1.,0,0,1.,canvas.getWidth()/2,canvas.getHeight()/2);
        g.rotate(rotation);
        drawMyself(g, position.getARect(rect));
        for(int i=0;i<object.size();i++){
            if (object.get(i).isDraw()){
                System.out.println("*");
                object.get(i).draw(canvas, position, rotation);
            }
        }
    }

    protected void drawMyself(GraphicsContext g, ARect r){}

    protected void loadMyself(){}

    @Override
    public ARect getPosition() {
        return position;
    }

    @Override
    public void setPosition(ARect rect) {
        position = rect;
    }

    @Override
    public boolean isDraw() {
        return isDrawed;
    }

    @Override
    public void loadTextures() {
        loadMyself();
        for(int i=0;i<object.size();i++){
            object.get(i).loadTextures();
        }
    }

    @Override
    public void setDraw(boolean d) {
        isDrawed = d;
    }
}
