package utils.debug;

import java.awt.*;

public class Logger {

    private static Boolean _enabled = true;

    public static void setEnabled(Boolean enabled) {
        _enabled = enabled;
    }

    public static void log(String message, Color color) {
        if (!_enabled) {
            return;
        }

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[2];
        String fileName = caller.getFileName();
        int lineNumber = caller.getLineNumber();
        String colorCode = getColorCode(color);

        String resetCode = "\u001B[0m";

        System.out.println(colorCode + "[" + fileName + ":" + lineNumber + "] "+ message + resetCode);
    }

    public static void log(String message) {
        log(message, Color.WHITE);
    }

    private static String getColorCode(Color color) {
        String colorCode;
        if (color.equals(Color.RED)) {
            colorCode = "\u001B[31m";
        } else if (color.equals(Color.GREEN)) {
            colorCode = "\u001B[32m";
        } else if (color.equals(Color.YELLOW)) {
            colorCode = "\u001B[33m";
        } else if (color.equals(Color.BLUE)) {
            colorCode = "\u001B[34m";
        } else if (color.equals(Color.CYAN)) {
            colorCode = "\u001B[36m";
        } else if (color.equals(Color.MAGENTA)) {
            colorCode = "\u001B[35m";
        } else if (color.equals(Color.WHITE)) {
            colorCode = "\u001B[37m";
        }
        else {
            colorCode = "\u001B[0m";
        }
        return colorCode;
    }
}
