/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla.Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
public class Cerdo3 extends Image {
    
    Animation ataque, caminar, muerte, parado ;
    public TiledMapTileLayer layer; 
    final float width;
    final float height; 
    float xVelocity = 0;
    float yVelocity = 0;
    float time = 0;
    boolean choco; 
    boolean pegar ; 
    public boolean miraDerecha; 
    public final float VELOCIDAD = 2f;
    final float GRAVITY = -3.5f;
    Sound hit;

     public Cerdo3(){
       
        this.miraDerecha = false; 
        this.width= 90;
        this.height = 145;
        this.xVelocity = 0f; 
        this.setSize(1, height / width);
        this.choco = false; 
        this.pegar = false; 
        
        this.crearAtaque();
        this.crearCaminar(); 
        this.crearMuerte();
        this.crearParado();
        this.hit = Gdx.audio.newSound(Gdx.files.internal("Sonidos/cerdo.mp3"));
         
           
    }
     public void gritar(){
         this.hit.play(); 
     }
    public void crearParado(){
         TextureRegion[] par = new TextureRegion[12];
         
          for (int i = 0; i < 6; i++) {
            par[i] = new TextureRegion(new Texture(Gdx.files.internal("cerdo3/parado/parado" + i + ".png")));
        }
          
        parado  = new Animation(0.15f, par[0],  par[1], par[2], par[3], par[4], par[5]);
        parado.setPlayMode(Animation.PlayMode.LOOP);
    }
    
     public void crearCaminar(){
         TextureRegion[] cam = new TextureRegion[10];
         
          for (int i = 0; i < 10; i++) {
            cam[i] = new TextureRegion(new Texture(Gdx.files.internal("cerdo3/correr/correr" + i + ".png")));
        }
          
        caminar  = new Animation(0.15f, cam[0],  cam[1], cam[2], cam[3], cam[4], cam[5], cam[6], cam[7], cam[8],
                               cam[9]);
        caminar.setPlayMode(Animation.PlayMode.LOOP);
  
     }
     public void crearAtaque(){
        TextureRegion[] atk = new TextureRegion[9];
         
        for (int i = 0; i < 9; i++) {
           atk[i] = new TextureRegion(new Texture(Gdx.files.internal("cerdo3/ataque/ataque" + i + ".png")));
        }
          
        ataque  = new Animation(1/12f, atk[0],  atk[1], atk[2], atk[3], atk[4], atk[5], atk[6], atk[7], atk[8]);
        ataque.setPlayMode(Animation.PlayMode.LOOP);
  
     }
     
    public void crearMuerte(){
        
        TextureRegion[] muer = new TextureRegion[8];
         
          for (int i = 0; i < 5; i++) {
            muer[i] = new TextureRegion(new Texture(Gdx.files.internal("cerdo3/muerte/muerte" + i + ".png")));
        }
          
        muerte  = new Animation(0.15f, muer[0],  muer[1], muer[2], muer[3], muer[4], muer[5]);
        muerte.setPlayMode(Animation.PlayMode.LOOP);
  
    }
     
     @Override
     public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame;
        
        if(pegar){
            frame = (TextureRegion) ataque.getKeyFrame(time);
             
            this.pegar = false; 
            
        }
        else if (xVelocity != 0) {
            frame = (TextureRegion) caminar.getKeyFrame(time);
        } else   {
            frame = (TextureRegion) parado.getKeyFrame(time);
        }
        
        
        if (miraDerecha == false) {
            batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
            
       } else {
            batch.draw(frame, this.getX() + this.getWidth(), this.getY(), -1 * this.getWidth(), this.getHeight());
           
        }
        this.xVelocity = VELOCIDAD;
    }   
     
     public boolean getMiraDerecha() {
        return miraDerecha;
    }

    public void setMiraDerecha(boolean miraDerecha) {
        this.miraDerecha = miraDerecha;
    }
  
    
    @Override
    public void act(float delta) {
        time += delta;
         this.xVelocity = 0;
         
        
          yVelocity = yVelocity + GRAVITY;
        
        float x = this.getX();
        float y = this.getY();
        float xChange = xVelocity * delta;
        float yChange = yVelocity * delta;
        
      
            if (x - xChange < 40){
                this.xVelocity = VELOCIDAD; 
                this.miraDerecha = true;
            } 
            if (x- xChange > 50) {
               this.xVelocity = - VELOCIDAD; 
                this.miraDerecha = false; 

            } 
        
            if (canMoveTo(x, y + yChange, yVelocity > 0) == false) {

                yVelocity = yChange = 0;
            }
            this.setPosition(x + xChange, y + yChange);

            if (Math.abs(xVelocity) < 0.5f) {
                xVelocity = 0;
            }
        
    }
    
    public float getxVelocity(){
        return xVelocity;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public boolean getPegar() {
        return pegar;
    }

    public void setPegar(boolean pegar) {
        this.pegar = pegar;
    }
    public Rectangle dimensiones(){
        Rectangle a = new Rectangle(getX() + 0.5f, getY(), getWidth(), getHeight());  
        return a; 
    }
     private boolean canMoveTo(float startX, float startY, boolean shouldDestroy) {
        float endX = startX + this.getWidth();
        float endY = startY + this.getHeight();

        int x = (int) startX; 
        while (x < endX) {

            int y = (int) startY;
            while (y < endY) {
                if (layer.getCell(x, y) != null) {
                    return false;
                }
                y = y + 1;
            }
            x = x + 1;
        }

        return true;
    }
     
     
    
}
