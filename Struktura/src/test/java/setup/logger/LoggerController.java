package setup.logger;

import com.webfirmframework.wffweb.tag.html.*;
import com.webfirmframework.wffweb.tag.html.attribute.Charset;
import com.webfirmframework.wffweb.tag.html.attribute.Href;
import com.webfirmframework.wffweb.tag.html.attribute.Rel;
import com.webfirmframework.wffweb.tag.html.attribute.Src;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnClick;
import com.webfirmframework.wffweb.tag.html.attribute.global.ClassAttribute;
import com.webfirmframework.wffweb.tag.html.attribute.global.Id;
import com.webfirmframework.wffweb.tag.html.attribute.global.Style;
import com.webfirmframework.wffweb.tag.html.attribute.global.Title;
import com.webfirmframework.wffweb.tag.html.attributewff.CustomAttribute;
import com.webfirmframework.wffweb.tag.html.formsandinputs.Button;
import com.webfirmframework.wffweb.tag.html.links.Link;
import com.webfirmframework.wffweb.tag.html.metainfo.Head;
import com.webfirmframework.wffweb.tag.html.metainfo.Meta;
import com.webfirmframework.wffweb.tag.html.programming.Script;
import com.webfirmframework.wffweb.tag.html.stylesandsemantics.Div;
import com.webfirmframework.wffweb.tag.htmlwff.NoTag;
import com.webfirmframework.wffweb.tag.repository.TagRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public enum LoggerController {
    INSTANCE;

    LoggerSection l1SectionR2 = null;
    private String testingBrowser;
    private String webAppVersion;
    private String browserVersion;
    private String os;
    private String executionTime;

    private List<Long> sumArray = new ArrayList<>();


    public List<Long> getSumArray() {
        return sumArray;
    }

    public long calculateSum() {
        return sumArray.stream().mapToLong(time -> time).sum();
    }

    public void setSumArray(List<Long> sumArray) {
        this.sumArray = sumArray;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getTestingBrowser() {
        return testingBrowser;
    }

    public void setTestingBrowser(String testingBrowser) {
        this.testingBrowser = testingBrowser;
    }

    public String getWebAppVersion() {
        return webAppVersion;
    }

    public void setWebAppVersion(String webAppVersion) {
        this.webAppVersion = webAppVersion;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public void setL1SectionR2(LoggerSection l1SectionR2) {
        this.l1SectionR2 = l1SectionR2;
    }

    public LoggerSection getL1SectionR2() {
        if (l1SectionR2 == null) {
            l1SectionR2 = new LoggerSection();
        }
            return l1SectionR2;
    }

    public void restartSection() {
        this.l1SectionR2 = new LoggerSection();
    }


    public void addToSection(LoggerTestCase l2TestCaseR2) {
        if (l1SectionR2 == null) return;
        l1SectionR2.getL2TestCaseR2List().add(l2TestCaseR2);
    }

    public void addStepToCurrentTestCase(LoggerTestCase l2TestCaseR2, LoggerStep l3StepR2) {
        if (l2TestCaseR2 == null) return;
        if (l3StepR2 == null) return;

        l2TestCaseR2.getSteps().add(l3StepR2);
    }

    public void addErrorToStep(LoggerStep l3StepR2, String error) {
        if (l3StepR2 == null) return;
        if (error == null || error.equals("")) return;
        l3StepR2.getErrors().add(error);
    }

    public void printAllDFS() {
        this.l1SectionR2.getL2TestCaseR2List().forEach(item -> printTestCase(item));
    }

    private void printTestCase(LoggerTestCase item) {
        if (item == null) return;
        System.out.println(item.toString());
        item.getSteps().forEach(step -> printStep(step));
    }

    private void printStep(LoggerStep step) {
        if (step == null) return;
        System.out.println(step.toString());
        if (step.getErrors().isEmpty()) return;
        step.getErrors().forEach(System.out::println);
    }

    public void makeHtmlDocumentReportForCurrentSection() {
        Html html = new Html(null) {{
            Head head = new Head(this);
            head.appendChild(new Meta(this, new Charset("UTF-8")));
            head.appendChild(new Link(this, new Href("https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"), new Rel("stylesheet")));
           // head.appendChild(new Link(this, new Href("https://cdn.rawgit.com/mfd/f3d96ec7f0e8f034cc22ea73b3797b59/raw/856f1dbb8d807aabceb80b6d4f94b464df461b3e/gotham.css"), new Rel("stylesheet")));
            head.appendChild(new Script(this, new Src("https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js")));
            head.appendChild(new Script(this, new Src("https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js")));
            new Body(this,
                    new ClassAttribute("body-styles-cls"), new Style("margin-left: 5%; margin-right: 5%;     color: white; background-color: #000000ba;"));
            new Title(l1SectionR2.getNameOfSection());
            // new Link(this,new Href("../resources/reports/report.css"),new Rel("stylesheet"));
            // new Link(this,new Href("https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"),new Rel("stylesheet"));
            //new Script(this,new Src("https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"));
            //new Script(this,new Src("https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"));
        }};

        Body body = TagRepository.findOneTagAssignableToTag(Body.class, html);
        Style styleH1 = new Style("margin-top: 5px; margin-bottom: 5px;");
        Div h1Div = new Div(null,new Style("text-align:center;"));
        H1 h1 = new H1(body, styleH1);
        h1.addInnerHtml(new NoTag(null, this.l1SectionR2.getNameOfSection() + "<br>"));
        h1Div.appendChild(h1);
        body.appendChild(h1Div);
        //ToDo version and browser info
        Div infoAndLogoDiv = new Div(body, new Id("infoAndLogoDiv"),new Style("display: flex; flex-wrap: wrap; flex-direction: row; padding-top: 5%; padding-bottom: 2%;"));
        Div logoDiv = new Div(infoAndLogoDiv, new ClassAttribute("logoDiv"),new Style("margin-left: 0%"));
        NoTag logo=new NoTag(logoDiv,"<img style=\"width:250px\" src=\"data:image/png;base64,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\"></img>");
        logoDiv.appendChild(logo);
        Div infoDiv = new Div(null, new Id("infoVersion"),new Style("margin-left: 25%"));
        P browser = new P(infoDiv, new Id("infoBrowser"));
        browser.addInnerHtml(new NoTag(browser, "<strong>Browser:" + testingBrowser + " </strong> <br>"));
        P browserVersion = new P(infoDiv, new Id("infoBrowserVersion"));
        browserVersion.addInnerHtml(new NoTag(browserVersion, "<strong>Browser version: " + this.browserVersion + "</strong>"));
        P appVersion = new P(infoDiv, new Id("appVersion"));
        appVersion.addInnerHtml(new NoTag(appVersion, "<strong>" + webAppVersion + "</strong>"));
        P osP = new P(infoDiv, new Id("os"));
        osP.addInnerHtml(new NoTag(osP, "<strong>OS: " + os + "</strong>"));
        P timeAndDateCreation = new P(infoDiv, new Id("timeAndDate"));
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        timeAndDateCreation.addInnerHtml(new NoTag(timeAndDateCreation, "<strong>Date and time: " + date + "</strong>"));
        infoDiv.appendChild(osP);
        infoDiv.appendChild(browser);
        infoDiv.appendChild(browserVersion);
        infoDiv.appendChild(appVersion);
        P allExecutionTime = new P(infoDiv, new Id("allExecutionTime"));
        allExecutionTime.addInnerHtml(new NoTag(allExecutionTime, executionTime));
        infoDiv.appendChild(allExecutionTime);
        infoAndLogoDiv.appendChild(logoDiv);
        infoAndLogoDiv.appendChild(infoDiv);
        body.appendChild(infoAndLogoDiv);
        Div testCaseContainer = new Div(null, new Id("testCaseContainer"));
        body.appendChild(testCaseContainer);
        int stepsContainerIndex = 0;
        for (LoggerTestCase testCaseR2 : this.l1SectionR2.getL2TestCaseR2List()) {
            String testCaseClassName = "";
            Style styleTC;
            if (testCaseR2.isPassed()) {
                testCaseClassName = "alert alert-success";
                styleTC = new Style("color: #3E5D3F; background-color: #DFF7D5; border-color: #4656388c;  padding: 3; " +
                        "margin-bottom: 3; border: 1px solid black; border-radius: 1px;");
            } else if (testCaseR2.isSkipped()) {
                testCaseClassName = "alert alert-info";
                styleTC = new Style("color: #004085; background-color: #cce5ff; border-color: #b8daff;  padding: 3; " +
                        "margin-bottom: 3; border: 1px solid black; border-radius: 1px;");
            } else {
                testCaseClassName = "alert alert-danger";
                styleTC = new Style(" color: #CA5151;background-color: #FDC9C9; border-color: #96535E;" +
                        " padding: 3; margin-bottom: 3;border: 1px solid black; border-radius: 1px;");
            }
            Div testCase = new Div(null, new Id("testCase"), new ClassAttribute(testCaseClassName));
            Style styleH2 = new Style("margin-top: 5px; margin-bottom: 5px; font-size:15px;");
            H2 titleTestCase = new H2(testCase);
            titleTestCase.addInnerHtml(new NoTag(null, testCaseR2.toString()));
            testCase.appendChild(titleTestCase);
            appendStepsToTestCase(testCase, testCaseR2, stepsContainerIndex);
            testCaseContainer.appendChild(testCase);
            stepsContainerIndex++;
        }
        pattern = "ddMMyyyyHH";
        simpleDateFormat = new SimpleDateFormat(pattern);
        date = simpleDateFormat.format(new Date());
        if(testingBrowser==null || l1SectionR2.getNameOfSection()==null){
            testingBrowser="Default";
            l1SectionR2.setNameOfSection("Default");
        }
        //ToDo -> Srediti null vrednost getNameOfSection kada imamo jedan TC u feature fajlu
        Script script=new Script(null);
        script.addInnerHtml(new NoTag(script,"function changeButtonName(e) { var isExpended=e.getAttribute(\"aria-expanded\"); if(isExpended=='true'){e.innerHTML='Expand  test case steps';}else{e.innerHTML='Collapse test case steps';}}"));
        Script script2=new Script(null);
        script2.addInnerHtml(new NoTag(script,"function changeButtonNameErrors(e) { var isExpended=e.getAttribute(\"aria-expanded\"); if(isExpended=='true'){e.innerHTML='Expand step errors';}else{e.innerHTML='Collapse step errors';}}"));
        body.appendChildren(script,script2);
        File file = new File(new File("").getAbsolutePath() + "/src/test/resources/reports/" + l1SectionR2.getNameOfSection().trim() + testingBrowser.toUpperCase() + date + ".html");
        try {
            if (file.createNewFile()) {
                System.out.println("File created, path: " + file.getAbsolutePath());
            } else {
                System.out.println("File overwritten, path: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println(file.getAbsolutePath());
        }
        try {
            html.toOutputStream(new FileOutputStream(file.getAbsolutePath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void appendStepsToTestCase(Div testCase, LoggerTestCase testCaseR2, int num) {
        Button buttonDropDown = new Button(null, new CustomAttribute("data-toggle", "collapse"), new CustomAttribute("data-target", "#stepsId" + num));
        buttonDropDown.addInnerHtml(new NoTag(null, "Expand  test case steps"));
        buttonDropDown.addAttributes(new OnClick("changeButtonName(this)"));



        Style style = new Style("padding-right:0px; margin-left:30px;");
        Div listOfSteps = new Div(testCase, new ClassAttribute("container collapse"), new Id("stepsId" + num), style);
        int stepErrorContainerIndex = 0;
        for (LoggerStep stepR2 : testCaseR2.getSteps()) {

            String stepClassName = "";
            Style styleTC = null;
            if (stepR2.getStatus().equals("PASS") && !stepR2.isSkipped()) {
                stepClassName = "alert alert-success";
                styleTC = new Style("color: #3E5D3F; background-color: #DFF7D5; border-color: #4656388c;  padding: 3; " +
                        "margin-bottom: 3; border: 1px solid black; border-radius: 1px;");
            } else if (stepR2.getStatus().equals("PASS") && stepR2.isSkipped()) {
                stepClassName = "alert alert-warning";
                //styleTC=new Style("color: #004085; background-color: #cce5ff; border-color: #b8daff;  padding: 3; " + "margin-bottom: 3; border: 1px solid black; border-radius: 1px;");
                styleTC = new Style("");
            } else {
                stepClassName = "alert alert-danger";
                styleTC = new Style(" color: #CA5151;background-color: #FDC9C9; border-color: #96535E;" +
                        " padding: 3; margin-bottom: 3;border: 1px solid black; border-radius: 1px;");
            }
            Div step = new Div(listOfSteps, new Id("stepId" + num), new ClassAttribute(stepClassName), styleTC);
            H3 h3 = new H3(step);
            h3.addInnerHtml(new NoTag(null, stepR2.toString()));
            step.appendChild(h3);
            if (stepR2.getStatus().equals("PASS") && !stepR2.isSkipped()) {
                for (String img : stepR2.getValidImages()) {
                    Style styleS = new Style("margin-left: 15px; margin-top: 10px; margin-bottom: 5px;");
                    P litem = new P(step, styleS);
                    litem.addInnerHtml(new NoTag(null, "<strong>Valid screenshot: </strong><br> " + img));
                    Hr hr = new Hr(null, new Style("width:97%;"));
                    step.appendChild(litem);
                    step.appendChild(hr);
                }
            }
            appendErrorsToStep(step, stepR2, stepErrorContainerIndex);
            listOfSteps.appendChild(step);
            stepErrorContainerIndex++;
        }
        testCase.appendChild(buttonDropDown);
        testCase.appendChild(listOfSteps);
    }

    private void appendErrorsToStep(Div step, LoggerStep stepR2, int num) {
        if (stepR2.getErrors().size() < 1) {
            return;
        }
        int errorIndex = 1;
        int uniqueNum = new Random().nextInt(1000);
        Div errorDiv = new Div(null, new ClassAttribute("container collapse"), new Id("errorsId" + num + uniqueNum));
        Button buttonDropDown = new Button(null, new CustomAttribute("data-toggle", "collapse"), new CustomAttribute("data-target", "#errorsId" + num + uniqueNum));
        buttonDropDown.addInnerHtml(new NoTag(null, "Expand step errors"));
        buttonDropDown.addAttributes(new OnClick("changeButtonNameErrors(this)"));
        for (String error : stepR2.getErrors()) {
            Style style = new Style("margin-left: 15px; margin-top: 10px; margin-bottom: 5px;");
            P litem = new P(errorDiv, style);
            litem.addInnerHtml(new NoTag(null, "[Error " + errorIndex + " ]" + error));
            Hr hr = new Hr(null, new Style("width:97%;"));
            errorDiv.appendChild(litem);
            errorDiv.appendChild(hr);
            errorIndex++;
        }
        step.appendChild(buttonDropDown);
        step.appendChild(errorDiv);
    }
}
