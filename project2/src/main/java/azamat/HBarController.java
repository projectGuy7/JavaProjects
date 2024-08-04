package azamat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HBarController implements Initializable{
    Menu menu;
    ImageLocating imageLocating;
    FXMLLoader fxmlBooking;
    Parent booking;
    AdminConsoleBox adminConsole;

    public HBarController(){
        menu = new Menu();
        imageLocating = new ImageLocating();
        fxmlBooking = new FXMLLoader(getClass().getResource("Booking.fxml"));
        adminConsole = new AdminConsoleBox();
        try {
            booking = (Parent) fxmlBooking.load();
        } catch (IOException e) {

        }
    }

    @FXML StackPane adminStackPane;

    @FXML
    private Circle adminCircle;

    @FXML
    private Label bookingLabel;

    @FXML
    private Label contactLabel;

    @FXML
    private Label exitLabel;

    @FXML
    private Label galleryLabel;

    @FXML
    private HBox hBar;

    @FXML
    private Label menuLabel;

    @FXML
    void adminStackPaneClicked(MouseEvent event) {
        adminConsole.showCheckBox();
    }

    @FXML
    void bookingLabelClicked(MouseEvent event) {
        App.getScrollPane().setLayoutX(0);
        App.setPage(booking);
    }

    @FXML
    void contactLabelClicked(MouseEvent event) throws IOException{
        App.getScrollPane().setLayoutX(0);
        FXMLLoader hBar = new FXMLLoader(getClass().getResource("contactUs.fxml"));
        Parent hBarRoot = (Parent) hBar.load();
        App.setPage(hBarRoot);
    }

    @FXML
    void exitLabelClicked(MouseEvent event) {
        App.closeStage();
    }

    @FXML
    void galleryLabelClicked(MouseEvent event) {
        App.getScrollPane().setLayoutX(50);
        App.setPage(imageLocating.getImagePane());
    }

    @FXML
    void menuLabelClicked(MouseEvent event) {
        App.getScrollPane().setLayoutX(50);
        App.setPage(menu.getMenu());
    }

    HBox getHBar(){
        return hBar;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            menu.generateMenu();
            imageLocating.generateInitialImagesBox();
        } catch (FileNotFoundException e) {
            
        }
    }

}
