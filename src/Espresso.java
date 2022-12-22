public class Espresso extends Inventory {

    public Espresso() {

    } // end constructor

    @Override
    public void incLevel() {

        this.suppliesLevel += 2;
    } // end incLevel 

    @Override
    public void decLevel() {

        this.suppliesLevel -= 2;
    } // end decLevel

} // end Espresso class