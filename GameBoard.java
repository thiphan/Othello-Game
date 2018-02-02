public class GameBoard{
    protected int row;
    protected int col;
    protected Tile[][] tile;
    public GameBoard( int r, int c){
        row = r;
        col = c;
        tile = new Tile[row][col];
        for(int i = 0; i <tile.length; i++){
            for (int j = 0; j<tile[i].length; j++){
                tile[i][j] = new Tile();
            }
        }
        tile[(int)((row-1)/2)][(int)((col-1)/2)].tileColor= 'O';
        tile[((int)((row-1)/2))+1][(int)((col-1)/2)].tileColor= 'X';
        tile[(int)((row-1)/2)][((int)((col-1)/2))+1].tileColor= 'X';
        tile[(int)((row-1)/2)+1][((int)((col-1)/2))+1].tileColor= 'O';
    }
    public int countPieces(char c){ //count total pieces with specific color on the board
        int count =0;
        for (int i = 0; i<tile.length;i++){
            for (int j = 0; j<tile[i].length; j++){
                if(tile[i][j].tileColor == c)
                    count++;
            }
        }
        return count;
    }
    
    /** Determine the current state of the game*/
    public int stateOfGame(){
        boolean validBlack = validMoveExist('X');
        boolean validWhite = validMoveExist('O');
        int countX = countPieces('X');
        int countO = countPieces('O');
        if((validBlack ==false && validWhite == false) || countX == 0||countO == 0){
            if(countX > countO){
                return 1;
            }
            else if(countX < countO){
                return 2;
            }
            else {
                return 0;
            }
        }
        else
            return -1;
    }
    /** Determine if a move is valid*/
    public boolean validMove(int i, int j, char c){
        boolean valid = false;
        if(tile[i][j].tileColor=='.' && (up(i,j,c)||down(i,j,c)||left(i,j,c)||right(i,j,c)||upLeft(i,j,c)||
        upRight(i,j,c)||downLeft(i,j,c)||downRight(i,j,c)))
            valid = true;
        return valid;
    }
     /**Determine if a valid move exists */
    public boolean validMoveExist(char c){
        boolean valid = false;
        for(int i = 0; i<this.row;i++){
            for(int j = 0; j<this.col; j++){
                if(tile[i][j].tileColor=='.' && (up(i,j,c)||down(i,j,c)||left(i,j,c)||right(i,j,c)||upLeft(i,j,c)||
                upRight(i,j,c)||downLeft(i,j,c)||downRight(i,j,c)))
                    valid = true;
            }
        }
        return valid;
    }
    
    /** Place piece and flip pieces of the opposite color*/
    public void placePiece(int row, int col,Tile t){//r - row, c- column
        boolean checkValidMove = validMove(row,col,t.tileColor);
        boolean checkUp = up(row,col, t.tileColor);boolean checkDown = down(row,col,t.tileColor);
        boolean checkLeft = left(row,col,t.tileColor);boolean checkRight = right(row,col,t.tileColor);
        boolean upLeft = upLeft(row,col,t.tileColor); boolean upRight = upRight(row,col,t.tileColor);
        boolean downLeft = downLeft(row,col,t.tileColor);boolean downRight = downRight(row,col,t.tileColor);

        if(checkValidMove == false)
            System.out.println("Invalid move! please place piece to another position!");
        else{
            tile[row][col] = t;//place a specific tile to specific location
        
            /** Flipping appropriate pieces of the opposite color */
            if(checkUp){
                for(int i = row -1, j =col; tile[i][j].tileColor!=t.tileColor;i--){
                    tile[i][j].tileColor=t.tileColor;
                }
            }
            if(checkDown){
                for(int i = row +1, j =col; tile[i][j].tileColor!=t.tileColor;i++){
                    tile[i][j].tileColor = t.tileColor;
                }
            }
            if(checkLeft){
                for(int i = row, j =col-1; tile[i][j].tileColor!=t.tileColor;j--){
                    tile[i][j].tileColor = t.tileColor;
                }
            }
            if(checkRight){
                for(int i = row, j =col+1; tile[i][j].tileColor!=t.tileColor;j++){
                    tile[i][j].tileColor = t.tileColor;
                }
            }
            if(upLeft){
                for(int i = row -1, j = col-1; tile[i][j].tileColor!=t.tileColor; i--,j--){
                    tile[i][j].tileColor = t.tileColor;
                }
            }
            if(upRight){
                for(int i = row -1, j = col+1; tile[i][j].tileColor!=t.tileColor; i--,j++){
                    tile[i][j].tileColor = t.tileColor;
                }
            }
            if(downLeft){
               for(int i = row +1, j = col-1; tile[i][j].tileColor!=t.tileColor; i++,j--){
                    tile[i][j].tileColor = t.tileColor;
               }
            }
            if(downRight){
               for(int i = row +1, j = col+1; tile[i][j].tileColor!=t.tileColor; i++,j++){
                    tile[i][j].tileColor = t.tileColor;
               }
            }
        }
    }

    /*********************************************/
    /** Support methods for deciding valid move */
    public boolean up(int r, int c, char color){ //r - row, c - column
        boolean result =false;
        if(r<2)
            result = false;
        else {
            if(tile[r-1][c].tileColor != color && tile[r-1][c].tileColor!='.'){
                for(int i = r-2; i>=0;i--){
                    if(tile[i][c].tileColor == '.'){
                        result =  false;break;
                    }
                    if(tile[i][c].tileColor == color){
                        result =  true; break;
                    }
                    if(i==0 && tile[i][c].tileColor != tile[r-1][c].tileColor && tile[i][c].tileColor!='.')
                        result =  false;
                }
            }
        }
        return result;
    }
    public boolean down(int r, int c, char color){ //r - row, c - column
        boolean result =false;
        if(r>this.row-2)
            result = false;
        else {
            if(tile[r+1][c].tileColor != color && tile[r+1][c].tileColor!='.'){
                for(int i = r+2; i< this.row;i++){
                    if(tile[i][c].tileColor == '.'){
                        result =  false;break;
                    }
                    if(tile[i][c].tileColor == color){
                        result =  true; break;
                    }
                    if(i==0 && tile[i][c].tileColor != tile[r+1][c].tileColor && tile[i][c].tileColor!='.')
                        result =  false;
                }
            }
        }
        return result;
    }
    public boolean left(int r, int c, char color){ //r - row, c - column
        boolean result =false;
        if(c<2)
            result = false;
        else {
            if(tile[r][c-1].tileColor != color && tile[r][c-1].tileColor!='.'){
                for(int i = c-2; i>=0;i--){
                    if(tile[r][i].tileColor == '.'){
                        result =  false;break;
                    }
                    if(tile[r][i].tileColor == color){
                        result =  true; break;
                    }
                    if(i==0 && tile[r][i].tileColor != tile[r][c-1].tileColor && tile[r][c-1].tileColor!='.')
                        result =  false;
                }
            }
        }
        return result;
    }
    public boolean right(int r, int c, char color){ //r - row, c - column
        boolean result =false;
        if(c>this.col-2)
            result = false;
        else {
            if(tile[r][c+1].tileColor != color && tile[r][c+1].tileColor!='.'){
                for(int i = c+2; i< this.col;i++){
                    if(tile[r][i].tileColor == '.'){
                        result =  false;break;
                    }
                    if(tile[r][i].tileColor == color){
                        result =  true; break;
                    }
                    if(i==0 && tile[r][i].tileColor != tile[r][c+1].tileColor && tile[r][i].tileColor!='.')
                        result =  false;
                }
            }
        }
        return result;
    }
    public boolean upLeft(int r, int c, char color){ //r - row, c - column
        boolean result =false;
        if(r<2||c<2)
            result = false;
        else {
            if(tile[r-1][c-1].tileColor != color && tile[r-1][c-1].tileColor!='.'){
                for(int i = r-2,j =c-2; i>=0 && j>=0;i--,j--){
                    if(tile[i][j].tileColor == '.'){
                        result =  false;break;
                    }
                    if(tile[i][j].tileColor == color){
                        result =  true; break;
                    }
                    if(i==0 && tile[i][j].tileColor != tile[r-2][c-2].tileColor && tile[i][j].tileColor!='.')
                        result =  false;
                }
            }
        }
        return result;
    }
    public boolean upRight(int r, int c, char color){ //r - row, c - column
        boolean result =false;
        if(r<2||c>this.col-2)
            result = false;
        else {
            if(tile[r-1][c+1].tileColor != color && tile[r-1][c+1].tileColor!='.'){
                for(int i = r-2,j =c+2; i>=0 && j<this.col;i--,j++){
                    if(tile[i][j].tileColor == '.'){
                        result =  false;break;
                    }
                    if(tile[i][j].tileColor == color){
                        result =  true; break;
                    }
                    if(i==0 && tile[i][j].tileColor != tile[r-2][c+2].tileColor && tile[i][j].tileColor!='.')
                        result =  false;
                }
            }
        }
        return result;
    }
    public boolean downRight(int r, int c, char color){ //r - row, c - column
        boolean result =false;
        if(r>this.row-2||c>this.col-2)
            result = false;
        else {
            if(tile[r+1][c+1].tileColor != color && tile[r+1][c+1].tileColor!='.'){
                for(int i = r+2,j =c+2; i<this.row && j<this.col;i++,j++){
                    if(tile[i][j].tileColor == '.'){
                        result =  false;break;
                    }
                    if(tile[i][j].tileColor == color){
                        result =  true; break;
                    }
                    if(i==0 && tile[i][j].tileColor != tile[r-2][c+2].tileColor && tile[i][j].tileColor!='.')
                        result =  false;
                }
            }
        }
        return result;
    }
    public boolean downLeft(int r, int c, char color){ //r - row, c - column
        boolean result =false;
        if(r>this.row-2||c<2)
            result = false;
        else {
            if(tile[r+1][c-1].tileColor != color && tile[r+1][c-1].tileColor!='.'){
                for(int i = r+2,j =c-2; i<this.row && j>=0;i++,j--){
                    if(tile[i][j].tileColor == '.'){
                        result =  false;break;
                    }
                    if(tile[i][j].tileColor == color){
                        result =  true; break;
                    }
                    if(i==0 && tile[i][j].tileColor != tile[r+2][c-2].tileColor && tile[i][j].tileColor!='.')
                        result =  false;
                }
            }
        }
        return result;
    }

    /**********************************************************/
    /**Display Gameboard method */
    public String toString(){
        String result = "     ";
        for (int k = 0; k<tile[0].length;k++){
            result += k + "     ";
            if(k==tile[0].length-1)
                result +="\n";
        }
        for(int i =0;i<tile.length; i++){
            result += "  ";
            for(int k = 0; k < tile[0].length; k++){
                result += "+-----" ;
            }
            result +="+\n";
            result +=  i+" |";
            for(int j = 0; j < tile[i].length; j++){
               
                result += " "+ tile[i][j] + " |";
            }
            
            result +="\n";
        }
        result += "  ";
        for(int k = 0; k < tile[0].length; k++){
                result += "+-----" ;
        }
        result += "+\n";
        return result;
    }
    
}