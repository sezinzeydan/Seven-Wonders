package view;

import javafx.scene.layout.BorderPane;
import model.Player;
//import javafx.scene.layout.VBox;

public class GamePane extends BorderPane {
    //    static GamePane gamePane;
    //public Button startButton = new Button("dknfs");
    public AllOpponentsPane allOpponentsPane;
    public PlayerInfoPane playerInfoPane;
    public CardActionPane cardActionPane;
    public ResourcePane resourcePaneLeft;
    public ResourcePane resourcePaneRight;


//    public static GamePane getInstance(){
//        if(gamePane == null){
//            gamePane = new GamePane();
//        }
//        return gamePane;
//    }

    public GamePane(Player player) {
        allOpponentsPane = new AllOpponentsPane(player);
        playerInfoPane = new PlayerInfoPane(player);
        cardActionPane = new CardActionPane();
        resourcePaneLeft = new ResourcePane();
        resourcePaneRight = new ResourcePane();
        setPrefSize(1300, 750);
        setTop(allOpponentsPane);
        setBottom(playerInfoPane);
        setLeft(resourcePaneLeft);
        setRight(resourcePaneRight);
        setCenter(cardActionPane);

    }

    public void update() {
        playerInfoPane.update();
    }

}
