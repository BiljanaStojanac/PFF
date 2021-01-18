package setup.driver.custom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class FireFoxDriverWithException extends FirefoxDriver {

    public FireFoxDriverWithException() {
    }

    @Override
    public List<WebElement> findElements(By by) {
        try {
            return super.findElements(by);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public WebElement findElement(By by) {
        try {
            return super.findElement(by);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    protected WebElement findElement(String by, String using) {
        try {
            return super.findElement(by, using);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    protected List<WebElement> findElements(String by, String using) {
        try{
            return super.findElements(by, using);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public WebElement findElementById(String using) {
        try{
            return super.findElementById(using);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<WebElement> findElementsById(String using) {
        try {
            return super.findElementsById(using);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public WebElement findElementByLinkText(String using) {
        try {
            return super.findElementByLinkText(using);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<WebElement> findElementsByLinkText(String using) {
        try{
            return super.findElementsByLinkText(using);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public WebElement findElementByPartialLinkText(String using) {
        try {
            return super.findElementByPartialLinkText(using);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<WebElement> findElementsByPartialLinkText(String using) {
        try{
            return super.findElementsByPartialLinkText(using);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public WebElement findElementByTagName(String using) {
        try {
            return super.findElementByTagName(using);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<WebElement> findElementsByTagName(String using) {
        try {
            return super.findElementsByTagName(using);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public WebElement findElementByName(String using) {
        try{
            return super.findElementByName(using);
        }catch (Exception e){
            return  null;
        }
    }

    @Override
    public List<WebElement> findElementsByName(String using) {
        try {
            return super.findElementsByName(using);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public WebElement findElementByClassName(String using) {
        try {
            return super.findElementByClassName(using);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<WebElement> findElementsByClassName(String using) {
        try{
            return super.findElementsByClassName(using);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public WebElement findElementByCssSelector(String using) {
        try {
            return super.findElementByCssSelector(using);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<WebElement> findElementsByCssSelector(String using) {
        try {
            return super.findElementsByCssSelector(using);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public WebElement findElementByXPath(String using) {
        try {
            return super.findElementByXPath(using);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<WebElement> findElementsByXPath(String using) {
        try {
            return super.findElementsByXPath(using);
        }catch (Exception e){
            return null;
        }
    }
}
