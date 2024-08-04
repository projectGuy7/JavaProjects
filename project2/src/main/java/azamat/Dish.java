package azamat;
import java.util.Collection;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Dish{
    private Rectangle highlightRect;;
    private StackPane stackPane;
    private String name;
    private String description;
    private int kgs;

    public Dish(String name, String description, int kgs){
        this.name = name;
        this.description = description;
        this.kgs = kgs;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getKgs() {
        return kgs;
    }

    public static Dish valueOf(String name, String description, int kgs){
        return new Dish(name, description, kgs);
    }

    public StackPane getDishBox(){
        Label descrLabel = new Label(description);
        Text nameText = new Text(name);
        Text kgsText = new Text("KGS " + kgs);

        descrLabel.setFont(Font.font("Mogra", 24));
        nameText.setFont(Font.font("Mogra", 36));
        kgsText.setFont(Font.font("Mogra", 18));

        Rectangle borderRect = new Rectangle(0, 0, 500, 200);
        borderRect.setFill(Paint.valueOf("ff0000"));
        borderRect.setArcHeight(10);
        borderRect.setArcWidth(10);
        
        Rectangle insideRect = new Rectangle(0, 0, 485, 185);
        insideRect.setFill(Paint.valueOf("ffc71e"));
        insideRect.setArcHeight(10);
        insideRect.setArcWidth(10);

        descrLabel.setPrefWidth(insideRect.getWidth() - 10);
        descrLabel.setPrefHeight(insideRect.getHeight() - 60);
        descrLabel.setWrapText(true);
        descrLabel.setAlignment(Pos.TOP_LEFT);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(nameText, descrLabel, kgsText);
        vbox.setAlignment(Pos.CENTER);
        stackPane = new StackPane();
        stackPane.getChildren().addAll(borderRect, insideRect, vbox);
        stackPane.setAlignment(Pos.CENTER);

        stackPane.setOnMouseEntered(this::mouseEntered);
        stackPane.setOnMouseExited(this::mouseExited);

        return stackPane;
    }

    public void mouseEntered(MouseEvent e){
        highlightRect = new Rectangle(0, 0, 500, 200);
        highlightRect.setFill(Paint.valueOf("f2831c"));
        highlightRect.setOpacity(0.3);
        highlightRect.setArcHeight(10);
        highlightRect.setArcWidth(10);
        stackPane.getChildren().add(highlightRect);
    }

    public void mouseExited(MouseEvent e){
        stackPane.getChildren().remove(highlightRect);
    }

    @Override
    public boolean equals(Object o){
        if(this.name.equals(((Dish) o).getName()) && this.description.equals(((Dish) o).getDescription()) && this.kgs == ((Dish) o).getKgs()){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        int result = 0;
        for(char character: name.toCharArray()){
            result += character;
        }
        for(char character: description.toCharArray()){
            result += character;
        }
        result += kgs;
        return result;
    }
}
