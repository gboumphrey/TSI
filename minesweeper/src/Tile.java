public class Tile {
    public boolean revealed;
    public int number; //9 = MINE
    public boolean flagged;
    public Tile() {
        number = 0;
        revealed = false;
        flagged = false;
    }


    public void setNumber(int number) {this.number = number;}

    public int getNumber() {return number;}

    public boolean check() {
        return (!revealed && !flagged);
    }

    public char drawTile() {
        if(flagged) {
            return 'F';
        } else if(!revealed) {
            return '#';
        } else {
            // + 48 to get correct ascii index
            return (char)(this.getNumber()+48);
        }
    }

    public int reveal() {
        revealed = true;
        return this.getNumber();
    }


}
