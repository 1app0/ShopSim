package shared.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Log {
  private static Log instance;
  private static Lock lock = new ReentrantLock();
  private BufferedWriter out;
  private File logfile;

  private Log() {
    logfile = new File("LogFile.txt");
  }

  public void addLog(String log) {
    LogLine logLine = new LogLine(log);
    String loggedInfo = logLine.toString();
    System.out.println(loggedInfo);
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(logfile, true));
      out.write(loggedInfo + "\n");
      out.flush();
      out.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void clear() {}

  public static Log getInstance() {
    if (instance == null) {
      synchronized (lock) {
        if (instance == null) {
          instance = new Log();
        }
      }
    }
    return instance;
  }
}
