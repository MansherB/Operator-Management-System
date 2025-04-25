package nz.ac.auckland.se281;

public class ExpertReview extends Reviews {

  private String rating;
  private String clientName;
  private String reviewText;
  private String recommendation;
  private String imageName;

  public ExpertReview(String reviewId, String activityId, String[] options) {
    super(reviewId, activityId);
    rating = options[1];
    clientName = options[0];
    reviewText = options[2];
    recommendation = options[3];
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getImageName() {
    return imageName;
  }

  public String getRecommendation() {
    return recommendation;
  }

  public String getRating() {
    return rating;
  }

  public String getClientName() {
    return clientName;
  }

  public String getText() {
    return reviewText;
  }
}
