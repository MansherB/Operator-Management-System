package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;

public class OperatorActivity extends Activities {
  private String operatorId;
  private String activityId;
  private String activityType;
  private List<Reviews> reviews = new ArrayList<>();

  public OperatorActivity(
      String name, String id, String type, String operatorName, String operatorId) {
    super(name, id, type, operatorName);
    this.operatorId = operatorId;
    this.activityId = id; // Saving the full activity ID (e.g., WACT-AKL-001-001)
    this.activityType = type;
  }

  public List<Reviews> getReviews() {
    return reviews;
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
