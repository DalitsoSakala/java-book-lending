package com.booklending.cs3301;

/**
 * @author Mambu Kaumba
 */
class Person {
  private String name;       //Full name of a person
  private int id;            //A unique id to identify them
  private String address;    //the persons address of residence
  private int contactNumber; //the persons contact number
  private Role role;         // enum that shows memebership level of a person
  
  // A function that sets a persons name
  public void setName(String name) {
    this.name = name;
  }

  // A fucntion that sets a persons id
  public void seId(int id) {
    this.id = id;
  }

  // A function that sets a persons address
  public void setAddess(String address) {
    this.address = address;
  }

  // A function that sets a persons contact number
  public void setContactNumber(int contactNumber) {
    this.contactNumber = contactNumber;
  }

  // Sets role of user
  public void setRole() {

  }

   // A function that returns a string 
   public String getName() {
    return this.name;
   }

   // A function that gets the persons identity number
   public int getId() {
    return this.id;
   }

   // A function that returns a persons address
   public String getAddress() {
    return this.address;
   }

   // A function that returns a persons contact number
   public int getContactNumber() {
    return this.contactNumber;
   }

   // Returns role of the user
   public Role getRole() {
        return this.role;
   }

  
}
