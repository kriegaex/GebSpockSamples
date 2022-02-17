package de.scrum_master.stackoverflow

import geb.Page
import geb.navigator.Navigator

import java.time.Month
import java.time.format.TextStyle

import static java.util.Calendar.*

class DatePickerPage extends Page {
  static url = "https://material.angularjs.org/1.1.2/demo/datepicker"
  static at = { heading == "Datepicker" }
  static now = new GregorianCalendar()

  static content = {
    heading { $("h2 > span.md-breadcrumb-page.ng-binding").text() }
    datePickerButtons { $("md-datepicker > button") }
    datePickerInputFields { $(".md-datepicker-input") }
    activeDatePicker(required: false) { $(".md-datepicker-calendar-pane.md-pane-open") }
    selectedDate { activeDatePicker.$(".md-calendar-selected-date") }
    currentMonthLabel {
      activeDatePicker
        .$("td.md-calendar-month-label", text: "${getMonthShort(now)} ${now.get(YEAR)}")
    }
    selectedMonth(required: false) { $("td.md-calendar-selected-date") }
  }

  String getDatePickerValue(Navigator datePickerInputField) {
    datePickerInputField.singleElement().getAttribute("value")
  }

  Navigator getMonthLabel(Calendar calendar) {
    $(".md-calendar-month-label", text: "${calendar.get(YEAR)}").closest("tbody")
      .$("span", text: getMonthShort(calendar)).closest("td")
  }

  Navigator getDayOfMonthLabel(Calendar calendar) {
    activeDatePicker
      .$(".md-calendar-month-label", text: "${getMonthShort(calendar)} ${calendar.get(YEAR)}")
      .closest("tbody")
      .$("span", text: "${calendar.get(DAY_OF_MONTH)}")
      .closest("td")
  }

  String getMonthShort(Calendar calendar) {
    Month
      .of(calendar.get(MONTH) + 1)
      .getDisplayName(TextStyle.FULL, new Locale("en"))
      .substring(0, 3)
  }
}
