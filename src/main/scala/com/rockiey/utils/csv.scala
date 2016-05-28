package com.rockiey.utils

/**
 * Created by Rockie Yang on 2016/01/16.
 *
 */
object csv {
  def escape(value: String): String = {
    value.replace(',', ' ')
  }
}
