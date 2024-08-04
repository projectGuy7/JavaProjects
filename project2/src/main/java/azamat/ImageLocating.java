package azamat;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ImageLocating { // 500
    private static final int inset = 10;
    private static double borderLine = inset;
    private static double prevBorderLine = inset;
    private static ArrayList<ImageView> images = new ArrayList<>();
    private static String folderPath = "C:\\Users\\Azamat\\Desktop\\Project#2\\Images\\Gallery\\";
    private static Pane imagePane = new Pane();
    private static int order = 0;



    public void generateInitialImagesBox(){
        addNewImage("Potato.jpg");
        addNewImage("Caesar.jpg");
        addNewImage("GreekSalad.jpg");
        imagePane.setStyle("-fx-background-color: transparent");
    }

    public static void addNewImage(String name){
        images.add(createImage(folderPath + name, 235));

        if(order % 2 == 0){
            images.get(order).setLayoutX(10);
            images.get(order).setLayoutY(borderLine);
            
            borderLine += images.get(order).getFitHeight() + inset;
            borderLine += 10;
            
            imagePane.getChildren().add(images.get(order));
        } else{
            images.get(order).setLayoutX(255);
            images.get(order).setLayoutY(prevBorderLine);
            if(images.get(order - 1).getFitHeight() < images.get(order).getFitHeight()){
                borderLine = images.get(order).getFitHeight() + inset + prevBorderLine;
                borderLine += 10;
            }
            prevBorderLine = borderLine;
            imagePane.getChildren().add(images.get(order));
        }
        order ++;
    }

    public Pane getImagePane(){
        return imagePane;
    }

    public static ImageView createImage(String path, int fitWidth){
        ImageView imgv = new ImageView();
        Image img;
        try {
            img = new Image(new FileInputStream(path));
            imgv.setImage(img);
            imgv.setFitWidth(fitWidth);
            imgv.setFitHeight(img.getHeight() / img.getWidth() * imgv.getFitWidth());
            imgv.setPreserveRatio(true);
        } catch (FileNotFoundException e) {
            
        }
        return imgv;
    }
}
