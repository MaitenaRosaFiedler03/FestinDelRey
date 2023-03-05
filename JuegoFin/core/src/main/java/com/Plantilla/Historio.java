/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 *
 * @author maite
 */
public class Historio implements Screen {
    
        final MyGdxGame game ; 
        final OrthographicCamera camera;
        Table testTable;
        Stage stage; 
        Rectangle ayuda; 
        Vector3 touchPoint;
        Rectangle jugar; 
        int mensajeActual = 0; 
        String[] mensajes ={"Oh no! Alguien se robó mi comida!" + "\n"  , "\n" + "Estoy seguro que fueron \n esos cerdos asquerosos"+ "\n" , "\n" + " Canchos de mierda"}; 
        Texture man; 
        
    public Historio(MyGdxGame juego){
        this.game = juego; 
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        testTable = new Table();
        testTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("fondo.png"))));
        testTable.setFillParent(true);
        testTable.setDebug(true);
        stage = new Stage();
        stage.addActor(testTable);
        touchPoint = new Vector3();
        
        man = new Texture(Gdx.files.internal("personaje/parado0.png")); 
        
            
    }
    @Override
    public void show() {
     }

    @Override
    public void render(float f) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        stage.act();
        stage.draw();
        
        if(Gdx.input.justTouched()){
            mensajeActual++;
            if(mensajeActual == mensajes.length) {
                mensajeActual--;
                game.setScreen(new Nivel2(game));
            }
	}

        this.game.batch.begin();
        this.game.font.draw(game.batch, mensajes[mensajeActual], 120, 400, 320, Align.center, false);
        this.game.batch.draw(man, 400 ,250);
        this.game.batch.end();
   }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
    
}
