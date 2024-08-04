package azamat;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class App extends Application{
    private static ScrollPane scrollPane;
    private static Pane mainPane;
    private static Scene scene;
    private static Stage stage;
    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws FileNotFoundException, IOException{
        scrollPane = new ScrollPane();
        scrollPane.setMaxHeight(700);
        scrollPane.setMinWidth(550);
        scrollPane.setLayoutX(50);
        scrollPane.setLayoutY(32);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        
        FXMLLoader hBar = new FXMLLoader(getClass().getResource("HBar.fxml"));
        Parent hBarRoot = (Parent) hBar.load();
        HBarController hBarController = hBar.getController();

        mainPane = new Pane();
        mainPane.getChildren().addAll(hBarController.getHBar(), scrollPane);
        mainPane.setStyle("-fx-background-color: #79f000; -fx-background-radius: 15;");

        String css = this.getClass().getResource("Styles.css").toExternalForm(); // css link
        scene = new Scene(mainPane, 600, 750);
        scene.getStylesheets().add(css); // css addition
        scene.setFill(Color.TRANSPARENT);
        stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static ScrollPane getScrollPane(){
        return scrollPane;
    }

    public static void setPage(Node node){
        if(scrollPane.getContent() == null || !scrollPane.getContent().equals(node)){
            scrollPane.setContent(node);
        }
    }

    public static void closeStage(){
        stage.close();
    }
}