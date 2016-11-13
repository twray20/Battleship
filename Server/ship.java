package salvoserver;

public class ship {

    public int x, y;
    private int length;
    private char c;
    private boolean hit;

    public ship() {
        x = 0;
        y = 0;
        length = 0;
        c = 'A';
        hit = false;
    }

    public ship(int xC, int yC, int shipL) {
        x = xC;
        y = yC;
        length = shipL + 1;
        switch (xC) {
            case 1:
                c = 'A';
                break;
            case 2:
                c = 'B';
                break;
            case 3:
                c = 'C';
                break;
            case 4:
                c = 'D';
                break;
            case 5:
                c = 'E';
                break;
            case 6:
                c = 'F';
                break;
            case 7:
                c = 'G';
                break;
            case 8:
                c = 'H';
                break;
            case 9:
                c = 'I';
                break;
            case 10:
                c = 'J';
                break;
        }
        hit = false;
    }

    public String getCoord() {
        return (c + "" + y);
    }

    public String getType() {
        switch (length) {
            case 2:
                return "Destroyer";
            case 3:
                return "Sub";
            case 4:
                return "Carrier";
            case 5:
                return "Battleship";
        }
        return "Unknown type";
    }
    
    public void shipHit(){
        hit = true;
    }
    
    public int getLength(){
        return length;
    }
    
    public boolean getHit(){
        return hit;
    }
}
