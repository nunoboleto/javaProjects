package jogo.pkg2048;

public class Tile {
    
    int value;

    Tile() {
      this.value = 0;
    }

    public int setValue(int x) {
      return value = x;
    }
    
    public int getValue() {
      return value;
    }

    public boolean isEmpty() {
      return value == 0;
    }
    
    public void empty(){
        value = 0;
    }
    
    public void print(){
        System.out.println(value);
    }
}