package Server.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConflictPoint extends Item {

    public ConflictPoint(int warPoint) {
        image = new Image("militaryPower.png");
        iv = new ImageView();
        iv.setImage(image);
        noOfItems = warPoint;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    void setNoOfItems(int warPoint) {
        noOfItems = noOfItems + warPoint;
    }
}