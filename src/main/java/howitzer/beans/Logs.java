package howitzer.beans;

import java.io.Serializable;
import java.util.Objects;
import org.apache.log4j.Logger;

public class Logs implements Serializable {

  private final static long serialVersionUID = 1L;

  private static final Logger log = Logger.getLogger(Logs.class.getName());
  
  private String dateCreated;
  private String logger;
  private String level;
  private String message;

  /**
   * Default Constructor.
   */
  public Logs() {

    log.debug("Created instance: " + this.toString());

  }

  public Logs(String dateCreated, String logger, String level, String message) {

    this.dateCreated = dateCreated;
    this.logger = logger;
    this.level = level;
    this.message = message;

  }

  public String getDateCreated() {
    
    return dateCreated;
    
  }

  public void setDateCreated(String dateCreated) {
    
    this.dateCreated = dateCreated;
    
  }

  public String getLogger() {
    
    return logger;
  }
  

  public void setLogger(String logger) {
    
    this.logger = logger;
    
  }

  public String getLevel() {
    
    return level;
    
  }

  public void setLevel(String level) {
    
    this.level = level;
    
  }

  public String getMessage() {
    
    return message;
    
  }

  public void setMessage(String message) {
    
    this.message = message;
    
  }

  @Override
  public boolean equals(Object obj) {

      if (this == obj) {
        
        return true;
        
      }
      
      if (obj == null) {
        
          return false;
          
      }
      
      if (!(obj instanceof Logs)) {
        
          return false;
          
      }
      
      Logs other = (Logs) obj;
      
      return Objects.equals(this.dateCreated, other.dateCreated) 
          && Objects.equals(this.logger, other.logger) 
          && Objects.equals(this.level, other.level)
          && Objects.equals(this.message, other.message);
                  
  }

  @Override
  public int hashCode() {
    
      return Objects.hash(this.dateCreated, this.logger, this.level, this.message);
      
  }

}