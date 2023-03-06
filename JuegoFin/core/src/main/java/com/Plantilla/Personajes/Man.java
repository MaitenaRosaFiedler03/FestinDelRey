/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla.Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
public class Man extends Image {
        Animation stand;
        Animation walk;
        public float tiempo; 
        Animation jump;
        Animation golpe, muerte;
        float time = 0;
        float xVelocity = 0;
        float yVelocity = 0;
        boolean canJump = false;
        boolean isFacingRight = true;
        public TiledMapTileLayer layer;
        final float GRAVITY = -0.5f;
        final float MAX_VELOCITY = 10f;
        final float DAMPING = 0.87f;
        public  final float width;
        public final float height; 
        Sound hit; 
        boolean muerto;
        int vida; 
        public Sound salto; 
        float tiempoPegar; 
        float tiempoPegando; 
        boolean pegar; 

    public boolean getPegar() {
        return pegar;
    }

    public void setPegar(boolean pegar) {
        
            this.pegar = pegar;
        
    }

    public Man( ) {
        
        this.width = 160;
        this.height= 140;
        this.setSize(1, height / width);
        this.pegar = false ;
        this.vida =3; 
       
        this.hit = Gdx.audio.newSound(Gdx.files.internal("Sonidos/hit.mp3"));
        this.salto = Gdx.audio.newSound(Gdx.files.internal("Sonidos/jump.mp3"));
               
         TextureRegion[] gpl = new TextureRegion[3];  
         TextureRegion[] run = new TextureRegion[7];  
         TextureRegion[] parado = new TextureRegion[10];
         TextureRegion[] muer = new TextureRegion[4];
       
         for (int i = 0; i < 7; i++) {
            run[i] = new TextureRegion(new Texture(Gdx.files.internal("Personaje/correr" + i + ".png")));
        }
         for (int i = 0; i < 3; i++) {
            gpl[i] = new TextureRegion(new Texture(Gdx.files.internal("Personaje/golpe" + i + ".png")));
        }
         for (int i = 0; i < 10; i++) {
            parado[i] = new TextureRegion(new Texture(Gdx.files.internal("Personaje/parado" + i + ".png")));
        }
         for (int i = 0; i < 4; i++) {
            muer[i] = new TextureRegion(new Texture(Gdx.files.internal("Personaje/muerto" + i + ".png")));
        }
       
        golpe  = new Animation(9000000f, gpl[0],  gpl[1], gpl[2]);
        golpe.setPlayMode(Animation.PlayMode.NORMAL);

        stand = new Animation(0.30f, parado[0],  parado[1],parado[2], parado[3], parado[4], parado[5], parado[6], parado[7],
                                                parado[8], parado[9]);
        stand.setPlayMode(Animation.PlayMode.LOOP);
        
        walk = new Animation(0.30f, run[0],  run[1], run[2], run[3], run[4], run[5], run[6]);
        walk.setPlayMode(Animation.PlayMode.LOOP);
        
        muerte = new Animation(0.30f, muer[0],  muer[1], muer[2], muer[3]);
        muerte.setPlayMode(Animation.PlayMode.NORMAL);
        
       tiempoPegar = 1f; 
    }
    public boolean comprobarMuerte(){
        
        muerto = false;
        if(this.getY() < 0){
            return muerto = true;
        }
        return false; 
    }

    public boolean isMuerto() {
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
    public Rectangle dimensiones(){
         Rectangle a = new Rectangle(getX() + 0.5f, getY(), getWidth(), getHeight());  
         return a; 
    }

    public void act(float delta) {
        time = time + delta;
        this.tiempoPegando += time; 
        
        
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            
            if (canJump) {
                  this.salto.play();
                  int i =0;
                  
                   yVelocity = yVelocity + MAX_VELOCITY * 2.2f;
            }
            canJump = false;
        }
        
    
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
            xVelocity = -1 * MAX_VELOCITY;
            isFacingRight = false;
        }

       
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
            xVelocity = MAX_VELOCITY;
            isFacingRight = true;
        }
        
      

        yVelocity = yVelocity + GRAVITY;

        float x = this.getX();
        float y = this.getY();
        float xChange = xVelocity * delta;
        float yChange = yVelocity * delta;

        if (canMoveTo(x + xChange, y, false) == false) {
            xVelocity = xChange = 0;
        } 

        if (canMoveTo(x, y + yChange, yVelocity > 0) == false) {
            canJump = yVelocity < 0;
            yVelocity = yChange = 0;
        }

        this.setPosition(x + xChange, y + yChange);

        xVelocity = xVelocity * DAMPING;
        if (Math.abs(xVelocity) < 0.5f) {
            xVelocity = 0;
        }
        
    }

    public Sound getHit() {
        return hit;
    }

    public void setHit(Sound hit) {
        this.hit = hit;
    }

    public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame;
        
        if(this.pegar == true){
            
             frame = (TextureRegion) golpe.getKeyFrame(time);
             batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
     
         }
        else if(muerto == true){
            frame = (TextureRegion) muerte.getKeyFrame(time);
        }
        else if (xVelocity != 0) {
            frame = (TextureRegion) walk.getKeyFrame(time);
        } else   {
             frame = (TextureRegion) stand.getKeyFrame(time);
        }
        

        if (isFacingRight) {
            batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
       } else {
            batch.draw(frame, this.getX() + this.getWidth(), this.getY(), -1 * this.getWidth(), this.getHeight());
        }
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
