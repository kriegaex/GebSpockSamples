package de.scrum_master.geb_ml

import geb.spock.GebReportingSpec
import spock.lang.IgnoreIf
import spock.lang.Unroll
import spock.util.concurrent.PollingConditions

import java.nio.file.Files
import java.nio.file.Path

class ConsoleGamesDownloadIT extends GebReportingSpec {
  static Path downloadFolder = new File("${System.getenv('USERPROFILE')}\\Downloads").toPath()

  @Unroll
  // TODO: Find out how to use downloads in Chrome Headless
  @IgnoreIf({ sys['geb.env'] in ['html_unit', 'chrome_headless', 'win_app'] })
  def "download #url"() {
    given: 'visit download page'
    go url
    // Needs ChromeDriver option '--disable-blink-features=AutomationControlled',
    // see https://stackoverflow.com/a/70509053/1082681
    report 'download page with cookie banner'

    expect: 'click cookie banner'
    waitFor 5, 1, {
      def acceptCookiesButton = $('button.css-47sehv')
      // Because we have no page with at checks or waiting navigators, we perform an extra check here before clicking,
      // to avoid problems in slower browsers, e.g. Opera
      if (!acceptCookiesButton.displayed || !acceptCookiesButton.firstElement().enabled)
        return false
      acceptCookiesButton.click()
      // Opera: wait for cookie banner to really go away, because sometimes clicks on the button seem to have no effect
      new PollingConditions().within(0.2) {
        assert !$('button.css-47sehv').displayed
      }
      true
    }
    report 'download page after closing cookie banner'
    sleep 1

    and: 'determine the current number of matching files in the download directory'
    def archiveName = $('#data-good-title').text().replace('.nes', '')
    def filesFoundBeforeDownload = numberOfMatchingFiles("$archiveName*", ".zip", ".7z")

    when: 'clicking download button'
    $("#download_form > button").click()

    then: 'wait for download completion'
    waitFor 20, 1, {
      numberOfMatchingFiles("$archiveName*", ".zip", ".7z") > filesFoundBeforeDownload
    }
    report 'file downloaded'

    where:
    url << new File("src/test/resources/games.txt")
      .readLines()[0..1] // Only use first 2 elements to speed up test
  }

  int numberOfMatchingFiles(String fileNamePattern, String... extensions) {
    int numberOfFilesFound
    extensions.each {
      def directoryStream = Files.newDirectoryStream(downloadFolder, fileNamePattern + it)
      numberOfFilesFound += directoryStream.size()
      directoryStream.close()
    }
    numberOfFilesFound
  }
}
