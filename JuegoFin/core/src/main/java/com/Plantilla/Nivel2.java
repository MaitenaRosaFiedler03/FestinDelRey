/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla;

import com.Plantilla.Personajes.Cerdo1;
import com.Plantilla.Personajes.Cerdo2;
import com.Plantilla.Personajes.Man;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 *
 * @author maite
 */
public class Nivel2 implements Screen {
    
    
    MyGdxGame juego; 
    Stage stage;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    public BitmapFont font;
    Rectangle menu; 
    Vector3 touchPoint;
    int puntos; 
    int lng; 
    int index; 
    Table testTable;
    Man man; 
    int i ;
    Cerdo1 enemigo; 
    Cerdo2 enemigo2;
    
    public Nivel2(MyGdxGame g, Man man){
         this.juego = g; 
         this.man = man;
         
    }
   
    @Override
    public void show() {
        i=0;
         map = new TmxMapLoader().load("nivel2.tmx");
        final float pixelsPerTile = 32;
        renderer = new OrthogonalTiledMapRenderer(map, 1 / pixelsPerTile);
        camera = new OrthographicCamera();

        stage = new Stage();
        stage.getViewport().setCamera(camera);
        puntos =0; 
        
        /*this.enemigo  = new Cerdo1();
        this.enemigo.layer = (TiledMapTileLayer) map.getLayers().get("principal");
        this.enemigo.setPosition(15, 7);
        stage.addActor(this.enemigo);
        
        this.enemigo2  = new Cerdo2();
        this.enemigo2.layer = (TiledMapTileLayer) map.getLayers().get("principal");
        this.enemigo2.setPosition(20, 13);
        stage.addActor(this.enemigo2);
        */
        
    }
    void comprobarCollision(){
        if(enemigo.dimensiones().overlaps(man.dimensiones())){
           this.enemigo.setxVelocity(0f);
            this.enemigo.setPegar(true);
           
        }
    }

    @Override
    public void render(float delta) {
        
        //this.compruebaSeg();
         this.menu(); 
        Gdx.gl.glClearColor(0f, 0f, 0.0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        this.juego.batch.enableBlending();
        
        camera.position.x = this.man.getX(); 
        camera.update();
            
        renderer.setView(camera);
        renderer.render();
        
 
        
        this.comprobarCollision();
    }
        /*(this.man.comprobarMuerte()){
            this.juego.setScreen(new Perdiste(juego));
                        
            dispose();
        } 
        stage.act(delta);
        stage.draw();
         this.juego.batch.begin();
            TextureRegion a= new TextureRegion(new Texture(Gdx.files.internal("perdiste.png")));
            this.juego.batch.draw(a, 0,0, 75, 75);
        this.juego.batch.end();   
        
       
       
    }
    public void compruebaSeg(){
        if(this.man.getX() > this.enemigo2.getX()){
            enemigo2.miraDerecha = true;
            enemigo2.setxVelocity(2 );
        }
        else{
            enemigo2.miraDerecha = false; 
            enemigo2.setxVelocity(-2 );
        }
        
    }
    
    @Override
    public void dispose() {
    }

    /**
     *
     */
        
    @Override
    public void hide() {
    }

    /**
     *
     */
    public void pause() {
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, 20 * width / height, 20);
    }

    @Override
    public void resume() {
    }
   
    public void menu(){
        if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            System.out.println("y : " + touchPoint.y + " x: " + touchPoint.x);
            if (menu.contains(touchPoint.x, touchPoint.y)) {
                //Assets.playSound(Assets.clickSound);
                //juego.setScreen(new Menu(juego));
                System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
                //return;
            }
            
        }
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
