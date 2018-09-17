package de.scrum_master.stackoverflow

import geb.Module

// See https://stackoverflow.com/a/50302663/1082681

class DateInputModule extends Module {
  static content = {
    input1 { $("input[id='Date Input 1']") }
    input2 { $("input[id='Date Input 2']") }
    input3 { $("input#date-input-3") }
    input4 { $("input#date-input-4") }
    input5 { $("input#date-input-5") }
  }
}
