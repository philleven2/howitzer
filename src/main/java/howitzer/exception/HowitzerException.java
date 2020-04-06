package howitzer.exception;

public class HowitzerException extends Exception {

  private static final long serialVersionUID = 5182137632236335764L;

  /**
   * Constructor for HowitzerException.
   */
  public HowitzerException() {

    super();

  }

  /**
   * Constructor for HowitzerException.
   * 
   * @param s
   */
  public HowitzerException(String s) {

    super(s);

  }

  /**
   * Constructor for HowitzerException.
   * 
   * @param s
   * @param t
   */

  public HowitzerException(String s, Throwable t) {

    super(s, t);

  }

}
