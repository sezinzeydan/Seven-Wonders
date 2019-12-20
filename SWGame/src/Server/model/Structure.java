package Server.model;

public class Structure extends Item {
    String type;

    public Structure(String type) {
        name = "type";
        this.type = type;
        noOfItems++;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    void setNoOfItems(int no) {

    }
}