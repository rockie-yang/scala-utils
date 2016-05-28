package com.rockiey.utils.processor

/**
 * Created by Rockie Yang on 5/20/16.
 *
 */
trait Processor {
  def apply(line: String): Unit
}

class Processors(processors: Processor*) extends Processor{
  def apply(line: String): Unit = {
    processors.foreach(processor => processor(line))
  }
}
