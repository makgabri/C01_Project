package org.jth.exceptions;

public class ConnectionFailedException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 3332556357607456496L;

  public ConnectionFailedException(String msg) {
    super(msg);
  }
  
  public ConnectionFailedException() {
    super();
  }

}
