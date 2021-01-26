package setup.logger;

import java.util.ArrayList;
import java.util.List;

public class LoggerSection {

    private String nameOfSection;
    private List<LoggerTestCase> l2TestCaseR2List;

    public int weightForAll(){
        int weight = 0;
        for (LoggerTestCase eachTC : l2TestCaseR2List) {
            weight += eachTC.getWeight();
        }
        return weight;
    }

    public LoggerSection() {
    }

    public LoggerSection(String nameOfSection) {
        this.nameOfSection = nameOfSection;
    }

    public LoggerSection(String nameOfSection, List<LoggerTestCase> l2TestCaseR2List) {
        this.nameOfSection = nameOfSection;
        this.l2TestCaseR2List = l2TestCaseR2List;
    }

    public void setNameOfSection(String nameOfSection) {
        this.nameOfSection = nameOfSection;
    }

    public String getNameOfSection() {
        return nameOfSection;
    }

    public List<LoggerTestCase> getL2TestCaseR2List() {
        if (l2TestCaseR2List == null) l2TestCaseR2List = new ArrayList<>();
        return l2TestCaseR2List;
    }

    public void setL2TestCaseR2List(List<LoggerTestCase> l2TestCaseR2List) {
        this.l2TestCaseR2List = l2TestCaseR2List;
    }

    public void printSection() {
        System.out.println("Section name: " + this.nameOfSection);
        l2TestCaseR2List.forEach(item -> System.out.println(item.getNameOfTestCase()));
    }

}
