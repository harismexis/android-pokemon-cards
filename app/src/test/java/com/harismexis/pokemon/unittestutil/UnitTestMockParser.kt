package com.harismexis.pokemon.unittestutil

import com.harismexis.pokemon.parser.BaseMockParser

class UnitTestMockParser : BaseMockParser() {

    override fun getFileAsString(filePath: String): String =
        ClassLoader.getSystemResource(filePath).readText()

}

