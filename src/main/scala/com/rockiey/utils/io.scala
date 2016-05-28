package com.rockiey.utils

import java.io._
import java.lang.management.ManagementFactory
import java.net.URL
import javax.management.ObjectName

import com.rockiey.utils.processor.Processor
import net.liftweb.json
import net.liftweb.json._
import org.apache.log4j.Logger

import scala.language.postfixOps
import scala.sys.process._
import scala.util.Try


/**
 * Created by Rockie Yang on 5/20/16.
 *
 */
object io {
  val logger = Logger.getLogger(this.getClass)

  /**
   * move file
   *
   * @return true if success, otherwise false
   */
  def mv(oldName: String, newName: String): Boolean = {
    val oldFile = new File(oldName)
    val newFile = new File(newName)

    Try(oldFile.renameTo(newFile)).getOrElse(false)
  }

  /**
   * ignore everything, always predict to false
   *
   * @return false
   */
  def ignore(line: String): Boolean = false


  /**
   * grep a file
   *
   * @param filename the file name, either absolute path or relative path
   * @param predicate predication
   * @return
   * grep result
   * empty array if no match
   */
  def grep(filename: String)(predicate: String => Boolean = ignore): Array[String] = {
    val source = scala.io.Source.fromFile(filename)
    try {
      val lines = source.getLines()
      lines.filter(predicate).toArray
    }
    finally {
      source.close()
    }
  }

  /**
   * download a url to file.
   *
   * NOTE the url must be a real file
   *
   * @param url the url to be downloaded
   * @param filename the dest file name to be saved, either absolute filename or relative
   */
  def download(url: String, filename: String): Unit = {

    val tmpFile = s"$filename.tmp"

    // it use postfix operator
    new URL(url) #> new File(tmpFile) !!

    mv(tmpFile, filename)
  }

  /**
   * download from a url to a file. ignore download if exist
   *
   * @param url a complete url
   * @param filename
   * @return true if download, false if ignore
   */
  def downloadUrl(url: String, filename: String, processor: Processor = null): Boolean = {
    if (new File(filename).exists()) {
      false
    }
    else {
      val source = scala.io.Source.fromURL(new URL(url))
      val tmpFile = s"$filename.tmp"

      val fileWriter = new FileWriter(tmpFile)
      val writer = new PrintWriter(fileWriter)
      try {
        for (line <- source.getLines()) {
          writer.println(line)
          if (processor != null) {
            processor(line)
          }
        }
      }
      catch {
        case ex: IOException =>
        // if the last line without return in the end
        // it will throw out
        // java.io.IOException: Premature EOF
        // we just need to ignore it

      }
      finally {
        source.close()
        fileWriter.close()
        writer.close()
      }

      mv(tmpFile, filename)
      true
    }
  }

  /**
   * load the file in json format
   *
   * @param absoluteFilename json file name in data directory
   * @return
   * JValue
   */
  def toJsonValue(absoluteFilename: String): JValue = {
    val source = scala.io.Source.fromFile(absoluteFilename)
    try {
      val jVal = json.parse(source.mkString)
      jVal
    }
    finally {
      source.close()
    }
  }

  def process(filename: String, processor: Processor): Unit = {
    val source = scala.io.Source.fromFile(filename)
    try {
      source.getLines().foreach(line => processor(line))
    }
    finally {
      source.close()
    }
  }

}
