package com.andrejaziv.daggervis.helpers

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import guru.nidi.graphviz.engine.Format

internal class DaggerComponentGraphGenerator(
    private val logger: KSPLogger,
    codeGenerator: CodeGenerator
) : GraphGenerator(logger, codeGenerator) {

   internal companion object {
        internal const val PACKAGE_NAME = "dagger.graph"
        private const val COMPONENT_GRAPH_NAME_PREFIX = "Dagger-Component-Graph"
        private const val MAIN_DAGGER_COMPONENT_GRAPH_NAME_EXTENSION = "Main"
        private const val COMPONENT_DEPENDENCIES = "dependencies"
        private const val DAGGER_COMPONENT = "Component"
    }

    override fun createGraph(annotations: Sequence<KSClassDeclaration>) {
        logger.info("Dagger graph creation started...")
        val mainComponentMap = mutableMapOf<String, List<String>>()
        annotations.forEach { component ->
            val componentName = component.simpleName.asString()
            val annotation = component.annotations.find {
                it.shortName.asString() == DAGGER_COMPONENT
            }
            annotation?.arguments?.forEach { arg ->
                logger.info("Processing component: $componentName")
                when (arg.name?.asString()) {
                    COMPONENT_DEPENDENCIES -> {
                        val dependenciesList = arg.value as? List<*>
                        val dependenciesListCasted =
                            dependenciesList?.map { it.toString() } ?: emptyList()
                        mainComponentMap[componentName] = dependenciesListCasted
                        //Create separate graph for every component and its dependencies
                        generateSvg(
                            dotContent = createGraphDot(mapOf(componentName to dependenciesListCasted)),
                            graphNamePrefix = COMPONENT_GRAPH_NAME_PREFIX,
                            graphNameExtension = componentName,
                            format = Format.SVG
                        )
                    }
                }
            }
        }
        //Create top level graph representing all the dependencies
        generateSvg(
            dotContent = createGraphDot(mainComponentMap),
            graphNamePrefix = COMPONENT_GRAPH_NAME_PREFIX,
            graphNameExtension = MAIN_DAGGER_COMPONENT_GRAPH_NAME_EXTENSION,
            format = Format.SVG
        )
    }

    private fun createGraphDot(componentsGraphs: Map<String, List<String>>): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("digraph G {\n")
        for ((componentName, dependencyList) in componentsGraphs) {
            val color = randomGraphColor()
            stringBuilder.append("    \"$componentName\" [color=\"$color\", fontcolor=\"$color\"];\n")
            for (dependency in dependencyList) {
                stringBuilder.append("    \"$componentName\" -> \"$dependency\" [color=\"$color\"];\n")
            }
        }
        stringBuilder.append("}\n")
        return stringBuilder.toString()
    }
}
