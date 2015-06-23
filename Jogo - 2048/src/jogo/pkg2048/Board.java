package jogo.pkg2048;

//HELP - https://www.youtube.com/watch?v=vLQhd6vQ9Wk&spfreload=10

import javax.swing.JFrame;
import java.util.Random;
import java.util.Scanner;

public class Board extends JFrame{
    
    int size;
    int grid[][];
    int score;
    Tile Tile;
    Tile[] allTile;
    JFrame frame;
    
    Board(int size){    
        this.size = size;

    }


    public void addTile(){
        int i;
        
        allTile = new Tile[size*size]; // cria um array de 16 Objectos para armazenar os Tiles
        
        for(i=0; i < allTile.length; i++){ // para cada elemento do Array é criado um Obejcto do tipo Tile
            Tile = new Tile();
            allTile[i] = Tile;
        }
    }

    
    public void newGame(){
        addTile();
        grid = new int[size][size];
        
        addTileValue();
        addTileValue();
        int x = 0;
        for (int i = 0; i < size; i++) {
           for (int j = 0; j < size; j++) {
             System.out.printf("%d ", grid[i][j] = allTile[x].value);
             x++;
           }
           System.out.printf("\n");
         }
    }
    
    public void restartGame(){
        for(int i = 0; i < allTile.length; i++){
            allTile[i].value = 0;
        }
        newGame();
    }
    
    public void printGrid(){

        int x = 0;
        
        System.out.println("***************");
        
        System.out.println("SCORE -- " + score + " --");
        
        System.out.println("***************");
        
        addTileValue();
  
        for (int i = 0; i < size; i++) {
          for (int j = 0; j < size; j++) {
            System.out.printf("%d ", grid[i][j] = allTile[x].value);
            x++;
          }
          System.out.printf("\n");
        }
        System.out.println("***************");
    }
    
    public void addTileValue(){
       
        Random gerador = new Random();
        
        int ranCoord = gerador.nextInt(size * size);
        
        int randValue = (Math.random()<0.5)?2:4;
            
            if(allTile[ranCoord].isEmpty() == true){
                allTile[ranCoord].changeValue(randValue);
            }else{
                addTileValue();
            }
    }
    
    
    public void down(){
        int i;
        if(!isFull()){
            for(i = 0; i < allTile.length; i++){
                if(!allTile[i].isEmpty()){
                    if(!downEdge(i)){
                        if(allTile[i].value == allTile[i+size].value || allTile[i+size].isEmpty()){
                            allTile[i+size].value = allTile[i+size].value + allTile[i].value;
                            allTile[i].value = 0;
                        }
                    }    
                }
            }
        }       
    }
    
    /*public void up(){
        int i;
        for(i = 15; i > 0; i--){
            if(!allTile[i].isEmpty()){
                if(!upEdge(i)){
                    if(allTile[i].value == allTile[i-size].value || allTile[i-size].isEmpty()){
                        allTile[i-size].value = allTile[i-size].value + allTile[i].value;
                        allTile[i].value = 0;
                    }
                }    
            }
        }
    }
    */
    
    public void up(){
        for(int i = 0; i < allTile.length; i++){
                if(!allTile[i].isEmpty()){
                    if(!upEdge(i)){
                        if( allTile[i+size].isEmpty()){
                            allTile[i+size].value = allTile[i+size].value + allTile[i].value;
                            allTile[i].value = 0;
                            if(!upEdge(i)){
                                i = i-2; 
                            }   
                        }
                    }    
                    if(!upEdge(i)){    
                        if(allTile[i].value == allTile[i+size].value){
                            allTile[i+size].value = allTile[i+size].value + allTile[i].value;
                            allTile[i].value = 0;
                            if(!upEdge(i)){
                                i = i-1; 

                            }   
                        }
                    }    

                }        
        }        
    }
    
    public void score(int i){
        score = score + i;
    }
    
    
    public void left(){
        for(int i = 0; i < allTile.length; i++){
                if(!allTile[i].isEmpty()){
                    if(!leftEdge(i)){
                        if(allTile[i-1].isEmpty()){
                            allTile[i-1].value = allTile[i-1].value + allTile[i].value;
                            allTile[i].value = 0;
                            if(!leftEdge(i)){
                                i = i-2; 
                            }   
                        }
                    }    
                    if(!leftEdge(i)){    
                        if(allTile[i].value == allTile[i-1].value){
                            allTile[i-1].value = allTile[i-1].value + allTile[i].value;
                            allTile[i].value = 0;
                            score(allTile[i-1].value);
                            if(!leftEdge(i)){
                                i = i-1; 

                            }   
                        }
                    }    

                }        
        }        
    }

    public void right(){
        for(int i = 15; i >= 0; i--){
                if(!allTile[i].isEmpty()){
                    if(!rightEdge(i)){
                        if(allTile[i+1].isEmpty()){
                            allTile[i+1].value = allTile[i+1].value + allTile[i].value;
                            allTile[i].value = 0;
                            if(!rightEdge(i)){
                                i = i+2; 
                            }   
                        }
                    }    
                    if(!rightEdge(i)){    
                        if(allTile[i].value == allTile[i+1].value){
                            allTile[i+1].value = allTile[i+1].value + allTile[i].value;
                            allTile[i].value = 0;
                            score(allTile[i+1].value);
                            if(!rightEdge(i)){
                                i = i+1; 

                            }   
                        }
                    }    

                }        
        }        
    }

    

    
    public boolean downEdge(int i){
        return i == 12 || i == 13 || i == 14 || i >= 15;
    }
    
    public boolean upEdge(int i){
        return i <= 0 || i == 1 || i == 2 || i == 3;
    }
    
    public boolean rightEdge(int i){
        return i == 3 || i == 7 || i == 11 || i >= 15;
    }
    
    public boolean leftEdge(int i){
        return i <= 0 || i == 4 || i == 8 || i == 12;
    }
    
    public boolean isFull(){
        
        int count = 0;
        for(int i = 0; i < allTile.length; i++){
            if(!allTile[i].isEmpty()){
                if(count == 15){
                    return true;
                }   
                count++;
            }else{
                return false;
            } 
        }
        return false;
    }
    
    public void gameOver(){
        System.out.println("GAME OVER");
        System.out.println("NEW GAME? Y/N");

        Scanner entry = new Scanner(System.in);
        String option = "";

        option = entry.next();

        switch (option) {
            case "y":
                restartGame();
                break;
            case "n":
                break;
        }
    }    
}    