import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.scene.text.*;
import javafx.geometry.Insets;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Main extends Application{
    private FSM fsm = new FSM();
    private Text timer;
    private Timeline timeline;

    private void updateTimerLabel(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;
        timer.setText(String.format("%02d:%02d", minutes, seconds));
    }

    @Override
    public void start(Stage stage){
        Circle study1 = new Circle();
        study1.setRadius(5);
        study1.setStroke(Color.GRAY);
        study1.setFill(null);

        Circle study2 = new Circle();
        study2.setRadius(5);
        study2.setStroke(Color.GRAY);
        study2.setFill(null);

        Circle study3 = new Circle();
        study3.setRadius(5);
        study3.setStroke(Color.GRAY);
        study3.setFill(null);

        Circle study4 = new Circle();
        study4.setRadius(5);
        study4.setStroke(Color.GRAY);
        study4.setFill(null);

        HBox studyCircles = new HBox(10); // 10 Pixels of spacing between
        studyCircles.getChildren().addAll(study1, study2, study3, study4);
        studyCircles.setAlignment(Pos.CENTER);

        timer = new Text();
        timer.setFont(new Font(100));
        timer.setText("00:00");
        timer.setStroke(Color.BLACK);
        timer.setBoundsType(TextBoundsType.VISUAL);

        Text round = new Text();
        round.setFont(new Font(15));
        round.setText("Round: ");
        round.setStroke(Color.BLACK);
        round.setBoundsType(TextBoundsType.VISUAL);

        Text stateLabel = new Text();
        stateLabel.setFont(new Font(60));
        stateLabel.setText("WORK");
        stateLabel.setStroke(Color.BLACK);

        VBox root = new VBox(15);
        root.getChildren().addAll(stateLabel, round, timer, studyCircles);
        root.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Pomodoro Timer");
        stage.show();

        timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                if (fsm.getTime() > 0) {
                    int newTime = fsm.getTime() - 1;
                    updateTimerLabel(newTime);
                } else {
                    timeline.stop(); // stop when done
                    // maybe trigger next state
                }
            })
        );
    }

    public static void main(String args[]){
        launch(args);
    }
}