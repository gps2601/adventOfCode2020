package day10

import FileLoader
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.jgrapht.alg.shortestpath.AllDirectedPaths
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import org.junit.jupiter.api.Test

class AdapterArray {
    private val fileLoader = FileLoader()
    private val input: List<Long> = fileLoader.createLongListFromFile("day10/input.txt")
    private val adapters = input.plus(0).plus(input.maxOrNull()!! + 3).sorted()

    @Test
    fun `part 1 solution`() {
        val voltageDiffProduct = adapters
            .asSequence()
            .zipWithNext()
            .map { it.second - it.first }
            .groupingBy { it }
            .eachCount()
            .run {
                get(1)!! * get(3)!!
            }

        assertThat(voltageDiffProduct, equalTo(1625))
    }

    @Test
    fun `part 2 solution`() {
        val distinct = adapters
            .asSequence()
            .windowed(3, 1)
            .filter { it.first() + 6 == it.last() }
            .map { it[1] }
            .plus(adapters.first())
            .plus(adapters.last())
            .sorted()
            .toList()
            .zipWithNext()
            .map { endNodes ->
                val subgraph = DefaultDirectedGraph<Long, DefaultEdge>(DefaultEdge::class.java)
                val nodesForSubGraph = adapters.filter { it >= endNodes.first && it <= endNodes.second }
                nodesForSubGraph.forEach { subgraph.addVertex(it) }
                nodesForSubGraph.forEach { source ->
                    nodesForSubGraph.forEach { target ->
                        if (target - source in 1..3) {
                            subgraph.addEdge(source, target)
                        }
                    }
                }
                val allDirectedPaths = AllDirectedPaths(subgraph)
                allDirectedPaths.getAllPaths(endNodes.first, endNodes.second, true, nodesForSubGraph.size).size.toLong()
            }
            .reduce { a, b -> a * b }

        assertThat(distinct, equalTo(3100448333024))
    }
}
