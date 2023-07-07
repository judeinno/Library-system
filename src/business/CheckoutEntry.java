package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry implements Serializable {
    private LocalDate checkOutDate;
    private LocalDate dueDate;
    private BookCopy bookCopy;
    private LocalDate dateReturned;

    CheckoutEntry(BookCopy bookCopy){
        this.checkOutDate = LocalDate.now();
        this.bookCopy = bookCopy;
        this.dueDate = LocalDate.now().plusDays(bookCopy.getBook().getMaxCheckoutLength());
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned){
        this.dateReturned = dateReturned;
    }
}
