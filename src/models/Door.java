package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;

public class Door extends Compositer {
    public Image[] images;
    public int current = 0;
    public String[] pathes;

    public Door(String[] pathes){
        super();
        this.pathes = pathes;
        images = new Image[this.pathes.length];
    }

    public Door(){
        super();
    }

    public void set(int i){
        current = i;
    }

    @Override
    protected void loadMyself() {
        for(int i=0;i<pathes.length;i++){
            images[i] = new Image(pathes[i]);
        }
    }

    @Override
    protected void drawMyself(GraphicsContext g, ARect r) {
        g.drawImage(images[current], r.X()-220,r.Y()-200,r.W(),r.H());
    }

}
