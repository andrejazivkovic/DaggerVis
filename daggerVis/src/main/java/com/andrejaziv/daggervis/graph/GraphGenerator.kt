package com.andrejaziv.daggervis.graph

import com.andrejaziv.daggervis.graph.DaggerComponentGraphGenerator.Companion.PACKAGE_NAME
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import guru.nidi.graphviz.engine.Format
import guru.nidi.graphviz.engine.Graphviz
import guru.nidi.graphviz.parse.Parser

internal sealed class GraphGenerator(
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator
) {
    internal abstract fun generateGraphs(classDeclarations: Sequence<KSClassDeclaration>)

    internal fun generateFile(
        dotContent: String,
        graphNamePrefix: String,
        graphNameExtension: String,
        format: Format
    ) {
        try {
            val graph = Parser().read(dotContent)
            codeGenerator.createNewFile(
                dependencies = Dependencies.ALL_FILES,
                packageName = PACKAGE_NAME,
                fileName = "$graphNamePrefix-$graphNameExtension",
                extensionName = format.name
            ).use { stream ->
                Graphviz.fromGraph(graph).render(format).toOutputStream(stream)
            }
            logger.info("SVG generated successfully for $graphNameExtension")
        } catch (e: Exception) {
            logger.info("Error generating SVG: $e")
        }
    }

    internal fun randomGraphColor(): String {
        val random = java.util.Random()
        val color = (0xFFFFFF and random.nextInt()).toString(16)
        return "#${color.padStart(6, '0')}"
    }
}