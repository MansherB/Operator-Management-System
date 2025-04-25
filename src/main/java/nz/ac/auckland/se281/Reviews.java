package nz.ac.auckland.se281;

public abstract class Reviews {
  protected String reviewId;
  protected String activityId;
  private boolean endorsed = false;
  private boolean resolved = false;

  public Reviews(String reviewId, String activityId) {
    this.reviewId = reviewId;
    this.activityId = activityId;
  }

  public void setEndorsed(boolean endorsed) {
    this.endorsed = endorsed;
  }

  public boolean isEndorsed() {
    return endorsed;
  }

  public void setResolved(boolean resolved) {
    this.resolved = resolved;
  }

  public boolean isResolved() {
    return resolved;
  }

  public String getReviewId() {
    return reviewId;
  }

  public String getActivityId() {
    return activityId;
  }
}
