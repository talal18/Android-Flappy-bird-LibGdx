package com.talal.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.talal.game.FlappyDemo;
import com.talal.game.sprite.Bird;
import com.talal.game.sprite.Tube;

/**
 * Created by Talal on 2017-05-10.
 */

public class PlayState extends State {


    private static final int TUBE_SPACING = 125; // the horizontal space between the the tubes
    private static final int TUBE_COUNT = 4; // how many tubes in each viewed screen
    private static final int GROUND_Y_OFFSET = -50; // to determine the ground height
    private Bird bird;
    private Texture playBackground;
    private Tube tube;
    private Array<Tube> tubes;
    private Texture ground;
    private Vector2 groundPostion1;
    private Vector2 groundPostion2; //we created 2 ground postion because after the first ground passed we move it after the second ground as a third ground.


    protected PlayState(GameStateManager gsm) {
        super(gsm);

        bird = new Bird(50, 250); // adding the position of the bird
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2); //setToOrtho is responsible for deciding which part of the game world the player see.
        playBackground = new Texture("backgr.png");
        ground = new Texture("ground.png");
        groundPostion1 = new Vector2((cam.position.x - cam.viewportWidth /2), GROUND_Y_OFFSET);// to make the ground texture start from the begining of the screen
        groundPostion2 =  new Vector2((cam.position.x - cam.viewportWidth /2) + ground.getWidth(), GROUND_Y_OFFSET);
        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++ ){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) // once we stop touch or click, the bird will have original velocity which is -15 and it will fall.
            bird.jump();
    }

    @Override
    public void update(float delta) {
        handleInput(); // we put it all the time
        updateGround();// to keep creating the grounds
        bird.update(delta); // update our bird with the change of the delta time.
        cam.position.x = bird.getPostion().x + 80;  // to make the cam follow the bird

        for (int i=0; i < tubes.size; i++){
            Tube tube = tubes.get(i);

            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {  // this to find the left side of the screen
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT)); // to move the tube to the end of the screen
            }

            if (tube.collides(bird.getBoundsBird())) //kill the bird if it touch the tubes
                gsm.set(new PlayState(gsm));
        }

            if(bird.getPostion().y <= ground.getHeight() + GROUND_Y_OFFSET) // kills the bird if it touch the ground
                gsm.set(new PlayState(gsm));

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined); // decide which part of the game world will be drawn on the screen
        sb.begin();
        sb.draw(playBackground, cam.position.x - (cam.viewportWidth /2), 0);// to draw the background in any given moment of the game depending on the camera position
                                                                         // the viewportWidth: is the width of the screen we are viewing and we divide it by 2 to make the cam in the middle of the screen , so the background will be in the middle.

        sb.draw(bird.getTexture(), bird.getPostion().x, bird.getPostion().y);

        for (Tube tube : tubes) { // loop to keep drawing the tubes
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }

        sb.draw(ground, groundPostion1.x, groundPostion1.y);
        sb.draw(ground, groundPostion2.x, groundPostion2.y);
        sb.end();
    }

    @Override
    public void dispose() {

        playBackground.dispose(); // dispose play background
        bird.dispose(); //dispose the bird
        ground.dispose();// dispose the ground

        for (Tube tube : tubes) // dispose tubes
            tube.dispose();
    }

    private void updateGround(){
        if(cam.position.x - cam.viewportWidth / 2 > groundPostion1.x + ground.getWidth())
            groundPostion1.add(ground.getWidth() * 2, 0);

        if(cam.position.x - cam.viewportWidth / 2 > groundPostion2.x + ground.getWidth())
            groundPostion2.add(ground.getWidth() * 2, 0);
    }
}
