
package jogo.pkg2048;

import java.util.ArrayList;
import java.util.Random;

public class Board{
    int size;
    int grid[][];
    int score;
    Tile Tile;
    private ArrayList<Tile> tileList;
    
    Board(int size){    
        this.size = size;
    }
    
    private void addTiles(){
        tileList = new ArrayList<Tile>(size*size);
        for(int i = 0; i < size*size; i++){
            Tile = new Tile();
            tileList.add(Tile);
        }
    }
    
    public void randomValue() throws StackOverflowError{
        try {
            Random gerador = new Random();
            int ranCoord = gerador.nextInt(size * size);

            int randValue = (Math.random()<0.5)?2:4; 
                if(tileList.get(ranCoord).isEmpty() == true){
                    tileList.get(ranCoord).setValue(randValue);
                }else{
                    randomValue();
                } 
    
        }catch (StackOverflowError e) {
            gameOver();
        }
    }
    public void newGame(){
        addTiles();
        
        randomValue();
        randomValue();

        grid = new int[size][size];
        
        printGrid();
    }
    
    public void printGrid(){
        System.out.println("xx--SCORE--xx");
        System.out.println("xx--"+score+"--xx");
        System.out.println("--------");
        int x = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%d    "   , grid[i][j] = tileList.get(x).getValue());
                x++;
            }
           System.out.printf("\n");
        }
        System.out.println("--------");
    }
    
    public void restartGame(){
        tileList.clear();
        newGame();
    }
    
    public void gameOver(){
        System.out.println("GAME OVER");
        restartGame();
    }
    
    public void score(int i){
        score = score + i;
    }
    
    public int movement(int i, int nexTile){
        if(tileList.get(nexTile).isEmpty()){
            tileList.get(nexTile).setValue(tileList.get(i).getValue());
            tileList.get(i).empty();
            return 1;
        }else if(tileList.get(i).getValue() == tileList.get(nexTile).getValue()){
            tileList.get(nexTile).setValue(tileList.get(i).value + tileList.get(nexTile).value);
            tileList.get(i).empty();
            score(tileList.get(nexTile).value);
            return 0;
        }
        return -1;
    }
    
    public boolean isMovable(String point){
        int numOfTiles = 0;
        int noMoovTiles = 0;
        int nexTile = 0;
        boolean edge = false;
        
        for(int i = 0; i < tileList.size(); i++){
            switch (point) {
                case "d":
                    nexTile = i+size;
                    edge = downEdge(i);
                    break;
                case "u":
                    nexTile = i-size;
                    edge = upEdge(i);
                    break;
                case "l":
                    nexTile = i-1;
                    edge = leftEdge(i);
                    break;
                case "r":
                    nexTile = i+1;
                    edge = rightEdge(i);
                    break;
            }
            if(!edge){
                if(!tileList.get(i).isEmpty()){
                    numOfTiles++;
                    if((tileList.get(i).getValue() != tileList.get(nexTile).getValue())==true && tileList.get(nexTile).isEmpty()==false){
                        noMoovTiles++;
                    }
                }
            }else if(!tileList.get(i).isEmpty()){
                noMoovTiles++;
                numOfTiles++;
            }
        }  
        if(numOfTiles == 16 && noMoovTiles == 16){
            gameOver();
        }else if(numOfTiles == noMoovTiles){
            return false;
        }
        return true;
    }
    
    public void down(){
        String point = "d";
        if(isMovable(point) == true){
            for(int i = (size*size)-1; i >= 0; i--){
            int nexTile = i+size;
                if(!downEdge(i) && !tileList.get(i).isEmpty()){ 
                    if(movement(i,nexTile) == 1){
                        if(!downEdge(i)){
                            i = i+(size*2); 
                        }   
                    }else if(movement(i,nexTile) == 0){
                        if(!downEdge(i)){
                            i = i+(size); 
                        }
                    }    
                }
            }
            randomValue();
            printGrid();
        }   
    }
    
    public void up(){
        String point = "u";
        if(isMovable(point) == true){
            for(int i = 0; i <= (size*size)-1; i++){
            int nexTile = i-size;
                if(!upEdge(i) && !tileList.get(i).isEmpty()){ 
                    if(movement(i,nexTile) == 1){
                        if(!upEdge(i)){
                            i = i-(size*2); 
                        }   
                    }else if(movement(i,nexTile) == 0){
                        if(!upEdge(i)){
                            i = i-(size); 
                        }
                    }    
                }
            }
            randomValue();
            printGrid();
        }    
    }    
    
    public void left(){
        String point = "l";
        if(isMovable(point) == true){
            for(int i = 0; i < tileList.size(); i++){
            int nexTile = i-1;
                if(!leftEdge(i) && !tileList.get(i).isEmpty()){ 
                    if(movement(i,nexTile) == 1){
                        if(!leftEdge(i)){
                            i = i-2; 
                        }   
                    }else if(movement(i,nexTile) == 0){
                        if(!leftEdge(i)){
                            i = i-1; 
                        }
                    }    
                }
            }
            randomValue();
            printGrid();
        }    
    }

    public void right(){
        String point = "r";
        if(isMovable(point) == true){
            for(int i = 15; i >= 0; i--){
            int nexTile = i+1;
                if(!rightEdge(i) && !tileList.get(i).isEmpty()){ 
                    if(movement(i,nexTile) == 1){
                        if(!rightEdge(i)){
                            i = i+2; 
                        }   
                    }else if(movement(i,nexTile) == 0){
                        if(!rightEdge(i)){
                            i = i+1; 
                        }
                    }    
                }
            }
            randomValue();
            printGrid();
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
    
    
}



