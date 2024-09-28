package com.andrejaziv.daggervis.graph

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
        private const val MAIN_DAGGER_COMPONENT_GRAPH_NAME_PREFIX = "Main"
        private const val DEPENDENCIES_ARGUMENT_NAME = "dependencies"
        private const val DAGGER_COMPONENT = "Component"
        private const val NODE_COLOR = "color"
        private const val FONT_COLOR = "fontcolor"
        private const val GRAPH_START = "digraph G {\n"
        private const val GRAPH_END = "}\n"
    }

    override fun generateGraphs(classDeclarations: Sequence<KSClassDeclaration>) {
        logger.info("Dagger graph creation started...")

        val componentDependenciesByModule = mutableMapOf<String, List<String>>()

        classDeclarations.forEach { classDeclaration ->
            val componentName = classDeclaration.simpleName.asString()

            logger.info("Processing component: $componentName")

            val componentAnnotation = classDeclaration.annotations.find {
                it.shortName.asString() == DAGGER_COMPONENT
            }

            val componentDependencies = componentAnnotation?.arguments?.firstOrNull { argument ->
                argument.name?.asString() == DEPENDENCIES_ARGUMENT_NAME
            }?.value as? List<*> ?: emptyList<String>()

            val componentDependenciesNames = componentDependencies.map { it.toString() }
            componentDependenciesByModule[componentName] = componentDependenciesNames

            //Create separate graph for every component and its dependencies
            generateSvg(
                dotContent = createGraphDot(mapOf(componentName to componentDependenciesNames)),
                graphNamePrefix = COMPONENT_GRAPH_NAME_PREFIX,
                graphNameExtension = componentName,
                format = Format.SVG
            )
        }

        //Create top level graph representing all the dependencies
        generateSvg(
            dotContent = createGraphDot(componentDependenciesByModule),
            graphNamePrefix = COMPONENT_GRAPH_NAME_PREFIX,
            graphNameExtension = MAIN_DAGGER_COMPONENT_GRAPH_NAME_PREFIX,
            format = Format.SVG
        )
    }

    private fun createGraphDot(componentDependencies: Map<String, List<String>>): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(GRAPH_START)
        componentDependencies.forEach { (componentName, dependencies) ->
            val color = randomGraphColor()
            stringBuilder.append(
                """
            "$componentName"[$NODE_COLOR="$color", $FONT_COLOR="$color"];
            """.trimIndent()
            )
            for (dependency in dependencies) {
                stringBuilder.append(
                    """
                "$componentName" -> "$dependency" [$NODE_COLOR="$color"];
                """.trimIndent()
                )
            }
        }
        stringBuilder.append(GRAPH_END)
        return stringBuilder.toString()
    }
}
