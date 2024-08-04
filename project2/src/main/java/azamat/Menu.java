package azamat;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Menu {
    static HashSet<Dish> starters = new HashSet<>();
    static HashSet<Dish> salads = new HashSet<>();
    static HashSet<Dish> soups = new HashSet<>();
    static VBox vbox;

    public void generateMenu() throws FileNotFoundException{
        StackPane startersPane = getTitle("Starters");
        StackPane saladsPane = getTitle("Salads");
        StackPane soupsPane = getTitle("Soups");

        Text menu = new Text("Menu");
        menu.setFont(Font.font("Mogra", 60));
        menu.setFill(Paint.valueOf("ffc71e"));

        vbox = new VBox(10);
        vbox.getChildren().addAll(menu, startersPane);

        addAllArrayList(starters,
        Dish.valueOf("Potato", "Just a Potato fries", 150),
        Dish.valueOf("Kotelette", "Kotelette with mashed potatos", 300),
        Dish.valueOf("Potato #2", "Just a Potato fries #2", 150));

        for(Dish starter: starters){
            StackPane dishBox = starter.getDishBox();
            
            vbox.getChildren().add(dishBox);
        }

        addAllArrayList(salads,
        Dish.valueOf("Greece Salad", "Cheese, Olives, Lettuce - all you need for happiness", 100),
        Dish.valueOf("Caesar", "Veni, Vidi, Vici", 120),
        Dish.valueOf("Chicken Salad", "Meth is not included", 200));

        vbox.getChildren().add(saladsPane);

        for(Dish salad: salads){
            StackPane dishBox = salad.getDishBox();
            
            vbox.getChildren().add(dishBox);
        }

        addAllArrayList(soups,
        Dish.valueOf("Nauryz kozhe", "7 ingredients (don't be concerned how we make that soup with fastfood ingredients)", 500),
        Dish.valueOf("Chicken Soup", "Ingredients don't really differ from that spring soup", 700),
        Dish.valueOf("Soup from soup", "Don't ask", 100));

        vbox.getChildren().add(soupsPane);

        for(Dish soup: soups){
            StackPane dishBox = soup.getDishBox();

            vbox.getChildren().add(dishBox);
        }

        vbox.setAlignment(Pos.CENTER);
        VBox.setMargin(startersPane, new Insets(5, 0, 0, 0));
        VBox.setMargin(menu, new Insets(25, 0, 0, 0));
    }
    
    public void addPosition(String name, String description, int kgs, String group){
        if(group.equals("Starters")){
            starters.add(Dish.valueOf(name, description, kgs));
        } else if(group.equals("Salads")){
            salads.add(Dish.valueOf(name, description, kgs));
        } else if(group.equals("Soups")){
            soups.add(Dish.valueOf(name, description, kgs));
        }
        try {
            generateMenu();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addNewPosition(String name, String description, int kgs, String type){
        switch(type){
            case "Starters":
                starters.add(Dish.valueOf(name, description, kgs));
                break;
            case "Salads":
                salads.add(Dish.valueOf(name, description, kgs));
                break;
            case "Soups":
                soups.add(Dish.valueOf(name, description, kgs));
                break;
        }
    }

    public VBox getMenu(){
        try {
            generateMenu();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return vbox;
    }

    public static <T> Collection<? super T> addAllArrayList(Collection<? super T> c, T... elements){ // CoMHuTeJIbHO
        for(T element: elements){
            c.add(element);
        }

        return c;
    }

    public static StackPane getTitle(String title) throws FileNotFoundException{
        ImageView imgv = new ImageView();
        Image img = new Image(new FileInputStream("C:/Users/Azamat/Desktop/Project#2/Images/GroupedDishes.png"));

        imgv.setImage(img);
        imgv.setFitWidth(500);
        imgv.setPreserveRatio(true);

        Text text = new Text(title);
        text.setFont(Font.font("Mogra", 48));
        text.setFill(Paint.valueOf("79f000"));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imgv, text);

        return stackPane;
    }
}
