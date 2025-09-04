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
        study1.setStroke(Color.web("0xa36361"));
        study1.setFill(null);
        study1.getStyleClass().add("study1");

        study2 = new Circle();
        study2.setRadius(5);
        study2.setStroke(Color.web("0xa36361"));
        study2.setFill(null);
        study2.getStyleClass().add("study2");


        study3 = new Circle();
        study3.setRadius(5);
        study3.setStroke(Color.web("0xa36361"));
        study3.setFill(null);
        study3.getStyleClass().add("study3");


        study4 = new Circle();
        study4.setRadius(5);
        study4.setStroke(Color.web("0xa36361"));
        study4.setFill(null);
        study4.getStyleClass().add("study4");


        HBox studyCircles = new HBox(10); // 10 Pixels of spacing between
        studyCircles.getChildren().addAll(study1, study2, study3, study4);
        studyCircles.setAlignment(Pos.CENTER);

        timer = new Text();
        timer.setFont(new Font(100));
        timer.setText("00:00");
        timer.setBoundsType(TextBoundsType.VISUAL);
        timer.getStyleClass().add("timer");

        round = new Text();
        round.setFont(new Font(15));
        round.setText("Round 1");
        round.setStroke(Color.BLACK);
        round.setBoundsType(TextBoundsType.VISUAL);

        stateLabel = new Text();
        stateLabel.setFont(new Font(60));
        stateLabel.setText("WORK");
        stateLabel.setFill(Color.web("0xa36361"));
        stateLabel.setStroke(Color.web("0xa36361"));

        start = new Button("Start");
        start.getStyleClass().add("start");

        VBox root = new VBox(15);
        root.getChildren().addAll(stateLabel, round, timer, studyCircles, start);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("root");
        
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());;
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
        round.setText("Round " + fsm.getWholeCounter());
        remainingTime = fsm.getTime();
        if (fsm.getCurrentName().equals("WORK"))
            switch (fsm.getRoundCounter()){
                case 1: 
                    study1.setFill(Color.web("0xbf6d6d"));
                    study2.setFill(null);
                    study3.setFill(null);
                    study4.setFill(null);
                    break;
                case 2:
                    study2.setFill(Color.web("0xbf6d6d"));
                    break;
                case 3:
                    study3.setFill(Color.web("0xbf6d6d"));
                    break;
                case 4:
                    study4.setFill(Color.web("0xbf6d6d"));
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