package com.rockiey.utils

import org.junit._

@Test
class TestXMLFilter{
    @Test
    def testFilterSmall(): Unit= {
        val testReport = "data/largeSmall.xml"
        val url = "https://en.wikipedia.org/w/api.php?action=query&format=xml&meta=siteinfo"

//        xmlfilter.filter(url, Array("stderr", "stdout"))
    }

    @Test
    def testFilterLarge(): Unit = {
        // https://tuxdna.wordpress.com/2014/02/03/a-simple-scala-parser-to-parse-44gb-wikipedia-xml-dump/
        // https://dumps.wikimedia.org/backup-index.html
        // http://www.cs.washington.edu/research/xmldatasets/www/repository.html#pir

    }

}


