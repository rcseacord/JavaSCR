package ser05j;

import java.io.Serializable;

public class Bicycle implements Serializable {

  private static final long serialVersionUID = 5754104541168320730L;

  private int id;
  private String name;
  private int nbrWheels;

  public Bicycle(int id, String name, int nbrWheels) {
    this.id = id;
    this.name = name;
    this.nbrWheels = nbrWheels;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public int getNbrWheels() {
    return this.nbrWheels;
  }

  public void setNbrWheels(int nbrWheels) {
    this.nbrWheels = nbrWheels;
  }
}
