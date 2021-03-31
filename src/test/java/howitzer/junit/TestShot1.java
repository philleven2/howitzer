package howitzer.junit;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import howitzer.beans.FireShot;

public class TestShot1 {
  
  @Test
  public void testFire() {
    
     // Test data
    FireShot fireShot = new FireShot();
    
    fireShot.setDistanceToTarget(25);
    fireShot.setVelocity(25);
    fireShot.setAngle(15);
    fireShot.setTargetSize(15);
    
    // Fire shot

    // Distance to target (meters)
    double distance = 0;
    
    // Muzzle angle (degrees)
    double angle = 0;
    
    // Initial muzzle velocity (meters/second)
    double velocity = 0;
    
    // Target diameter (meters)
    double targetSize = 0;
    
    // Gravity (meters/second^2)
    final double gravity = -9.81;
    
    // Steps
    final int steps = 10;

    // Horizontal distance traveled (meters)
    double x = 0;
    
    // Vertical distance traveled (meters)
    double y = 0;
    
    // Time passed (seconds)
    double time = 0;

    // Result
    String result = "miss";
    
    // Distance missed by (meters)
    double distanceMissedBy = 0;

    // Get distance to target (meters)
    distance = fireShot.getDistanceToTarget();

    // Get muzzle angle
    angle = Math.toRadians(fireShot.getAngle());

    // Get initial muzzle velocity
    velocity = fireShot.getVelocity();
    
    // Get target diameter (meters)
    targetSize = fireShot.getTargetSize();

    // Horizontal velocity = velocity * cos(angle)
    double xVelocity = velocity * Math.cos(angle);
    
    // Vertical velocity = velocity * sin(angle)
    double yVelocity = velocity * Math.sin(angle);
    
    // Time  
    double totalTime = - 2.0 * yVelocity / gravity;
    
    // Time increment
    double timeIncrement = totalTime / steps;
    
    // Horizontal distance increment
    double xIncrement = xVelocity * timeIncrement;

    for (int i = 1; i <= steps; i++) {
      
      time += timeIncrement;
      x += xIncrement;
      y = yVelocity * time + 0.5 * gravity * time * time;
      
    }

    // Calculate radius of target
    double radius = targetSize / 2;
    
    // If hit
    if (x == distance || (x >= (distance - radius) && x <= (distance + radius))) {
      
      result = "hit";
      
    // If miss
    } else {
      
      distanceMissedBy = distance - x;
      
    }
  
    fireShot.setResult(result);
    fireShot.setDistanceTraveled(x);
    fireShot.setDistanceMissedBy(distanceMissedBy);
    fireShot.setTimeTraveled(time);

     // check for hit
     assertEquals(fireShot.getResult(), "hit");
     
     // check for miss
     //assertEquals(fireShot.getResult(), "miss");
     
  }
  
}