package fitrack_proj.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import fitrack_proj.model.dao.LoginDAO;
import fitrack_proj.util.FitnessHistory;

public class User {
  public User(int userId, LoginDAO connection) {
    this.connection = connection;
    this.userId = userId;
    setUserInfo(userId);
  }

  private boolean setUserInfo(int userId) {
    ResultSet userInfo = connection.retrieveUser(userId);
    try {
      while (userInfo.next()) {
        username = userInfo.getString("username");
        height = userInfo.getInt("height");
        weight = userInfo.getInt("weight");
        age = userInfo.getInt("age");
        gender = userInfo.getString("gender");
        activityLevel = userInfo.getString("activity");
      }
    } catch (SQLException sqe) {
      System.out.println("Failed to set the user to the current DashPanel.\n" + sqe);
    }
    return true;
  }

  public int getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  public String getGender() {
    return gender;
  }


  public String getActivityLevel() {
    return activityLevel;
  }

  public int getHeight() {
    return height;
  }

  public int getWeight() {
    return weight;
  }


  public int getAge() {
    return age;
  }



  public int getWeightGoal() {
    return weightGoal;
  }


  public int getWorkoutPerWeek() {
    return workoutPerWeek;
  }


  public int getMinutesPerWeek() {
    return minutesPerWeek;
  }
  
  public int calculateCalories() {
	  return FitnessHistory.calculateCal(weight, height, gender, activityLevel, age);
  }

  private LoginDAO connection;
  private int userId;
  private String username;
  private String gender;
  private String activityLevel;
  private int weightGoal;
  private int workoutPerWeek;
  private int minutesPerWeek;
  private int height;
  private int weight;
  private int age;
}
