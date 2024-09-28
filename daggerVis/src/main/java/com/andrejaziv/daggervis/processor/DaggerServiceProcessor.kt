package com.andrejaziv.daggervis.processor

import com.andrejaziv.daggervis.graph.DaggerComponentGraphGenerator
import com.andrejaziv.daggervis.graph.GraphGenerator
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration

private const val DAGGER_COMPONENT_ANNOTATION_NAME = "dagger.Component"

internal class DaggerServiceProcessor(
    logger: KSPLogger,
    codeGenerator: CodeGenerator
) : SymbolProcessor {
    private val daggerComponentsGraphGenerator: GraphGenerator by lazy {
        DaggerComponentGraphGenerator(logger, codeGenerator)
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val daggerComponents = resolver.getSymbolsWithAnnotation(DAGGER_COMPONENT_ANNOTATION_NAME)
            .filterIsInstance<KSClassDeclaration>()
        daggerComponentsGraphGenerator.generateGraphs(daggerComponents)
        return emptyList()
    }
}
