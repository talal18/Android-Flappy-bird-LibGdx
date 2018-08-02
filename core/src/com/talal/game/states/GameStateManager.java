package com.talal.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Talal on 2017-05-09.
 */

public class GameStateManager {

    private Stack<State> states; // a stack of States, it's like an array of States

    public GameStateManager(){
        states = new Stack<State>();
    }

// the following methods comes and used with the Stack(push, pop, peek)

    public void push(State state){ // push the state
        states.push(state);
    }

    public void pop(){ // pop a state and we added dispose to dispose the state because we will not use it agian, this will save memory
        states.pop().dispose();
    }

    public void set(State state){ // pop and push a new state
        states.pop().dispose();
        states.push(state);

    }

// update and render are methods for the top object of the state.

    public void update(float delta){  // changing time between 2 renders
        states.peek().update(delta);  // peek looks for the top object of the stack
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

}
