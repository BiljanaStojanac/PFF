package setup.logger;

import cucumber.api.Scenario;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class LoggerTestCase {

    private List<LoggerStep> steps;
    private String nameOfTestCase;
    private String numOfTestCase;
    private String status;
    private int damage;
    private int frequency;
    private int weight;

    static String extractInt(String str)
    {
        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^\\d]", " ");

        // Remove extra spaces from the beginning
        // and the ending of the string
        str = str.trim();

        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" +", "");

        if (str.equals(""))
            return "-1";

        return str;
    }


    public void getFrequencyAndDamage() {
        String test = extractInt(nameOfTestCase);
        frequency = Integer.parseInt(String.valueOf(test.charAt(0)));
        damage = Integer.parseInt(String.valueOf(test.charAt(1)));
    }


    public int getWeight() {
        double result = Math.pow(2,damage)*Math.pow(2,frequency);
        return (int) result;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus() {
        int errorsSize = 0;
        if (steps == null) return;
        for (LoggerStep step : steps) {
            errorsSize += step.getErrors().size();
        }
        if (errorsSize <= 0) {
            this.status = "PASS";
        } else {
            this.status = "FAIL";
        }
    }

    public void setStatus(String reasonForSkip) {
        this.status = "SKIPPED - " + reasonForSkip;
    }

    public LoggerTestCase(List<LoggerStep> steps, String nameOfTestCase, String numOfTestCase, String status) {
        this.steps = steps;
        this.nameOfTestCase = nameOfTestCase;
        this.numOfTestCase = numOfTestCase;
        this.status = status;
    }

    public LoggerTestCase(List<LoggerStep> steps, String nameOfTestCase, String numOfTestCase) {
        this.steps = steps;
        this.nameOfTestCase = nameOfTestCase;
        this.numOfTestCase = numOfTestCase;

    }

    public LoggerTestCase() {
    }

    public List<LoggerStep> getSteps() {
        if (steps == null) steps = new ArrayList<>();
        return steps;
    }

    public void setSteps(List<LoggerStep> steps) {
        this.steps = steps;
    }

    public String getNameOfTestCase() {
        return nameOfTestCase;
    }

    public void setNameOfTestCase(String nameOfTestCase) {
        this.nameOfTestCase = nameOfTestCase;
    }

    public String getNumOfTestCase() {
        return numOfTestCase;
    }

    public void setNumOfTestCase(String numOfTestCase) {
        this.numOfTestCase = numOfTestCase;
    }

    public boolean isPassed() {
        return this.status.equals("PASS");
    }

    public boolean isSkipped() {
        return this.status.contains("SKIPPED");
    }


    @Override
    public String toString() {
        return "[ CLA-" + this.numOfTestCase + " ]" + nameOfTestCase + "  Status: " + status;
    }


}
