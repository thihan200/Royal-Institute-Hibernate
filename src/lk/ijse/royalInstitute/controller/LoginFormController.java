package lk.ijse.royalInstitute.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtUsername;
    public JFXPasswordField txtpwd;
    public AnchorPane root;
    public TextField txtPassword;
    public ImageView imgEye;


    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String userName = txtUsername.getText().trim();
        String password = txtpwd.getText().trim();
        if(userName.length()>0 && password.length()>0){
            if(userName.equalsIgnoreCase("royal")&&password.equalsIgnoreCase("12345")){
                //Start login
                Stage window = (Stage) this.root.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/DashboardForm.fxml"))));
                window.centerOnScreen();

                //end login
            }else {
                new Alert(Alert.AlertType.WARNING, "Wrong Username or Password", ButtonType.OK).show();

            }

        }else{
            new Alert(Alert.AlertType.WARNING,"UserName or Password Empty",ButtonType.OK).show();
        }
    }

    public void usernameOnAction(ActionEvent actionEvent) {
        txtpwd.requestFocus();
    }



    public void pwdshowOnAction(MouseEvent mouseEvent) {

    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtUsername.setText("");
        txtpwd.setText("");
        txtPassword.setText("");
    }
}
