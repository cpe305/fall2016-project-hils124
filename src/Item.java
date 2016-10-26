public class Item {
  private String name;

  public Item() {
  }

  public Item(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void use() {
    System.out.println("Using " + this.getName() + "...");
  }
}
