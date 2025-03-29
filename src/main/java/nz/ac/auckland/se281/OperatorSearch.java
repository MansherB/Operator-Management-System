package nz.ac.auckland.se281;

public class OperatorSearch {
  private String operatorNames;
  private String wordsAsString;
  private String locationAsString;

  public OperatorSearch(String name, String id, String location) {
    // Initializing the fields with provided values
    this.operatorNames = name;
    this.wordsAsString = id;
    this.locationAsString = location;
  }

  public String getName() {
    return operatorNames;
  }

  public String getId() {
    return wordsAsString;
  }

  public String getLocation() {
    return locationAsString;
  }

  // Checks for keyword inside Location enum to return True or False
  public boolean isValidLocation(String keyword) {
    for (Types.Location list : Types.Location.values()) {
      // Making sure case sensitivity is not a factor
      if (list.toString().equalsIgnoreCase(keyword)) {
        return true;
      }
    }
    return false;
  }
}
