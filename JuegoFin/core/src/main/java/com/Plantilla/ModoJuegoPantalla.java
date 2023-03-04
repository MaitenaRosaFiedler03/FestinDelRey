/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla;

import com.Plantilla.PantallasAyuda.Ayuda;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 *
 * @author maite
 */
public class ModoJuegoPantalla implements Screen {
    
        final MyGdxGame game ; 
        final OrthographicCamera camera;
        Table testTable;
        Stage stage; 
        Rectangle botonModoFacil, botonModoDificil; 
        Vector3 touchPoint;
        String opcion; 
        Rectangle salir, continuar; 
        Texture  bSalir; 
        Texture bContinuar ; 

        public ModoJuegoPantalla(final MyGdxGame game){
            
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
            opcion = ""; 
            botonModoFacil = new Rectangle(360, 250, 64, 64);
            botonModoDificil = new Rectangle(360, 200, 64, 64);
            salir = new Rectangle(250, 150, 64, 64);
            continuar = new Rectangle(500, 150, 64, 64);
            bSalir = new Texture(Gdx.files.internal("atras.png"));
            bContinuar = new Texture(Gdx.files.internal("adelante.png"));
        }

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
                stage.act();
                stage.draw();
                
                this.game.batch.begin();
               
                this.game.batch.draw(bSalir, 250, 150);
                this.game.batch.draw(bContinuar, 500, 150);
                this.game.font.draw(game.batch,"Fácil", 360, 300);
                this.game.font.draw(game.batch,"Dificil", 360, 250);
                this.game.font.draw(game.batch,"Modo de juego", 300, 400);
                 this.game.font.draw(game.batch,opcion, 250, 100);
                this.game.batch.end(); 

		
                
            if (Gdx.input.justTouched()) {
                camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
                System.out.println("x : " + touchPoint.x + " y : " + touchPoint.y);
                if (botonModoFacil.contains(touchPoint.x, touchPoint.y)) {
                        MyGdxGame.modoJuego = MyGdxGame.JUEGO_FACIL;
                          opcion = "Haz elegido el modo Fácil"; 
                           
                }
                if (botonModoDificil.contains(touchPoint.x, touchPoint.y)) {
                    MyGdxGame.modoJuego = MyGdxGame.JUEGO_DIFICIL;
                
                     opcion = "Haz elegido el modo Dificil";
                }
                if (salir.contains(touchPoint.x, touchPoint.y)) {
                       game.setScreen(new Inicio(game));
                            dispose();
                }   
                if (continuar.contains(touchPoint.x, touchPoint.y)) {
                       game.setScreen(new Historio(game));
                            dispose();
                } 
	}
    }
    @Override
    public void show() {
        
      /*  this.game.batch.begin();
            this.game.font.draw(game.batch,"Modo de juego",120, 120);
            this.game.font.draw(game.batch,"Fácil", botonModoFacil.x, botonModoFacil.y);
            this.game.font.draw(game.batch,"Dificil", botonModoDificil.x, botonModoDificil.y);
        this.game.batch.end(); 

*/
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
