package nz.ac.auckland.se281;

public class OperatorSearch {
  private String operatorNames;
  private String wordsAsString;
  private String locationAsString;

  public OperatorSearch(String name, String id, String location) {
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
}
