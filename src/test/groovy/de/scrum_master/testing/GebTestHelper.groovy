package de.scrum_master.testing

import geb.js.JavascriptInterface
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class GebTestHelper {
  private WebDriver driver
  private JavascriptInterface js

  GebTestHelper(WebDriver driver, JavascriptInterface js) {
    this.driver = driver
    this.js = js
  }

  boolean isFullyVisible(WebElement webElement) {
    println "\nCheck fully visible"
    isTopLeftCornerVisible(webElement) && isBottomRightCornerVisible(webElement)
  }

  boolean isPartiallyVisible(WebElement webElement) {
    println "\nCheck partially visible"
    isTopLeftCornerVisible(webElement) || isBottomRightCornerVisible(webElement)
  }

  private boolean isTopLeftCornerVisible(WebElement webElement) {
    println "Top left"
    // Window size
    Dimension windowSize = driver.manage().window().size
    int scrollbarWidth = getScrollbarWidth()
    int windowWidth = windowSize.width - (hasHorizontalScrollbar() ? scrollbarWidth : 0)
    int windowHeight = windowSize.height - (hasVerticalScrollbar() ? scrollbarWidth : 0)
    windowSize = new Dimension(windowWidth, windowHeight)

    // Window offset
    int windowXOffset = js.exec("return window.pageXOffset;") as int
    int windowYOffset = js.exec("return window.pageYOffset;") as int
    Point windowOffset = new Point(windowXOffset, windowYOffset)

    // Top left corner
    Point elementLocation = webElement.location
    Point point = new Point(elementLocation.x, elementLocation.y)

    isPointVisible(windowSize, windowOffset, point)
  }

  private boolean isBottomRightCornerVisible(WebElement webElement) {
    // TODO: determine scrollbar visibility + size, subtract from window dimensions
    println "Bottom right"
    // Window size
    Dimension windowSize = driver.manage().window().size
    int scrollbarWidth = getScrollbarWidth()
    int windowWidth = windowSize.width - (hasHorizontalScrollbar() ? scrollbarWidth : 0)
    int windowHeight = windowSize.height - (hasVerticalScrollbar() ? scrollbarWidth : 0)
    windowSize = new Dimension(windowWidth, windowHeight)

    // Window offset
    int windowXOffset = js.exec("return window.pageXOffset;") as int
    int windowYOffset = js.exec("return window.pageYOffset;") as int
    Point windowOffset = new Point(windowXOffset, windowYOffset)

    // Bottom right corner
    Dimension elementSize = webElement.size
    Point elementLocation = webElement.location
    Point point = new Point(elementLocation.x + elementSize.width, elementLocation.y + elementSize.height)

    isPointVisible(windowSize, windowOffset, point)
  }

  private boolean isPointVisible(Dimension windowSize, Point windowOffset, Point point) {
    println "Window size = $windowSize"
    println "Window offset = (${windowOffset.x}, ${windowOffset.y})"
    println "Point = $point"

    boolean isVisible = point.x >= windowOffset.x && point.x < windowOffset.x + windowSize.width &&
      point.y >= windowOffset.y && point.y < windowOffset.y + windowSize.height
    println "visible = $isVisible"

    isVisible
  }

  private int getScrollbarWidth() {
    String jsScript = """
      var getScrollbarWidth = function() {
        // Create the measurement node
        var scrollDiv = document.createElement("div");
        scrollDiv.className = "scrollbar-measure";
        scrollDiv.style.width = "100px";
        scrollDiv.style.height = "100px";
        scrollDiv.style.overflow = "scroll";
        scrollDiv.style.position = "absolute";
        scrollDiv.style.top = "-9999px";
        document.body.appendChild(scrollDiv);
        // Get the scrollbar width
        var scrollbarWidth = scrollDiv.offsetWidth - scrollDiv.clientWidth;
        // Delete the DIV 
        document.body.removeChild(scrollDiv);
        return scrollbarWidth;
      }
      
      return getScrollbarWidth();
    """
    int scrollbarWidth = js.exec(jsScript)
    println "Scrollbar width = $scrollbarWidth"
    scrollbarWidth
  }

  private boolean hasVerticalScrollbar() {
    String jsScript = """
      var hasVerticalScrollbar = function() {
console.warn('hasVerticalScrollbar');
console.warn(typeof window.innerWidth === 'number');
console.warn(window.innerWidth);
console.warn(document.documentElement.clientWidth);
        // The Modern solution
        if (typeof window.innerWidth === 'number')
          return window.innerWidth > document.documentElement.clientWidth
      
        // rootElem for quirksmode
        var rootElem = document.documentElement || document.body
      
        // Check overflow style property on body for fauxscrollbars
        var overflowStyle
      
        if (typeof rootElem.currentStyle !== 'undefined')
          overflowStyle = rootElem.currentStyle.overflow
      
        overflowStyle = overflowStyle || window.getComputedStyle(rootElem, '').overflow
      
          // Also need to check the Y axis overflow
        var overflowYStyle
      
        if (typeof rootElem.currentStyle !== 'undefined')
          overflowYStyle = rootElem.currentStyle.overflowY
      
        overflowYStyle = overflowYStyle || window.getComputedStyle(rootElem, '').overflowY
      
        var contentOverflows = rootElem.scrollHeight > rootElem.clientHeight
        var overflowShown    = /^(visible|auto)\$/.test(overflowStyle) || /^(visible|auto)\$/.test(overflowYStyle)
        var alwaysShowScroll = overflowStyle === 'scroll' || overflowYStyle === 'scroll'
      
        return (contentOverflows && overflowShown) || (alwaysShowScroll)
      }
      
      return hasVerticalScrollbar();
    """
    boolean hasVerticalScrollbar = js.exec(jsScript)
    println "Vertical scrollbar = $hasVerticalScrollbar"
    hasVerticalScrollbar
  }

  private boolean hasHorizontalScrollbar() {
    String jsScript = """
      var hasHorizontalScrollbar = function() {
console.warn('hasHorizontalScrollbar');
console.warn(typeof window.innerHeight === 'number');
console.warn(window.innerHeight);
console.warn(document.documentElement.clientHeight);
        // The Modern solution
        if (typeof window.innerHeight === 'number')
          return window.innerHeight > document.documentElement.clientHeight
      
        // rootElem for quirksmode
        var rootElem = document.documentElement || document.body
      
        // Check overflow style property on body for fauxscrollbars
        var overflowStyle
      
        if (typeof rootElem.currentStyle !== 'undefined')
          overflowStyle = rootElem.currentStyle.overflow
      
        overflowStyle = overflowStyle || window.getComputedStyle(rootElem, '').overflow
      
          // Also need to check the X axis overflow
        var overflowXStyle
      
        if (typeof rootElem.currentStyle !== 'undefined')
          overflowXStyle = rootElem.currentStyle.overflowX
      
        overflowXStyle = overflowXStyle || window.getComputedStyle(rootElem, '').overflowX
      
        var contentOverflows = rootElem.scrollWidth > rootElem.clientWidth
        var overflowShown    = /^(visible|auto)\$/.test(overflowStyle) || /^(visible|auto)\$/.test(overflowXStyle)
        var alwaysShowScroll = overflowStyle === 'scroll' || overflowXStyle === 'scroll'
      
        return (contentOverflows && overflowShown) || (alwaysShowScroll)
      }
      
      return hasHorizontalScrollbar();
    """
    boolean hasHorizontalScrollbar = js.exec(jsScript)
    println "Horizontal scrollbar = $hasHorizontalScrollbar"
    hasHorizontalScrollbar
  }
}
