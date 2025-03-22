package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;
import java.util.ArrayList;
public class OperatorManagementSystem {

  private ArrayList<String> operatorNames;
  private String wordsAsString = "";
  
  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    operatorNames = new ArrayList<>();
  }

  public void searchOperators(String keyword) {
    
    if (keyword.equals("*") && operatorNames.size() == 1) {
      //Getting number of operators in array list
      Integer numberOfOperators = operatorNames.size();
      MessageCli.OPERATORS_FOUND.printMessage("is", numberOfOperators.toString(), "", ":");
    }    
  }

  public void createOperator(String operatorName, String location) {
    // Getting location from Types.java 
    Location locationFound = Location.fromString(location);
    // Assigning full english and maori name
    String locationAsString = locationFound.getFullName();
    // Using split to split up the Operator words in the array
    String[] words = operatorName.split(" ");

    String abbreviationnAsString = locationFound.getLocationAbbreviation();

    

    // Looping through each word in the array and retreiving the first letter (index 0)
    for (String word : words) {
      wordsAsString += word.charAt(0); 
  }   
    int id = 1;
    String finalId = "";
  
      // Generating ID with leading zeros in 3 digit format
      finalId = String.format("%03d", id);
      id = id + 1;

    wordsAsString = wordsAsString + "-" + abbreviationnAsString + "-" + finalId; 

    operatorNames.add(operatorName);
    
    MessageCli.OPERATOR_CREATED.printMessage(operatorName, wordsAsString, locationAsString);
    
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


