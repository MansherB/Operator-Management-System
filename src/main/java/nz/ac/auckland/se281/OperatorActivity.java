package nz.ac.auckland.se281;

public class OperatorActivity extends Activities {
  private String operatorId;
  private String activityId;
  private String activityType;

  public OperatorActivity(
      String name, String id, String type, String operatorName, String operatorId) {
    super(name, id, type, operatorName);
    this.operatorId = operatorId;
    this.activityId = id; // Saving the full activity ID (e.g., WACT-AKL-001-001)
    this.activityType = type;
  }

  public String getOperatorId() {
    return operatorId;
  }

  public String getActivityId() {
    return activityId;
  }

  public String getActivityType() {
    return activityType;
  }
}
