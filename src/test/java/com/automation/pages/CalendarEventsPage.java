package com.automation.pages;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


import java.util.ArrayList;
import java.util.List;

public class CalendarEventsPage extends AbstractPageBase {
    @FindBy(id="prependedInput")
    private WebElement username;

    @FindBy(id="prependedInput2")
    private WebElement password;

    @FindBy(xpath="//span[@class='title title-level-1' and contains(text(),'Activities')]")
    private WebElement activitiesTab;

    @FindBy(xpath="//span[@class='title title-level-2' and contains(text(),'Calendar Events')]")
    private WebElement calendareventsmodule;

   @FindBy(xpath="//td[text()='Testers Meeting']/..//div//a")
   private WebElement threedots;

   @FindBy(xpath="//td[text()='Testers Meeting']/..//div/div/a/following-sibling::ul//a")
   public List<WebElement> vieweditdelete;

    @FindBy(xpath = "//div[@class='column-manager dropdown']")
    private WebElement grid;

    @FindBy(css = "[data-role='renderable']")
    private List<WebElement> showButton;

    @FindBy(css = "[class='title-cell']")
    private List<WebElement> nameButton;

    @FindBy(xpath = "//span[text()='Title']")
    private WebElement titleheader;

    @FindBy(css = "[class='btn-group'] > a")
    private WebElement createCalEventButton;

    @FindBy(css = "[class='btn-group pull-right'] > a")
    private WebElement saveandclosebutton;

    @FindBy(css = "[class='btn-group pull-right open']  li")
    private List<WebElement> buttonoptions;

    @FindBy(css = "[class='pull-left btn-group icons-holder'] > a")
    private WebElement cancelbutton;

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement subtitle;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    public WebElement startTime;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    public WebElement endTime;

    @FindBy(css = "[id^='oro_calendar_event_form_allDay']")
    public WebElement alldayeventbox;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startdate;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    public WebElement enddate;

    @FindBy(css = "[data-name='recurrence-repeat']")
    public WebElement repeat;

    @FindBy(css = "[data-name='recurrence-repeats']")
    public WebElement repeatsdropdown;

    @FindBy(xpath="(//input[@type='radio'])[1]")
    public WebElement repeat_everyButton;

    @FindBy(xpath="(//input[@type='radio'])[2]")
    public WebElement weekdaybutton;

    @FindBy(xpath="(//input[@type='radio'])[3]")
    public WebElement endsbutton;

    @FindBy(xpath="//div[@class='control-group recurrence-summary alert-info']")
    public WebElement summary;

    @FindBy(xpath="//span[text()='Daily every 1 day']")
    public WebElement dailyoneday;

    @FindBy(xpath="//span[text()=', end after 10 occurrences']")
    public WebElement endphrase;

    @FindBy(xpath="//div[@class='controls recurrence-subview-control recurrence-subview-control__items']/div/label/span[text()='After']")
    public WebElement afterButton;

    @FindBy(xpath="//div[@class='controls recurrence-subview-control recurrence-subview-control__items']/div/label/span[text()='After']/following-sibling::input")
    public WebElement inputBoxOfafter;

    @FindBy(xpath="(//input[@type='radio'])[5]")
    public WebElement byRadio;

    @FindBy(xpath="((//input[@type='radio'])[5]/../..//div/input)[2]")
    public WebElement byDateRadio;

    @FindBy(css = "[data-handler='selectMonth']")
    public WebElement selectMonth;

    @FindBy(css = "[data-handler='selectYear']")
    public WebElement selectYear;

    @FindBy(xpath="(//td[@data-handler='selectDay'])[18]")
    public WebElement selectDate;

    @FindBy(xpath="//span[text()=', end by Nov 18, 2021']")
    public WebElement endDatephrase;

    @FindBy(xpath="//input[@value='monday']")
    public WebElement mondayButton;

    @FindBy(xpath="//input[@value='friday']")
    public WebElement fridayButton;

    public void login(){
        username.sendKeys(ConfigurationReader.getProperty("store_manager"));
        password.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.wait(3);
    }
    public void navigate(){
        Actions actions = new Actions(driver);
        actions.moveToElement(activitiesTab).
                pause(2000).click(calendareventsmodule).
                build().perform();

        BrowserUtils.wait(5);
    }
    public boolean verifyViewDeleteEdit() {
        Actions actions = new Actions(driver);
        actions.moveToElement(threedots).perform();
        boolean result=false;
        for (WebElement each : vieweditdelete) {
              result = each.isDisplayed();
        }
        return result;
    }

    public boolean verifyTitleDisplayed() {
        grid.click();
        for (int i = 1; i < showButton.size(); i++) {
            if (!nameButton.get(i).getText().equals("Title")) {
                showButton.get(i).click();
            }
        }
        return titleheader.isDisplayed();
    }
    public void clickToCreateCalendarEvent() {
        BrowserUtils.wait(3);
        wait.until(ExpectedConditions.elementToBeClickable(createCalEventButton)).click();
        BrowserUtils.waitForPageToLoad(15);
    }
    public List<String> saveCloseOptions() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='btn-group pull-right'] > a")));
        wait.until(ExpectedConditions.elementToBeClickable(saveandclosebutton)).click();
        BrowserUtils.wait(5);
        wait.until(ExpectedConditions.visibilityOfAllElements(buttonoptions));
        return BrowserUtils.getTextFromWebElements(buttonoptions);
    }

    public String verifySubtitle() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='pull-left btn-group icons-holder'] > a")));
        BrowserUtils.wait(5);
        wait.until(ExpectedConditions.elementToBeClickable(cancelbutton)).click();
        BrowserUtils.wait(5);
        String actualSubTitle = subtitle.getText().trim();
        return actualSubTitle;
    }

    public String getStartTime() {
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(startTime));
        return startTime.getAttribute("value");
    }

    public String getEndTime() {
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(endTime));
        return endTime.getAttribute("value");
    }

    public void clickStartTime() {
//        BrowserUtils.wait(3);
//        startTime.clear();
//        startTime.sendKeys("9:00 PM");
//        startTime.sendKeys(Keys.ENTER);
//        startTime.submit();
    }

    public WebElement getStartTime2() {
        return startTime;
    }

    public void clickTime(String h) {

        driver.findElement(By.xpath("//li[text()='" + h + ":00 PM']")).click();
    }

    public void clickalldaybox() {
        wait.until(ExpectedConditions.elementToBeClickable(alldayeventbox)).click();
    }
    public boolean notDisplayTimeandDate() {
        wait.until(ExpectedConditions.invisibilityOf(startTime));
        wait.until(ExpectedConditions.invisibilityOf(endTime));
        wait.until(ExpectedConditions.visibilityOf(startdate));
        wait.until(ExpectedConditions.visibilityOf(enddate));
        if(!(startTime.isDisplayed() && endTime.isDisplayed() && startdate.isDisplayed() && enddate.isDisplayed())){
            return true;
        }
        return false;
    }

    public void clickRepeatButton() {
        BrowserUtils.wait(3);
        wait.until(ExpectedConditions.visibilityOf(repeat));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-name='recurrence-repeat']"))).click();
    }

    public String defaultDropDown() {
        Select select = new Select(repeatsdropdown);
        select.selectByVisibleText("Daily");
        return select.getFirstSelectedOption().getAttribute("value");
    }
    public List<String> selectDropdownOptions() {
        Select select = new Select(repeatsdropdown);
        List<WebElement> allOptions = select.getOptions();
        return BrowserUtils.getTextFromWebElements(allOptions);
    }

    public String getSummaryText(){
        return summary.getText();
    }

    public void clickAfterSend10(){
        afterButton.click();
        inputBoxOfafter.sendKeys("10", Keys.ENTER);
    }

    public String selectDate(){
          byRadio.click();
          byDateRadio.sendKeys("Nov 18, 2021",Keys.ENTER);
          return summary.getText();
    }
    public void clickBy(){
         byRadio.click();
    }
    public void clickByDateRadio(){
        byDateRadio.click();
    }
    public WebElement clickMonth(){
        return selectMonth;
    }
    public WebElement clickYear(){
       return selectYear;
    }
    public void chooseDate(){
        wait.until(ExpectedConditions.visibilityOf(selectDate)).click();
    }

    public void selectweekly() {
        Select select = new Select(repeatsdropdown);
        select.selectByVisibleText("Weekly");
    }
    public void clickMonAndFri(){
        mondayButton.click();
        fridayButton.click();
    }

}


