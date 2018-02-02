import java.util.*;
/**
 * Write a description of class test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Othello{
    
     public static void main(String[] args){
         GameBoard board = new GameBoard(8,8);
         System.out.println(board);
         Scanner input = new Scanner(System.in);
         int col;
         int row;
         boolean valid;
         int gameState=-1;
         
         do{
             do{
                 //Player 1
                 Tile tile1 = new Tile('X');
                 if(board.validMoveExist('X')== false){
                     System.out.println("No valid move exists for Black Tile! pass to White Tile turn!");
                     break;
                 }
                 System.out.println("Enter row & col to place the Black Tile(Seperate by space tab)");
                 row = input.nextInt();
                 col = input.nextInt();
                 valid = board.validMove(row,col,'X');
                 
                 board.placePiece(row,col, tile1);
                 System.out.println(board);
                 gameState = board.stateOfGame();
                 if(gameState ==1){
                    System.out.println("Game over! Black Tile win!");
                    System.exit(0);
                 }
                 if(gameState ==2){
                    System.out.println("Game over! White Tile win!");
                    System.exit(0);
                 }
                 if(gameState ==0){
                    System.out.println("Game over! Tie!!!");
                    System.exit(0);
                 }
             }while(valid==false && board.validMoveExist('X'));
             do{    
    
                 //Player 2
                 Tile tile2 = new Tile('O');
                 if(board.validMoveExist('O')== false){
                    System.out.println("No valid move exists for White Tile! pass to Black Tile turn!");
                    break;
                 }
                 System.out.println("Enter row & col to place the white tile(Seperate by space tab)");
                 row = input.nextInt();
                 col = input.nextInt();
                 valid = board.validMove(row,col,'O');
                 
                 board.placePiece(row,col, tile2);
                 System.out.println(board);
                 gameState = board.stateOfGame();
                 if(gameState ==1){
                    System.out.println("Game over! Black Tile win!");
                    System.exit(0);
                 }
                 if(gameState ==2){
                    System.out.println("Game over! White Tile win!");
                    System.exit(0);
                 }
                 if(gameState ==0){
                    System.out.println("Game over! Tie!!!");
                    System.exit(0);
                 }
             }while(valid==false && board.validMoveExist('O'));
         }while(gameState==-1);
     }
}
