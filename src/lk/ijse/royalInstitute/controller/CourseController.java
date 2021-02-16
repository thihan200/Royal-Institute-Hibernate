package lk.ijse.royalInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.royalInstitute.bo.BOFactory;
import lk.ijse.royalInstitute.bo.custom.CourseBO;
import lk.ijse.royalInstitute.dto.CourseDTO;
import lk.ijse.royalInstitute.dto.StudentDTO;
import lk.ijse.royalInstitute.view.tm.CourseTM;
import lk.ijse.royalInstitute.view.tm.StudentTM;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CourseController {

    public AnchorPane course;
    public JFXTextField txtcode;
    public JFXTextField txtName;
    public JFXTextField txtType;
    public JFXTextField txtDuration;
    public Label lblDate;
    public TableView<CourseTM> tblCourse;
    public TableColumn colCoursecode;
    public TableColumn colCoursename;
    public TableColumn colCoursetype;
    public TableColumn colDuration;
    public TableColumn colOperate;
    public JFXButton btnAdd;

    CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOType.COURSE);

    public void initialize() throws Exception {
        colCoursecode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCoursename.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCoursetype.setCellValueFactory(new PropertyValueFactory<>("courseType"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblCourse.setItems(getAllCourses());

        tblCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnAdd.setText("Update");
            txtcode.setText(newValue.getCode());
            txtName.setText(newValue.getCourseName());
            txtType.setText(newValue.getCourseType());
            txtDuration.setText(newValue.getDuration());
        });

        getTimeDate();
    }

    private ObservableList<CourseTM> getAllCourses() throws Exception {
        ArrayList<CourseDTO> courseDTOS = loadAllCourses();
        ObservableList<CourseTM> tmObservableList = FXCollections.observableArrayList();
        for (CourseDTO dto : courseDTOS) {
            Button btn = new Button("Delete");
            btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
            tmObservableList.add(
                    new CourseTM(
                           dto.getCode(),
                            dto.getCourseName(),
                            dto.getCourseType(),
                            dto.getDuration(),
                            btn
                    )
            );
            btn.setOnAction((e) -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Dialog");
                        alert.setHeaderText("Confirmation Delete");
                        alert.setContentText("Are you sure to Delete ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            String code = txtcode.getText();
                            String courseName = txtName.getText();
                            String courseType = txtType.getText();
                            String courseDuration = txtDuration.getText();
                            try {
                                boolean isDeleted = courseBO.deleteCourse(new CourseDTO(code, courseName, courseType, courseDuration));
                                if (isDeleted) {
                                    // new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted", ButtonType.OK).show();
                                    tblCourse.setItems(getAllCourses());
                                    clearAllFields();
                                    btnAdd.setText("Add Course");
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Remove Failed", ButtonType.OK).show();
                                }
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                    }

            );
            tblCourse.setItems(tmObservableList);
        }
        return tmObservableList;
    }

    private ArrayList<CourseDTO> loadAllCourses() throws Exception {
        return (ArrayList<CourseDTO>) courseBO.getAllCourses();
    }

    private void getTimeDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd ");
        lblDate.setText(sdf.format(d));
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) course.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/DashboardForm.fxml"))));
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.show();
    }

    public void registerOnAction(ActionEvent actionEvent) {
        String code = txtcode.getText();
        String courseName = txtName.getText();
        String courseType = txtType.getText();
        String courseDuration = txtDuration.getText();

        CourseDTO courseDTO = new CourseDTO(code, courseName, courseType, courseDuration);
        if (code.length()>0 && courseName.length()>0 && courseType.length()>0 && courseDuration.length()>0){
            if (btnAdd.getText().equalsIgnoreCase("Add Course")) {
                try {
                    boolean isAdded = add(courseDTO);
                    if (isAdded) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Added Success", ButtonType.OK).show();
                        tblCourse.setItems(getAllCourses());
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Added Failed", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    boolean isUpdated = courseBO.updateCourse(courseDTO);

                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Update Success", ButtonType.OK).show();
                        tblCourse.setItems(getAllCourses());
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Update Failed", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "Empty Fields", ButtonType.OK).show();
        }
    }

    private boolean add(CourseDTO courseDTO) throws Exception {
        return courseBO.addCourse(courseDTO);
    }

    public void clearOnAction(ActionEvent actionEvent) throws Exception {
        clearAllFields();
        btnAdd.setText("Add Course");
    }

    public void clearAllFields() throws Exception {
        txtcode.setText("");
        txtName.setText("");
        txtType.setText("");
        txtDuration.setText("");
    }


}
