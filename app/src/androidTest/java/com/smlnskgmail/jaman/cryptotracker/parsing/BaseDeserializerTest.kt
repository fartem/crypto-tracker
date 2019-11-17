package com.smlnskgmail.jaman.cryptotracker.parsing

import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.google.gson.JsonDeserializer
import com.google.gson.JsonParser
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader

abstract class BaseDeserializerTest {

    private val context = InstrumentationRegistry.getInstrumentation().context

    @Test
    fun deserializeJson() {
        val responseStream = context.resources.openRawResource(
            pathToJson()
        )
        val response = BufferedReader(
            InputStreamReader(
                responseStream
            )
        ).readText()

        val result = deserializer().deserialize(
            Gson().toJsonTree(
                JsonParser().parse(
                    response
                )
            ),
            null,
            null
        )

        assertNotNull(result)
    }

    abstract fun pathToJson(): Int
    abstract fun deserializer(): JsonDeserializer<*>

}