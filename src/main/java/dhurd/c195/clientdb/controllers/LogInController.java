package dhurd.c195.clientdb.controllers;

import dhurd.c195.clientdb.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LogInController implements Initializable {
    public Label loginScreenLbl;
    public Label currentLocLbl;
    public Label LocationLbl;
    public Label userNameLbl;
    public Label passwordLbl;
    public TextField userNameText;
    public TextField passwordText;
    public Button loginButton;
    public Label currentTimeLbl;
    public Label timeLbl;

    private ResourceBundle resourceBundle;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        resourceBundle = ResourceBundle.getBundle("Login", Locale.getDefault());
        loginScreenLbl.setText(resourceBundle.getString("schedule"));
        currentLocLbl.setText(resourceBundle.getString("location"));
        LocationLbl.setText(resourceBundle.getString("current_location"));
        userNameLbl.setText(resourceBundle.getString("username"));
        passwordLbl.setText(resourceBundle.getString("password"));
        loginButton.setText(resourceBundle.getString("submit"));
        currentTimeLbl.setText(resourceBundle.getString("time_zone"));
        timeLbl.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));





    }
    public void loginAttempt(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = (Stage) loginScreenLbl.getScene().getWindow();
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();

    }
}
