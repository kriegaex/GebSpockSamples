package de.scrum_master.stackoverflow

import geb.module.FormElement
import geb.spock.GebReportingSpec
import org.openqa.selenium.support.ui.ExpectedConditions
import spock.lang.Requires
import spock.lang.Unroll

import static java.util.Calendar.*

/**
 * See http://stackoverflow.com/a/42392006/1082681
 */
@Requires({
  // TODO: This test is flaky on GitHub under MacOS, no idea why.
  os.windows || os.linux
})
class DatePickerIT extends GebReportingSpec {
  def now = new GregorianCalendar()
  def xmas = new GregorianCalendar(now.get(YEAR), 12 - 1, 25)

  @Unroll
  def "Get selected date from Angular date label"() {
    when: "a date picker on the Angular demo page is chosen"
    DatePickerPage page = to DatePickerPage
    def datePickerInputField = page.datePickerInputFields[datePickerIndex]
    ExpectedConditions.elementToBeClickable datePickerInputField.firstElement()

    then: "that date picker instance is (in-)active as expected"
    datePickerInputField.module(FormElement).enabled == isEnabled

    when: "the active date is read from the date picker's text input field"
    String activeDateString = page.getDatePickerValue(datePickerInputField)
    String todayString = "${now.get(MONTH) + 1}/${now.get(DAY_OF_MONTH)}/${now.get(YEAR)}"

    then: "it is today"
    todayString == activeDateString

    where:
    datePickerIndex << [0, 1, 2, 3, 4, 5, 6, 7, 8]
    isEnabled << [true, false, true, true, true, true, true, true, true]
  }

  @Unroll
  def "Get selected date from opened Angular date picker"() {
    when: "a date picker on the Angular demo page is chosen"
    DatePickerPage page = to DatePickerPage
    def datePickerButton = page.datePickerButtons[datePickerIndex]

    then: "that date picker instance is active (not greyed out)"
    datePickerButton.module(FormElement).enabled

    when: "the calendar button for the date picker is clicked"
    datePickerButton.click()

    then: "the date picker pop-up opens, displaying the current month"
    waitFor 3, { page.activeDatePicker.displayed }

    when: "the active date's timestamp is read from the highlighted day in the calendar"
    def selectedDateMillis = page.selectedDate.attr("data-timestamp") as long
    def sinceMidnightMillis = now.getTimeInMillis() - selectedDateMillis

    then: "it is today"
    sinceMidnightMillis >= 0
    sinceMidnightMillis < 24 * 60 * 60 * 1000

    where:
    datePickerIndex << [0, 2, 4, 5, 6, 7, 8]
  }

  def "Set date in Angular date picker"() {
    when: "the first date picker's calendar button on the Angular demo page is clicked"
    DatePickerPage page = to DatePickerPage
    page.datePickerButtons[0].click()

    then: "the date picker pop-up opens, displaying the current month"
    waitFor 3, { page.activeDatePicker.displayed }

    when: "the current month label is clicked"
    page.currentMonthLabel.click()

    then: "the month selection page opens"
    waitFor 3, { page.selectedMonth.displayed }
    page.selectedMonth.text() == page.getMonthShort(now)

    when: "December for the current year is selected"
    page.getMonthLabel(xmas).click()

    then: "the month selection page closes again"
    waitFor 3, { !page.selectedMonth.displayed }

    when: "Xmas Day (25) is selected on the month calendar"
    page.getDayOfMonthLabel(xmas).click()

    then: "the date picker pop-up closes again"
    waitFor 3, { !page.activeDatePicker.displayed }

    when: "the active date is read from the date picker's text input field"
    String activeDateString = page.getDatePickerValue(page.datePickerInputFields[0])
    String xmasDayString = "${xmas.get(MONTH) + 1}/${xmas.get(DAY_OF_MONTH)}/${xmas.get(YEAR)}"

    then: "it is Xmas Day of the current year"
    xmasDayString == activeDateString
  }
}
