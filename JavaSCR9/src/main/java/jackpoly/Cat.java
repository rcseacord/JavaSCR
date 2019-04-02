package jackpoly;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class Cat extends Animal {

  @JsonProperty
  public Byte[] extra_memory;

  @JsonCreator
  public Cat(@JsonProperty("name") String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getSound() {
    return "trumpet";
  }

  public String getType() {
    return "herbivorous";
  }

  public boolean isEndangered() {
    return false;
  }

  @Override
  public String toString() {
    return "Cat [name=" + name + ", getName()=" + getName() + ", getSound()=" + getSound() + ", getType()=" + getType()
        + ", isEndangered()=" + isEndangered() + "]";
  }

}