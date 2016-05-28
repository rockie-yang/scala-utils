package com.rockiey.utils

import java.text.SimpleDateFormat
import java.util.Date

/**
 * Created by Rockie Yang on 5/20/16.
 *
 */
object time {
  def now: String = {
    val format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
    format.format(new Date())
  }
}
