package com.yojplex.cardswipe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kenthall on 12/12/15.
 */
public class Card {
    private Texture card;
    private Vector2 loc;
    private Vector2 vel;
    private float width;
    private float height;

    public Card(Vector2 loc){
        card=new Texture("card.png");
        width=card.getTextureData().getWidth()*1.5f*MyGdxGame.masterScale;
        height=card.getTextureData().getHeight()*1.5f*MyGdxGame.masterScale;

        this.loc=new Vector2(loc.x-width/2, loc.y-height/2);
        vel=new Vector2(0, 0);

    }

    public void draw(SpriteBatch batch){
        batch.draw(card, loc.x, loc.y, width, height);

        loc.x+=vel.x;
        loc.y+=vel.y;
    }

    public void dispose(){
        card.dispose();
    }

    public void setVel(Vector2 vel){
        this.vel=vel;
    }

    public Vector2 getVel(){
        return vel;
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
