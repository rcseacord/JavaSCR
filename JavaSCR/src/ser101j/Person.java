// The MIT License (MIT)
// 
// Copyright (c) 2018 Robert C. Seacord
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package ser101j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

enum Gender {
  MALE, FEMALE
}

public class Person implements java.io.Serializable {
  // private static final long serialVersionUID = 9043182744481133479L;
  private String firstName;
  private String lastName;
  private String socialSecurity;
  private int age;
  private Person spouse;
//  private Gender gender;

  public Person(String fn, String ln, String socialSecurity, int a) {
    this.firstName = fn;
    this.lastName = ln;
    this.socialSecurity = socialSecurity;
    this.age = a;
  }

//  public Person(String fn, String ln, String socialSecurity, int a, Gender g) {
//    this.firstName = fn;
//    this.lastName = ln;
//    this.socialSecurity = socialSecurity;
//    this.age = a;
//    this.gender = g;
//  }
//  public Gender getGender() { return gender; }
//  public void setGender(Gender value) { gender = value; }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String value) {
    this.firstName = value;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String value) {
    this.lastName = value;
  }

  public int getAge() {
    return this.age;
  }

  public void setAge(int value) {
    this.age = value;
  }

  public Person getSpouse() {
    return this.spouse;
  }

  public void setSpouse(Person value) {
    this.spouse = value;
  }

  public String getSocialSecurity() {
    return this.socialSecurity;
  }

  public void setSocialSecurity(String value) {
    this.socialSecurity = value;
  }

  @Override
  public String toString() {
    return "[Person: firstName=" + this.firstName + " lastName=" + this.lastName +
        " age=" + this.age + " spouse=" + this.spouse.getFirstName() + "]";
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    Person p1 = new Person("John", "Doe", "012-34-5678", 25);   
    Person p2 = new Person("Jane", "Doe", "987-65-4321", 24);   

    // Create objects with new Gender field
//    p1 = new Person("John", "Doe", "012-34-5678", 25, Gender.MALE);
//    p2 = new Person("Jane", "Doe", "987-65-4321", 24, Gender.FEMALE);

    p1.setSpouse(p2);
    p2.setSpouse(p1);

    // Comment out when deserializing Person with Gender field
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempdata.ser"))) {
      oos.writeObject(p1);
    }

    Person p;
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tempdata.ser"))) {
      p = (Person) ois.readObject();
    }

    if (!Objects.equals(p.getFirstName(), "John") || 
        !Objects.equals(p.getSpouse().getFirstName(), "Jane")) { 
      System.err.println("Object changed during deserialization"); 
    } else {
      System.out.println("Object successfully deserialized");
    }

    // Clean up the file
    // new File("tempdata.ser").delete();

  } // end main
} // end class
