package com.andrejaziv.daggervis.processor

import com.andrejaziv.daggervis.helpers.DaggerComponentGraphGenerator
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration

internal class DaggerServiceProcessor(
    logger: KSPLogger,
    codeGenerator: CodeGenerator
) : SymbolProcessor {
    private val daggerComponentsGraphGenerator by lazy {
        DaggerComponentGraphGenerator(logger, codeGenerator)
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val daggerComponents = resolver.getSymbolsWithAnnotation("dagger.Component")
            .filterIsInstance<KSClassDeclaration>()
        daggerComponentsGraphGenerator.createGraph(daggerComponents)
        return emptyList()
    }
}
