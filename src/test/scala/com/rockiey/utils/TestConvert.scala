package com.rockiey.utils

import org.junit._

@Test
class TestConvert{
    @Test
    def testTimestamp2Time() = {
        // this is from build (job/number/api/xml)
        val timestamp = "1445268107137"
        val time = convert.milli2Time(timestamp)
        println(time)
    }
    @Test
    def test2Time() = {
        // this is from testReport (job/number/testReport/api/xml
        // NOTE: a testReport might not have timestamp
        val timestamp = "2016-05-12T07:31:46"
        val time = convert.milli2Time(timestamp)
        println(time)
    }
}


