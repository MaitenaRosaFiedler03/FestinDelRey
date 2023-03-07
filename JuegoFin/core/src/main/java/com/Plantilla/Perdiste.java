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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;

/**
 *
 * @author maite
 */
public class Perdiste implements Screen {
    
        final MyGdxGame game ; 
        final OrthographicCamera camera;
        Table testTable;
        Stage stage; 
        int[] b; 
        
        public Perdiste(final MyGdxGame game){
            this.game = game; 
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 800, 480);
            testTable = new Table();
            testTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("perdiste.png"))));
            testTable.setFillParent(true);
            testTable.setDebug(true);
             stage = new Stage();
            stage.addActor(testTable);
            b = this.top3();
             MyGdxGame.restaurar();
        }

	@Override
	public void render(float delta) {
            ScreenUtils.clear(0, 0, 0.2f, 1);

            camera.update();
            game.batch.setProjectionMatrix(camera.combined);
            stage.act();
            stage.draw();

            game.batch.begin();
             game.font.draw(game.batch,String.valueOf(MyGdxGame.puntos) , 320,220 );

            game.font.draw(game.batch, "Puntuaciones  " , 170,190 );
            if(this.game.puntajes.size() >= 1){
                game.font.draw(game.batch, "1- " + String.valueOf(b[0]) , 280,200 );
            }
            if(this.game.puntajes.size() >= 2){

                game.font.draw(game.batch, "2- " + String.valueOf(b[1]) , 280,180 );
            }
            if(this.game.puntajes.size() >= 3){

                game.font.draw(game.batch, "3- " + String.valueOf(b[2]) , 280,160 );
            }
            if(this.game.puntajes.size() <=  1) {
                game.font.draw(game.batch, "1: k"  , 320,180 );
                game.font.draw(game.batch, "2: 0" , 320,160 );
            }
            game.batch.end();

           
            if (Gdx.input.justTouched()) {
                    game.setScreen(new Inicio(game));
                    dispose();
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
    public int[] top3(){
        
        int[] a = new int[3]; 
        int k =0; 
        int mayor =0 ;
        ArrayList<Integer > aux = new ArrayList<>(); 
        
        for (int i = 0; i < this.game.puntajes.size()  ; i++) {
            if(i ==0 ){
                 aux.add(this.game.puntajes.get(i)); 
            }
            if(this.game.puntajes.get(i) >  aux.get(k) ){
                aux.add(this.game.puntajes.get(i)); 
                System.out.println("valor : " + this.game.puntajes.get(i));
                k++; 
            }
            else if (this.game.puntajes.get(i) < aux.get(k) ){
                aux.add(this.game.puntajes.get(i)); 
                System.out.println("valor : " + this.game.puntajes.get(i));
                k++; 
            } 
            
        }
        
       for (int i = aux.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (aux.get(j) > aux.get(j + 1)) {
                    int value = aux.get(j+ 1);
                    aux.set(j+1, aux.get(j)); 
                    aux.set(j, value); 
                } 
            }
        }
       
       int g = aux.size();
      
       g -= 1; 
       if(aux.size() >= 3){
            for (int i = g;  mayor < 3; i--) {

                a[mayor] = aux.get(i);
                mayor++; 
            }
       }
        else{
           for (int i = g;  mayor < aux.size(); i--) {

                a[mayor] = aux.get(i);
                mayor++; 
            }  
        }
       
        
        
        return a; 
    }
}
