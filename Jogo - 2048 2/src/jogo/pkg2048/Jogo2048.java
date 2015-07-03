package jogo.pkg2048;

/* Autor Nuno Boleto l32592*/

import java.util.Scanner;

public class Jogo2048 {

    public static void main(String[] args) {
        
        Board C2 = new Board(4);
        C2.newGame();

        Scanner entrada = new Scanner(System.in);
        String option = null;
        
        while(!"s".equals(option)){
            System.out.println("|R|right|L|left|D|Down|U|up");
           
            option = entrada.next();

            switch (option) {
                case "d":
                    C2.down();
                    break;
            
                case "u":
                    C2.up();
                    break;
                
                case "l":
                    C2.left();
                    break;
                
                case "r":
                    C2.right();
                    break;              
            }
        }    
    } 
}
    

