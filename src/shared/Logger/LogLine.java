package shared.Logger;

public class LogLine {
  private String text;
  private DateTime dateTime;

  public LogLine(String text) {
    this.text = text;
    dateTime = new DateTime();
  }

  public String toString() {
    return dateTime.getTimestamp() + " " + text;
  }
}
