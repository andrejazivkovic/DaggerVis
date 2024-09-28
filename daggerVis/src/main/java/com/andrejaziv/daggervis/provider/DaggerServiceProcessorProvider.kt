package com.andrejaziv.daggervis.provider

import com.andrejaziv.daggervis.processor.DaggerServiceProcessor
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

internal class DaggerGraphProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return DaggerServiceProcessor(
            logger = environment.logger,
            codeGenerator = environment.codeGenerator
        )
    }
}