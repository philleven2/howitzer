package howitzer.beans;

import java.io.Serializable;
import java.util.Objects;
import org.apache.log4j.Logger;

public class HowitzerHistory implements Serializable {

  private final static long serialVersionUID = 1L;
  
  final static Logger log = Logger.getLogger(HowitzerHistory.class.getName());
  
  private String userId;
  private double distanceToTarget;
  private double angle;
  private double velocity;
  private double targetSize;
  private String result;
  private double distanceTraveled;
  private double distanceMissedBy;
  private double timeTraveled;
  private String timeStamp;

  // Default constructor
  public HowitzerHistory() {
    
    log.debug("Created instance: " + this.toString());

  }

  public HowitzerHistory(String userId, double distanceToTarget, double angle, double velocity, 
      double targetSize, String result, double distanceTraveled, double distanceMissedBy, 
      double timeTraveled, String timeStamp) {

    this.userId = userId;
    this.distanceToTarget = distanceToTarget;
    this.angle = angle;
    this.velocity = velocity;
    this.targetSize = targetSize;
    this.result = result;
    this.distanceTraveled = distanceTraveled;
    this.distanceMissedBy = distanceMissedBy;
    this.timeTraveled = timeTraveled;
    this.timeStamp = timeStamp;
    
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

  public String getTimeStamp() {
    
    return timeStamp;
    
  }

  public void setTimeStamp(String timeStamp) {
    
    this.timeStamp = timeStamp;
    
  }

  @Override
  public boolean equals(Object obj) {

      if (this == obj) {
        
        return true;
        
      }
      
      if (obj == null) {
        
          return false;
          
      }
      
      if (!(obj instanceof HowitzerHistory)) {
        
          return false;
          
      }
      
      HowitzerHistory other = (HowitzerHistory) obj;
      
      return Objects.equals(this.userId, other.userId) 
          && Objects.equals(this.distanceToTarget, other.distanceToTarget) 
          && Objects.equals(this.angle, other.angle)
          && Objects.equals(this.velocity, other.velocity)
          && Objects.equals(this.targetSize, other.targetSize)
          && Objects.equals(this.result, other.result)
          && Objects.equals(this.distanceTraveled, other.distanceTraveled)
          && Objects.equals(this.distanceMissedBy, other.distanceMissedBy)
          && Objects.equals(this.timeTraveled, other.timeTraveled)
          && Objects.equals(this.timeStamp, other.timeStamp);
                  
  }

  @Override
  public int hashCode() {
    
      return Objects.hash(this.userId, this.distanceToTarget, this.angle, this.velocity, this.targetSize, this.result,
          this.distanceTraveled, this.distanceMissedBy, this.timeTraveled, this.timeStamp);
      
  }

}