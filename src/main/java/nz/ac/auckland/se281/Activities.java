package nz.ac.auckland.se281;

public abstract class Activities {
  protected String name;
  protected String type;
  protected String activityId;
  protected String operatorName;

  // Initializing Activities fields
  public Activities(String name, String type, String activityId, String operatorName) {
    this.name = name;
    this.type = type;
    this.activityId = activityId;
    this.operatorName = operatorName;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getActivityId() {
    return activityId;
  }

  public String getOperatorName() {
    return operatorName;
  }
}
