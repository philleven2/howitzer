package howitzer.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import org.apache.log4j.Logger;

public class Users implements Serializable {

  private final static long serialVersionUID = 1L;
  
  final static Logger log = Logger.getLogger(Users.class.getName());
  
  private String userId;
  private int shots;
  private int hits;
  private int misses;
  private BigDecimal avgHits;
  private int rank;

  // Default constructor
  public Users() {

    log.debug("Created instance: " + this.toString());

  }

  public Users(String userId, int shots, int hits, int misses, BigDecimal avgHits, int rank) {

    this.userId = userId;
    this.shots = shots;
    this.hits = hits;
    this.misses = misses;
    this.avgHits = avgHits;
    this.rank = rank;
    
  }
  
  public String getUserId() {
    
    return userId;
    
  }

  public void setUserId(String userId) {
    
    this.userId = userId;
    
  }

  public int getShots() {
    
    return shots;
    
  }

  public void setShots(int shots) {
    
    this.shots = shots;
    
  }

  public int getHits() {
    
    return hits;
    
  }

  public void setHits(int hits) {
    
    this.hits = hits;
    
  }

  public int getMisses() {
    
    return misses;
    
  }

  public void setMisses(int misses) {
    
    this.misses = misses;
    
  }

  public BigDecimal getAvgHits() {
    
    return avgHits;
    
  }

  public void setAvgHits(BigDecimal avgHits) {
    
    this.avgHits = avgHits;
    
  }

  public int getRank() {
    
    return rank;
    
  }

  public void setRank(int rank) {
    
    this.rank = rank;
    
  }

  @Override
  public boolean equals(Object obj) {

      if (this == obj) {
        
        return true;
        
      }
      
      if (obj == null) {
        
          return false;
          
      }
      
      if (!(obj instanceof Users)) {
        
          return false;
          
      }
      
      Users other = (Users) obj;
      
      return Objects.equals(this.userId, other.userId) 
          && Objects.equals(this.shots, other.shots) 
          && Objects.equals(this.hits, other.hits)
          && Objects.equals(this.misses, other.misses)
          && Objects.equals(this.avgHits, other.avgHits)
          && Objects.equals(this.rank, other.rank);
                  
  }

  @Override
  public int hashCode() {
    
      return Objects.hash(this.userId, this.shots, this.hits, this.misses, this.avgHits, this.rank);
      
  }

}