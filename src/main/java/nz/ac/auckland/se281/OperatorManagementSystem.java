package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private ArrayList<OperatorSearch> operators;

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
    for (OperatorSearch operator : operators) {
      if (operator.getId().equals(operatorId)) {
        MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
        return;
      }
    }
    MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    if (activityName.trim().length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }
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
