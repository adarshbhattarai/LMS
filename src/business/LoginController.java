package business;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import dataaccess.Auth;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
/*import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;*/
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LoginController extends LoginException implements Initializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Stage prevStage;
	Pane pane = null;
	Stage stage = new Stage();
	

	@FXML
	private TextField txtUsername;
	
	@FXML
	private PasswordField txtPassword;
	
	private String username, password;

    public void setPrevStage(Stage stage){
         this.prevStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
	
	/**
	 * Called when the user clicks on the submit button.
	 * @throws LoginException 
	 */
	@FXML
	private void handleSubmitTask() throws LoginException {
		username = txtUsername.getText();
		password = txtPassword.getText();
		
		SystemController sys = new SystemController();
		sys.login(username, password);
		if(SystemController.currentAuth != null) {
			if(SystemController.currentAuth.equals(Auth.LIBRARIAN)) {
				showLibrarianDashboard();
			}
			else if(SystemController.currentAuth.equals(Auth.ADMIN)) {
				showAdministratorDashboard();
			}
			else if(SystemController.currentAuth.equals(Auth.BOTH)) {;
				showDashboard();
			}
		}
		else {
			/*Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("");
			alert.setContentText("Either username or password is incorrect!");
			alert.showAndWait();
			txtUsername.setText("");
			txtPassword.setText("");
	*/	}
	}
	
	@FXML
	private void handleCancelTask() {
		System.exit(0);
	}
	
	@FXML
	public void showAdministratorDashboard() {
        try {
        	stage.setTitle("Administrator Dashboard");
        	pane = FXMLLoader.load(getClass().getResource("../view/AdminDashboardLayout.fxml"));
        	Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.getIcons().add(new Image("view/icon.jpg"));
            stage.setResizable(false);
            prevStage.close();
            stage.show();

        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
	
	@FXML
	public void showLibrarianDashboard() {
        try {
        	stage.setTitle("Librarian Dashboard");
        	pane = FXMLLoader.load(getClass().getResource("../view/LibrarianDashboardLayout.fxml"));
        	Scene scene = new Scene(pane);
        	stage.getIcons().add(new Image("view/icon.jpg"));
        	stage.setResizable(false);
            stage.setScene(scene);
            prevStage.close();
            stage.show();

        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
	
	public void showDashboard() {
        try {
        	stage.setTitle("Dashboard");
        	pane = FXMLLoader.load(getClass().getResource("../view/DashboardLayout.fxml"));
        	Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.getIcons().add(new Image("view/icon.jpg"));
            stage.setResizable(false);
            prevStage.close();
            stage.show();

        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
	
	@FXML
    public void handle(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
        	try {
				handleSubmitTask();
			} catch (LoginException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
    }	
	
}
