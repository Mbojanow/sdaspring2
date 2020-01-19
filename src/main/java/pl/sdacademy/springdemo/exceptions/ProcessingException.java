package pl.sdacademy.springdemo.exceptions;

public class ProcessingException extends RuntimeException {
  public ProcessingException() {
    super();
  }

  public ProcessingException(final String message) {
    super(message);
  }

  public ProcessingException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ProcessingException(final Throwable cause) {
    super(cause);
  }

  protected ProcessingException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
