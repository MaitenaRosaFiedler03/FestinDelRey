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
public class Cerdo1 extends Image {
  
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
     public final float VELOCIDAD_FACIL = 2.5f;
    public final float VELOCIDAD_DIFICIL = 3.5f; 
    public final float VELOCIDAD; 
    Sound hit; 
    int vida; 
    boolean muerto ; 

     public Cerdo1( int velocidad){
         
        if(velocidad == 1){
            this.VELOCIDAD = VELOCIDAD_DIFICIL; 
        }
        else{
            this.VELOCIDAD = VELOCIDAD_FACIL; 
        } 
         
        this.vida =3; 
        this.miraDerecha = false; 
        this.width= 120;
        this.height = 130;
        this.xVelocity = 0f; 
        this.setSize(1, height / width);
        this.choco = false; 
        this.pegar = false; 
        this.hit = Gdx.audio.newSound(Gdx.files.internal("Sonidos/gritoPig.mp3"));
        this.muerto= false; 
         
        this.crearAtaque();
        this.crearCaminar();
        this.crearMuerte();
        this.crearParado(); 
       
          
    }

    public boolean getMuerto() {
        return muerto;
    }

    public void setMuerto(boolean muerto) {
        this.muerto = muerto;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
     
    public void gritar(){
        this.hit.play(); 
    }
    
    public void crearAtaque(){
        TextureRegion[] atk = new TextureRegion[12];
         
        for (int i = 0; i < 12; i++) {
            atk[i] = new TextureRegion(new Texture(Gdx.files.internal("cerdo1/ataque/ataque" + i + ".png")));
        }
          
        ataque  = new Animation(1/12f, atk[0],  atk[1], atk[2], atk[3], atk[4], atk[5], atk[6], atk[7], atk[8],
                               atk[9], atk[10], atk[11]);
        ataque.setPlayMode(Animation.PlayMode.LOOP);
  
     }
     public void crearCaminar(){
         
        TextureRegion[] cam = new TextureRegion[11];
         
        for (int i = 0; i < 11; i++) {
            cam[i] = new TextureRegion(new Texture(Gdx.files.internal("cerdo1/correr/correr" + i + ".png")));
        }
          
        caminar  = new Animation(0.15f, cam[0],  cam[1], cam[2], cam[3], cam[4], cam[5], cam[6], cam[7], cam[8],
                               cam[9], cam[10]);
        caminar.setPlayMode(Animation.PlayMode.LOOP);
  
     }
    public void crearMuerte(){
        TextureRegion[] muer = new TextureRegion[7];
         
        for (int i = 0; i < 7; i++) {
            muer[i] = new TextureRegion(new Texture(Gdx.files.internal("cerdo1/muerto/muerto" + i + ".png")));
        }
          
        muerte  = new Animation(0.15f, muer[0],  muer[1], muer[2], muer[3], muer[4], muer[5], muer[6]);
        muerte.setPlayMode(Animation.PlayMode.LOOP);
  
    }
    
    public void crearParado(){
        TextureRegion[] par = new TextureRegion[7];
         
          for (int i = 0; i < 7; i++) {
            par[i] = new TextureRegion(new Texture(Gdx.files.internal("cerdo1/parado/parado" + i + ".png")));
        }
          
        parado  = new Animation(0.15f, par[0],  par[1], par[2], par[3], par[4], par[5], par[6]);
        parado.setPlayMode(Animation.PlayMode.LOOP);
    }
    
    public boolean getMiraDerecha() {
        return miraDerecha;
    }

    public void setMiraDerecha(boolean miraDerecha) {
        this.miraDerecha = miraDerecha;
    }
    final float GRAVITY = -3.5f;
   
    
    @Override
    public void act(float delta) {
        time += delta;

        if(choco){
            this.xVelocity = VELOCIDAD; 
            this.miraDerecha = true;
        }
        else{
            this.xVelocity = -VELOCIDAD; 
            this.miraDerecha = false; 
        }
        
          yVelocity = yVelocity + GRAVITY;
        
        float x = this.getX();
        float y = this.getY();
        float xChange = xVelocity * delta;
        float yChange = yVelocity * delta;
        
      
        if (canMoveTo(x + xChange, y, false) == false) {
           if(choco){
               choco = false; 
           }
           else{
               choco = true;
           }
        } 
        if (canMoveTo(x - xChange, y, false) == false) {
           if(choco){
               choco = true; 
           }
           else{
               choco = false;
           }
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
     
    @Override
     public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame;
        
       
        if(pegar){
             frame = (TextureRegion) ataque.getKeyFrame(time);
             
            this.pegar = false; 
  
         }
        else if(muerto){
             frame = (TextureRegion) muerte.getKeyFrame(time);
             this.hit.play(); 
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

}
