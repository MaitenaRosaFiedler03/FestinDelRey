/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla;

import com.Plantilla.PantallasAyuda.Ayuda;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
public class Menu implements Screen {
    
        final MyGdxGame game ; 
        final OrthographicCamera camera;
        Table testTable;
        Stage stage; 
        Rectangle sonido, volver, salir; 
        Vector3 touchPoint;
        Texture sonidos; 
        String estadoSound;
        boolean estado;
        Music musica; 

        public Menu(final MyGdxGame game ){
            estado = true; 
            this.game = game; 
            this.musica = musica ; 
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 800, 480);
            testTable = new Table();
            testTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("fondo.png"))));
            testTable.setFillParent(true);
            testTable.setDebug(true);
            stage = new Stage();
            stage.addActor(testTable);
            touchPoint = new Vector3();
            estadoSound= "soundOn.png"; 
            
            sonido = new Rectangle(370, 150, 64, 64);
            volver = new Rectangle(345, 270, 64, 64);
            salir = new Rectangle(350, 200, 64, 44);
            sonidos = new Texture (Gdx.files.internal(estadoSound)); 
        }

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
                stage.act();
                stage.draw();
                
                this.game.batch.begin();
                
                this.game.font.draw(game.batch,"VOLVER", 345, 300);
                this.game.font.draw(game.batch,"SALIR",350, 250);
                this.game.batch.draw(sonidos,370, 150 ); 
                this.game.batch.end(); 
                 sonidos = new Texture (Gdx.files.internal(estadoSound)); 
		
                
            if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            System.out.println("y : " + touchPoint.y + " x: " + touchPoint.x);
            if (volver.contains(touchPoint.x, touchPoint.y)) {
                    
                  game.setScreen(new MainScreen(game));
			dispose();
                 
            }
            if (salir.contains(touchPoint.x, touchPoint.y)) {
                 game.setScreen(new Inicio(game));
			dispose();
            }
            if (sonido.contains(touchPoint.x, touchPoint.y)) {
                if(estado == false){
                    this.estadoSound = "soundOn.png"; 
                    MyGdxGame.SONIDO = true; 
                    System.out.println("a");
                    estado= true;
                     MyGdxGame.musica.play();
                }
                else if(estado){
                    this.estadoSound = "soundOff.png"; 
                    MyGdxGame.SONIDO = false; 
                    System.out.println("a");
                    estado= false;
                    MyGdxGame.musica.stop();
                }
            }
	}
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

     

