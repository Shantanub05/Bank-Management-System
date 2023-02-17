package bankmanagement;
//JAVA SDK 19, JAVAfx , Scenebuilder , Xampp , MySQL
import DB.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.sql.ResultSet;

import java.io.IOException;
import java.sql.Statement;

public class Login {
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordFIeld;
    @FXML
    private Label loginMessageLabel;

    @FXML
    private void CancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void loginButtonOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        if (usernameTextField.getText().isBlank() == false && passwordPasswordFIeld.getText().isBlank() == false) {
            validateLogin();



        } else {
            loginMessageLabel.setText("Please enter username and password");
        }






    }

    public void validateLogin() throws SQLException, ClassNotFoundException {
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();
        String verifylogin = "SELECT count(1) FROM login WHERE username= '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordFIeld.getText() + "'";
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);

            while(queryResult.next()) {
                if(queryResult.getInt(1)==1) {
                    Parent root = FXMLLoader.load(getClass().getResource("BankMain.fxml"));

                    Scene scene = new Scene(root);

                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                 


                }
                else {
                    loginMessageLabel.setText("Invalid Login");
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }
}


