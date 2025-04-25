package nz.ac.auckland.se281;

public abstract class Reviews {
  protected String reviewId;
  protected String activityId;

  public Reviews(String reviewId, String activityId) {
    this.reviewId = reviewId;
    this.activityId = activityId;
  }

  public String getReviewId() {
    return reviewId;
  }

  public String getActivityId() {
    return activityId;
  }
}
