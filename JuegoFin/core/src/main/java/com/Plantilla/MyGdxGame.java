/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla;

import Puntuaciones.Puntuaciones;
import com.Plantilla.Personajes.Man;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
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
    public static boolean SONIDO = false; 
    public static Music musica; 
    public static int nivel; 
    public static Man man; 
    public final static int ACEITUNA =0;
    public final static int TARTA =1 ; 
    public final static int SALCHICA =2; 
    public static ArrayList<Texture> vidas; 
    public static int puntos; 
    
    
	public void create() {
            this.puntos =0; 
            batch = new SpriteBatch();
            font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false); 
            this.setScreen(new Inicio(this));
            //puntuaciones = new Puntuaciones(); 
            this.puntajes = new ArrayList<>(); 
            this.musica = Gdx.audio.newMusic(Gdx.files.internal("Sonidos/musica.mp3"));
            this.nivel = 1; 
            this.man = new Man(); 
            
            this.vidas = new ArrayList<>();
            
            for (int i = 0; i < 3; i++) {
                vidas.add(new Texture(Gdx.files.internal("corazon.png")));
            }
            
	}
        public static void restaurar(){
            
            vidas = new ArrayList<>();
            
            for (int i = 0; i < 3; i++) {
                vidas.add(new Texture(Gdx.files.internal("corazon.png")));
            }
            puntos =0; 
        }
}
