package com.talal.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Talal on 2017-05-11.
 */

public class Tube {

    private static final int FLUCTUATION = 130; // that means we can move the tubes between 0 to 130
    private static final int TUBE_GAP = 100; // the difference between the openings is 100
    private static final int LOWEST_OPENING = 120; // the base of the tube
    public static final int TUBE_WIDTH = 52; // the width of the tube
    private Texture topTube;
    private Texture bottomTube;
    private Vector2 posTopTube; // postion of the top tube
    private Vector2 posBottomTube; // postion of the bottom tube
    private Random rand; // to position our tubes randomly
    private Rectangle boundsTop; // a hidden Rectangle which covers out top tubes ( used for the collusion )
    private Rectangle boundsBottom; // a hidden Rectangle which covers out bottom tubes ( used for the collusion )

    public Tube(float x){ // it have the position on the X axes

        topTube = new Texture("top_tube.png");
        bottomTube = new Texture("bottom_tube.png");

        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING); // to position the top tube randomly.
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight()); // positioning the rectangle on the top tube
        boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight()); // positioning the rectangle on the bottom tube
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public void reposition(float x){  // this method to remove the tubes after the bird passed it and reposition them again later
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y); // reposition the rectangle on the top tube
        boundsBottom.setPosition(posBottomTube.x, posBottomTube.y); // reposition the rectangle on the bottom tube
    }

    public Boolean collides(Rectangle player){ // boolean method that return true when the player overlap on top or bottom rectangles.
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
