package com.example.oopproject;

import com.example.oopproject.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.sql.*;

public class homePageController extends HelloApplication {
    public CheckBox applicant;
    public CheckBox employer;
    public Button cancel;
    public Button signIn;
    public TextField emailInput;
    public PasswordField passwordInput;
    public Label error;
    static String Email;
    static String password01;
    @FXML
    public void signUp(ActionEvent event) throws IOException{
        Object root4 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signUp.fxml")));
        Scene scene4 = new Scene((Parent) root4);
        Stage Window =(Stage)((Node)event.getSource()).getScene().getWindow();
        Window.setScene(scene4);
        Window.show();
    }
    @FXML
    public void dataBaseConnect(ActionEvent event) throws IOException{
        Email=emailInput.getText();
        password01=passwordInput.getText();

       databaseConnector connectKnow =new databaseConnector();
        Connection connectDB = connectKnow.getConnection();
        if(emailInput.getText().isBlank() || passwordInput.getText().isBlank()){
            error.setText("Please enter all inputs correctly");
        }


        else{
            if(applicant.isSelected()) {
                try {
                    String sqlQuery = "SELECT count(1) FROM our_4_killo_job.employees_list WHERE Email = '" + emailInput.getText() + "' AND Password = '" + passwordInput.getText() + "'";

                    Statement sta = connectDB.createStatement();
                    ResultSet resultSet = sta.executeQuery(sqlQuery);
                    while (resultSet.next()) {
                        if (resultSet.getInt(1) == 1) {
                            Object root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("jobSearch.fxml")));
                            Scene scene = new Scene((Parent) root1);
                            Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Window.setScene(scene);
                            Window.show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else  if (employer.isSelected()) {
                try {
                    String sqlQuery = "SELECT count(1) FROM our_4_killo_job.employers_list WHERE Email = '" + emailInput.getText() + "' AND Password = '" + passwordInput.getText() + "'";

                    Statement sta = connectDB.createStatement();
                    ResultSet resultSet = sta.executeQuery(sqlQuery);
                    while (resultSet.next()) {
                        if (resultSet.getInt(1) == 1) {
                            Object root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view.fxml")));
                            Scene scene = new Scene((Parent) root1);
                            Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Window.setScene(scene);
                            Window.show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                error.setText("Please enter Correct Password And user Name");
            }
        }
    }
    @FXML
    public TextField search;
    @FXML
//    public TableView<JobSearch> table;
    public Label total;
    public ScrollPane scroll;
    @FXML
    private BorderPane bord;
    @FXML
//    Second page
    private void profile(ActionEvent event) throws IOException {
        scroll.setContent(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("personal-info.fxml"))));
        bord.setCenter(scroll);
    }
    @FXML
    public void logOut(ActionEvent event) throws IOException{
        Object logOut = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homePage.fxml")));
        Scene scene0 = new Scene((Parent) logOut);
        Stage Window =(Stage)((Node)event.getSource()).getScene().getWindow();
        Window.setScene( scene0);
        Window.show();
    }
    public void jobs(ActionEvent actionEvent) throws IOException {
        scroll.setContent(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("jobList.fxml"))));
        bord.setCenter(scroll);

    }
    @FXML
    public void overView() throws IOException {
        scroll.setContent(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("jobFilter.fxml"))));
        bord.setCenter(scroll);
    }
}

