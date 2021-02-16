package lk.ijse.royalInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
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
import lk.ijse.royalInstitute.bo.custom.StudentBO;
import lk.ijse.royalInstitute.dto.StudentDTO;
import lk.ijse.royalInstitute.view.tm.StudentTM;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class StudentController {

    public Label lblDate;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXDatePicker txtDOB;
    public JFXRadioButton radioMale;
    public ToggleGroup gender;
    public JFXRadioButton radioFemale;
    public TableView<StudentTM> tblStudent;
    public AnchorPane student;
    public TableColumn colStuid;
    public TableColumn colStuname;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;
    public TableColumn colOperate;
    public JFXButton btnRegister;

    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);

    public void initialize() throws Exception {
            colStuid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colStuname.setCellValueFactory(new PropertyValueFactory<>("studentName"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
            colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

                tblStudent.setItems(getALLStudents());


            tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                btnRegister.setText("Update");
                txtId.setText(newValue.getId());
                txtName.setText(newValue.getStudentName());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());
                txtDOB.setValue(LocalDate.parse(newValue.getDob()));
                if ("Male".equals(newValue.getGender())) {
                    radioMale.setSelected(true);
                } else {
                    radioFemale.setSelected(true);
                }
            });
            getTimeDate();
            genrateStudentID();

    }

    private void getTimeDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd ");
        lblDate.setText(sdf.format(d));
    }

    private ObservableList<StudentTM> getALLStudents() throws Exception {
        ArrayList<StudentDTO> studentDTOS = loadAllStudent();
        ObservableList<StudentTM> tmObservableList = FXCollections.observableArrayList();
        for (StudentDTO dto : studentDTOS) {
            Button btn = new Button("Delete");
            btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
            tmObservableList.add(
                    new StudentTM(
                           dto.getId(),
                            dto.getStudentName(),
                            dto.getAddress(),
                            dto.getContact(),
                            dto.getDob(),
                            dto.getGender(),
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
                            String sid = txtId.getText();
                            String sname = txtName.getText();
                            String address = txtAddress.getText();
                            String contact = txtContact.getText();
                            String dob = String.valueOf(txtDOB.getValue());
                            boolean selected = radioMale.isSelected();
                            String gender = "";
                            if (selected){
                                gender = "Male";
                            }else {
                                gender = "Female";
                            }
                            try {
                                boolean isDeleted = studentBO.deleteStudent(new StudentDTO(sid, sname, address, contact, dob, gender));
                                if (isDeleted) {
                                    // new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted", ButtonType.OK).show();
                                    tblStudent.setItems(getALLStudents());
                                    clearAllFields();
                                    btnRegister.setText("Register");
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Removed Failed", ButtonType.OK).show();
                                }
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                    }

            );
            tblStudent.setItems(tmObservableList);
        }
        return tmObservableList;
    }

    public void registerOnAction(ActionEvent actionEvent) {
        String sid= txtId.getText();
        String sname = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String dob = String.valueOf(txtDOB.getValue());
        boolean selected = radioMale.isSelected();
        String gender = "";
        if (selected){
            gender = "Male";
        }else {
            gender = "Female";
        }

        StudentDTO studentDTO = new StudentDTO(sid, sname, address, contact, dob, gender);
        if (sid.length() > 0 && sname.length()>0 && address.length()>0 && contact.length()>0 && dob.length()>0 && gender.length()>0) {
            if (btnRegister.getText().equalsIgnoreCase("Register")) {
                try {
                    boolean isAdded = add(studentDTO);
                    if (isAdded) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Added Success", ButtonType.OK).show();
                        tblStudent.setItems(getALLStudents());
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Added Failed", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    boolean isUpdated = studentBO.updateStudent(studentDTO);

                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Update Success", ButtonType.OK).show();
                    tblStudent.setItems(getALLStudents());

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

    public void clearOnAction(ActionEvent actionEvent) throws Exception {
        btnRegister.setText("Register");
        clearAllFields();
    }

    public void clearAllFields() throws Exception {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtDOB.setPromptText("Date Of Birth");
        txtId.setText(genrateStudentID());
    }

    public String genrateStudentID()throws Exception{
        String lastID = getLastStudentID();
        if (lastID != null) {
            lastID = lastID.split("[A-Z]")[1];
            if (Integer.parseInt(lastID)<=9) {
                lastID = "S00" + (Integer.parseInt(lastID) + 1);
                txtId.setText(lastID);
            }else if (Integer.parseInt(lastID)<=99){
                lastID = "S0" + (Integer.parseInt(lastID) + 1);
                txtId.setText(lastID);
            }
        } else {
            txtId.setText("S001");
        }
        return lastID;
    }

    private String getLastStudentID() throws Exception {
        return studentBO.getLastStudentId();
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) student.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/DashboardForm.fxml"))));
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.show();
    }

    private boolean add(StudentDTO studentDTO) throws Exception {
        return studentBO.addStudent(studentDTO);
    }

    private ArrayList<StudentDTO> loadAllStudent() throws Exception {
        return (ArrayList<StudentDTO>) studentBO.getAllStudents();
    }


}
