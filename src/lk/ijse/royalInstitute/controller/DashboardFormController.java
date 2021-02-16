package lk.ijse.royalInstitute.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class DashboardFormController {
    public AnchorPane context;



    public void studentOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/Student.fxml"))));
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.show();
    }

    public void logoutOnAction(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirmation Logout");
        alert.setContentText("Are you sure to logout ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) context.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"))));
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(true);
            stage.show();
        }else{
            //new Alert(Alert.AlertType.WARNING, "Logout Failed", ButtonType.OK).show();
        }
    }

    public void courseOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/Course.fxml"))));
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.show();
    }

    public void registerOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/Registration.fxml"))));
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.show();
    }
}
