package day17

import FileLoader
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.math.abs

class ConwayCubesTest {
    val fileLoader = FileLoader()
    val input = fileLoader.createLinesFrom("day17/input.txt").map { it.toCharArray() }

    @Test
    fun `part 1 solution`() {
        val coords = input.mapIndexed { y, chars ->
            chars.mapIndexed { x, c ->
                val active = c == '#'
                val point3D = Point3D(x, y, 0)
                if (active) {
                    point3D.isActive = true
                }
                point3D
            }
        }.flatten().toMutableSet()

        val activeCubeEachCycle = mutableListOf<Int>()
        for(i in 0..5) {
            generateNeighbouringCubes(coords)
            changeState(coords)
            activeCubeEachCycle.add(activeCubes(coords))
        }

        assertThat(activeCubeEachCycle.last(), equalTo(380))
    }

    @Test
    @Disabled("this is really slow due to state changing checking all coordinates")
    fun `part 2 solution`() {
        val coords = input.mapIndexed { y, chars ->
            chars.mapIndexed { x, c ->
                val active = c == '#'
                val point4D = Point4D(x, y, 0, 0)
                if (active) {
                    point4D.isActive = true
                }
                point4D
            }
        }.flatten().toMutableSet()

        val activeCubeEachCycle = mutableListOf<Int>()
        for(i in 0..5) {

            println("on step number $i")
            val neighbourMilli = measureTimeMillis {
                generateNeighbouringCubes4D(coords)
            }
            println("Generate neighbours: $neighbourMilli")
            val statemilli = measureTimeMillis {
                changeState4D(coords)
            }
            println("Change state: $statemilli")
            val element = activeCubes4D(coords)
            activeCubeEachCycle.add(element)
        }

        assertThat(activeCubeEachCycle.last(), equalTo(2332))
    }

    private fun activeCubes(coords: MutableSet<Point3D>): Int {
        return coords.filter { it.isActive }.count()
    }

    private fun activeCubes4D(coords: MutableSet<Point4D>): Int {
        return coords.filter { it.isActive }.count()
    }

    private fun changeState(coords: MutableSet<Point3D>) {
        coords.forEach { point ->
            val activeNeighbours = coords.filter { potentialNeighbour ->
                point.isNeighbour(potentialNeighbour)
            }.count { it.isActive }
            if(point.isActive) {
                if (activeNeighbours != 2 && activeNeighbours != 3) {
                    point.shouldChangeState = true
                }
            } else {
                if (activeNeighbours == 3) {
                    point.shouldChangeState = true
                }
            }
        }

        coords.filter { it.shouldChangeState }.forEach { pointToChange ->
            pointToChange.isActive = !pointToChange.isActive
            pointToChange.shouldChangeState = false
        }
    }

    private fun generateNeighbouringCubes(coordinateSystem: MutableSet<Point3D>) {
        val neighbouringCoordinates = mutableSetOf<Point3D>()
        coordinateSystem.forEach { point ->
            for (x in -1..1) {
                for (y in -1..1) {
                    for (z in -1..1) {
                        val newPoint = Point3D(point.x + x, point.y + y, point.z + z)
                        val isNeighbour = point.isNeighbour(newPoint)
                        val alreadyExists = coordinateSystem.contains(newPoint)
                        if (isNeighbour && !alreadyExists) {
                            neighbouringCoordinates.add(newPoint)
                        }
                    }
                }
            }
        }
        coordinateSystem.addAll(neighbouringCoordinates)
    }

    private fun generateNeighbouringCubes4D(coordinateSystem: MutableSet<Point4D>) {
        val neighbouringCoordinates = mutableSetOf<Point4D>()
        coordinateSystem.forEach { point ->
            for (x in -1..1) {
                for (y in -1..1) {
                    for (z in -1..1) {
                        for(w in -1..1) {
                            val newPoint = Point4D(point.x + x, point.y + y, point.z + z, point.w + w)
                            val isNeighbour = point.isNeighbour(newPoint)
                            val alreadyExists = coordinateSystem.contains(newPoint)
                            if (isNeighbour && !alreadyExists) {
                                neighbouringCoordinates.add(newPoint)
                            }
                        }
                    }
                }
            }
        }
        coordinateSystem.addAll(neighbouringCoordinates)
    }

    private fun changeState4D(coords: MutableSet<Point4D>) {
        coords.forEach { point ->

            val activeNeighbours = coords.filter { potentialNeighbour ->
                point.isNeighbour(potentialNeighbour)
            }.count { it.isActive }
            if(point.isActive) {
                if (activeNeighbours != 2 && activeNeighbours != 3) {
                    point.shouldChangeState = true
                }
            } else {
                if (activeNeighbours == 3) {
                    point.shouldChangeState = true
                }
            }
        }

        coords.filter { it.shouldChangeState }.forEach { pointToChange ->
            pointToChange.isActive = !pointToChange.isActive
            pointToChange.shouldChangeState = false
        }
    }

}

data class Point4D(val x: Int, val y: Int, val z: Int, val w: Int){
    var isActive: Boolean = false
    var shouldChangeState = false

    fun isNeighbour(newPoint: Point4D): Boolean {
        val isTheSameCube = (x == newPoint.x) && (y == newPoint.y) && (z == newPoint.z) && (w == newPoint.w)
        return abs(x - newPoint.x) <= 1
                && abs(y - newPoint.y) <= 1
                && abs(z - newPoint.z) <= 1
                && abs(w - newPoint.w) <= 1
                && !isTheSameCube
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point4D

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false
        if (w != other.w) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + z
        result = 31 * result + w
        return result
    }
}

data class Point3D(val x: Int, val y: Int, val z: Int) {
    var isActive: Boolean = false
    var shouldChangeState = false

    fun isNeighbour(newPoint: Point3D): Boolean {
        val isTheSameCube = (x == newPoint.x) && (y == newPoint.y) && (z == newPoint.z)
        return abs(x - newPoint.x) <= 1 && abs(y - newPoint.y) <= 1 && abs(z - newPoint.z) <= 1 && !isTheSameCube
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point3D

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + z
        return result
    }
}

inline fun measureTimeMillis(block: () -> Unit): Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}
