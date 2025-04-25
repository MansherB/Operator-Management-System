package nz.ac.auckland.se281;

public class PublicReview extends Reviews {
  private String rating;
  private String clientName;
  private String reviewText;

  public PublicReview(String reviewId, String activityId, String[] options) {
    super(reviewId, activityId);
    rating = options[2];
    clientName = options[0];
    reviewText = options[3];
  }

  public String getReviewId() {
    return reviewId;
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
