package com.harismexis.pokemon.instrumentedsetup.instrumentedutil

import androidx.test.platform.app.InstrumentationRegistry

import com.harismexis.pokemon.parser.BaseMockParser

class InstrumentedMockParser: BaseMockParser() {

    override fun getFileAsString(filePath: String): String =
        InstrumentationRegistry.getInstrumentation().context.classLoader
            .getResource(filePath).readText()

}

