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
import javafx.application.Platform;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Main extends Application{
    private FSM fsm = new FSM();
    private boolean running[] = {false};
    private Timeline currentTimeline;
    private int remainingTime;
    private boolean started[] = {false};

    private Circle study1;
    private Circle study2;
    private Circle study3;
    private Circle study4;
    private Text timer;
    private Text round;
    private Text stateLabel;
    private Button start;

    private void updateTimerLabel(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;
        timer.setText(String.format("%02d:%02d", minutes, seconds));
    }

    @Override
    public void start(Stage stage){
        study1 = new Circle();
        study1.setRadius(5);
        study1.setStroke(Color.GRAY);
        study1.setFill(null);

        study2 = new Circle();
        study2.setRadius(5);
        study2.setStroke(Color.GRAY);
        study2.setFill(null);

        study3 = new Circle();
        study3.setRadius(5);
        study3.setStroke(Color.GRAY);
        study3.setFill(null);

        study4 = new Circle();
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

        round = new Text();
        round.setFont(new Font(15));
        round.setText("Round: 1");
        round.setStroke(Color.BLACK);
        round.setBoundsType(TextBoundsType.VISUAL);

        stateLabel = new Text();
        stateLabel.setFont(new Font(60));
        stateLabel.setText("WORK");
        stateLabel.setStroke(Color.BLACK);

        start = new Button("Start");

        VBox root = new VBox(15);
        root.getChildren().addAll(stateLabel, round, timer, studyCircles, start);
        root.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Pomodoro Timer");
        stage.show();

        start.setOnAction(e -> {
            running[0] = !running[0];
            if (!started[0]){
                started[0] = true;
                startCountdown();
            }
            if (running[0]){
                start.setText("Pause");
            }else{
                start.setText("Play");
            }
        });
    }

    private void startCountdown(){
        stateLabel.setText(fsm.getCurrentName());
        remainingTime = fsm.getTime();
        if (fsm.getCurrentName().equals("WORK"))
            switch (fsm.getRoundCounter()){
                case 1: 
                    study1.setFill(Color.BLACK);
                    study2.setFill(null);
                    study3.setFill(null);
                    study4.setFill(null);
                    break;
                case 2:
                    study2.setFill(Color.BLACK);
                    break;
                case 3:
                    study3.setFill(Color.BLACK);
                    break;
                case 4:
                    study4.setFill(Color.BLACK);
                    break;
            }

        currentTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {                    
            if (running[0]){
                if (remainingTime == -1) {
                    currentTimeline.stop();
                    currentTimeline = null;
                    
                    fsm.setNextState();
                    startCountdown();
                }
                timer.setText(String.format("%02d:%02d", remainingTime/60, remainingTime%60)); 
                remainingTime--;
            }
        }));

        currentTimeline.setCycleCount(Timeline.INDEFINITE);
        currentTimeline.play();

    }   

    public static void main(String args[]){
        launch(args);
    }
}