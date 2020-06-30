package models;

public class ARect {
    public double w,h,x,y;
    public static int width, height;
    public static ARect full = new ARect(0.,0.,1.,1.);

    public ARect(double x, double y, double w, double h) {
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }

    public ARect getARect(ARect rect){
        return new ARect(rect.x+x*rect.w,rect.y+y*rect.h,w*rect.w,h*rect.h);
    }

    public int X(){
        return (int)x;
    }
    public int Y(){
        return (int)y;
    }
    public int W(){
        return (int)w;
    }
    public int H(){
        return (int)h;
    }
}
