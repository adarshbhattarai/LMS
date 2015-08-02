package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LibraryMemberList implements Serializable {
	 
	 /**
	  * 
	  */
	 private static final long serialVersionUID = -617070314315355241L;
	 private static LibraryMemberList instance = null;
	    protected LibraryMemberList() {
	       // Exists only to defeat instantiation.
	    }
	    public static LibraryMemberList getInstance() {
	       if(instance == null) {
	          instance = new LibraryMemberList();
	       }
	       return instance;
	    }
	    
	 private List<LibraryMember> members = new ArrayList<>();

	 public List<LibraryMember> getMembers() {
	  return members;
	 }
	 
	 public void addMember(LibraryMember member) {
	  members.add(member);
	 }
	 
	 @Override
	 public String toString() {
	  // TODO Auto-generated method stub
	  return members.toString();
	 }
	}