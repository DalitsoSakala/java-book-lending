package com.booklending.cs3301;

/**
 * @author Mambu Kaumba
 */
class Person {
  private String name; // Full name of a person
  private final int id; // A unique id to identify them
  private String address; // the persons address of residence
  private String contactNumber; // the persons contact number
  private static int instanceCount = 0;

  public Person(String name, String phNo, String addr) {
    instanceCount++;
    id = instanceCount;
    this.name = name;
    contactNumber = phNo;
    address = addr;

  }

  // A method that sets a persons name
  public void setName(String name) {
    this.name = name;
  }
    @Override
    public String toString(){
        return this.name;
    }

  // // A fucntion that sets a persons id
  // public void seId(int id) {
  // this.id = id;
  // }

  // A method that sets a persons address
  public void setAddess(String address) {
    this.address = address;
  }

  // A method that sets a persons contact number
  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  // A method that returns a string
  public String getName() {
    return this.name;
  }

  // A method that gets the persons identity number
  public int getId() {
    return this.id;
  }

  // A method that returns a persons address
  public String getAddress() {
    return this.address;
  }

  // A method that returns a persons contact number
  public String getContactNumber() {
    return this.contactNumber;
  }

}
