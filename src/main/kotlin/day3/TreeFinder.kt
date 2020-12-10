package day3

import java.awt.Point

class TreeFinder {
    private val tree = "#"
    private var actualTreeMap: List<String> = emptyList()

    fun findNumberOfTreeCollisions(inputMap: List<String>, xTranslation: Int, yTranslation: Int): Int {
        actualTreeMap = inputMap
        var treeCounter = 0
        var currentCoordinate = Point()
        while (notReachedTheBottomYet(currentCoordinate)) {
            when {
                placedOnTree(currentCoordinate) -> treeCounter++
            }
            currentCoordinate = findNewCoordinate(currentCoordinate, xTranslation, yTranslation)
        }
        return treeCounter
    }

    private fun notReachedTheBottomYet(coordinates: Point) = coordinates.y < actualTreeMap.size

    private fun placedOnTree(coordinates: Point) = actualTreeMap[coordinates.y].substring(coordinates.x, coordinates.x + 1) == tree

    private fun findNewCoordinate(currentCoordinate: Point, xTranslation: Int, yTranslation: Int) =
        Point((currentCoordinate.x + xTranslation) % actualTreeMap[0].length, currentCoordinate.y + yTranslation)
}
