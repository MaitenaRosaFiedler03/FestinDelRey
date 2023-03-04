package com.Plantilla;


import com.Plantilla.Personajes.Cerdo1;
import com.Plantilla.Personajes.Cerdo2;
import com.Plantilla.Personajes.Cerdo3;
import com.Plantilla.Personajes.Comida;
import com.Plantilla.Personajes.Man;
import com.Plantilla.Personajes.Puerta;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
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
import java.util.ArrayList;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainScreen implements Screen {
    
    MyGdxGame juego; 
    Stage stage;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    Puerta puerta; 
    Rectangle menu; 
    Vector3 touchPoint;
    int puntos; 
    Man man; 
    Cerdo1 enemigo; 
    Cerdo2 enemigo2;
    Cerdo3 enemigo3; 
    int anterior; 
    ArrayList<Texture> vidas; 
    Comida[] comida; 
    boolean inicioCerdo2; 
    
  
    
    public MainScreen(MyGdxGame pantalla){
        
       this.inicioCerdo2 = true; 
        this.juego = pantalla; 
        menu = new Rectangle(0, 19, 24, 64);
        touchPoint = new Vector3();
        this.puerta = new Puerta(); 
        this.comida = new Comida[5];
        for (int i = 0; i < 5; i++) {
            this.comida[i] = new Comida(); 
        }
        this.vidas = new ArrayList<>(); 
        for (int i = 0; i < 3; i++) {
            vidas.add(new Texture(Gdx.files.internal("corazon.png")));
        }
       
    }
    @Override
    public void show() {
     
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.TILES_IN_CAMERA * MyGdxGame.TILE_WIDTH, MyGdxGame.TILES_IN_CAMERA * MyGdxGame.TILE_HEIGHT);
        camera.update();
        
        
        map = new TmxMapLoader().load("nivel1.tmx");
        final float pixelsPerTile = 32;
        renderer = new OrthogonalTiledMapRenderer(map, 1 / pixelsPerTile);
        camera = new OrthographicCamera(320, 480);
        camera.position.set(320, 480, 0);

        stage = new Stage();
        stage.getViewport().setCamera(camera);
        puntos =0;
        
        this.crearEnemigos();
        this.crearFinal();
        this.crearMan();
    }
    public void generarComida(){
        
        this.comida[0] = new Comida(); 
        this.comida[0].setVista();
        this.comida[0].layer = (TiledMapTileLayer) map.getLayers().get("ventana");
        this.comida[0].setPosition(10, 10);
        stage.addActor(this.comida[0]);
        
    }
    public void crearMan(){
        
        this.man  = new Man();
        this.man.layer = (TiledMapTileLayer) map.getLayers().get("ventana");
        this.man.setPosition(5, 10);
        stage.addActor(this.man);
    }
    public void crearFinal(){
        
        this.puerta  = new Puerta();
        this.puerta.layer = (TiledMapTileLayer) map.getLayers().get("ventana");
        this.puerta.setPosition(48, 12);
        stage.addActor(this.puerta);
    }
    public void crearEnemigos(){
        
        this.enemigo  = new Cerdo1();
        this.enemigo.layer = (TiledMapTileLayer) map.getLayers().get("ventana");
        this.enemigo.setPosition(15, 7);
        stage.addActor(this.enemigo);


        this.enemigo2  = new Cerdo2();
        this.enemigo2.layer = (TiledMapTileLayer) map.getLayers().get("ventana");
        this.enemigo2.setPosition(20, 11);
        stage.addActor(this.enemigo2);

        this.enemigo3  = new Cerdo3();
        this.enemigo3.layer = (TiledMapTileLayer) map.getLayers().get("ventana");
        this.enemigo3.setPosition(40, 12);
        stage.addActor(this.enemigo3);
    }
    
    void comprobarCollisionEnemigo(){
        
        if(enemigo.dimensiones().overlaps(man.dimensiones())){
            this.enemigo.gritar();
            this.enemigo.setxVelocity(0f);
            this.enemigo.setPegar(true);  
        }
    }
      void comprobarCollisionEnemigo2(){
          
        if(enemigo2.dimensiones().overlaps(man.dimensiones())){
            this.enemigo2.gritar();
            this.enemigo2.setxVelocity(0f);
            this.enemigo2.setPegar(true);
        }
    }
    void comprobarCollisionEnemigo3(){
        
        if(enemigo3.dimensiones().overlaps(man.dimensiones())){
            this.enemigo3.gritar();
            this.enemigo3.setxVelocity(0f);
            this.enemigo3.setPegar(true);
        }
    }
    void comprabarGolpes(){
        this.comprobarGolpeEnemigo();
        this.comprobarGolpeEnemigo2();
        this.comprobarGolpeEnemigo3();
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
    void finalNivel(){
        if(this.man.dimensiones().overlaps(this.puerta.dimensiones())){
           this.juego.setScreen(new Nivel2(this.juego, this.man));
        }
    }

    @Override
    public void render(float delta) {
        
        this.compruebaSeg();

        this.perseguir(delta);
        anterior = 0; 
        this.pegarMan();
        this.generarComida();
        this.comprabarGolpes();

        this.finalNivel();
        this.menu(); 

        
        this.posicionarCamara();

        this.comprobarCollisionEnemigo();     
        this.pintarStatusJuego();
        stage.act(delta);
        stage.draw();

        this.comprobarMuerto();
        this.man.setPegar(false);
       
    }
    public void comprobarEnemigosPegarMan(){
        this.comprobarCollisionEnemigo();
        this.comprobarCollisionEnemigo2();
        this.comprobarCollisionEnemigo3();
    }
    
    public void comprobarMuerto(){
        if(this.man.comprobarMuerte()){
            this.juego.puntajes.add(this.puntos);
            this.juego.puntuaciones.guardarPuntuaciones(  this.juego.puntajes); 
            this.juego.setScreen(new Perdiste(juego));
                        
            this.man.remove();
        } 
    }
    public void pegarMan(){
        if (Gdx.input.isKeyPressed(Input.Keys.Z ) && (!(anterior == Keys.Z ))) {
            anterior = Keys.Z ; 
            
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
            this.juego.batch.draw(vidas.get(1), 525, 450, 30, 30);    
            this.juego.batch.draw(vidas.get(2), 545, 450, 30, 30);

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
            System.out.println("perseguir ");
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
                        this.enemigo2.yVelocity = this.enemigo2.yVelocity + this.enemigo2.VELOCIDAD* 2.2f;
                   }

                if((this.man.getX() < this.enemigo2.getX())){
                    this.enemigo2.setChoco(false); 
                } 
            } 
        }
        if((this.inicioCerdo2 == false) && (this.man.getX() < 16) ){
           System.out.println("dar vueltas");
            this.enemigo2.darVueltas(delta); 
        }
        
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
        juego.batch.dispose();
	juego.font.dispose();
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
    @Override
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
            
            if (menu.contains(touchPoint.x, touchPoint.y)) {
                //Assets.playSound(Assets.clickSound);
                juego.setScreen(new Menu(juego));
                
                return; 

            }
            
        }
    }
}
