/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla;

import com.Plantilla.PantallasAyuda.Ayuda;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 *
 * @author maite
 */
public class Inicio  implements Screen {
    
        final MyGdxGame game ; 
        final OrthographicCamera camera;
        Table testTable;
        Stage stage; 
        Rectangle ayuda; 
        Vector3 touchPoint;
        Rectangle jugar; 
        Music musica; 
        

        public Inicio(final MyGdxGame game){
            this.game = game; 
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 800, 480);
            testTable = new Table();
            testTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("Inicio.png"))));
            testTable.setFillParent(true);
            testTable.setDebug(true);
            stage = new Stage();
            stage.addActor(testTable);
            touchPoint = new Vector3();
            
            ayuda = new Rectangle(500, 95, 134, 20);
            jugar = new Rectangle(500, 50, 134, 20);
            
            // this.musica = Gdx.audio.newMusic(Gdx.files.internal("Sonidos/musica.mp3"));
             //this.musica.setLooping(true);
        }

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
                stage.act();
                stage.draw();
                
                this.game.batch.begin();
                
                this.game.font.draw(game.batch,"", ayuda.x, ayuda.y);
                 this.game.font.draw(game.batch,"", jugar.x, jugar.y);
                this.game.batch.end(); 

		
                
            if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            System.out.println("y : " + touchPoint.y + " x: " + touchPoint.x);
            if (ayuda.contains(touchPoint.x, touchPoint.y)) {
                    
                    game.setScreen(new Ayuda(game));
			dispose();
                 
            }
            if (jugar.contains(touchPoint.x, touchPoint.y)) {
                   game.setScreen(new ModoJuegoPantalla(game));
			dispose();
            }
	}
          //  this.musica.play(); 
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
    public void hide() {
       
    }

    @Override
    public void dispose() {
       
    }

       
}

    
