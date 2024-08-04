package azamat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class adminConsoleController {

    @FXML
    private TextField KGSfield;

    @FXML
    private Button addButton;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField pathField;

    @FXML
    private TextField typeField;

    @FXML
    void addButtonClicked(ActionEvent event) {
        Menu.addNewPosition(nameField.getText().trim(), descriptionField.getText().trim(), Integer.parseInt(KGSfield.getText().trim()), typeField.getText().trim());
        ImageLocating.addNewImage(pathField.getText());
    }

}
