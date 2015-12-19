package com.yojplex.cardswipe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by kenthall on 12/19/15.
 */
public class Balloon {
    public enum StartSide{
        R, L
    }
    public StartSide startSide;
    private Vector2 loc;
    private Vector2 vel;
    private Texture balImg;
    private float width;
    private float height;
    private Random generator;

    public Balloon(){
        balImg=new Texture("balloon.png");
        width = balImg.getTextureData().getWidth()*0.6f*MyGdxGame.masterScale;
        height = balImg.getTextureData().getHeight()*0.6f*MyGdxGame.masterScale;

        generator=new Random();

        switch (generator.nextInt(2)){
            case 0:
                startSide=StartSide.R;
                loc=new Vector2(Gdx.graphics.getWidth()+balImg.getWidth()*MyGdxGame.masterScale, 1800*MyGdxGame.masterScale);
                vel=new Vector2(-10*MyGdxGame.masterScale, 0*MyGdxGame.masterScale);
                break;
            case 1:
                startSide=StartSide.L;
                loc=new Vector2(0-balImg.getWidth()*MyGdxGame.masterScale, 1800*MyGdxGame.masterScale);
                vel=new Vector2(10*MyGdxGame.masterScale, 0*MyGdxGame.masterScale);
                break;
        }
    }

    public void draw(SpriteBatch batch){
        batch.draw(balImg, loc.x, loc.y, width, height);

        loc.x+=vel.x;
        loc.y+=vel.y;
    }

    public void dispose(){
        balImg.dispose();
    }

    public Vector2 getLoc(){
        return loc;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }
}
