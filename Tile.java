public class Tile{
    protected char tileColor; // 'X' - black; 'O' - white; '.' - empty 
    
    public Tile(){ //default constructor with empty 
        tileColor = '.';
    }
    public Tile(char color){
        tileColor = color;
    }
    public void flipping(){
        if(tileColor == 'X'){
            tileColor = 'O';
        }
        if(tileColor == 'O'){
            tileColor = 'X';
        }
    }
    public String toString(){
        return " "+ tileColor+ " ";
    }
}