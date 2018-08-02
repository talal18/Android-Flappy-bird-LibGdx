package com.talal.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Talal on 2017-05-10.
 */

public class Bird {

    private static final int GRAVITY = -15; //  vertical movement: because if the user don't touch or click the bird will fall
    private static final int MOVEMENT = 100; // horizontal movement
    private Vector3 postion; // the bird postion which holds x,y,z axes for position(we will use only x,y because it's 2 dimention game).
    private Vector3 velocity;


    private Rectangle boundsBird;
    private Animation birdAnimation;
    private Texture birdTexture;
    private Sound flap; // Sound runs from the RAM

    public Vector3 getPostion() {
        return postion;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public Bird(int x, int y){
        postion = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        birdTexture = new Texture("bird_texture.png"); // the texture which have our frames
        birdAnimation = new Animation(new TextureRegion(birdTexture), 3, 0.5f); // 3: the frames in the texture , 0.5: total cycle time
        boundsBird = new Rectangle(x, y, birdTexture.getWidth() / 3 , birdTexture.getHeight()); // create hidden rectangle over our bird and we divided the getWidth by 3 because we have 3 frames in the birdTexture
        flap = Gdx.audio.newSound(Gdx.files.internal("jump.ogg"));
    }

    public void update(float delta){ // send the delta time to the Bird class and allow it to do the calculation for reset the position in the game world.

        birdAnimation.update(delta);
        if (postion.y > 0)
        velocity.add(0, GRAVITY, 0); // we add the gravity to the Y axes and 0 gravity for the x axes, if the position on the Y axes is larger than 0.

        velocity.scl(delta);// because we do the movement with the relation with time, we need to scale the velocity by the change in time,
                            // so it will multiply everything by delta time.

        postion.add(MOVEMENT * delta, velocity.y, 0); //we add the position

        velocity.scl(1/delta); //reverse the scale again, so we can ad the velocity for the next frame.
        boundsBird.setPosition(postion.x, postion.y); // update the postion of the rectangle depends on the bird position

        if (postion.y < 0) // if the position on the Y axes is less than zero, it will change the postion on the Y axes to zero and keep the bird at the bottom of the screen.
            postion.y = 0;
    }

    public void jump(){
        velocity.y = 250; // we give the velocity a positive number to make the bird jump.
        flap.play(0.5f); // the float number is for the voluume (setVolume work with Music not Sound)
    }

    public Rectangle getBoundsBird(){
        return boundsBird;
    }

    public void dispose(){
        birdTexture.dispose();
        flap.dispose();
    }
}
