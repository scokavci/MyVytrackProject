package com.automation.tests;

import com.automation.pages.CalendarEventsPage;
import  static org.testng.Assert.*;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DateTimeUtilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class CalendarEventsTests extends AbstractTestBase {
    /**
     * Test Case #1
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Hover on three dots “…” for “Testers meeting”
     * calendar event
     * 5.Verify that “view”, “edit” and “delete” options
     * are available
     */
    @Test()
    public void testCase1(){
        test = report.createTest("Verify view,edit,delete options ");

        CalendarEventsPage calendareventspage = new CalendarEventsPage();
        calendareventspage.login();
        calendareventspage.navigate();

        calendareventspage.verifyViewDeleteEdit();
        test.pass("View/Edit/Delete displayed!");
    }
    /**
     * Test Case #2
     * 4. Click on “Grid Options” button
     * 5. Deselect all options except “Title”
     * 6. Verify that “Title” column still displayed
     */
   @Test
    public void testCase2(){
       test = report.createTest("Verify Title only display after deselect others in grid ");

       CalendarEventsPage calendareventspage = new CalendarEventsPage();
       calendareventspage.login();
       calendareventspage.navigate();

       assertTrue( calendareventspage.verifyTitleDisplayed() );

       test.pass("Title column is displayed!");
    }
    /**
     * TestCase #3
     * 4.Click on “Create Calendar Event” button
     * 5. Expand “Save And Close” menu
     * 6. Verify that “Save And Close”, “Save And New”
     * and “Save” options are available
     */
    @Test
    public void testCase3(){
        test = report.createTest("Verify Save And Close options display ");

        CalendarEventsPage calendareventspage = new CalendarEventsPage();
        calendareventspage.login();
        calendareventspage.navigate();
        calendareventspage.clickToCreateCalendarEvent();

        List<String> expected = Arrays.asList("Save And Close","Save And New","Save");
        assertEquals( calendareventspage.saveCloseOptions(),expected,"it's not matching" );

        test.pass("save and close all three options displayed!");
    }
    /**
     * Test Case #4
     * 4. Click on “Create Calendar Event” button
     * 5.Then, click on “Cancel” button
     * 6.Verify that “All Calendar Events” page subtitle is
     * displayed
     */
    @Test
    public void testCase4() {
        test = report.createTest("Verify subtitle All Calendar Events displayed ");

        CalendarEventsPage calendareventspage = new CalendarEventsPage();
        calendareventspage.login();
        calendareventspage.navigate();
        calendareventspage.clickToCreateCalendarEvent();

        assertEquals( calendareventspage.verifySubtitle(), "All Calendar Events","It's not matching");

        test.pass("subtitle displayed!");
    }
    /**
     * Test Case #5
     * 5.Verify that difference between end and start time
     * is exactly 1 hour
     */
    @Test
    public void testCase5() {
        test = report.createTest("Verify starttime and endtime difference is one hour");

        CalendarEventsPage calendareventspage = new CalendarEventsPage();
        calendareventspage.login();
        calendareventspage.navigate();
        calendareventspage.clickToCreateCalendarEvent();

        String startTime = calendareventspage.getStartTime();
        String endTime = calendareventspage.getEndTime();
        String format = "h:mm a";

        long actualDiff = DateTimeUtilities.getTimeDifference(startTime,endTime,format);

        assertEquals(actualDiff,1,"Time difference is not matching");

        test.pass("Time difference is verified as 1 hour!");
    }
    /**
     * TestCase #6
     * Select “9:00 PM” as a start time
     * Verify that end time is equals to “10:00 PM”
     */
    @Test
    public void testCase6() {
           test = report.createTest("Select 9:00 pm then verify 10 pm end time");

            CalendarEventsPage calendareventspage = new CalendarEventsPage();
            calendareventspage.login();
            calendareventspage.navigate();
            calendareventspage.clickToCreateCalendarEvent();
            BrowserUtils.wait(5);
            actions.moveToElement(calendareventspage.getStartTime2()).clickAndHold().perform();
            BrowserUtils.wait(5);
            calendareventspage.clickTime("9");
            BrowserUtils.wait(2);

           assertEquals(calendareventspage.getEndTime(),"10:00 PM","Ending time is not matching");

           test.pass("ending time verified by given start time");
    }
    /**
     * TestCase #7
     * 5. Select “All-Day Event” checkbox
     * 6. Verify that “All-Day Event” checkbox is selected
     * 7. Verify that start and end time input boxes are
     * not displayed
     * 8. Verify that start and end date input boxes are
     * displayed
     */
    @Test
    public void testCase7() {
        test = report.createTest("select alldayevent button;verify its selected, time and date boxes");

        CalendarEventsPage calendareventspage = new CalendarEventsPage();
        calendareventspage.login();
        calendareventspage.navigate();
        calendareventspage.clickToCreateCalendarEvent();
        calendareventspage.clickalldaybox();

        assertTrue( calendareventspage.alldayeventbox.isSelected());
        assertTrue( calendareventspage.notDisplayTimeandDate());

        test.pass("time boxes not diplayed but date boxes diplayed after selecting alldayevent");
    }
    /**
     * TestCase #8
     * 5.Select “Repeat” checkbox
     * 6. Verify that “Repeat” checkbox is selected
     * 7. Verify that “Daily” is selected by default and
     * following options are available in
     * “Repeats” drop-down:
     */
    @Test
    public void testCase8() {
        test = report.createTest("verify repeat and repeats dropdown options");

        CalendarEventsPage calendareventspage = new CalendarEventsPage();
        calendareventspage.login();
        calendareventspage.navigate();
        calendareventspage.clickToCreateCalendarEvent();
        calendareventspage.clickRepeatButton();
        assertTrue(calendareventspage.repeat.isSelected());

        String actual = calendareventspage.defaultDropDown();
        String expected = "Daily".toLowerCase();
        assertEquals( actual,expected);

        List<String> expectedOptions = Arrays.asList("Daily","Weekly","Monthly","Yearly");
        assertEquals(calendareventspage.selectDropdownOptions(),expectedOptions,"options are not matching");

        test.pass("repeats dropdown options verified");
    }
    /**
     * TestCase #9
     * 7. Verify that “Repeat Every” radio button is
     * selected
     * 8. Verify that “Never” radio button is selected as an
     * “Ends” option.
     * 9. Verify that following message as a summary is
     * displayed: “Summary: Daily every 1 day”
     */
    @Test
    public void testCase9() {
        test = report.createTest("verify repeat, repeat every, never buttons and Summary");

        CalendarEventsPage calendareventspage = new CalendarEventsPage();
        calendareventspage.login();
        calendareventspage.navigate();
        calendareventspage.clickToCreateCalendarEvent();
        calendareventspage.clickRepeatButton();

        assertTrue(calendareventspage.repeat.isSelected());
        assertTrue( calendareventspage.repeat_everyButton.isSelected());
        assertTrue( calendareventspage.endsbutton.isSelected());

        String expected = "Summary:\n"+
                           "Daily every 1 day";
        assertEquals(calendareventspage.getSummaryText(),expected,"it's not matching");

        test.pass("every,ends radio button verified");
    }
    /**
     * TestCase #10
     * 5.Select “Repeat” checkbox
     * 6. Select “After 10 occurrences” as an “Ends”
     * option.
     * 7. Verify that following message as a summary is
     * displayed: “Summary: Daily every 1 day, end
     * after 10 occurrences”
     */
    @Test
    public void testCase10() {
        test = report.createTest("verify Summary sentence after selecting After and writing 10 occurences");

        CalendarEventsPage calendareventspage = new CalendarEventsPage();
        calendareventspage.login();
        calendareventspage.navigate();
        calendareventspage.clickToCreateCalendarEvent();
        calendareventspage.clickRepeatButton();
        String expected = "Summary:\n"+
                           "Daily every 1 day, end after 10 occurrences";
        calendareventspage.clickAfterSend10();

        assertEquals(calendareventspage.getSummaryText(), expected, "it's not same");

        test.pass("summary sentence after 10 occurences");
    }
    /**
     * TestCase#11
     * 5.Select “Repeat” checkbox
     * 6. Select “By Nov 18, 2021” as an “Ends” option.
     * 7. Verify that following message as a summary is
     * displayed: “Summary: Daily every 1 day, end by
     * Nov 18, 2021”
     */
    @Test
    public void testCase11() {
        test = report.createTest("verify Summary sentence after selecting month,year,date");

        CalendarEventsPage calendareventspage = new CalendarEventsPage();
        calendareventspage.login();
        calendareventspage.navigate();
        calendareventspage.clickToCreateCalendarEvent();
        calendareventspage.clickRepeatButton();

        String expected="Summary:\n" +
                "Daily every 1 day, end by Nov 18, 2021";
        String actual = calendareventspage.selectDate();

        assertEquals(actual,expected,"it's not matching");

        test.pass("choosen date is verified");
    }
    @Test(description="The second way of solution testcase#11")
    public void select(){
        test = report.createTest("verify summary in another way of solution after monday&friday selected");

        CalendarEventsPage calendareventspage = new CalendarEventsPage();
        calendareventspage.login();
        calendareventspage.navigate();
        calendareventspage.clickToCreateCalendarEvent();
        calendareventspage.clickRepeatButton();
        calendareventspage.clickBy();
        calendareventspage.clickByDateRadio();
        Select month = new Select(calendareventspage.clickMonth() ) ;
        month.selectByVisibleText("Nov");
        Select year = new Select(calendareventspage.clickYear());
        year.selectByVisibleText("2021");
        calendareventspage.chooseDate();

        assertEquals(calendareventspage.getSummaryText(),"Summary:\n" +
                "Daily every 1 day, end by Nov 18, 2021");

        test.pass("Selected date is verified!");
    }

    @Test
    public void testCase12() {
        test = report.createTest("verify summary after monday & friday selected");

        CalendarEventsPage calendareventspage = new CalendarEventsPage();
        calendareventspage.login();
        calendareventspage.navigate();
        calendareventspage.clickToCreateCalendarEvent();
        calendareventspage.clickRepeatButton();
        calendareventspage.selectweekly();
        calendareventspage.clickMonAndFri();

        assertTrue(calendareventspage.mondayButton.isSelected());
        assertTrue(calendareventspage.fridayButton.isSelected());
        assertEquals(calendareventspage.getSummaryText(),"Summary:\n" +
                "Weekly every 1 week on Monday, Friday","it's not matching");

        test.pass("summary text displayed after checking Mon and Fri");
    }
}
