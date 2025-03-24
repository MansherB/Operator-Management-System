package nz.ac.auckland.se281;

public class OperatorSearch {
  // Private fields to store operator details
  private String operatorNames;
  private String wordsAsString;
  private String locationAsString;

  // Constructor that initialises an OperatorSearch object with name, id, and location
  public OperatorSearch(String name, String id, String location) {
      this.operatorNames = name;
      this.wordsAsString = id;
      this.locationAsString = location;
    }

    // Retreiving the operators name, id, and location and returning name of operator
    public String getName() {
      return operatorNames;
    }

    
    public String getId() {
      return wordsAsString;
    }

    public String getLocation() {
      return locationAsString;
    }
}
