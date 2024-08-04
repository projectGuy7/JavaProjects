package azamat;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminConsoleBox{
    private Stage checkBoxStage;
    private Stage adminConsoleStage;
    private Scene checkBoxScene;
    private Scene adminConsoleScene;
    private VBox checkBoxVBox;
    private Button confirmButton;
    private PasswordField passwordTextField;
    private FXMLLoader adminFXML;
    private Parent adminParent;


    public AdminConsoleBox(){
        confirmButton = new Button("Confirm");
        confirmButton.setStyle("-fx-background-color: rgb(250, 1, 1)");
        confirmButton.setPrefSize(100, 25);
        checkBoxVBox = new VBox(10);
        passwordTextField = new PasswordField();
        passwordTextField.setStyle("-fx-background-color: rgb(243, 190, 29); -fx-text-fill: rgb(250, 1, 1)");
        checkBoxVBox.getChildren().addAll(passwordTextField, confirmButton);
        checkBoxVBox.setStyle("-fx-background-color: rgb(121, 240, 0)");
        checkBoxVBox.setAlignment(Pos.CENTER);
        checkBoxScene = new Scene(checkBoxVBox, 150, 100);
        checkBoxStage = new Stage();
        checkBoxStage.initStyle(StageStyle.TRANSPARENT);
        checkBoxStage.setScene(checkBoxScene);

        confirmButton.setOnAction(this::confirmButtonIsClicked);

        adminFXML = new FXMLLoader(getClass().getResource("AdminConsole.fxml"));
        try {
            adminParent = (Parent) adminFXML.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        adminConsoleScene = new Scene(adminParent);
        adminConsoleStage = new Stage();
        adminConsoleStage.setScene(adminConsoleScene);
        adminConsoleStage.initModality(Modality.APPLICATION_MODAL);
    }

    void showCheckBox(){
        checkBoxStage.show();
    }

    void confirmButtonIsClicked(ActionEvent event){
        checkBoxStage.close();
        if(passwordTextField.getText().equals("LosPollos")){
            adminConsoleStage.show();
        }
        passwordTextField.clear();
    }
}
