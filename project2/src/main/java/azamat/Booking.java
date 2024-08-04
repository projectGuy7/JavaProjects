package azamat;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class Booking implements Initializable{
    EmailSender ea = new EmailSender();
    @FXML
    private StackPane confirmPane;

    @FXML
    private TextField dateTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private ChoiceBox<String> hoursChoiceBox;

    @FXML
    private ChoiceBox<String> minutesChoiceBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private ChoiceBox<String> sizeChoiceBox;

    @FXML
    private TextArea specialRequestsChoiceBox;

    @FXML
    void confirmPanePressed(MouseEvent event) {
        ea.setName(nameTextField.getText());
        ea.setDate(dateTextField.getText());
        ea.setEmail(emailTextField.getText());
        ea.setTime(hoursChoiceBox.getValue() + ":" + minutesChoiceBox.getValue());
        ea.setSize(sizeChoiceBox.getValue());
        ea.setSpecialRequests(specialRequestsChoiceBox.getText());

        ea.sendMessage();
    }

    private String[] listeners = new String[3]; // hours, minutes, size
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hoursChoiceBox.setItems(FXCollections.observableArrayList(("8,9,10,11,12,13,14,15,16,17,18,19,20,21,22").split(",")));
        minutesChoiceBox.setItems(FXCollections.observableArrayList(("15,30,45").split(",")));
        sizeChoiceBox.setItems(FXCollections.observableArrayList(("1 person,2 people,3 people,4 people,5 people,5+ people").split(",")));
        
        hoursChoiceBox.setOnAction(e -> listeners[0] = hoursChoiceBox.getValue());
        minutesChoiceBox.setOnAction(e -> listeners[1] = minutesChoiceBox.getValue());
        sizeChoiceBox.setOnAction(e -> listeners[2] = sizeChoiceBox.getValue());
    }
}

