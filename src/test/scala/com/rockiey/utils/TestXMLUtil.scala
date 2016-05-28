package com.rockiey.utils

import com.rockiey.utils
import org.junit.Assert._
import org.junit._

@Test
class TestXMLUtil{

    @Test
    def testSafeToXMLNode() = {
        val filename = "src/test/resources/invalidXMLContent.xml"
        val node = utils.xmlutil.safeToXMLNode(filename)
        val printer = new scala.xml.PrettyPrinter(120, 2)
        println(printer.format(node))
    }

    @Test
    def testToXMLNode() = {
        val filename = "src/test/resources/invalidXMLContent.xml"
        val node = utils.xmlutil.toXMLNode(filename)
        val printer = new scala.xml.PrettyPrinter(120, 2)
        println(printer.format(node))
    }

    @Test
    def testUnsafeToXMLNode() = {
        val filename = "src/test/resources/invalidXMLContent.xml"
        try {
            utils.xmlutil.unsafeToXMLNode(filename)
            fail("expect exception to be throw")
        }
        catch {
            case ex: Exception =>
        }

    }

}


