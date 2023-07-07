package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
    private List<CheckoutEntry> checkoutEntries;
    private final LibraryMember member;

    public CheckoutRecord(LibraryMember member, List<CheckoutEntry> entries) {
        this.member = member;
        this.checkoutEntries = entries;
    }

    public void addCheckoutEntry(CheckoutEntry checkoutEntry){
        this.checkoutEntries.add(checkoutEntry);
    }

    public List<CheckoutEntry> getEntries() {
        return checkoutEntries;
    }

    public LibraryMember getMember() {
        return member;
    }

}
