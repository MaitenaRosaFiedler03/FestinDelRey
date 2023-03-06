/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Plantilla;

import com.Plantilla.Personajes.Bomba;
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
    Man man; 
    int vida;
    Cerdo1 enemigo; 
    Cerdo2 enemigo2;
    Cerdo3 enemigo3; 
    int anterior; 
    ArrayList<Texture> vidas; 
    ArrayList<Bomba> bombas; 
    Comida[] comida; 
    boolean inicioCerdo2;
    ReyCerdo rey; 
    Canon canon; 
    float tiempoDisparoCanon; 
    int cantBombas; 
    Bomba c; 
    boolean Final;
    int[] comidas; 
    
    public Nivel2(MyGdxGame g){
        
        this.bombas = new ArrayList<>(); 
        this.juego = g; 
        this.man = MyGdxGame.man;
        this.cantBombas =0;
        vida =3; 
        this.comidas = new int[3]; 
        vida =3; 
        this.inicioCerdo2 = true; 
        this.juego = g; 
        menu = new Rectangle(0, 19, 24, 64);
        touchPoint = new Vector3();
        this.Final = false; 
        this.comida = new Comida[5]; 
        
        for (int i = 0; i < 5; i++) {
            this.comida[i] = new Comida(); 
           
        }
        this.vidas = MyGdxGame.vidas; 
       c = new Bomba(); 
       MyGdxGame.nivel =2; 
    }
   
    public void generarComida(){

        this.comida[0] = new Comida(); 
        this.comida[0].setVista();
        this.comida[0].layer = (TiledMapTileLayer) map.getLayers().get("ventana");
        this.comida[0].setPosition(47, 12);
        stage.addActor(this.comida[0]);
        
        this.comida[1] = new Comida(); 
        this.comida[1].setVista();
        this.comida[1].layer = (TiledMapTileLayer) map.getLayers().get("ventana");
        this.comida[1].setPosition(30, 4);
        stage.addActor(this.comida[1]);
        
        this.comida[2] = new Comida(); 
        this.comida[2].setVista();
        this.comida[2].layer = (TiledMapTileLayer) map.getLayers().get("ventana");
        this.comida[2].setPosition(27, 11);
        stage.addActor(this.comida[2]);
        
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
      
        
        this.crearEnemigos(MyGdxGame.modoJuego);
        this.generarRey();
        this.generarCanon();
        this.crearMan();
        this.generarComida();
       
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
        this.enemigo.setPosition(30, 5);
        stage.addActor(this.enemigo);


        this.enemigo2  = new Cerdo2(velocidad);
        this.enemigo2.layer = (TiledMapTileLayer) map.getLayers().get("principal");
        this.enemigo2.setPosition(15, 4);
        stage.addActor(this.enemigo2);

        this.enemigo3  = new Cerdo3(velocidad);
        this.enemigo3.layer = (TiledMapTileLayer) map.getLayers().get("principal");
        this.enemigo3.setPosition(45, 4);
        stage.addActor(this.enemigo3);
    }
    public void comprobarCollisionEnemigo(){
        
        if(enemigo.dimensiones().overlaps(man.dimensiones()) && man.getPegar() == false){
            if(MyGdxGame.modoJuego == 0){
                if(this.enemigo.tiempoGolpe >= 1.8f){
                    this.enemigo.gritar();
                    this.vida--; 
                    this.enemigo.tiempoGolpe =0; 
                    if(vidas.size() >= 1)
                   this.vidas.remove(vidas.size() -1 ); 
                }
                
                this.enemigo.setxVelocity(0f);
                this.enemigo.setPegar(true);
               
            }else{
               
                this.enemigo.setPegar(true);
                this.man.setMuerto(true);
            }
            
        }
    }
    void comprobarCollisionEnemigo2(){
          
        if(enemigo2.dimensiones().overlaps(man.dimensiones()) && man.getPegar() == false){
            if(MyGdxGame.modoJuego == 0){
                if(this.enemigo2.tiempoGolpe >= 1.8f){
                    this.enemigo2.gritar();
                    this.vida--; 
                    this.enemigo2.tiempoGolpe =0;
                    if(vidas.size() >= 1)
                        this.vidas.remove(vidas.size() -1 ); 
                   
                }
                
                this.enemigo2.setxVelocity(0f);
                this.enemigo2.setPegar(true);
               
            }else{
               
                this.enemigo2.setPegar(true);
                this.man.setMuerto(true);
            }
            
        }
    }
    
    void comprobarGolpeEnemigo2(){
        if(this.man.dimensiones().overlaps(this.enemigo2.dimensiones())){
            
           if(this.man.tiempo >= 0.4f && man.getPegar() == true){
               
                this.enemigo2.setyVelocity(-100000);
                this.enemigo2.setMuerto(true);
                this.man.setPegar(true);
                
                if (enemigo2.getY()<=0) {
                    this.enemigo2.remove(); 
                }
               
               MyGdxGame.puntos += 15;  
              
            }
        }
    }
    void comprobarGolpeEnemigo3(){
        if(this.man.dimensiones().overlaps(this.enemigo3.dimensiones())){
            
            if(this.man.tiempo >= 0.4f && man.getPegar() == true){
              
                this.enemigo3.setyVelocity(-100000);
                this.enemigo3.setMuerto(true);
                
                this.man.setPegar(true);
                if (enemigo3.getY()<=0) {
                    this.enemigo3.remove(); 
                }
                MyGdxGame.puntos += 15;  
                 this.man.tiempo=0; 
            }
        }
    }
    
    

    @Override
    public void render(float delta) {
        this.enemigo.tiempoGolpe += delta; 
        this.enemigo2.tiempoGolpe += delta; 
        this.enemigo3.tiempoGolpe +=delta; 
        this.tiempoDisparoCanon+=delta; 
        this.man.tiempo += delta; 
      
        if(vida == 0 || vidas.size() == 0){
            this.juego.setScreen(new Perdiste(this.juego));
        }
        this.comprobarBomba(); 
        if(c.destriuda == true){
            c = this.disparar(); 
            
            c.destriuda = false; 
            if(c.dimensiones().overlaps(man.dimensiones())){
            this.vida--; 
            this.vidas.remove(vidas.size() -1); 
                if(vida == 0){
                    this.man.setMuerto(true);
                }
            }
            this.tiempoDisparoCanon =0; 
        }
        this.comprobarMuerto();
        this.collisionComida();
        this.compruebaSeguimiento();
        this.perseguir(delta);
        anterior = 0; 
        this.pegarMan();
        this.comprobarEnemigosPegarMan(); 
        this.comprabarGolpes();
        this.menu(); 
        
       
        this.posicionarCamara();
         
        this.pintarStatusJuego();
        stage.act(delta);
        stage.draw();
        
        this.man.setPegar(false);
       
    }
    public void comprobarBomba(){
        if(c.getX() < 40 || c.getY() > 30 || c.getY() <= 0){
           
        }
    }
    
     public void collisionComida(){
        if(this.man.dimensiones().overlaps(this.comida[0].dimensiones()) && this.comidas[0] == 0){
            if(this.comida[0].getTipo() == MyGdxGame.ACEITUNA){
                this.vida++; 
                this.comida[0].remove();
                this.comidas[0] =1; 
                if(this.vidas.size() < 3){
                    this.vidas.add(new Texture(Gdx.files.internal("corazon.png"))); 
                }
                MyGdxGame.puntos +=100;
            }
            else{
                MyGdxGame.puntos += 50;
                this.comida[0].remove();
                this.comidas[0] =1; 
            }
        }
        else if(this.man.dimensiones().overlaps(this.comida[1].dimensiones()) && this.comidas[1] == 0){
            if(this.comida[1].getTipo() == MyGdxGame.ACEITUNA){
                this.vida++; 
                this.comida[1].remove();
                this.comidas[1] =1; 
                if(this.vidas.size() < 3){
                    this.vidas.add(new Texture(Gdx.files.internal("corazon.png"))); 
                }
                MyGdxGame.puntos +=100;
            }
            else{
                this.comida[1].remove();
                this.comidas[1] =1;
                MyGdxGame.puntos += 50;
            }
        }
        else if(this.man.dimensiones().overlaps(this.comida[2].dimensiones()) && this.comidas[2] == 0){
            if(this.comida[2].getTipo() == MyGdxGame.ACEITUNA){
                this.vida++; 
                this.comida[2].remove();
                this.comidas[2] =1; 
                if(this.vidas.size() < 3){
                    this.vidas.add(new Texture(Gdx.files.internal("corazon.png"))); 
                }
                MyGdxGame.puntos +=100;
            }
            else{
                this.comida[2].remove();
                this.comidas[2] =1; 
                MyGdxGame.puntos += 50;
            } 
        }
    }
    
     void comprobarCollisionEnemigo3(){
        
        if (enemigo3.getX() >= 50 && enemigo3.getX() > 33) {
            enemigo3.setxVelocity(-2);
            enemigo3.miraDerecha = false;
        }
        else if (enemigo3.getX() <= 33 && enemigo3.getX() < 50) {
            enemigo3.setxVelocity(2);
            enemigo3.miraDerecha = true; 
        }
        
        if(enemigo3.dimensiones().overlaps(man.dimensiones())&& man.getPegar() == false){
             if(MyGdxGame.modoJuego == 0){
                if(this.enemigo3.tiempoGolpe >= 1.8f){
                    this.enemigo3.gritar();
                    this.vida--; 
                    this.enemigo3.tiempoGolpe =0; 
                    if(vidas.size() >= 1)
                        this.vidas.remove(vidas.size() -1 ); 
                }
               
                this.enemigo3.setPegar(true);
               
            }else{
                this.enemigo3.gritar();
                this.enemigo3.setxVelocity(0f);
                this.enemigo3.setPegar(true);
                this.man.setMuerto(true);
            }
        }
    }
    void comprobarGolpeEnemigo(){
        if(this.man.dimensiones().overlaps(this.enemigo.dimensiones())){
            
            if(this.man.tiempo >= 0.4f && man.getPegar() == true){
                
                this.enemigo.setyVelocity(-500);
               
                if (enemigo.getY()<=0) {
                    this.enemigo.remove(); 
                }
               
                this.man.setPegar(true);
               
                MyGdxGame.puntos += 15;  
              
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
            this.juego.puntajes.add(MyGdxGame.puntos);
           // this.juego.puntuaciones.guardarPuntuaciones(  this.juego.puntajes); 
            this.juego.setScreen(new Perdiste(juego));
                        
            this.man.remove();
        } 
    }
    public void pegarMan(){
       
           if (Gdx.input.isKeyPressed(Input.Keys.Z ) && (!(anterior == Input.Keys.Z ))) {
                anterior = Input.Keys.Z ; 
            
                this.man.setPegar(true);
               
            }
       
    }
    public void pintarStatusJuego(){
       
        this.juego.batch.begin();
            TextureRegion a= new TextureRegion(new Texture(Gdx.files.internal("menu.png")));
            this.juego.batch.draw(a, 2, 450, 30, 30);
            this.juego.font.draw(this.juego.batch, "Puntos: " + MyGdxGame.puntos, 180, 480);
            
            if(vidas.size() >= 1)
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
    public Bomba disparar(){
        Bomba bomba;
        bomba = new Bomba();
        bomba.setPosition(57, 9);
        
        bomba.xVelocity = -6f;
        bomba.yVelocity = man.getY() - 10; 
       
        bombas.add(bomba);
        
        stage.addActor(bombas.get(this.cantBombas));
       
        this.cantBombas++; 
        
        return bombas.get(this.cantBombas -1 );
        
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
 
    public void collisionCanon(){
        if(man.dimensiones().overlaps(canon.dimensiones())){
            if(this.man.getPegar() == true){
                canon.setPosition(0, -122222222);
                
                this.juego.setScreen(new Final(this.juego));
               
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
