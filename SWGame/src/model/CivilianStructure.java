package model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class CivilianStructure extends Card {

    VictoryPoints victoryPoints;
    Structure requiredStructure;
    Structure providedStructure;
    Resource[] requiredProduct;
    Image image;
    ImageView iv;

    /*Constructor for Civilian Cards which require more than one resources*/
    public CivilianStructure( int vPoints, String rStructure, String pStructure, String[] rProductType,  int[] rProductNo, String img){

        image = new Image(img);
        iv = new ImageView();
        iv.setImage(image);

        victoryPoints = new VictoryPoints(vPoints);
        requiredStructure = new Structure(rStructure);
        providedStructure = new Structure(pStructure);
        requiredProduct = new Resource[rProductType.length];

        for (int i = 0; i < rProductType.length; i++ ){
                Resource r = new Resource(rProductType[i], rProductNo[i]);
                requiredProduct[i] = r;
        }

    }

    /*Constructor for Civilian Cards which require only one resource */
    public CivilianStructure( int vPoints, String rStructure, String pStructure, String resName,  int resNo, String img){

        image = new Image(img);
        iv = new ImageView();
        iv.setImage(image);
        iv.setFitHeight(100);
        iv.setFitWidth(65);
        victoryPoints = new VictoryPoints(vPoints);
        requiredStructure = new Structure(rStructure);
        providedStructure = new Structure(pStructure);
        requiredProduct = new Resource[1];
        requiredProduct[0] =new Resource(resName,  resNo);

    }

    @Override
    void constructCard() {

    }
}