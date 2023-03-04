/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla;

import Puntuaciones.Puntuaciones;
import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class MyGdxGame extends Game {
    
    public SpriteBatch batch;
    public BitmapFont font;
    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 768;
    public static final int TILES_IN_CAMERA = 16;
    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
    public static final int CAMERA_WIDTH = TILES_IN_CAMERA * TILE_WIDTH;
    public static final int CAMERA_HEIGHT = TILES_IN_CAMERA * TILE_HEIGHT;
    public static final int JUEGO_FACIL =0; 
    public static final int JUEGO_DIFICIL =1; 
    public static int cantidad_comida;
    public static int modoJuego;
    public Puntuaciones puntuaciones; 
    public ArrayList<Integer> puntajes; 
    
	public void create() {
            batch = new SpriteBatch();
            font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false); 
            this.setScreen(new Inicio(this));
            puntuaciones = new Puntuaciones(); 
            this.puntajes = new ArrayList<>(); 
	}
}
