package ru.itschool.sundayghost;

public class NecroObject {
    float x, y;
    float width, height;
    float r;
    String type;

    public NecroObject(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
        type = "circle";
    }

    public NecroObject(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        type = "box";
    }

    boolean isTouched(float tx, float ty){
        if(type.equals("circle"))
            return Math.pow(tx-x,2)+Math.pow(ty-y,2)<r*r;
        else
            return x<tx && tx<x+width && y<ty && ty<y+height;
    }
}
