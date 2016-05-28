package com.rockiey.utils

import com.rockiey.utils
import org.junit.Assert._
import org.junit._

@Test
class TestGC{
    @Test
    def testGCIfTooManyOpenFiles() = {
        utils.gc.gcIfTooManyOpenFiles()
    }

}


