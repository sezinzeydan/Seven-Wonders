package Client.view;

import Server.model.Card;
import Server.model.Player;
import javafx.scene.layout.BorderPane;
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

    public GamePane(Player player, Card[] cards, Player left, Player right) {
        /* TODO() remove player by implementing a way to read it from client itself */

        allOpponentsPane = new AllOpponentsPane(left, right);
        playerInfoPane = new PlayerInfoPane(player);
        System.out.println("GAME");
        cardActionPane = new CardActionPane(cards);
        resourcePaneLeft = new ResourcePane();
        resourcePaneRight = new ResourcePane();
        setPrefSize(1300, 750);
        setTop(allOpponentsPane);
        setBottom(playerInfoPane);
        setLeft(resourcePaneLeft);
        setRight(resourcePaneRight);
        setCenter(cardActionPane);
        System.out.println("GAMNEPANE SON");

    }

    public void update() {
        playerInfoPane.update();
    }

}
