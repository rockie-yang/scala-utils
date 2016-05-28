package com.rockiey.utils

import java.io.InputStream
import java.net.URL
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date

/**
  * Created by Rockie Yang on 1/10/16.
  *
  */
object convert {

  // The format can be compare time directly with string
  val format = new SimpleDateFormat("yyyy/MM/dd' 'HH:mm:ss")

  /**
    * convert from millisecond to time format in string
    *
    *
    * @param millisecond 1445268107137
    * @return time in string, "" if can not be converted
    */
  def milli2Time(millisecond: String): String = {
    try {
      val milli = millisecond.toLong
      val date = new Date(new Timestamp(milli).getTime)
      format.format(date)
    }
    catch {
      case ex: Exception =>
        ""
    }
  }

  /**
    * from a url to stream
    *
    * @param url
    * @return
    */
  def urlToStream(url: String): InputStream = {
    val conn = new URL(url).openConnection()
    conn.setRequestProperty("User-Agent",
      "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")

    conn.connect()
    conn.getInputStream
  }

  /**
    * xml node to String with 120 in width and indent 2
    * @param node
    * @return
    */
  def nodeToString(node: xml.Node): String = {
    val printer = new scala.xml.PrettyPrinter(120, 2)
    printer.format(node)
  }

}
