package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class OperatorSearch {
  private String operatorNames;
  private String wordsAsString;
  private String locationAsString;
  private String nameEnglish;
  private String nameTeReo;
  private String locationAbbreviation;
  private ArrayList<String> abbList;

  public OperatorSearch(String name, String id, String location) {
    // Initializing Location enum fields
    this.operatorNames = name;
    this.wordsAsString = id;
    this.locationAsString = location;
    this.locationAbbreviation = id;

    // Creating new arraylist and storing city abbrevations to check with for each loop
    this.abbList = new ArrayList<>();
    abbList.add("AKL");
    abbList.add("HLZ");
    abbList.add("TRG");
    abbList.add("TUO");
    abbList.add("WLG");
    abbList.add("NSN");
    abbList.add("CHC");
    abbList.add("DUD");
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
          || list.getNameTeReo().trim().equalsIgnoreCase(keyword)) {
        return true;
      }
    }
    return false;
  }

  public boolean isValidAbb(String keyword) {
    // Checking each string in abbreviations Array for keyword
    for (String abbreviation : abbList) {
      if (abbreviation.trim().toLowerCase().contains(keyword.toLowerCase())) {
        return true;
      }
    }

    return false;
  }
}
