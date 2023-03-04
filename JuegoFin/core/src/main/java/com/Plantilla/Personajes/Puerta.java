/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla.Personajes;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author maite
 */
public class Puerta extends Image{
    
    final float width;
    final float height; 
    public TiledMapTileLayer layer; 
    
    public Puerta (){
        this.width= 230;
        this.height = 130;
    }
    public Rectangle dimensiones(){
        Rectangle a = new Rectangle(getX() + 0.5f, getY(), getWidth(), getHeight());  
        return a; 
    }
}
