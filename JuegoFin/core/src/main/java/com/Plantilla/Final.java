/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla;

import com.Plantilla.PantallasAyuda.Ayuda1;
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

/**
 *
 * @author maite
 */
public class Final implements Screen{

   
    MyGdxGame game;

    OrthographicCamera camera;
    Rectangle nextBounds;
    Vector3 touchPoint;
   
    TextureRegion helpRegion;
    Texture flecha;
    Table testTable;
    Stage stage;
    float tiempoDisparoCanon; 
    String[] dialogos ={"Rey Man: Devuelveme mi comida!", "\nRey Cerdo: Esa comida que \ntanto quieres era mi hermano" ,
                        "Rey Man: No lo sabia" , "\nRey Cerdo: ya es tarde *se muere*"
                };
    int mensajeActual; 

    public Final (MyGdxGame game) {
        
       
        this.game = game; 
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        testTable = new Table();
        testTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("fondo.png"))));
        testTable.setFillParent(true);
        testTable.setDebug(true);
        stage = new Stage();
        stage.addActor(testTable);
        touchPoint = new Vector3();
        mensajeActual=0;     
           
            
    }

    public void update () {
        
        
            
    }
    public void draw () {
            
           
           
    }

    @Override
    public void render (float delta) {
        
        

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        stage.act();
        stage.draw();
            
        if(Gdx.input.justTouched()) {
                 mensajeActual++; 
                if(mensajeActual == dialogos.length) {
                    mensajeActual--;
                    game.setScreen(new Inicio(game));
                }
                
               
           }
        
        this.game.batch.begin();
        this.game.font.draw(game.batch, dialogos[mensajeActual], 120, 400, 320, Align.center, false);

        this.game.batch.end();
       
            
    }

    @Override
    public void hide () {
          
    }

    @Override
    public void show() {
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
    public void dispose() {
        
    }
    
}
