package jogo.pkg2048;

import java.util.Scanner;

/*
 * @author nunoboleto
 */

public class Jogo2048 {

    public static void main(String[] args) {
        Board c1 = new Board(4);
        c1.newGame();
        
        Scanner entrada = new Scanner(System.in);
        String c = null;
        
        while(!"s".equals(c)){
            System.out.println("Movimento??");
            c = entrada.next();

            switch (c) {
                case "d":
                    c1.down();
                    c1.printGrid(); 
                    break;
                case "u":
                    c1.up();
                    c1.printGrid(); 
                    break;
                case "r":
                    c1.right();
                    c1.printGrid(); 
                    break;
                case "l":
                    c1.left();
                    c1.printGrid(); 
                    break;
                
            }
        }
        
    }
}
    

