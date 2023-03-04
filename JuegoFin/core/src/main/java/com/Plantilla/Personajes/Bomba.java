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
    
    Animation disparando ;
    public TiledMapTileLayer layer; 
    final float width;
    final float height; 
    float xVelocity = 0;
    float yVelocity = 0;
    float time = 0;
    
    public Bomba(){
        this.width = 30; 
        this.height = 30; 
        this.setSize(1, height / width);
        this.crearAnimacion();
    }
    
    public void crearAnimacion(){
         TextureRegion[] par = new TextureRegion[8];
         
          for (int i = 0; i < 4; i++) {
            par[i] = new TextureRegion(new Texture(Gdx.files.internal("reyCerdo/canon/disparo" + i + ".png")));
        }
          
        disparando  = new Animation(0.15f, par[0],  par[1], par[2], par[3]);
        disparando.setPlayMode(Animation.PlayMode.LOOP);
  
    }
     @Override
     public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame;
        frame = (TextureRegion) disparando.getKeyFrame(time);
        batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
     }
      @Override
    public void act(float delta) {
        time += delta;
    
    }
    public Rectangle dimensiones(){
        Rectangle a = new Rectangle(getX() , getY(), getWidth(), getHeight());  
        return a; 
    }
    
    
}
