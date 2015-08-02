package business;

import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MemberController {
	 	@FXML
	    private TableView<LibraryMember> memberTable;
	    @FXML
	    private TableColumn<LibraryMember, String> memIDColumn;
	    @FXML
	    private TableColumn<LibraryMember, String> firstNameColumn;
	    @FXML
	    private TableColumn<LibraryMember, String> lastNameColumn;
	    @FXML
	    private TableColumn<LibraryMember, String> telephoneNumberColumn;
	    @FXML
	    private TableColumn<LibraryMember, String> streetColumn;
	    @FXML
	    private TableColumn<LibraryMember, String> cityColumn;
	    @FXML
	    private TableColumn<LibraryMember, String> stateColumn;
	    @FXML
	    private TableColumn<LibraryMember, String> zipColumn;

	    static	DataAccess da = new DataAccessFacade();

	    public MemberController() {
	    }

	    @FXML
	    private void initialize() {
	    	memIDColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("memberId"));
	        firstNameColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("firstName"));
	        lastNameColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("lastName"));
	        telephoneNumberColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("telephoneNumber"));
	        streetColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("street"));
	        cityColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("city"));
	        stateColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("state"));
	        zipColumn.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("zip"));
	        

	    }
	    public void setMemberController() {
	 		 ObservableList<LibraryMember> list = FXCollections.observableArrayList();
	    	 List<LibraryMember> aa = da.getMemberList().getMembers();
	    	 list.addAll(aa);
	 		 try {
	 			  memberTable.setItems(list);
	 		    }
	 		    catch (Exception ex) {
	 		    	throw ex;
	 		    }
	 	        
	    }

}
