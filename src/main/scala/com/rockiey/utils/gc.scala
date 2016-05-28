package com.rockiey.utils

import java.lang.management.ManagementFactory
import javax.management.ObjectName

/**
  * Created by Rockie Yang on 2016/05/28.
  */
object gc {

  def gcIfTooManyOpenFiles(num: Int = 1024) = {
    val mbeanServer = ManagementFactory.getPlatformMBeanServer

    val oName = new ObjectName("java.lang:type=OperatingSystem")

    val openFileCount = mbeanServer.getAttribute(oName, "OpenFileDescriptorCount").asInstanceOf[Long]
    val maxFileCount = mbeanServer.getAttribute(oName, "MaxFileDescriptorCount").asInstanceOf[Long]

    if (openFileCount > (maxFileCount / 2)) {
      System.gc()
    }
  }
}
