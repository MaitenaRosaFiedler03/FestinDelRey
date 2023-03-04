/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla;

import com.Plantilla.Personajes.Canon;
import com.Plantilla.Personajes.Cerdo1;
import com.Plantilla.Personajes.Cerdo2;
import com.Plantilla.Personajes.Cerdo3;
import com.Plantilla.Personajes.Comida;
import com.Plantilla.Personajes.Man;
import com.Plantilla.Personajes.ReyCerdo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import java.util.ArrayList;

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
    //Puerta puerta; 
    Rectangle menu; 
    Vector3 touchPoint;
    int puntos; 
    Man man; 
    int vida;
    Cerdo1 enemigo; 
    Cerdo2 enemigo2;
    Cerdo3 enemigo3; 
    int anterior; 
    ArrayList<Texture> vidas; 
    Comida[] comida; 
    boolean inicioCerdo2;
    int pegado ; 
    ReyCerdo rey; 
    Canon canon; 
    String[] dialogos ={"Rey Man: Devuelveme mi comida!", "\nRey Cerdo: Esa comida que \ntanto quieres era mi hermano" ,
                        "Rey Man: No lo sabia" , "\nRey Cerdo: ya es tarde *se muere*"
                };
    int mensajeActual; 
    boolean Final ; 
    
    public Nivel2(MyGdxGame g){
        this.juego = g; 
        this.man = MyGdxGame.man;
        this.mensajeActual =0;
        vida =3; 
        this.inicioCerdo2 = true; 
        this.juego = g; 
        menu = new Rectangle(0, 19, 24, 64);
        touchPoint = new Vector3();
        this.Final = false; 
        this.comida = new Comida[5];
        pegado=0; 
        
        for (int i = 0; i < 5; i++) {
            this.comida[i] = new Comida(); 
           
        }
        this.vidas = new ArrayList<>(); 
        for (int i = 0; i < 3; i++) {
            vidas.add(new Texture(Gdx.files.internal("corazon.png")));
        }
         
    }
   
    public void generarComida(){
        
        this.comida[0] = new Comida(); 
        this.comida[0].setVista();
        this.comida[0].layer = (TiledMapTileLayer) map.getLayers().get("ventana");
        this.comida[0].setPosition(10, 10);
        stage.addActor(this.comida[0]);
        
    }
    
    @Override
    public void show() {
       
         map = new TmxMapLoader().load("nivel2.tmx");
         camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.TILES_IN_CAMERA * MyGdxGame.TILE_WIDTH, MyGdxGame.TILES_IN_CAMERA * MyGdxGame.TILE_HEIGHT);
        camera.update();
        
        
        
        final float pixelsPerTile = 32;
        renderer = new OrthogonalTiledMapRenderer(map, 1 / pixelsPerTile);
        camera = new OrthographicCamera(320, 480);
        camera.position.set(320, 480, 0);

        stage = new Stage();
        stage.getViewport().setCamera(camera);
        puntos =0;
        
        this.crearEnemigos(MyGdxGame.modoJuego);
        this.generarRey();
        this.generarCanon();
        this.crearMan();
       
       
    }
    void comprobarCollision(){
        if(enemigo.dimensiones().overlaps(man.dimensiones())){
           this.enemigo.setxVelocity(0f);
            this.enemigo.setPegar(true);
           
        }
    }
    public void generarCanon(){
        this.canon  = new Canon();
        this.canon.layer = (TiledMapTileLayer) map.getLayers().get("principal");
        this.canon.setPosition(57, 9);
        stage.addActor(this.canon);
    }
    public void generarRey(){
        this.rey  = new ReyCerdo();
        this.rey.layer = (TiledMapTileLayer) map.getLayers().get("principal");
        this.rey.setPosition(63, 9);
        stage.addActor(this.rey);
    }
    public void crearMan(){
        
        this.man  = MyGdxGame.man;
        this.man.layer = (TiledMapTileLayer) map.getLayers().get("principal");
        this.man.setPosition(5, 10);
        stage.addActor(this.man);
    }
    public void crearEnemigos(int velocidad){
        
        this.enemigo  = new Cerdo1(velocidad);
        this.enemigo.layer = (TiledMapTileLayer) map.getLayers().get("principal");
        this.enemigo.setPosition(15, 7);
        stage.addActor(this.enemigo);


        this.enemigo2  = new Cerdo2(velocidad);
        this.enemigo2.layer = (TiledMapTileLayer) map.getLayers().get("principal");
        this.enemigo2.setPosition(20, 11);
        stage.addActor(this.enemigo2);

        this.enemigo3  = new Cerdo3(velocidad);
        this.enemigo3.layer = (TiledMapTileLayer) map.getLayers().get("principal");
        this.enemigo3.setPosition(40, 12);
        stage.addActor(this.enemigo3);
    }
    public void comprobarCollisionEnemigo(){
        
        if(enemigo.dimensiones().overlaps(man.dimensiones())){
            if(MyGdxGame.modoJuego == 0){
                this.enemigo.gritar();
                this.enemigo.setxVelocity(0f);
                this.enemigo.setPegar(true);
                if(pegado == 0){
                    this.vidas.remove(vidas.size() -1 ); 
                    pegado++; 
                    vida--; 
                }
               
            }else{
                
                this.enemigo.gritar();
                this.enemigo.setxVelocity(0f);
                this.enemigo.setPegar(true);
                this.man.setMuerto(true);
            }
            
        }
    }
    void comprobarCollisionEnemigo2(){
          
        if(enemigo2.dimensiones().overlaps(man.dimensiones())){
            if(MyGdxGame.modoJuego == 0){
                this.enemigo2.gritar();
                this.enemigo2.setxVelocity(0f);
                this.enemigo2.setPegar(true);
                this.vidas.remove(vidas.size()-1 ); 
                vida--; 
                
            }else{
                this.enemigo2.gritar();
                this.enemigo2.setxVelocity(0f);
                this.enemigo2.setPegar(true);
                this.man.setMuerto(true);
            }
            
        }
    }
    void comprobarGolpeEnemigo2(){
        if(this.man.dimensiones().overlaps(this.enemigo2.dimensiones())){
            
            if(this.man.getPegar() == true){
                this.enemigo2.gritar();
                this.enemigo2.remove(); 
                this.puntos += 15;  
              
            }
        }
    }
    void comprobarGolpeEnemigo3(){
        if(this.man.dimensiones().overlaps(this.enemigo3.dimensiones())){
            
            if(this.man.getPegar() == true){
                this.enemigo3.gritar();
                this.enemigo3.remove(); 
                this.puntos += 15;  
              
            }
        }
    }
    
    

    @Override
    public void render(float delta) {
        
        if(Final){
         
            if(Gdx.input.justTouched()) {

                if(mensajeActual == dialogos.length) {
                    mensajeActual--;
                    juego.setScreen(new Inicio(juego));
                }
                  System.out.println("mensajes "+  mensajeActual);
               this.juego.batch.begin();
               this.juego.font.draw(juego.batch, dialogos[mensajeActual], 500, 400, 320, Align.center, false);

               this.juego.batch.end();
               mensajeActual++; 
           }
        }
        else{
             this.comprobarMuerto();
        
        this.compruebaSeguimiento();
        this.perseguir(delta);
        anterior = 0; 
        this.pegarMan();
        this.comprobarEnemigosPegarMan(); 
        this.comprabarGolpes();
        this.menu(); 
        }
       
        this.posicionarCamara();
         
        this.pintarStatusJuego();
        stage.act(delta);
        stage.draw();
        
        this.man.setPegar(false);
       
    }
     void comprobarCollisionEnemigo3(){
        
        if(enemigo3.dimensiones().overlaps(man.dimensiones())){
            this.enemigo3.gritar();
            this.enemigo3.setxVelocity(0f);
            this.enemigo3.setPegar(true);
        }
    }
    void comprobarGolpeEnemigo(){
        if(this.man.dimensiones().overlaps(this.enemigo.dimensiones())){
            
            if(this.man.getPegar() == true){
                this.enemigo.gritar();
                this.enemigo.remove(); 
                this.puntos += 15;  
              
            }
        }
    }
    public void comprobarEnemigosPegarMan(){
        this.collisionCanon();
        this.comprobarCollisionEnemigo();
        this.comprobarCollisionEnemigo2();
        this.comprobarCollisionEnemigo3();
    }
     void comprabarGolpes(){
        this.comprobarGolpeEnemigo();
        this.comprobarGolpeEnemigo2();
        this.comprobarGolpeEnemigo3();
    }
    public void comprobarMuerto(){
        
        if(this.man.isMuerto() || this.man.comprobarMuerte()){
            this.juego.puntajes.add(this.puntos);
           // this.juego.puntuaciones.guardarPuntuaciones(  this.juego.puntajes); 
            this.juego.setScreen(new Perdiste(juego));
                        
            this.man.remove();
        } 
    }
    public void pegarMan(){
        if (Gdx.input.isKeyPressed(Input.Keys.Z ) && (!(anterior == Input.Keys.Z ))) {
            anterior = Input.Keys.Z ; 
            
             if(this.man.getPegar() == false){
               
                this.man.getHit().play(); 
             }
           this.man.setPegar(true);
        }
    }
    public void pintarStatusJuego(){
       
        this.juego.batch.begin();
            TextureRegion a= new TextureRegion(new Texture(Gdx.files.internal("menu.png")));
            this.juego.batch.draw(a, 2, 450, 30, 30);
            this.juego.font.draw(this.juego.batch, "Puntos: " + this.puntos, 180, 480);
            
            
            this.juego.batch.draw(vidas.get(0), 500, 450, 30, 30);
            if(vidas.size() >= 2){
                this.juego.batch.draw(vidas.get(1), 525, 450, 30, 30);
            }
            if(vidas.size() == 3){
                 this.juego.batch.draw(vidas.get(2), 545, 450, 30, 30);
            } 
           

        this.juego.batch.end();   
    }
    public void posicionarCamara(){
        
        if(this.man.getX() > 22){
            camera.position.x = this.man.getX(); 
            camera.update();
        }
        else{
            camera.position.x = 22; 
            camera.update();
        }
        if(this.man.getX() > 46){
            camera.position.x = 46; 
            camera.update();
        }
            
        renderer.setView(camera);
        renderer.render();
        
    }
    
    public void perseguir(float delta){
        
        if(this.man.getX() >= 16){
            
            this.inicioCerdo2 = false;
           if((this.enemigo2.getY() == this.man.getY()) && (this.man.getX() == this.enemigo2.getX() )){
              this.enemigo2.miraDerecha= true; 
              this.enemigo2.setxChange(0);

            }else{
                if(this.enemigo2.getChoco() == false){
                  this.enemigo2.setxChange(this.man.getX() + 0.5f);  

                }
                else{
                     this.enemigo2.setxChange(0);
                }

                 this.enemigo2.moverse(delta);

                   if(this.man.getY() > this.enemigo2.getY() ){
                        this.enemigo2.yVelocity = 0;
                   }

                if((this.man.getX() < this.enemigo2.getX())){
                    this.enemigo2.setChoco(false); 
                } 
            } 
        }
        if((this.inicioCerdo2 == false) && (this.man.getX() < 16) ){
          
            this.enemigo2.darVueltas(delta); 
        }
        
    }
    public void nivelTerminadoDialogo(){
        
       
        
        

        
    }
    public void collisionCanon(){
        if(man.dimensiones().overlaps(canon.dimensiones()) && Final== false){
            if(this.man.getPegar() == true){
                canon.remove(); 
                
                this.Final = true; 
               
               
              
            }
        }
    }
    public void compruebaSeguimiento(){
       
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
        juego.batch.dispose();
	juego.font.dispose();
    }


    public void menu(){
        if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            
            if (menu.contains(touchPoint.x, touchPoint.y)) {
                //Assets.playSound(Assets.clickSound);
                juego.setScreen(new Menu(juego));
                
                return; 

            }
            
        }
    }
   
        
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
   
}
