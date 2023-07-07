package business;

import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord {
    private List<CheckoutEntry> checkoutEntries;

    CheckoutRecord(){
        this.checkoutEntries = new ArrayList<>();
    }

    public void addCheckoutEntry(CheckoutEntry checkoutEntry){
        this.checkoutEntries.add(checkoutEntry);
    }
}
