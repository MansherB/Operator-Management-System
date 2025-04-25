package nz.ac.auckland.se281;

public class PrivateReview extends Reviews {
  private String rating;
  private String clientName;
  private String reviewText;
  private String followUp;
  private String email;

  public PrivateReview(String reviewId, String activityId, String[] options) {
    super(reviewId, activityId);
    rating = options[2];
    clientName = options[0];
    reviewText = options[3];
    followUp = options[4];
    email = options[1];
  }

  public String getReviewId() {
    return reviewId;
  }

  public String getEmail() {
    return email;
  }

  public String getFollowUp() {
    return followUp;
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
