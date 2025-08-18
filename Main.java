import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class Main extends Application{
    public void start(Stage stage){
        stage.setTitle("Pomodoro Timer");
        stage.show();
    }

    public static void main(String args[]){
        launch(args);
    }
}