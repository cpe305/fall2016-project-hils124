public class Room {

    private Wall leftWall;
    private Wall rightWall;
    private Wall frontWall;
    private Wall backWall;
    private String name;

    public Room(String name, Wall leftWall, Wall rightWall, Wall frontWall, Wall backWall) {
        this.leftWall = leftWall;
        this.rightWall = rightWall;
        this.frontWall = frontWall;
        this.backWall = backWall;
        this.name = name;
    }
    
    public void setLeftWall(Wall wall) {
        this.leftWall = wall;
    }

    public Wall getLeftWall() {
        return this.leftWall;
    }

    public void setRightWall(Wall wall) {
        this.rightWall = wall;
    }

    public Wall getRightWall() {
        return this.rightWall;
    }

    public void setFrontWall(Wall wall) {
        this.frontWall = wall;
    }

    public Wall getFrontWall() {
        return this.frontWall;
    }

    public void setBackWall(Wall wall) {
        this.backWall = wall;
    }

    public Wall getBackWall() {
        return this.backWall;
    }

    public void setRoomName(String name) {
        this.name = name;
    }
    public String getRoomName() {
        return this.name;
    }
}
