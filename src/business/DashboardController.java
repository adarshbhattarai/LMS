package business;


import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DashboardController {
	@FXML
	Pane mainPane;
	
	@FXML
	private void handleAddNewMemberTask() {
		mainPane.getChildren().clear();
		
		GridPane gridPane = new GridPane(); 
		gridPane.setHgap(10); 
		gridPane.setVgap(10); 
		gridPane.setPadding(new Insets(0, 10, 0, 10));
		 
		Text heading = new Text("Member Registration");
		heading.setFont(Font.font("Serif", FontWeight.BLACK, 15)); 
		gridPane.add(heading, 0, 0, 4,1);
		
		Button btnShowMember = new Button("Show Member");
		gridPane.add(btnShowMember, 3, 0, 4, 1);
		
		//Each set of three Text controls makes up a row of the table 
		Text txtMemID = new Text("Membership ID"); 
		txtMemID.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtMemID, 1,2);
		
		TextField memID = new TextField();
		gridPane.add(memID, 3,2);
		
		Text txtFirstName = new Text("First Name"); 
		txtFirstName.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtFirstName, 1,3);
		
		TextField firstName = new TextField();
		gridPane.add(firstName, 3,3);
		
		Text txtLastName = new Text("Last Name"); 
		txtLastName.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtLastName, 1,4);
		
		TextField lastName = new TextField();
		gridPane.add(lastName, 3,4);
		
		Text txtTelephoneNumber = new Text("Telephone Number"); 
		txtTelephoneNumber.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtTelephoneNumber, 1,5);
		
		TextField telephoneNumber = new TextField();
		gridPane.add(telephoneNumber, 3,5);
		
		Text txtStreet = new Text("Street"); 
		txtStreet.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtStreet, 1,6);
		
		TextField street = new TextField();
		gridPane.add(street, 3,6);
		
		Text txtCity = new Text("City"); 
		txtCity.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtCity, 1,7);
		
		TextField city = new TextField();
		gridPane.add(city, 3,7);
		
		Text txtState = new Text("State"); 
		txtState.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtState, 1,8);
		
		TextField state = new TextField();
		gridPane.add(state, 3,8);
		
		Text txtZip = new Text("Zip"); 
		txtZip.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtZip, 1,9);
		
		TextField zip = new TextField();
		gridPane.add(zip, 3,9);
		
		Button btnSubmit = new Button("Submit");
		gridPane.add(btnSubmit, 2, 10);
		btnSubmit.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		
		btnShowMember.setOnAction(e -> showMember(e));
		
		btnSubmit.setOnAction(e -> addNewMember(
				memID.getText(), firstName.getText(), lastName.getText(),
				telephoneNumber.getText(), street.getText(), city.getText(),
				state.getText(), zip.getText()
				));
		
		

		mainPane.getChildren().add(gridPane);
	}

	public void addNewMember(String memID, String firstName, String lastName,
			String telephoneNumber, String street, String city, String state, String zip
			) {
		SystemController syscon = new SystemController();
		try {
			syscon.addNewMember(memID, firstName,
					lastName, telephoneNumber, new Address(street, city,
							state, zip));
			handleAddNewMemberTask();
		} catch (LibrarySystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@FXML
	private void handleAddNewBookTask() {
		mainPane.getChildren().clear();
		
		GridPane gridPane = new GridPane(); 
		gridPane.setHgap(10); 
		gridPane.setVgap(10); 
		gridPane.setPadding(new Insets(0, 10, 0, 10));
		 
		Text heading = new Text("Book Addition");
		heading.setFont(Font.font("Serif", FontWeight.BLACK, 15)); 
		gridPane.add(heading, 0,0,4,1);
		
		Button btnShowBook = new Button("Show Book");
		gridPane.add(btnShowBook, 3, 0, 4, 1);
		
		Text txtISBN = new Text("ISBN"); 
		txtISBN.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtISBN, 1,2);
		
		TextField isbn = new TextField();
		gridPane.add(isbn, 3,2);
		
		Text txtTitle = new Text("Title"); 
		txtTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtTitle, 1,3);
		
		TextField title = new TextField();
		gridPane.add(title, 3,3);
		
		Text txtMaxCheckoutLength = new Text("Max. Checkout Length"); 
		txtMaxCheckoutLength.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtMaxCheckoutLength, 1,4);
		
		TextField maxCheckoutLength = new TextField();
		gridPane.add(maxCheckoutLength, 3,4);
		
		Text txtAuthors = new Text("Authors"); 
		txtAuthors.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtAuthors, 1,5);
		
		TextField authors = new TextField();
		gridPane.add(authors, 3,5);

		btnShowBook.setOnAction(e -> showBook(e));

		Button btnSubmit = new Button("Submit");
		gridPane.add(btnSubmit, 2, 6);
		btnSubmit.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		btnSubmit.setOnAction(e -> addNewBook(
				isbn.getText(), title.getText(), Integer.parseInt(maxCheckoutLength.getText()),
				authors.getText()
				));

		mainPane.getChildren().add(gridPane);
	}

	public void addNewBook(String isbn, String title, int maxCheckoutLength,
			String authors
			) {
		SystemController syscon = new SystemController();
		try {
			String[] authorList;
			authorList = authors.split(",");
			ArrayList<Author> authorsList=new ArrayList<Author>();
			for(int i = 0; i < authorList.length; i++){
				authorsList.add(new Author(authorList[i]) );
			}
			
		
			syscon.addBook(isbn, title,
					maxCheckoutLength, authorsList);
			handleAddNewBookTask();
		} catch (LibrarySystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	@FXML
	private void handleAddBookCopyTask() {
		mainPane.getChildren().clear();
		
		GridPane gridPane = new GridPane(); 
		gridPane.setHgap(10); 
		gridPane.setVgap(10); 
		gridPane.setPadding(new Insets(0, 10, 0, 10));
		 
		Text heading = new Text("Book Copy Addition");
		heading.setFont(Font.font("Serif", FontWeight.BLACK, 15)); 
		gridPane.add(heading, 0,0,4,1);
		
		//Each set of three Text controls makes up a row of the table 
		Text txtISBN = new Text("ISBN"); 
		txtISBN.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtISBN, 1,2);
		
		TextField isbn = new TextField();
		gridPane.add(isbn, 3,2);
		
		Text txtCopyNo = new Text("Copy Number"); 
		txtCopyNo.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtCopyNo, 1,3);
		
		TextField copyNumber = new TextField();
		gridPane.add(copyNumber, 3,3);
		
		Button btnSubmit = new Button("Submit");
		gridPane.add(btnSubmit, 2, 4);
		btnSubmit.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		btnSubmit.setOnAction(e -> addNewCopyBook(
				isbn.getText(), copyNumber.getText()
				));

		mainPane.getChildren().add(gridPane);
	}
	
	public void addNewCopyBook(String isbn, String copyNumber
			) {
		
	}
	
	@FXML
	private void handleCheckoutBookTask() {
		mainPane.getChildren().clear();
		
		GridPane gridPane = new GridPane(); 
		gridPane.setHgap(10); 
		gridPane.setVgap(10); 
		gridPane.setPadding(new Insets(0, 10, 0, 10));
		 
		Text heading = new Text("Book Checkout");
		heading.setFont(Font.font("Serif", FontWeight.BLACK, 15));
		gridPane.add(heading, 0,0,4,1);
		
		//Each set of three Text controls makes up a row of the table 
		Text txtISBN = new Text("ISBN"); 
		txtISBN.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtISBN, 1,2);
		
		TextField isbn = new TextField();
		gridPane.add(isbn, 3,2);
		
		Text txtCheckoutDate = new Text("Checkout Date"); 
		txtCheckoutDate.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtCheckoutDate, 1,3);
		
		TextField checkoutDate = new TextField();
		gridPane.add(checkoutDate, 3,3);
		
		Text txtDueDate = new Text("Due Date"); 
		txtDueDate.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtDueDate, 1,4);
		
		TextField dueDate = new TextField();
		gridPane.add(dueDate, 3,4);

		Button btnSubmit = new Button("Submit");
		gridPane.add(btnSubmit, 2, 5);
		btnSubmit.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		btnSubmit.setOnAction(e -> checkoutBook(
				checkoutDate.getText(), dueDate.getText()
				));

		mainPane.getChildren().add(gridPane);
	}
	
	public void checkoutBook(String checkoutDate, String dueDate
			) {
		
	}
	
	@FXML
	private void handleSearchBookTask() {
		mainPane.getChildren().clear();
		
		GridPane gridPane = new GridPane(); 
		gridPane.setHgap(10); 
		gridPane.setVgap(10); 
		gridPane.setPadding(new Insets(0, 10, 0, 10));
		 
		Text heading = new Text("Search Book");
		heading.setFont(Font.font("Serif", FontWeight.BLACK, 15)); 
		gridPane.add(heading, 0,0,4,1);
		
		//Each set of three Text controls makes up a row of the table 
		Text txtISBN = new Text("ISBN"); 
		txtISBN.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtISBN, 1,2);
		
		TextField isbn = new TextField();
		gridPane.add(isbn, 3,2);
		
		Button btnSubmit = new Button("Submit");
		gridPane.add(btnSubmit, 2, 3);
		btnSubmit.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		btnSubmit.setOnAction(e -> searchBook(
				isbn.getText()
				));

		mainPane.getChildren().add(gridPane);
	}
	
	public void searchBook(String isbn) {
		
	}
	
	@FXML
	private void handleSearchOverdueBookTask() {
		mainPane.getChildren().clear();
		
		GridPane gridPane = new GridPane(); 
		gridPane.setHgap(10); 
		gridPane.setVgap(10); 
		gridPane.setPadding(new Insets(0, 10, 0, 10));
		 
		Text heading = new Text("Search Overdue Book");
		heading.setFont(Font.font("Serif", FontWeight.BLACK, 15)); 
		gridPane.add(heading, 0,0,4,1);
		
		//Each set of three Text controls makes up a row of the table 
		Text txtISBN = new Text("ISBN"); 
		txtISBN.setFont(Font.font("Arial", FontWeight.BOLD, 15)); 
		gridPane.add(txtISBN, 1,2);
		
		TextField isbn = new TextField();
		gridPane.add(isbn, 3,2);
		
		Button btnSubmit = new Button("Submit");
		gridPane.add(btnSubmit, 2, 3);
		btnSubmit.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		btnSubmit.setOnAction(e -> searchOverdueBook(
				isbn.getText()
				));

		mainPane.getChildren().add(gridPane);
	}
	
	public void searchOverdueBook(String isbn) {
		
	}
	
	/**
	 * Called when the user clicks on the exit button.
	 */ 
	@FXML
	private void handleExitTask() {
		System.exit(0);
	}
	
	private void showMember(ActionEvent e) {
		mainPane.getChildren().clear();	
		FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("../view/MemberOverview.fxml"));
		try {
			mainPane.getChildren().setAll((Pane) loader.load());
	        MemberController memberController = loader.getController();
	        memberController.setMemberController();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	private void showBook(ActionEvent e) {
		mainPane.getChildren().clear();	
		FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("../view/BookOverview.fxml"));
		try {
			mainPane.getChildren().setAll((Pane) loader.load());
	        Book book = loader.getController();
	        book.setMemberController();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
}
