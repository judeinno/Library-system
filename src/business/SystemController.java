package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();

	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public boolean checkOutBook(String memberId, String isbn) throws LibrarySystemException{
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> libraryMembers =  da.readMemberMap();
		HashMap<String, Book> books = da.readBooksMap();
		if(!(libraryMembers.containsKey(memberId))){
			throw new LibrarySystemException("No member with this Id found!");
		}

		if(!books.containsKey(isbn)){
			throw new LibrarySystemException("No book with this isbn found!");
		}else{
			System.out.println(books.get(isbn));
		}

		if(books.get(isbn).isAvailable()){
			Book book = books.get(isbn);
			LibraryMember member = libraryMembers.get(memberId);
			BookCopy copy = book.getNextAvailableCopy();
			if(copy != null) {
				CheckoutEntry newEntry = new CheckoutEntry(copy);
				member.addCheckoutRecord(newEntry);
				copy.changeAvailability();
				return true;
			}
		}

		return false;
	}

}
