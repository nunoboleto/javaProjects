package jogo.pkg2048;

public class Tile {
    
    int value;

    Tile() {
      this.value = 0;
    }

    public int changeValue(int x) {
      return value = x;
    }

    public boolean isEmpty() {
      return value == 0;
    }
    
    public void print(){
        System.out.println(value);
    }
}