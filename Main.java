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
    private boolean settingsOpened[] = {false};

    private Work work = new Work();
    private ShortBreak sb = new ShortBreak();
    private LongBreak lb = new LongBreak();

    private Circle study1;
    private Circle study2;
    private Circle study3;
    private Circle study4;
    private Text timer;
    private Label round;
    private Label stateLabel;
    private Button start;
    private VBox settingsScene;

    @Override
    public void start(Stage stage){
        // Circles that indicate which stage of work you are within the round
        study1 = new Circle();
        study1.setRadius(5);
        study1.setStroke(Color.web("#647FBC"));
        study1.setFill(null);
        study1.getStyleClass().add("study1");

        study2 = new Circle();
        study2.setRadius(5);
        study2.setStroke(Color.web("#647FBC"));
        study2.setFill(null);
        study2.getStyleClass().add("study2");

        study3 = new Circle();
        study3.setRadius(5);
        study3.setStroke(Color.web("#647FBC"));
        study3.setFill(null);
        study3.getStyleClass().add("study3");

        study4 = new Circle();
        study4.setRadius(5);
        study4.setStroke(Color.web("#647FBC"));
        study4.setFill(null);
        study4.getStyleClass().add("study4");

        // Placing them in a row
        HBox studyCircles = new HBox(10); // 10 Pixels of spacing between
        studyCircles.getChildren().addAll(study1, study2, study3, study4);
        studyCircles.setAlignment(Pos.CENTER);

        // Central timer
        timer = new Text();
        timer.setFont(new Font(100));
        int time = fsm.getTime();
        if (time < 3600){
            timer.setText(String.format("%02d:%02d", time/60, time%60)); 
        } else {
            timer.setText(String.format("%02d:%02d:%02d", time/3600, (time%3600) / 60, (time%60)));               
        }
        timer.setStroke(Color.web("#FAFDD6"));
        timer.setFill(Color.web("#FAFDD6"));
        timer.setBoundsType(TextBoundsType.VISUAL);
        timer.getStyleClass().add("timer");
        
        // States which round we are in
        round = new Label();
        round.setText("Round 1");
        round.setTextFill(Color.web("#FAFDD6"));
        round.getStyleClass().add("round");

        // States whether we are studying, or on a break
        stateLabel = new Label();
        stateLabel.setText("WORK");
        stateLabel.setTextFill(Color.web("#647FBC"));
        stateLabel.getStyleClass().add("stateLabel");

        // Start button that will also become play and pause
        start = new Button("Start");
        start.getStyleClass().add("start");

        // Populating the settings menu
        Label settingsTitle = new Label("Settings");
        settingsTitle.setTextFill(Color.web("#FAFDD6"));
        settingsTitle.getStyleClass().add("settingsTitle");

         // Settings button that adds settings overlay
        Button settings = new Button("settings");
        settings.setTextFill(Color.web("#FAFDD6"));
        settings.getStyleClass().add("settings");

        // Time selector in settings
        // Work
        Label workTime = new Label("Select work duration: ");
        workTime.getStyleClass().add("enterTimeLabel");
        Label workHours = new Label("Hours:");
        Spinner<Integer> workHoursSelector = new Spinner<>(0, 59, 0); // min, max, inital
        workHoursSelector.setPrefSize(60, 25);
        workHoursSelector.setEditable(true); // Allows typing
        Label workMins = new Label("Mins:");
        Spinner<Integer> workMinutesSelector = new Spinner<>(0, 59, 25); // min, max, inital
        workMinutesSelector.setPrefSize(60, 25);
        workMinutesSelector.setEditable(true);

        HBox workSpinners = new HBox();
        workSpinners.getChildren().addAll(workTime, workHours, workHoursSelector, workMins, workMinutesSelector);
        workSpinners.setAlignment(Pos.CENTER);

        // Short Break
        Label sbTime = new Label("Select short break duration: ");
        sbTime.getStyleClass().add("enterTimeLabel");
        Label sbHours = new Label("Hours:");
        Spinner<Integer> sbHoursSelector = new Spinner<>(0, 59, 0); // min, max, inital
        sbHoursSelector.setPrefSize(60, 25);
        sbHoursSelector.setEditable(true);
        Label sbMins = new Label("Mins:");
        Spinner<Integer> sbMinutesSelector = new Spinner<>(0, 59, 5); // min, max, inital
        sbMinutesSelector.setPrefSize(60, 25);
        sbMinutesSelector.setEditable(true);

        HBox sbSpinners = new HBox();
        sbSpinners.getChildren().addAll(sbTime, sbHours, sbHoursSelector, sbMins, sbMinutesSelector);
        sbSpinners.setAlignment(Pos.CENTER);

        // Long Break
        Label lbTime = new Label("Select long break duration: ");
        lbTime.getStyleClass().add("enterTimeLabel");
        Label lbHours = new Label("Hours:");
        Spinner<Integer> lbHoursSelector = new Spinner<>(0, 59, 0); // min, max, inital
        lbHoursSelector.setPrefSize(60, 25);
        lbHoursSelector.setEditable(true);
        Label lbMins = new Label("Mins:");
        Spinner<Integer> lbMinutesSelector = new Spinner<>(0, 59, 15); // min, max, inital
        lbMinutesSelector.setPrefSize(60, 25);
        lbMinutesSelector.setEditable(true);

        HBox lbSpinners = new HBox();
        lbSpinners.getChildren().addAll(lbTime, lbHours, lbHoursSelector, lbMins, lbMinutesSelector);
        lbSpinners.setAlignment(Pos.CENTER);

        // Button to save changes
        Button saveTime = new Button("Save Changes");
        saveTime.getStyleClass().add("saveTime");
        
        saveTime.setOnAction(e -> {
            // Setting new values
            int workTotal = workHoursSelector.getValue()*3600 + workMinutesSelector.getValue()*60;
            int sbTotal = sbHoursSelector.getValue()*3600 + sbMinutesSelector.getValue()*60;
            int lbTotal = lbHoursSelector.getValue()*3600 + lbMinutesSelector.getValue()*60;
            fsm.setTime(workTotal, work);
            fsm.setTime(sbTotal, sb);
            fsm.setTime(lbTotal, lb);

            // Resetting format
            fsm.setCurrentState(work);
            stateLabel.setText(fsm.getCurrentName());
            fsm.setWholeCounter(1);
            round.setText("Round " + fsm.getWholeCounter());
            int newTime = fsm.getTime();
            if (newTime < 3600){
                timer.setText(String.format("%02d:%02d", newTime/60, newTime%60)); 
            } else {
                timer.setText(String.format("%02d:%02d:%02d", newTime/3600, (newTime%3600) / 60, (newTime%60)));               
            }
            study1.setFill(null);
            study2.setFill(null);
            study3.setFill(null);
            study4.setFill(null);
            
            // Resetting defualt states
            running[0] = false;
            started[0] = false;
            settingsOpened[0] = false;
            settingsScene.setVisible(false);
            settings.setText("settings");
            settings.setTextFill(Color.web("#FAFDD6"));
        });

        // Settings menu that will pop up on button press
        settingsScene = new VBox(15);
        settingsScene.setVisible(false);
        settingsScene.getChildren().addAll(settingsTitle, workSpinners, sbSpinners, lbSpinners, saveTime);
        settingsScene.setMinSize(450, 300);
        settingsScene.setMaxSize(450, 300);
        settingsScene.setAlignment(Pos.TOP_CENTER);
        settingsScene.getStyleClass().add("settingsScene");

        // Opening of the settings button
        settings.setOnAction(e -> {
            settingsOpened[0] = !settingsOpened[0];
            if (settingsOpened[0]){
                settingsScene.setVisible(true);
                settings.setText("close");
                settings.setTextFill(Color.web("#647FBC"));
            } else {
                settingsScene.setVisible(false);
                settings.setText("settings");
                settings.setTextFill(Color.web("#FAFDD6"));
            }
        });

        VBox main = new VBox(15);
        main.getChildren().addAll(stateLabel, round, timer, studyCircles, start);
        main.setAlignment(Pos.CENTER);
        StackPane root = new StackPane();
        root.getChildren().addAll(main, settingsScene, settings);
        StackPane.setAlignment(settings, Pos.TOP_RIGHT);
        root.getStyleClass().add("root");
        
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());;
        stage.setScene(scene);
        stage.setTitle("Pomodoro Timer");
        stage.show();

        // Toggles the pause and play
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
        // Handles start and circle filling
        stateLabel.setText(fsm.getCurrentName());
        round.setText("Round " + fsm.getWholeCounter());
        remainingTime = fsm.getTime();
        if (fsm.getCurrentName().equals("WORK"))
            switch (fsm.getRoundCounter()){
                case 1: 
                    study1.setFill(Color.web("#647FBC"));
                    study2.setFill(null);
                    study3.setFill(null);
                    study4.setFill(null);
                    break;
                case 2:
                    study2.setFill(Color.web("#647FBC"));
                    break;
                case 3:
                    study3.setFill(Color.web("#647FBC"));
                    break;
                case 4:
                    study4.setFill(Color.web("#647FBC"));
                    break;
            }
        
        // Handles countdown logic
        currentTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {                    
            if (running[0]){
                if (remainingTime == -1) {
                    currentTimeline.stop();
                    currentTimeline = null;
                    
                    fsm.setNextState();
                    startCountdown();
                }
                if (remainingTime < 3600){
                    timer.setText(String.format("%02d:%02d", remainingTime/60, remainingTime%60)); 
                } else {
                    timer.setText(String.format("%02d:%02d:%02d", remainingTime/3600, (remainingTime%3600) / 60, (remainingTime%60)));               
                }
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