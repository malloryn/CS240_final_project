import java.io.*;

public abstract class Inventory implements Serializable{
    
    int suppliesLevel;

    public void setLevel(int lev) {

        this.suppliesLevel = lev;
    } // end setLevel

    public int getLevel() {

        return this.suppliesLevel;
    } // end getLevel 

    public abstract void incLevel();
    public abstract void decLevel();

} // end Inventory class