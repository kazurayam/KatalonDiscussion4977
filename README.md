How to find SVG element in HTML in Katalon Studio
=====

# What is this?

This is a simple Katalon Studio project for demonstration purpose.
You can clone this out on you PC and execute it with your Katalon Studio.

This project is developed to propose a solution for the following discussion:

- https://forum.katalon.com/discussion/4977/katalon-css-xpath-not-working-with-svg-charts

Question raised there was:

>Katalon CSS/Xpath not working with SVG charts
>Hi,
>We are working with SVG charts for our applications, the identification via spy web is not working.
>Basic Application available at : https://www.highcharts.com/demo/line-basic

# Problem to solve

The target HTML document looks as follows:

```
<html>
<body>
  <div id="hs-component">
    <div class="container">
      <div id="wrap">
        ...
        <div class="... demo">
          ...
          <div class="chart-container">
            <div id="container">
              <div id="highcharts-0x1te9k-0">
                <svg xmlns="http://http://www.w3.org/2000/svg" ...>
                  ...
                  <text class="highcharts-title">
                    <tspan>Solar Employment Growth by Sector, 2010-2016</tspan>
                  </text>
                  ...
                </svg>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
```

I want to check if the title of SVG chart is identical to a string "Solar Employment Growth by Sector, 2010-2016".

# Description

I needed to find out a XPath expression to select the text node.



## XML Namespace ignorant XPath fails
I tried this XPath expression
```
//div[@id="container"]/div/svg
```
This did not work. Katalon Studio warned me:
```
com.kms.katalon.core.webui.exception.WebElementNotFoundException:
Web element with id: 'Object Repository/Page_Basic line/svg_namespace-ignorant'
located by 'By.xpath: //div[@id="container"]/div/svg' not found
```

## Knowledge required

I realized that I need to respect the XML Namespace. The SVG nodes belong to the Namespace URI of "http://http://www.w3.org/2000/svg". XPath needs to be Namespace-aware.

 See [W3CSchool / XML Namespaces](https://www.w3schools.com/xml/xml_namespaces.asp) to learn the XML Namespace concept.

## Solutions

The svg node with the Namespace-uri `http://www.w3.org/2000/svg` can be selected by the following XPath:

```
//div[@id="container"]/div/*[namespace-uri() = "http://www.w3.org/2000/svg" and local-name()="svg"]
```

However, the target document has only one node named `svg`, so that you can relax the predicate omitting the namespace-uri. The following XPath expression works as well:

```
//div[@id="container"]/div/*[local-name()="svg"]
```

Finally I could select the SVG text node by the following XPath expression:

```
//div[@id="container"]/div/*[local-name()="svg"]/*[local-name()="text" and @class="highcharts-title"]
```

# Reference

1. [W3CSchool / XML Namespaces](https://www.w3schools.com/xml/xml_namespaces.asp)
2. [MDN WebDocs / XPath namespace-uri() function](https://developer.mozilla.org/en-US/docs/Web/XPath/Functions/namespace-uri)
2. [MDN WebDocs / XPath local-name() function](https://developer.mozilla.org/en-US/docs/Web/XPath/Functions/local-name)
