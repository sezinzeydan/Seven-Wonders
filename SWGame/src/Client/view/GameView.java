package Client.view;

import Server.model.Player;
import Server.model.WonderBoard;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameView {
    private static final GameView gameView = new GameView();

    Stage htpPopUp = new Stage();
    Stage creditsPopUp = new Stage();
    Stage primaryStage;
    Scene scene;

    private GameView() {
        System.out.println("Game View");
        htpPopUp.setScene(new Scene(new HowToPlayPane()));
        htpPopUp.initModality((Modality.APPLICATION_MODAL));
        creditsPopUp.setScene(new Scene(new CreditsPane()));
        creditsPopUp.initModality(Modality.APPLICATION_MODAL);
        //gamePane = GamePane.getInstance();
        //mainMenuPane = MainMenuPane.getInstance();
        //nextTurnPane = new NextTurnPane();
        //creditsPane = new CreditsPane();
        //howToPlayPane = new HowToPlayPane();
    }

    public static GameView getInstance() {
//        if(gameView == null){
//            gameView = new GameView();
//        }
//        System.out.println("View Instance");
        return gameView;
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
    }


    public void showMainMenu() {
        MainMenuPane mainMenu = MainMenuPane.getInstance();
        scene = new Scene(mainMenu, 1300, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void showEnterKeyScreen() {

    }

    public void waitingScreen() {

        NextTurnPane nextTurn = new NextTurnPane();

        scene = new Scene(nextTurn, 1300, 750);
        System.out.println("before show");
        primaryStage.setScene(scene);
        System.out.println("before show");
        primaryStage.show();
        System.out.println("after show");
    }

    public void showConflictScreen(ArrayList<Player> playerList) {
    }

    public void showCredits() {
        creditsPopUp.showAndWait();
    }

    public void showHowToPlay() {
        htpPopUp.showAndWait();
    }

    public void showUpdatedWonderPane(ArrayList<WonderBoard> wonders) {

    }

    public void showGameOverPane(ArrayList<Player> playerList) {
    }

    public void showGamePane(Player player) throws IllegalStateException {
        try {
            System.out.println("showGamePane in GameView");
            //amePane gamePane = new GamePane(player);

            System.out.println("after creation of GamePane");
            //Scene sc = new Scene(gamePane);
            System.out.println("sout1");
            Platform.runLater(() -> primaryStage.setScene(new Scene(new GamePane(new Player()))));
            System.out.println("sout2");
            Platform.runLater(() -> primaryStage.show());
            System.out.println("sout3");
        } catch (IllegalStateException ex) {
            System.out.println("exception");
        }
    }
}