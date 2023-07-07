package business;

import java.util.Collection;
import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public void checkOutBook(String memberID, String isbn) throws LibrarySystemException;
	public Collection<LibraryMember> alLibraryMembers();
	public Collection<Book> allBooks();
	public List<CheckoutHistory> getCheckoutHistory();
	public Book getBookByISBN(String isbn);
	public void deleteMember(String memberId);
	LibraryMember getLibraryMemberById(String memberId);
	void saveMember(LibraryMember member);
	public void saveBook(Book book);
}
