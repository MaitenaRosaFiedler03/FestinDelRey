/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla.Personajes;

import com.Plantilla.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author maite
 */
public class Comida extends Image{
    Texture vista; 
    public TiledMapTileLayer layer; 
    public  final float width = 120;
    public final float height = 120;
    int tipo; 
    
    String salchichas = "comida/salchichas.png"; 
    String aceitunas = "comida/aceitunas.png"; 
    String tarta = "comida/tarta.png"; 
    int time; 

    public Comida(){
         this.setSize(1, height / width);
    }
    
    public TiledMapTileLayer getLayer() {
        return layer;
    }

    public void setLayer(TiledMapTileLayer layer) {
        this.layer = layer;
    }
    
    
    public String RandomComida(){
        long i =  (int)(Math.random()*(3-1+1)+1); 
        
        if(i == 1){
            this.tipo = MyGdxGame.ACEITUNA; 
            return aceitunas; 
            
        }
        if(i == 2){
            this.tipo = MyGdxGame.TARTA; 
            return tarta ; 
        }
        if(i == 3){
            this.tipo = MyGdxGame.SALCHICA; 
            return salchichas; 
        }
        return null;
    }

    public Texture getVista() {
        return vista;
    }

    public void setVista() {
      
        String valor = this.RandomComida();
        this.vista = new Texture(Gdx.files.internal(valor));
    }
    @Override
     public void draw(Batch batch, float parentAlpha) {
         batch.draw(this.vista, this.getX() + this.getWidth(), this.getY(), -1 * this.getWidth(), this.getHeight());
     }
     
    @Override
    public void act(float delta) {
        time+=delta; 
    }
    public Rectangle dimensiones(){
        Rectangle a = new Rectangle(getX() , getY(), getWidth(), getHeight());  
        return a; 
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
}
