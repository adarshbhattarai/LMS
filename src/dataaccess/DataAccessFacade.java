package dataaccess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import business.Book;
import business.LibraryMember;
import business.LibraryMemberList;

public class DataAccessFacade implements DataAccess {
	
	enum StorageType {
		BOOKS, MEMBERS, USERS;
	}
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ "\\src\\dataaccess\\storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
	public Book searchBook(String isbn) {
		HashMap<String,Book> booksMap =  readBooksMap();
		Book b = booksMap.get(isbn);
		return b;
	}
	
	public Auth login(String id, String pwd) {
		HashMap<String, User> userMap = readUserMap();
		if(!userMap.containsKey(id)) return null;
		User user = userMap.get(id);
		if(!pwd.equals(user.getPassword())) {
			return null;
		}
		return user.getAuthorization();
	}
	
	public void saveNewMember(LibraryMember member){
		LibraryMemberList memberlist = getMemberList();

		  if (memberlist == null) {
		   memberlist = LibraryMemberList.getInstance();
		  }  
		  
		  memberlist.addMember(member);
		  saveToStorage(StorageType.MEMBERS, memberlist);  
	} 
		
	public void saveNewBook(Book book) {
		HashMap<String, Book> bookMap = readBooksMap();
		String isbn = book.getIsbn();
		bookMap.put(isbn, book);
		saveToStorage(StorageType.BOOKS, bookMap);	
	}
	
	
	@SuppressWarnings("unchecked")
	public  HashMap<String,Book> readBooksMap() {	
		return (HashMap<String,Book>) readFromStorage(StorageType.BOOKS);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		return (HashMap<String, User>)readFromStorage(StorageType.USERS);
	}
	
		
	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		//extract each book in the bookList, and add (ISBN, each bookItem) as an entry in the HashMap
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}
	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}
	
	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			return initializeStorage(type);
			//e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
	
	public static Object initializeStorage(StorageType type) {
		  Object obj = null;
		  if (type.equals(StorageType.MEMBERS)) {
		   obj = LibraryMemberList.getInstance();
		  } 

		  return obj;
		 }
	
	
//	final static class Pair<S,T> implements Serializable{
//		
//		S first;
//		T second;
//		Pair(S s, T t) {
//			first = s;
//			second = t;
//		}
//		@Override 
//		public boolean equals(Object ob) {
//			if(ob == null) return false;
//			if(this == ob) return true;
//			if(ob.getClass() != getClass()) return false;
//			@SuppressWarnings("unchecked")
//			Pair<S,T> p = (Pair<S,T>)ob;
//			return p.first.equals(first) && p.second.equals(second);
//		}
//		
//		@Override 
//		public int hashCode() {
//			return first.hashCode() + 5 * second.hashCode();
//		}
//		
//
//		
//
//		private static final long serialVersionUID = 5399827794066637059L;
//	}

	
	public LibraryMemberList getMemberList() {
		  LibraryMemberList memberList = (LibraryMemberList)readFromStorage(StorageType.MEMBERS);
		  return memberList;
	}
	


	@Override
	public HashMap<String, LibraryMember> readMemberMap() {
		// TODO Auto-generated method stub
		
		//LibraryMemberList list = 
		
		return null;
	}



	
}
