package com.talal.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Talal on 2017-05-13.
 */

public class Animation {
    private Array<TextureRegion> frames; // array of frames
    private float maxFrameTime; // telling how much time the frame must stay in view
    private float currentFrameTime; // the time the animation will stay in the current frame
    private int frameCount; // counting the numbers of frames
    private int frame; // the current frame

    public Animation (TextureRegion region, int frameCount, float cycleTime){ // cycleTime: is the time of the whole animation takes to complete the cycle
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount; // the width for the sigle frame

        for (int i = 0; i <= frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
                        //(region, start point on x axis = i*frameWidth, start point on the y axis = 0, the width of each frame = frameWidth, frame Height = region.getRegionHeight)
        }

        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;  // to divide the time on our frames
        frame = 0; // the start point is when the frame = 0

    }

    public void update(float delta){
        currentFrameTime += delta; // how long the current frame in view
        if (currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0; // if the currentFrameTime is greater than maxFrameTime we move to the next frame and the currentFrameTime will become 0
        }
        if (frame >= frameCount){ // to start again fromthe first frame if we passed all the frames
            frame = 0;
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
