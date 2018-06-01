import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.highcharts.com/demo/line-basic')

// checking if a usual HTML node is accessible
//WebUI.waitForElementPresent(
//	findTestObject('Page_Basic line/a_Basic line'), 5, FailureHandling.OPTIONAL)
//WebUI.verifyElementPresent(
//	findTestObject('Page_Basic line/a_Basic line'), 5, FailureHandling.OPTIONAL)

// checking if SVG node is accessible by a Namespace-ignorant XPath; this fails
WebUI.verifyElementPresent(
	findTestObject('Page_Basic line/svg_namespace-ignorant'), 5, FailureHandling.OPTIONAL)

// checking if SVG node is accessible by a Namespace-aware XPath; this succeeds
WebUI.verifyElementPresent(
	findTestObject('Page_Basic line/svg_ns-uri_local-name'), 5, FailureHandling.OPTIONAL)

WebUI.verifyElementPresent(
	findTestObject('Page_Basic line/svg_local-name'), 5, FailureHandling.OPTIONAL)

WebUI.verifyElementPresent(
	findTestObject('Page_Basic line/svg_highcharts-title'), 5, FailureHandling.OPTIONAL)

WebUI.verifyElementText(
	findTestObject('Page_Basic line/svg_highcharts-title'),
	"Solar Employment Growth by Sector, 2010-2016",
	FailureHandling.OPTIONAL)

def title = WebUI.getText(findTestObject('Page_Basic line/svg_highcharts-title'))
WebUI.comment("SVT Title: ${title}")

WebUI.closeBrowser()

