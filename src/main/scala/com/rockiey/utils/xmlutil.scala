package com.rockiey.utils

import java.io.{FileWriter, FileInputStream, File}

import org.apache.log4j.Logger

/**
  * Created by Rockie Yang on 2016/05/27.
  */
object xmlutil {

  val logger = Logger.getLogger(this.getClass)

  /**
    * get file content and filter out characters which invalid in XML
    *
    * @param filename
    * @return
    */
  def getValidXMLContent(filename: String): String = {
    val stream = scala.io.Source.fromFile(filename)

    try {
      val content: String = stream.getLines().mkString("")

      // val pattern = "[^\u0001-\uD7FF\uE000-\uFFFD\ud800\udc00-\udbff\udfff]+"
      val pattern = "[^\u0011-\uD7FF\uE000-\uFFFD\ud800\udc00-\udbff\udfff]+"
      content.replaceAll(pattern, "")
    }
    finally {
      stream.close()
    }
  }

  /**
    * from file to xml node, filter out invalid characters
    *
    * @param filename
    * @return
    */
  def safeToXMLNode(filename: String): xml.Node = {
    xml.XML.loadString(getValidXMLContent(filename))
  }

  /**
    * from file to xml node
    *
    * try load without filtering
    * if fail, try to load with invalid character filter
    *
    * @param filename
    * @return
    */
  def toXMLNode(filename: String): xml.Node = {
    try {
      unsafeToXMLNode(filename)
    }
    catch {
      case ex: Exception =>
        safeToXMLNode(filename)
    }
  }

  /**
    * from a file to xml node, exception will throw if there is invalid character
    *
    * @param filename
    * @return
    */
  def unsafeToXMLNode(filename: String): scala.xml.Node = {

    val file = new File(filename)
    val is = new FileInputStream(file)

    try {
      xml.XML.load(is)
    }
    catch {
      case ex: Exception =>
        logger.warn(s"Can not load $filename to XML node", ex)
        throw ex
    }
    finally {
      is.close()
    }
  }

  /**
    * save xml node to
    * @param node
    * @param filename
    */
  def saveXMLNode(node: xml.Node, filename: String) = {
    val writer = new FileWriter(new File(filename))
    val printer = new scala.xml.PrettyPrinter(120, 2)
    try {
      writer.write(printer.format(node))
    }
    finally {
      writer.close()
    }
  }

}
