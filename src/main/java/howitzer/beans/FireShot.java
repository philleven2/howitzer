package howitzer.beans;

import java.io.Serializable;
//import org.apache.log4j.Logger;
import java.util.Objects;

public class FireShot implements Serializable {

  private final static long serialVersionUID = 1L;
  
  //final static Logger log = Logger.getLogger(FireShot.class.getName());
  
  private String userId;
  private double distanceToTarget;
  private double angle;
  private double velocity;
  private double targetSize;
  private String result;
  private double distanceTraveled;
  private double distanceMissedBy;
  private double timeTraveled;
  private int rank;
  private String trajectory;

  // Default constructor
  public FireShot() {
    
    //log.debug("Created instance: " + this.toString());

    this.userId = "User1";
    this.distanceToTarget = 1000;
    this.angle = 0;
    this.velocity = 0;
    this.targetSize = 0;
    this.result = "";
    this.distanceTraveled = 0;
    this.distanceMissedBy = 0;
    this.timeTraveled = 0;
    this.rank = 0;
    this.trajectory = "";

  }

  public FireShot(String userId, double distanceToTarget, double angle, double velocity, 
      double targetSize, String result, double distanceTraveled, double distanceMissedBy, 
      double timeTraveled, int rank, String trajectory) {

    this.userId = userId;
    this.distanceToTarget = distanceToTarget;
    this.angle = angle;
    this.velocity = velocity;
    this.targetSize = targetSize;
    this.result = result;
    this.distanceTraveled = distanceTraveled;
    this.distanceMissedBy = distanceMissedBy;
    this.timeTraveled = timeTraveled;
    this.rank = rank;
    this.trajectory = trajectory;
    
  }

  public String getUserId() {
    
    return userId;
    
  }

  public void setUserId(String userId) {
    
    this.userId = userId;
    
  }

  public double getDistanceToTarget() {
    
    return distanceToTarget;
    
  }

  public void setDistanceToTarget(double distanceToTarget) {
    
    this.distanceToTarget = distanceToTarget;
    
  }

  public double getAngle() {
    
    return angle;
    
  }

  public void setAngle(double angle) {
    
    this.angle = angle;
    
  }

  public double getVelocity() {
    
    return velocity;
    
  }

  public void setVelocity(double velocity) {
    
    this.velocity = velocity;
    
  }

  public double getTargetSize() {
    
    return targetSize;
    
  }

  public void setTargetSize(double targetSize) {
    
    this.targetSize = targetSize;
    
  }

  public String getResult() {
    
    return result;
    
  }

  public void setResult(String result) {
    
    this.result = result;
    
  }

  public double getDistanceTraveled() {
    
    return distanceTraveled;
    
  }

  public void setDistanceTraveled(double distanceTraveled) {
    
    this.distanceTraveled = distanceTraveled;
    
  }

  public double getDistanceMissedBy() {
    
    return distanceMissedBy;
    
  }

  public void setDistanceMissedBy(double distanceMissedBy) {
    
    this.distanceMissedBy = distanceMissedBy;
    
  }

  public double getTimeTraveled() {
    
    return timeTraveled;
    
  }

  public void setTimeTraveled(double timeTraveled) {
    
    this.timeTraveled = timeTraveled;
    
  }

  public int getRank() {
    
    return rank;
    
  }

  public void setRank(int rank) {
    
    this.rank = rank;
    
  }

  public String getTrajectory() {
    
    return trajectory;
    
  }

  public void setTrajectory(String trajectory) {
    
    this.trajectory = trajectory;
    
  }

  @Override
  public boolean equals(Object obj) {

      if (this == obj) {
        
        return true;
        
      }
      
      if (obj == null) {
        
          return false;
          
      }
      
      if (!(obj instanceof FireShot)) {
        
          return false;
          
      }
      
      FireShot other = (FireShot) obj;
      
      return Objects.equals(this.userId, other.userId) 
          && Objects.equals(this.distanceToTarget, other.distanceToTarget) 
          && Objects.equals(this.angle, other.angle)
          && Objects.equals(this.velocity, other.velocity)
          && Objects.equals(this.targetSize, other.targetSize)
          && Objects.equals(this.result, other.result)
          && Objects.equals(this.distanceTraveled, other.distanceTraveled)
          && Objects.equals(this.distanceMissedBy, other.distanceMissedBy)
          && Objects.equals(this.timeTraveled, other.timeTraveled)
          && Objects.equals(this.rank, other.rank)
          && Objects.equals(this.trajectory, other.trajectory);
                  
  }

  @Override
  public int hashCode() {
    
      return Objects.hash(this.userId, this.distanceToTarget, this.angle, this.velocity, this.targetSize, this.result,
          this.distanceTraveled, this.distanceMissedBy, this.timeTraveled, this.rank, this.trajectory);
      
  }

}