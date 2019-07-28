package com.samples.factsdemoapp

import androidx.test.filters.SmallTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import java.nio.charset.Charset

/**
 * To check api availability
 *
 * @author AkashG
 * @since 26-07-2019.
 */
@RunWith(RobolectricTestRunner::class)
@SmallTest
class ApiAvailabilityTest {

    @Test
    @Throws(Exception::class)
    fun testApiAvailability() {
        val urlConnection: URLConnection =
            URL("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json").openConnection()
        val inputStream = urlConnection.getInputStream()

        val buffer = StringBuffer()
        val reader = BufferedReader(InputStreamReader(inputStream, Charset.defaultCharset()))

        for (line: String in reader.readLines()) {
            buffer.append(line)
        }

        assert(buffer.isNotEmpty())
    }

}