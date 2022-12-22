public class Milk extends Inventory{

    public Milk() {

    } // end constructor

    @Override
    public void incLevel() {

        this.suppliesLevel += 15;
    } // end incLevel 

    @Override
    public void decLevel() {

        this.suppliesLevel -= 15;
    } // end decLevel
    
} // end Milk class