package lk.ijse.royalInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.royalInstitute.bo.custom.RegistrationBO;
import lk.ijse.royalInstitute.bo.custom.StudentBO;
import lk.ijse.royalInstitute.dto.CourseDTO;
import lk.ijse.royalInstitute.dto.RegistrationDTO;
import lk.ijse.royalInstitute.dto.StudentDTO;
import lk.ijse.royalInstitute.entity.Course;
import lk.ijse.royalInstitute.entity.Student;
import lk.ijse.royalInstitute.view.tm.RegistrationTM;
import lk.ijse.royalInstitute.view.tm.StudentTM;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class RegistrationController {
    public AnchorPane register;
    public JFXTextField txtregId;
    public JFXTextField txtregDate;
    public JFXComboBox cmbCode;
    public JFXTextField txtCoursename;
    public JFXTextField txtcourseDuration;
    public JFXTextField txtcourseType;
    public JFXComboBox cmbId;
    public JFXTextField txtstudentId;
    public JFXTextField txtstudentContact;
    public JFXTextField txtstudentAddress;
    public JFXTextField txtdob;
    public JFXTextField txtgender;
    public TableView<RegistrationTM> tblregister;
    public TableColumn colRegid;
    public TableColumn colRegdate;
    public TableColumn colRegFee;
    public TableColumn colStudentid;
    public TableColumn colCourseId;
    public TableColumn colOperate;
    public TableColumn colCourseId1;
    public JFXTextField txtRegFee;
    public JFXButton btnRegister;

    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getInstance().getBO(BOFactory.BOType.REGISTRATION);

    public void initialize() throws Exception {
       colRegid.setCellValueFactory(new PropertyValueFactory<>("regNo"));
       colRegdate.setCellValueFactory(new PropertyValueFactory<>("regDate"));
       colRegFee.setCellValueFactory(new PropertyValueFactory<>("regFee"));
       colStudentid.setCellValueFactory(new PropertyValueFactory<>("studentId"));
       colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
       colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblregister.setItems(getAllRegistrations());

        tblregister.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnRegister.setText("Update Registration");
            txtregId.setText(newValue.getRegNo());
            txtregDate.setText(newValue.getRegDate());
            txtRegFee.setText(String.valueOf(newValue.getRegFee()));
            cmbId.setValue(newValue.getStudentId());
            cmbCode.setValue(newValue.getCourseId());

        });
        getTimeDate();
        getAllCourse();
        getAllStudents();
        genrateRegistrationID();
    }

    private void getTimeDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd ");
        txtregDate.setText(sdf.format(d));
    }

    private ObservableList<RegistrationTM> getAllRegistrations() throws Exception {
        ArrayList<RegistrationDTO> registrationDTOS = loadAllRegistration();
        ObservableList<RegistrationTM> tmObservableList = FXCollections.observableArrayList();
        for (RegistrationDTO dto : registrationDTOS) {
            Button btn = new Button("Delete");
            btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
            tmObservableList.add(
                    new RegistrationTM(
                           dto.getRegNo(),
                            dto.getRegDate(),
                            dto.getRegFee(),
                            dto.getStudentId(),
                            dto.getCourseId(),
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
                            String regid = txtregId.getText();
                            String regdate = txtregDate.getText();
                            Double regfee = Double.parseDouble(txtRegFee.getText());
                            String stuId = (String) cmbId.getValue();
                            String  cosCode = (String) cmbCode.getValue();
                            try {
                                boolean isDeleted = registrationBO.deleteRegistration(new RegistrationDTO(regid, regdate, regfee, stuId, cosCode));
                                if (isDeleted) {
                                    // new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted", ButtonType.OK).show();
                                    tblregister.setItems(getAllRegistrations());
                                    btnRegister.setText("Register To The Course");
                                    clearAllFields();
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Removed Failed", ButtonType.OK).show();
                                }
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                    }

            );
            tblregister.setItems(tmObservableList);
        }
        return tmObservableList;
    }

    private ArrayList<RegistrationDTO> loadAllRegistration() throws Exception {
        return (ArrayList<RegistrationDTO>) registrationBO.getAllRegistrations();
    }

    private ArrayList<CourseDTO> loadAllCourses() throws Exception {
        return (ArrayList<CourseDTO>) registrationBO.getAllCourses();
    }

    private ArrayList<StudentDTO> loadAllStudents()throws Exception{
        return (ArrayList<StudentDTO>) registrationBO.getAllStudents();
    }

    private void getAllCourse() throws Exception {
        ArrayList<CourseDTO> courseDTOS = loadAllCourses();
        ObservableList<String> allCourse = FXCollections.observableArrayList();
        for (CourseDTO dto : courseDTOS) {
            allCourse.add(dto.getCode());
        }
        cmbCode.setItems(allCourse);
    }

    private void getAllStudents() throws Exception {
        ArrayList<StudentDTO> studentDTOS = loadAllStudents();
        ObservableList<String> allStudents = FXCollections.observableArrayList();
        for (StudentDTO dto : studentDTOS) {
            allStudents.add(dto.getId());
        }
        cmbId.setItems(allStudents);
    }


    public void bacckOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) register.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/DashboardForm.fxml"))));
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.show();
    }

    public void registerOnAction(ActionEvent actionEvent) {
        String regid = txtregId.getText();
        String regdate = txtregDate.getText();
        Double regfee = Double.parseDouble(txtRegFee.getText());
        String stuId = (String) cmbId.getValue();
        String cosCode = (String) cmbCode.getValue();

        RegistrationDTO registrationDTO = new RegistrationDTO(regid, regdate, regfee, stuId, cosCode);

        if (regid.length() > 0 && regdate.length() > 0 && regfee.doubleValue() > 0) {
            if (btnRegister.getText().equalsIgnoreCase("Register To The Course")) {
                try {
                    boolean isAdded = add(registrationDTO);
                    if (isAdded) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Added Success", ButtonType.OK).show();
                        tblregister.setItems(getAllRegistrations());
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Added Failed", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    boolean isUpdated = registrationBO.updateRegistration(registrationDTO);

                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Update Success", ButtonType.OK).show();
                        tblregister.setItems(getAllRegistrations());

                    } else {
                        new Alert(Alert.AlertType.WARNING, "Update Failed", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Field", ButtonType.OK).show();
        }
    }

    private boolean add(RegistrationDTO registrationDTO) throws Exception {
        return registrationBO.addRegistration(registrationDTO);
    }

    public void clearOnAction(ActionEvent actionEvent) throws Exception {
        clearAllFields();

    }

    public void clearAllFields() throws Exception {
        txtRegFee.setText("");
        cmbCode.setPromptText("");
        txtCoursename.setText("");
        txtcourseType.setText("");
        txtcourseDuration.setText("");
        cmbId.setPromptText("Course Code");
        txtstudentId.setText("");
        txtstudentAddress.setText("");
        txtstudentContact.setText("");
        txtdob.setText("");
        txtgender.setText("");
        txtregId.setText(genrateRegistrationID());
    }

    private String genrateRegistrationID() throws Exception {
        String lastID = getLastRegID();
        if (lastID != null) {
            lastID = lastID.split("[A-Z]")[1];
            if (Integer.parseInt(lastID) <= 9) {
                lastID = "R00" + (Integer.parseInt(lastID) + 1);
                txtregId.setText(lastID);
            } else if (Integer.parseInt(lastID) <= 99) {
                lastID = "R0" + (Integer.parseInt(lastID) + 1);
                txtregId.setText(lastID);
            }
        } else {
            txtregId.setText("R001");
        }
        return lastID;
    }

    private String getLastRegID() throws Exception {
        return registrationBO.getLastRegID();
    }

    public void cmbCodeOnAction(ActionEvent actionEvent) {
        String Code = (String) cmbCode.getValue();
        try {
            CourseDTO courseDTO = searchCourse(Code);
            if (courseDTO != null){
                txtCoursename.setText(courseDTO.getCourseName());
                txtcourseType.setText(courseDTO.getCourseType());
                txtcourseDuration.setText(courseDTO.getDuration());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CourseDTO searchCourse(String courseCode) throws Exception {
        return registrationBO.searchCourse(courseCode);
    }

    private StudentDTO searchStudent(String studentID)throws Exception{
        return registrationBO.searchStudent(studentID);
    }

    public void cmbIdOnAction(ActionEvent actionEvent) {
        String sid = (String) cmbId.getValue();
        try {
            StudentDTO studentDTO = searchStudent(sid);
            if (studentDTO != null){
                txtstudentId.setText(studentDTO.getStudentName());
                txtstudentAddress.setText(studentDTO.getAddress());
                txtstudentContact.setText(studentDTO.getContact());
                txtdob.setText(studentDTO.getDob());
                txtgender.setText(studentDTO.getStudentName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


