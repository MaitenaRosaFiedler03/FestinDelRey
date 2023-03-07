/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla.Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author maite
 */
public class Bomba extends Image{
    
    TextureRegion disparando ;
    public TiledMapTileLayer layer; 
    final float width;
    final float height; 
    public float xVelocity = 0;
    public float yVelocity = 0;
    float time = 0;
    public boolean destriuda ; 
    
    public Bomba(){
        this.width = 60; 
        this.height = 60; 
        this.setSize(1, height / width);
        destriuda = true; 
        this.disparando = new TextureRegion(new Texture(Gdx.files.internal("reyCerdo/canon/bomba.png"))); 
        
    }
    
   
     @Override
     public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame;
        frame = disparando;
        batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
     }
      @Override
    public void act(float delta) {
        time += delta;
        setPosition(getX() + xVelocity * delta, getY() + yVelocity * delta);
        
    
    }
    public Rectangle dimensiones(){
        Rectangle a = new Rectangle(getX() , getY(), getWidth(), getHeight());  
        return a; 
    }
    
    
}
