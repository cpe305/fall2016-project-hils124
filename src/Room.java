public class Room {

	private String name;
    private Wall leftWall;
    private Wall rightWall;
    private Wall frontWall;
    private Wall backWall;

    public Room() {}
    
    public Room(String name, Wall leftWall, Wall rightWall, Wall frontWall, Wall backWall) {
    	this.name = name;
    	this.leftWall = leftWall;
        this.rightWall = rightWall;
        this.frontWall = frontWall;
        this.backWall = backWall;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
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
}
