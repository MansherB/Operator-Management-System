package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class OperatorSearch {
  private String operatorNames;
  private String wordsAsString;
  private String locationAsString;
  private String nameEnglish;
  private String nameTeReo;
  private String locationAbbreviation;

  public OperatorSearch(String name, String id, String location) {
    // Initializing Location enum fields
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

  public String getNameEnglish() {
    return this.nameEnglish;
  }

  public String getNameTeReo() {
    return this.nameTeReo;
  }

  public String getLocationAbbreviation() {
    return this.locationAbbreviation;
  }

  // Checking for valid keyword
  // Returns true if located in Enum
  public boolean isValidLocation(String keyword) {
    for (Location list : Location.values()) {
      // Making sure case sensitivity is not a factor
      if (list.getNameEnglish().trim().equalsIgnoreCase(keyword)
          || list.getNameTeReo().trim().equalsIgnoreCase(keyword)
          || list.getLocationAbbreviation().trim().equalsIgnoreCase(keyword)) {
        return true;
      }
    }
    return false;
  }
}
