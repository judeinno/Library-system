package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import static java.time.LocalDate.now;

public class CheckoutEntry implements Serializable {
    private LocalDate checkOutDate;
    private LocalDate dueDate;
    private BookCopy bookCopy;
    private LocalDate dateReturned;

    CheckoutEntry(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate){
        this.bookCopy = Objects.requireNonNull(bookCopy);
        this.checkOutDate = Objects.requireNonNull(checkoutDate);
        this.dueDate = Objects.requireNonNull(dueDate);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckoutEntry that = (CheckoutEntry) o;

        if (!Objects.equals(checkOutDate, that.checkOutDate)) return false;
        return Objects.equals(dueDate, that.dueDate);
    }

    public boolean isOverdue() {
        return now().isAfter(dueDate) && !bookCopy.isAvailable();
    }


    @Override
    public int hashCode() {
        int result = checkOutDate != null ? checkOutDate.hashCode() : 0;
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CheckoutRecordEntry{" +
                "copy=" + bookCopy +
                ", checkoutDate=" + checkOutDate +
                ", dueDate=" + dueDate +
                ", isOverdue=" + isOverdue() +
                '}';
    }
}
