package com.rockiey.utils.processor

/**
 * Created by Rockie Yang on 5/20/16.
 *
 */
class ContainsGetLast(splitStr: String, containStrs: String*) extends Processor{
  var value: String = ""

  def apply(line: String): Unit = {
    if (value == "") {
      containStrs.foreach {
        containStr => if (line.contains(containStr)) {
          value = line.stripMargin.split(splitStr).last
        }
      }
    }
  }
}
