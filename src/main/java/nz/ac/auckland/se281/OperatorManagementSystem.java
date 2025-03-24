package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;
import java.util.ArrayList;
public class OperatorManagementSystem {

  private ArrayList<OperatorSearch> operators;
  
  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    operators = new ArrayList<>();
  }

  public void searchOperators(String keyword) {
    
    if (keyword.equals("*") && operators.size() == 1) {
      //Getting number of operators in array list
      Integer numberOfOperators = operators.size();
      MessageCli.OPERATORS_FOUND.printMessage("is", numberOfOperators.toString(), "", ":");

      for (OperatorSearch operator : operators) {
        MessageCli.OPERATOR_ENTRY.printMessage(operator.getName(), operator.getId(), operator.getLocation());
      }
    } else {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    }
  }

  public void createOperator(String operatorName, String location) {
    // Getting location from Types.java 
    Location locationFound = Location.fromString(location);
    String locationAsString = locationFound.getFullName();
    String abbreviationAsString = locationFound.getLocationAbbreviation();

    // Extracting first letter of each word in operatorName
    String[] words = operatorName.split(" ");
    String wordsAsString = ""; // Reset wordsAsString each time this function is called
    for (String word : words) {
        wordsAsString += word.charAt(0);
    }

    // Generating unique ID based on the number of operators
    int id = operators.size() + 1; 
    String finalId = String.format("%03d", id); 

    String operatorId = wordsAsString + "-" + abbreviationAsString + "-" + finalId;

    // Add operator to array list
    operators.add(new OperatorSearch(operatorName, operatorId, locationAsString));

    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorId, locationAsString);
}



  public void viewActivities(String operatorId) {
    // TODO implement
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    // TODO implement
  }

  public void searchActivities(String keyword) {
    // TODO implement
  }

  public void addPublicReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addPrivateReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addExpertReview(String activityId, String[] options) {
    // TODO implement
  }

  public void displayReviews(String activityId) {
    // TODO implement
  }

  public void endorseReview(String reviewId) {
    // TODO implement
  }

  public void resolveReview(String reviewId, String response) {
    // TODO implement
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // TODO implement
  }

  public void displayTopActivities() {
    // TODO implement
  }
}


