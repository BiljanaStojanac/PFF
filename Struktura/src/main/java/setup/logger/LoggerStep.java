package setup.logger;

import java.util.ArrayList;
import java.util.List;

public class LoggerStep {

    private String nameOfStep;
    private boolean isExpected=false;
    private List<String> errors;
    private boolean skipped;
    private List<String> validImages;

    public List<String> getValidImages() {
        if(validImages==null){
            return  new ArrayList<>();
        }
        return validImages;
    }

    public void setValidImages(List<String> validImages) {
        this.validImages = validImages;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public void setSkipped() {
        this.skipped = true;
    }

    public LoggerStep(String nameOfStep, boolean isExpected) {
        this.nameOfStep = nameOfStep;
        this.isExpected = isExpected;
    }

    public LoggerStep(String nameOfStep) {
        this.nameOfStep = nameOfStep;
    }

    public LoggerStep(String nameOfStep, List<String> errors) {
        this.nameOfStep = nameOfStep;
        this.errors = errors;
    }

    public String getNameOfStep() {
        return nameOfStep;
    }

    public void setNameOfStep(String nameOfStep) {
        this.nameOfStep = nameOfStep;
    }

    public List<String> getErrors() {
        if(this.errors==null) this.errors=new ArrayList<>();
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getStatus(){
        if(this.getErrors().size()>0){
            return "FAIL";
        }else{
            return "PASS";
        }
    }


    @Override
    public String toString() {
        if(isExpected){
            if(isSkipped()) {
                return "Expected result step name : " + this.nameOfStep + " Status: " + "Skipped";
            }else {
                return "Expected result step name : " + this.nameOfStep + " Status: " + getStatus();
            }
        }else {
            if(isSkipped()) {
                return "Step name : " + this.nameOfStep + " Status: " + "Skipped";
            }else {
                return "Step name result step name : " + this.nameOfStep + " Status: " + getStatus();
            }
        }
    }
}
