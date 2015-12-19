package com.yojplex.cardswipe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.yojplex.cardswipe.Card;
import com.yojplex.cardswipe.MyGdxGame;
import com.yojplex.cardswipe.MyGestureListener;
import com.yojplex.cardswipe.Balloon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kenthall on 12/11/15.
 */
public class GameScreen implements Screen {
    private static SpriteBatch batch;
    private static Card card;
    private static Card dummyCard;
    private MyGestureListener gestureListener;
    private ArrayList <Balloon> balloons;
    private ArrayList <Integer> balloonsToRemove;
    private Random generator;
    private long goTime;

    public GameScreen (SpriteBatch batch){
        this.batch=batch;

        card=new Card(new Vector2(Gdx.graphics.getWidth()/2, 350*MyGdxGame.masterScale));
        dummyCard=new Card(new Vector2(-175*MyGdxGame.masterScale, 350*MyGdxGame.masterScale));

        balloons=new ArrayList<Balloon>();
        balloonsToRemove=new ArrayList<Integer>();

        gestureListener=new MyGestureListener();
        Gdx.input.setInputProcessor(new GestureDetector(gestureListener));

        generator=new Random();

        goTime= TimeUtils.nanoTime();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        //if the player card is moving, move the next "dummy" card into side position
        if (card.getVel().x!=0 || card.getVel().y!=0){
            if (dummyCard.getLoc().x<0-dummyCard.getWidth()/2) {
                dummyCard.setVel(new Vector2(20*MyGdxGame.masterScale, 0));
            }
            else{
                dummyCard.setVel(new Vector2(0, 0));
                dummyCard=new Card(new Vector2(0, 350*MyGdxGame.masterScale));
            }
        }

        //respawn the player card when player card is off screen and dummy card is in position
        if (card.getLoc().x<0-card.getWidth() || card.getLoc().y<0-card.getHeight() || card.getLoc().x>Gdx.graphics.getWidth() || card.getLoc().y>Gdx.graphics.getHeight()){
            if (dummyCard.getLoc().x<Gdx.graphics.getWidth()/2-dummyCard.getWidth()/2){
                card.setVel(new Vector2(0, 0));
                dummyCard.setVel(new Vector2(50*MyGdxGame.masterScale, 0));
            }
            else{
                card=new Card(new Vector2(Gdx.graphics.getWidth()/2, 350*MyGdxGame.masterScale));
                dummyCard=new Card(new Vector2(-175*MyGdxGame.masterScale, 350*MyGdxGame.masterScale));
            }
        }

        //add a new balloon to arraylist every set amount of time
        if (TimeUtils.timeSinceNanos(goTime)>TimeUtils.millisToNanos(2000)){
            Balloon bal=new Balloon();
            balloons.add(bal);
            goTime=TimeUtils.nanoTime();
        }

        //draw every balloon in arraylist
        for (Balloon balloon:balloons){
            balloon.draw(batch);

            //checks which side balloon came from, adds to 'remove' arraylist
            switch (balloon.startSide){
                case R:
                    if (balloon.getLoc().x<0-balloon.getWidth()){
                        balloon.dispose();
                        Integer integer = balloons.indexOf(balloon);
                        balloonsToRemove.add(integer);
                    }
                    break;
                case L:
                    if (balloon.getLoc().x>Gdx.graphics.getWidth()+balloon.getWidth()){
                        balloon.dispose();
                        Integer integer = balloons.indexOf(balloon);
                        balloonsToRemove.add(integer);
                    }
                    break;
            }
        }

        //remove every balloon in the 'remove' arraylist from the inital arraylist
        for (Integer integer:balloonsToRemove){
            balloons.remove(integer.intValue());
        }
        balloonsToRemove.clear();

        card.draw(batch);
        dummyCard.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        card.dispose();
        dummyCard.dispose();
    }

    public static Card getCard(){
        return card;
    }

    public static Card getDummyCard(){
        return dummyCard;
    }
}
