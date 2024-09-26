package com.andrejaziv.daggervis.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import guru.nidi.graphviz.engine.Format
import guru.nidi.graphviz.engine.Graphviz
import guru.nidi.graphviz.parse.Parser

internal class DaggerServiceProcessor(
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator
) : SymbolProcessor {
    companion object{
        private const val GRAPH_NAME = "Dagger-Graph"
        private const val EXTENSION = "svg"
        private const val BASE_GRAPH_NAME = "Main"
        private const val PACKAGE_NAME = "dagger.graph"
    }
    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.info("Dagger graph creation started...")
        val mainComponentMap = mutableMapOf<String, List<String>>()
        val components = resolver.getSymbolsWithAnnotation("dagger.Component")
            .filterIsInstance<KSClassDeclaration>()
        components.forEach { component ->
            val componentName = component.simpleName.asString()
            logger.info("Processing component: $componentName")
            val annotation = component.annotations.find {
                it.shortName.asString() == "Component"
            }
            annotation?.arguments?.forEach { arg ->
                when (arg.name?.asString()) {
                    "dependencies" -> {
                        logger.info("forEach annotation: $arg")
                        val dependenciesList = arg.value as? List<*>
                        val dependenciesListCasted =
                            dependenciesList?.map { it.toString() } ?: emptyList()
                        mainComponentMap[componentName] = dependenciesListCasted
                        //Create separate graph for every component and his dependencies
                        createSvg(
                            generateGraphDot(mapOf(componentName to dependenciesListCasted)),
                            componentName
                        )
                    }
                }
            }
        }
        //Create top level graph representing all the dependencies
        createSvg(generateGraphDot(mainComponentMap), BASE_GRAPH_NAME)
        finish()
        return emptyList()
    }

    private fun generateGraphDot(componentsGraphs: Map<String, List<String>>): String {
        val componentColors = mutableMapOf<String, String>()
        val stringBuilder = StringBuilder()
        stringBuilder.append("digraph G {\n")
        componentsGraphs.forEach { (componentName, dependencyList) ->
            val color = componentColors.getOrPut(componentName) { randomGraphColor() }
            stringBuilder.append("    \"$componentName\" [color=\"$color\", fontcolor=\"$color\"];\n")
            dependencyList.forEach { dependency ->
                stringBuilder.append("    \"$componentName\" -> \"$dependency\" [color=\"$color\"];\n")
            }
        }
        stringBuilder.append("}\n")
        logger.info("graph G ready for parsing $stringBuilder")
        return stringBuilder.toString()
    }

    private fun createSvg(dotContent: String, graphNameExtension: String) {
        try {
            val graph = Parser().read(dotContent)
            logger.info("SVG graph read:$graph")
            codeGenerator.createNewFile(
                dependencies = Dependencies(true),
                packageName = PACKAGE_NAME,
                fileName = "$GRAPH_NAME-$graphNameExtension",
                extensionName = EXTENSION
            ).use {
                logger.info("SVG stream:$it")
                Graphviz.fromGraph(graph).render(Format.SVG).toOutputStream(it)
            }
            logger.info("SVG generated successfully")
        } catch (e: Exception) {
            logger.info("Error generating SVG: $e")
        }
    }

    private fun randomGraphColor(): String {
        val random = java.util.Random()
        val color = (0xFFFFFF and random.nextInt()).toString(16)
        return "#${color.padStart(6, '0')}"
    }
}
