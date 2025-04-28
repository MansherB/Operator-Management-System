package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private ArrayList<OperatorSearch> operators;
  private ArrayList<OperatorActivity> activities = new ArrayList<>();
  private ArrayList<Reviews> reviews = new ArrayList<>();
  private ArrayList<String> multipleImages = new ArrayList<>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    operators = new ArrayList<>();
  }

  public void searchOperators(String keyword) {

    if (keyword.equals("*") && operators.size() == 1) {
      // Getting number of operators in array list
      Integer numberOfOperators = operators.size();
      MessageCli.OPERATORS_FOUND.printMessage("is", numberOfOperators.toString(), "", ":");

      for (OperatorSearch operator : operators) {
        MessageCli.OPERATOR_ENTRY.printMessage(
            operator.getName(), operator.getId(), operator.getLocation());
      }
    }

    // If keyword is * AND operator size is greater than 1
    else if (keyword.equals("*") && operators.size() > 1) {
      // Printing correlating message depending on size
      Integer numberOfOperators = operators.size();
      MessageCli.OPERATORS_FOUND.printMessage("are", numberOfOperators.toString(), "s", ":");

      for (OperatorSearch operator : operators) {
        MessageCli.OPERATOR_ENTRY.printMessage(
            operator.getName(), operator.getId(), operator.getLocation());
      }
    }

    // Else print error message
    else if (keyword.equals("*") && operators.size() <= 0) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");

    } else if (!validateEnum(keyword) && (!keyword.equals("|"))) {

      // Creating new arraylist for matching words
      ArrayList<OperatorSearch> matchingWord = new ArrayList<>();
      String trimmedKeyword = keyword.trim().toLowerCase();

      // For each loop iterating through each operators name and location
      for (OperatorSearch operator : operators) {
        String operatorName = operator.getName().trim().toLowerCase();
        String operatorLocation = operator.getLocation().trim().toLowerCase();
        String operatorAbbreviation = operator.getLocationAbbreviation().trim().toLowerCase();

        // Adding to arraylist if keyword matches operator name, operator location, or operator
        // abbreviation
        if (operatorName.contains(trimmedKeyword.toLowerCase())
            || operatorLocation.contains(trimmedKeyword.toLowerCase())
            || operatorAbbreviation.contains(trimmedKeyword)) {
          matchingWord.add(operator);
        }
      }

      Integer numberOfOperators = matchingWord.size();
      // If statement to differentiate whether there is 1 input or more than 1
      if (matchingWord.size() == 1) {
        MessageCli.OPERATORS_FOUND.printMessage("is", numberOfOperators.toString(), "", ":");
      } else if (matchingWord.size() == 0) {
        MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
      } else {
        MessageCli.OPERATORS_FOUND.printMessage("are", numberOfOperators.toString(), "s", ":");
      }
      // Print only matching operators
      for (OperatorSearch operator : matchingWord) {
        MessageCli.OPERATOR_ENTRY.printMessage(
            operator.getName(), operator.getId(), operator.getLocation());
      }

    } else if (validateEnum(keyword)) {

      ArrayList<OperatorSearch> matchingWord = new ArrayList<>();
      String trimmedKeyword = keyword.trim().toLowerCase();

      // For each loop iterating through each operators name and location
      for (OperatorSearch operator : operators) {
        String operatorName = operator.getName().trim().toLowerCase();
        String operatorLocation = operator.getLocation().trim().toLowerCase();

        // If keyword matches name, location, add into arraylist
        if (operatorName.contains(trimmedKeyword.toLowerCase())
            || operatorLocation.contains(trimmedKeyword.toLowerCase())
            || operator.isValidAbb(trimmedKeyword)) {
          matchingWord.add(operator);
        }
      }
      Integer numberOfOperators = matchingWord.size();
      if (operators.size() == 1) {
        MessageCli.OPERATORS_FOUND.printMessage("is", numberOfOperators.toString(), "", ":");
      } else {
        MessageCli.OPERATORS_FOUND.printMessage("are", numberOfOperators.toString(), "s", ":");
      }

      // Print only matching operators
      for (OperatorSearch operator : matchingWord) {
        MessageCli.OPERATOR_ENTRY.printMessage(
            operator.getName(), operator.getId(), operator.getLocation());
      }

    } else {
      // Creating a new array to store matching Maori keywords
      ArrayList<OperatorSearch> matchingWord = new ArrayList<>();

      // Getting Maori words from enum in Types.java
      Location maori = Location.fromString(keyword);
      String maoriAsString = maori.getNameTeReo();

      // Iterating through each operator to check if it contains the Maori keyword
      for (OperatorSearch operator : operators) {
        if (operator.getLocation().contains(maoriAsString)) {
          matchingWord.add(operator);
        }
      }

      // After adding matched Maori words, storing into an integer to get size
      Integer numberOfOperators = matchingWord.size();
      MessageCli.OPERATORS_FOUND.printMessage("are", numberOfOperators.toString(), "s", ":");

      // Print only matching operators
      for (OperatorSearch operator : matchingWord) {
        MessageCli.OPERATOR_ENTRY.printMessage(
            operator.getName(), operator.getId(), operator.getLocation());
      }
    }
  }

  // Checking to see if keyword matches Types Enum
  public Boolean validateEnum(String keyword) {
    for (OperatorSearch operator : operators) {
      if (operator.isValidLocation(keyword)) {
        return true;
      }
    }
    return false; // Ensures that a Boolean value is returned
  }

  public void createOperator(String operatorName, String location) {
    // Getting location from Types.java
    Location locationFound = Location.fromString(location);

    // If invalid location, print error message
    if (locationFound == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return;
    }

    // Getting location/abbreviation from Types.java, and converting to string
    String locationAsString = locationFound.getFullName();

    String abbreviationAsString = locationFound.getLocationAbbreviation();

    if (operatorName.trim().length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return;
    }

    // Extracting first letter of each word in operatorName
    String[] words = operatorName.split(" ");
    String wordsAsString = ""; // Reset wordsAsString each time this function is called
    for (String word : words) {
      wordsAsString += word.charAt(0);
    }

    // Generating unique ID based on the number of operators
    int id = 1; // Default id at 001
    for (OperatorSearch operator : operators) {
      if (operator.getLocation().equals(locationAsString)) {
        // Increment id only if operator with same location exists
        id++;
      }
    }

    // &03d ensures two trailing zeros
    String finalId = String.format("%03d", id);

    String operatorId = wordsAsString + "-" + abbreviationAsString + "-" + finalId;

    // Check if operator already exists in the same location
    for (OperatorSearch operator : operators) {
      if (operator.getName().equals(operatorName)
          && operator.getLocation().equals(locationAsString)) {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
            operatorName, locationAsString);
        return;
      }
    }

    // Add operator to array list
    operators.add(new OperatorSearch(operatorName, operatorId, locationAsString));

    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorId, locationAsString);
  }

  public void viewActivities(String operatorId) {
    // Checking if operator exists
    boolean operatorFound = false;
    String operatorName = "";

    for (OperatorSearch operator : operators) {
      if (operator.getId().equals(operatorId)) {
        operatorFound = true;
        operatorName = operator.getName();
        break;
      }
    }

    if (!operatorFound) {
      MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
      return;
    }

    // Collecting activities for the given operator
    ArrayList<OperatorActivity> activitiesFound = new ArrayList<>();
    for (OperatorActivity activity : activities) {
      if (activity.getOperatorId().equals(operatorId)) {
        activitiesFound.add(activity);
      }
    }

    if (activitiesFound.size() == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    }

    // Printing singular or plural messages based on activity count
    if (activitiesFound.size() == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
    } else {
      MessageCli.ACTIVITIES_FOUND.printMessage(
          "are", String.valueOf(activitiesFound.size()), "ies", ":");
    }

    // Displaying activities that matching the operator
    for (OperatorActivity activity : activities) {
      if (activity.getOperatorId().equals(operatorId)) {

        MessageCli.ACTIVITY_ENTRY.printMessage(
            activity.getName(),
            activity.getActivityId(),
            ActivityType.ADVENTURE.getName(),
            operatorName);

      } else {
        MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      }
    }
  }

  public void createActivity(String activityName, String activityType, String operatorId) {

    int id = 1; // Default id at 001
    for (OperatorActivity activity : activities) {
      if (activity.getOperatorId().equals(operatorId)) {
        id++;
      }
    }

    // &03d ensures two trailing zeros
    String finalId = String.format("%03d", id);

    String createOperatorId = operatorId + "-" + finalId;

    if (activityName.trim().length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName.trim());
      return;
    }

    for (OperatorSearch operator : operators) {
      if (operator.getId().equals(operatorId)) {
        String matchedType = "Other";
        for (ActivityType type : ActivityType.values()) {
          if (type.name().equalsIgnoreCase(activityType)) {
            matchedType = type.toString();
          }
        }
        activities.add(
            new OperatorActivity(
                activityName, createOperatorId, matchedType, operator.getName(), operatorId));

        MessageCli.ACTIVITY_CREATED.printMessage(
            activityName.trim(), createOperatorId, matchedType, operator.getName());
        return;
      }
    }
    MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
  }

  // Searching for all activities if keyword = *
  public void searchActivities(String keyword) {
    if (keyword.equals("*") && activities.size() == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    } else if (keyword.equals("*") && activities.size() > 0) {
      Integer numberOfActivities = activities.size();
      MessageCli.ACTIVITIES_FOUND.printMessage("are", numberOfActivities.toString(), "ies", ":");
      return;
    }

    // New arraylist for matching words so their size can be checked
    ArrayList<OperatorActivity> matchingWord = new ArrayList<>();

    for (OperatorActivity activity : activities) {
      if (activity.getName().toLowerCase().contains(keyword.toLowerCase())
          || (activity.getActivityType().toLowerCase().contains(keyword.toLowerCase()))) {
        matchingWord.add(activity);
      }
    }

    Location locationKeyword = Location.fromString(keyword.trim().toLowerCase());

    for (OperatorSearch operator : operators) {
      if (locationKeyword != null) {
        String fullLocationName = locationKeyword.getFullName().trim().toLowerCase();
        String abbLocationName = locationKeyword.getLocationAbbreviation().trim().toLowerCase();

        // Comparing fullname and abbreviation name
        if (operator.getLocation().toLowerCase().contains(fullLocationName)
            || operator.getLocationAbbreviation().toLowerCase().contains(abbLocationName)) {
          for (OperatorActivity activity : activities) {
            if (activity.getOperatorId().equals(operator.getId())) {
              matchingWord.add(activity);
            }
          }
          continue; // If matched, continue to next operator
        }
      }

      // Comparing partial name and partial abbreviation name
      if (operator.getLocation().toLowerCase().contains(keyword.trim().toLowerCase())
          || operator
              .getLocationAbbreviation()
              .toLowerCase()
              .contains(keyword.trim().toLowerCase())) {
        for (OperatorActivity activity : activities) {
          if (activity.getOperatorId().equals(operator.getId())) {
            matchingWord.add(activity);
          }
        }
      }
    }

    Integer numberOfActivities = matchingWord.size();
    if (numberOfActivities == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    } else if (numberOfActivities == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
    } else {
      MessageCli.ACTIVITIES_FOUND.printMessage(
          "are", String.valueOf(numberOfActivities), "ies", ":");
    }

    // Print only matching activities
    for (OperatorActivity matchingActivity : matchingWord) {
      MessageCli.ACTIVITY_ENTRY.printMessage(
          matchingActivity.getName(),
          matchingActivity.getActivityId(),
          matchingActivity.getActivityType(),
          matchingActivity.getOperatorName());
    }
  }

  // Helper method to generate a review ID based on activity ID and existing review count for that
  // activity
  private String generateReviewId(String activityId) {
    int id = 1;
    for (Reviews review : reviews) {
      if (review.getActivityId().equals(activityId)) {
        id++;
      }
    }
    return activityId + "-" + "R" + id;
  }

  public void addPublicReview(String activityId, String[] options) {
    // Generating unique review id and creating public review
    String reviewId = generateReviewId(activityId);
    PublicReview review = new PublicReview(reviewId, activityId, options);
    reviews.add(review);

    // Validating activity id and printing success or error messages
    for (OperatorActivity activity : activities) {
      if (activity.getActivityId().equals(activityId)) {
        String activityName = activity.getName();
        MessageCli.REVIEW_ADDED.printMessage("Public", reviewId, activityName);
        return;
      }
    }
    MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
  }

  public void addPrivateReview(String activityId, String[] options) {

    // Unique review id and creating private review
    String reviewId = generateReviewId(activityId);
    PrivateReview review = new PrivateReview(reviewId, activityId, options);
    reviews.add(review);

    // Printing success or error message based on activity id
    for (OperatorActivity activity : activities) {
      if (activity.getActivityId().equals(activityId)) {
        String activityName = activity.getName();
        MessageCli.REVIEW_ADDED.printMessage("Private", reviewId, activityName);
        return;
      }
    }
    MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
  }

  public void addExpertReview(String activityId, String[] options) {

    // Generating a unique review id and creating expert review
    String reviewId = generateReviewId(activityId);
    ExpertReview review = new ExpertReview(reviewId, activityId, options);
    reviews.add(review);

    // Validating activity id and printing messages accordingly
    for (OperatorActivity activity : activities) {
      if (activity.getActivityId().equals(activityId)) {
        String activityName = activity.getName();
        MessageCli.REVIEW_ADDED.printMessage("Expert", reviewId, activityName);
        return;
      }
    }
    MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
  }

  public void displayReviews(String activityId) {
    int count = 0;
    String activityName = "";
    boolean activityFound = false;

    // Finding activity name from the activityId
    for (OperatorActivity activity : activities) {
      if (activity.getActivityId().equals(activityId)) {
        activityName = activity.getName();
        activityFound = true;
        break;
      }
    }

    // Checking if activity exists
    if (!activityFound) {
      MessageCli.ACTIVITY_NOT_FOUND.printMessage(activityId);
      return;
    }

    // Counting matching reviews
    for (Reviews review : reviews) {
      if (review.getActivityId().equals(activityId)) {
        count++;
      }
    }

    // Displaying review count message
    if (count == 0) {
      MessageCli.REVIEWS_FOUND.printMessage("are", "no", "s", activityName);
      return;
    } else if (count == 1) {
      MessageCli.REVIEWS_FOUND.printMessage("is", "1", "", activityName);
    } else {
      MessageCli.REVIEWS_FOUND.printMessage("are", String.valueOf(count), "s", activityName);
    }

    for (Reviews review : reviews) {
      // Matching review with activity
      if (!review.getActivityId().equals(activityId)) {
        continue;
      } else count++;

      if (review instanceof PublicReview) {
        PublicReview publicReview = (PublicReview) review;
        Integer rating = Integer.parseInt(publicReview.getRating());
        String name = publicReview.getClientName();
        String text = publicReview.getText();
        String anonymous = publicReview.getAnonymous();
        if (rating < 1) rating = 1;
        if (rating > 5) rating = 5;

        if (anonymous.equalsIgnoreCase("y")) {
          MessageCli.REVIEW_ENTRY_HEADER.printMessage(
              String.valueOf(rating), "5", "Public", review.getReviewId(), "Anonymous");
        } else {
          MessageCli.REVIEW_ENTRY_HEADER.printMessage(
              String.valueOf(rating), "5", "Public", review.getReviewId(), name);
        }

        if (review.isEndorsed()) {
          MessageCli.REVIEW_ENTRY_ENDORSED.printMessage();
        }

        MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(text);

      } else if (review instanceof PrivateReview) {
        PrivateReview privateReview = (PrivateReview) review;
        Integer rating = Integer.parseInt(privateReview.getRating());
        // Assigning getters from PrivateReview file
        String name = privateReview.getClientName();
        String text = privateReview.getText();
        String followUp = privateReview.getFollowUp();
        String email = privateReview.getEmail();
        // Edge cases where rating is greater than 5 or less than 1
        if (rating < 1) rating = 1;
        if (rating > 5) rating = 5;

        MessageCli.REVIEW_ENTRY_HEADER.printMessage(
            String.valueOf(rating), "5", "Private", review.getReviewId(), name);

        if (followUp.equalsIgnoreCase("n")) {
          MessageCli.REVIEW_ENTRY_RESOLVED.printMessage("-");
        } else if (review.isResolved()) {
          MessageCli.REVIEW_ENTRY_RESOLVED.printMessage("So sorry to hear that!");
        } else {
          MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(email);
        }

        MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(text);

      } else if (review instanceof ExpertReview) {
        ExpertReview expertReview = (ExpertReview) review;
        Integer rating = Integer.parseInt(expertReview.getRating());
        String name = expertReview.getClientName();
        String text = expertReview.getText();
        String recommendation = expertReview.getRecommendation();
        if (rating < 1) rating = 1;
        if (rating > 5) rating = 5;

        MessageCli.REVIEW_ENTRY_HEADER.printMessage(
            String.valueOf(rating), "5", "Expert", review.getReviewId(), name);
        MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(text);

        if (recommendation.equalsIgnoreCase("y")) {
          MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage(text);
        }
        if (review.isImage()) {
          String imagesString = String.join(",", multipleImages);
          MessageCli.REVIEW_ENTRY_IMAGES.printMessage(imagesString);
        }
      }
    }
  }

  public void endorseReview(String reviewId) {
    // Iterating through all reviews to find the matching review id
    for (Reviews review : reviews) {
      // Checking if review id matches and review is a PublicReview
      if (review.getReviewId().equals(reviewId) && review instanceof PublicReview) {
        review.setEndorsed(true);
        MessageCli.REVIEW_ENDORSED.printMessage(reviewId);

        return;
      } else if (!(review.getReviewId().equals(reviewId))) {
        // If review id doesn't match, print not found message
        MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      } else {
        // If review is not a PublicReview, print not endorsed message
        MessageCli.REVIEW_NOT_ENDORSED.printMessage(reviewId);
      }
    }
    // If no review is found
    MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
  }

  public void resolveReview(String reviewId, String response) {
    for (Reviews review : reviews) {
      // Checking if review ID matches and review is a PrivateReview
      if (review.getReviewId().equals(reviewId) && review instanceof PrivateReview) {
        // Marking the review as resolved and printing success message
        MessageCli.REVIEW_RESOLVED.printMessage(reviewId);
        review.setResolved(true);
        return;
      } else if (review instanceof PublicReview || review instanceof ExpertReview) {
        // If review is not a PrivateReview, print not resolved message
        MessageCli.REVIEW_NOT_RESOLVED.printMessage(reviewId);
      } else {
        // If review ID doesn't match, print not found message
        MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      }
    }
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    for (Reviews review : reviews) {
      // Checking if review is an ExpertReview
      if (review instanceof ExpertReview) {
        // Casting review to ExpertReview to access methods
        ExpertReview expert = (ExpertReview) review;
        expert.setImageName(imageName);

        // Checking if review ID matches
        if (review.getReviewId().equals(reviewId)) {
          // Marking review as having an image and adding image to multipleImages list
          review.setImage(true);

          // Adding imageName to array and printing success message
          multipleImages.add(imageName);
          MessageCli.REVIEW_IMAGE_ADDED.printMessage(imageName, reviewId);

          return;
        }
      } else if (review instanceof PublicReview || review instanceof PrivateReview) {
        // If review is not an ExpertReview, print not expert message
        MessageCli.REVIEW_IMAGE_NOT_ADDED_NOT_EXPERT.printMessage(reviewId);
      }
      return;
    }
    // If no review is found:
    MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
  }

  public void displayTopActivities() {
    // Storing matching location abbreviations for activities
    ArrayList<String> matchingAbb = new ArrayList<>();

    // Iterating through each location to find top-rated activity
    for (Types.Location location : Types.Location.values()) {
      boolean reviewFound = false;
      OperatorActivity topActivity = null;
      int topRating = 0;

      // Getting abbreviation for location via splitting by "-"
      for (OperatorActivity activity : activities) {
        String[] parts = activity.getActivityId().split("-");
        String locationPart = parts[1];

        if (locationPart.equalsIgnoreCase(location.name())) {
          matchingAbb.add(locationPart);

          int finalRating = 0;
          int count = 0;

          // Calculating total rating from public and expert reviews
          for (Reviews review : reviews) {
            if (review.getActivityId().equals(activity.getActivityId())
                && (review instanceof PublicReview || review instanceof ExpertReview)) {
              if (review instanceof PublicReview) {
                String ratingCheck = ((PublicReview) review).getRating();
                int ratingValue = 0;
                if (ratingCheck.equals("1")) {
                  ratingValue = 1;
                } else if (ratingCheck.equals("2")) {
                  ratingValue = 2;
                } else if (ratingCheck.equals("3")) {
                  ratingValue = 3;
                } else if (ratingCheck.equals("4")) {
                  ratingValue = 4;
                } else if (ratingCheck.equals("5")) {
                  ratingValue = 5;
                }
                finalRating = finalRating + ratingValue;
                count++;
              } else if (review instanceof ExpertReview) {
                String ratingCheck = ((ExpertReview) review).getRating();
                int ratingValue = 0;
                if (ratingCheck.equals("1")) {
                  ratingValue = 1;
                } else if (ratingCheck.equals("2")) {
                  ratingValue = 2;
                } else if (ratingCheck.equals("3")) {
                  ratingValue = 3;
                } else if (ratingCheck.equals("4")) {
                  ratingValue = 4;
                } else if (ratingCheck.equals("5")) {
                  ratingValue = 5;
                }
                finalRating = finalRating + ratingValue;
                count++;
              }
            }
          }

          // Get average rating and update top activity if greater
          if (count > 0) {
            reviewFound = true;
            int averageRating = finalRating / count;
            if (averageRating > topRating) {
              topRating = (int) averageRating;
              topActivity = activity;
            }
          }
        }
      }

      // Displaying top activity if there is a review, otherwise printing no reviewed activites
      if (reviewFound) {
        MessageCli.TOP_ACTIVITY.printMessage(
            location.getFullName(), topActivity.getName(), String.valueOf(topRating));
      } else {
        MessageCli.NO_REVIEWED_ACTIVITIES.printMessage(location.getFullName());
        ;
      }
    }
  }
}
