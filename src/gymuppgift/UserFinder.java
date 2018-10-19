/*

 */
package gymuppgift;

import java.nio.file.Path;
import java.nio.file.*;
import java.util.Scanner;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author stevi
 */
public class UserFinder {

    protected Path path = Paths.get("src/gymuppgift/customers.txt");

    protected String hasName(String name) throws IOException {

        String[] lineOne;
        String datePayed = "";

        Scanner scan = new Scanner(path);
        name = name.trim();
        while (scan.hasNext()) {

            lineOne = scan.nextLine().trim().split(",");
            datePayed = scan.nextLine().trim();

            if (lineOne[1].trim().equalsIgnoreCase(name)) {
                return lineOne[1].trim() + "," + lineOne[0] + "," + datePayed;//string med all info 
            }

            if (lineOne[0].trim().equalsIgnoreCase(name)) {
                return lineOne[1].trim() + "," + lineOne[0] + "," + datePayed;//all info
            }
        }
        return null;
    }

    protected void logWorkout(String nameSscPaydate) throws IOException {
        String name = nameSscPaydate.substring(0, nameSscPaydate.indexOf(","));
        String sSc = nameSscPaydate.substring(nameSscPaydate.indexOf(",") + 1, nameSscPaydate.lastIndexOf(",") - 1);
        Path log = Paths.get("src/gymuppgift/workoutLog.txt");
        logWriter(log, name, sSc);

    }

    protected void logWriter(Path log, String name, String sSc) throws IOException {
        LocalDate n = LocalDate.now();
        if (!Files.exists(log)) {
            Files.createFile(log);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(log, StandardCharsets.UTF_8, StandardOpenOption.APPEND);) {
            writer.write(name + "," + sSc + " tranade den: " + n + "\n");

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    protected boolean validatePayment(String userInfo) throws Exception {
        LocalDate now = LocalDate.now();
        String s = (userInfo.substring(userInfo.lastIndexOf(",") + 1).trim());

        LocalDate payDate = LocalDate.parse(s);
        int a = (int) ChronoUnit.DAYS.between(now, payDate.plusYears(1));

        

        if (a >= 0) {
            logWorkout(userInfo);
            return true;
        }

        return false;
    }

}
