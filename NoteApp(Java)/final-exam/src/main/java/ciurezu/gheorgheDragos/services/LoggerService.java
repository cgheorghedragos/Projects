package ciurezu.gheorgheDragos.services;

import java.io.*;
import java.util.Date;

public final class LoggerService {
    private static final String activityLogName = "activityLog.al";
    private static final String systemLogName = "systemLog.sl";
    private static final String criticalErrorLogName = "criticalErrorLog.cl";
    private static final String fileLocation = "src/main/java/ciurezu/gheorgheDragos/logs";

    public static void activityLog(String message) throws IOException {
        Logger(fileLocation, message, activityLogName);
    }

    public static void systemLog(String message) throws IOException {

        Logger(fileLocation, message, systemLogName);
    }

    public static void criticalErrorLog(String message) throws IOException {

        Logger(fileLocation, message, criticalErrorLogName);
    }

    private static void Logger(String fileLocation, String message, String fileLogName) throws IOException {
        Date currentDate = new Date();
        FileWriter fileWriter = new FileWriter(fileLocation + "/" + fileLogName,true);
        fileWriter.write("..............." + "\n");
        fileWriter.write(currentDate + "\n");
        fileWriter.write(message + "\n");
        fileWriter.close();
    }
}
