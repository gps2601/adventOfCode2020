package dayseven

import FileLoader
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import org.junit.jupiter.api.Test

class BagInspectorTest {
    private val fileLoader = FileLoader()

    @Test
    internal fun `part 1 example`() {
        val rules = fileLoader.createLinesFrom("dayseven/example-bags.txt")

        val graph = DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge::class.java)

        rules.forEach { rule ->
            val bagAndBagsItCanHold = rule.split("contain")
            val bagName = bagAndBagsItCanHold.first().removeSuffix(" bags ")
            graph.addVertex(bagName)
        }

        rules.forEach { rule ->
            val bagAndBagsItCanHold = rule.split("contain")
            val bagName = bagAndBagsItCanHold.first().removeSuffix(" bags ")
            val bagsItCanHold = bagAndBagsItCanHold.last().removePrefix(" ").removeSuffix(".")
            if (bagsItCanHold != "no other bags") {
                val bags = bagsItCanHold.split(",")
                bags.forEach {
                    val trimmmedBag = it.removePrefix(" ").removeSuffix(",")
                    val bagColour = trimmmedBag.split(" ").subList(1, 3).joinToString(" ")
                    graph.addEdge(bagColour, bagName)
                }
            }
        }
        val dijkstraShortestPath = DijkstraShortestPath(graph)
        val paths = dijkstraShortestPath.getPaths("shiny gold")

        val count = rules.filter { it ->
            val firstPart = it.split("contain").first()
            val bagName = firstPart.removeSuffix(" bags ")
            paths.getPath(bagName) != null
        }.count()
        println(count - 1)
    }

    @Test
    internal fun `part 2 example`() {
        val rules = fileLoader.createLinesFrom("dayseven/example-bags.txt")
//        126

        val numberOfBags =  getBagsFor(rules, "shiny gold")
        println(numberOfBags - 1)
    }

    private fun getBagsFor(rules: List<String>, bagColour: String): Int {
        val rule = rules.first { it -> it.split(" ").take(2).joinToString(" ") == bagColour }
        val containedBags = rule.split("contain").last()
        val trimmed = containedBags.removePrefix(" ")
        val puncTrimmed = trimmed.removeSuffix(".")
        if(puncTrimmed == "no other bags") {
            return 1
        } else {
            val listOfContainedBag = puncTrimmed.split(",")
            val sumBy = listOfContainedBag.sumBy { it ->
                println(it)
                val number = it.trim().split(" ").first().toInt()
                val color = it.trim().split(" ").subList(1, 3).joinToString(" ")
                number * getBagsFor(rules, color)
            }
            return 1 + sumBy
        }
    }
}