package day11

import FileLoader
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class Day11Test {
    private val fileLoader = FileLoader()

    @Test
    fun `part 1 example`() {
        val input = fileLoader.createLinesFrom("day11/example.txt").map { ".$it." }.toMutableList()
        input.add(0, ".".repeat(input.first().length))
        input.add(".".repeat(input.first().length))
        val initialLayout = input.map { it.toCharArray() }.toTypedArray()
        var nextLayout = initialLayout
        val occupied: Int
        while(true) {
            val currentLayout = nextLayout
            nextLayout = next(nextLayout)
            if(currentLayout.contentDeepEquals(nextLayout)){
                occupied = currentLayout.flatMap { it1 -> it1.filter { it2 -> it2 == '#' } }.count()
                break
            }
        }

        assertThat(occupied, equalTo(37))
    }

    @Test
    fun `part 1 solution`() {
        val input = fileLoader.createLinesFrom("day11/input.txt").map { ".$it." }.toMutableList()
        input.add(0, ".".repeat(input.first().length))
        input.add(".".repeat(input.first().length))
        val initialLayout = input.map { it.toCharArray() }.toTypedArray()
        var nextLayout = initialLayout
        val occupied: Int
        while(true) {
            val currentLayout = nextLayout
            nextLayout = next(nextLayout)
            if(currentLayout.contentDeepEquals(nextLayout)){
                occupied = currentLayout.flatMap { it1 -> it1.filter { it2 -> it2 == '#' } }.count()
                break
            }
        }

        assertThat(occupied, equalTo(2448))
    }

    private fun next(layout: Array<CharArray>) = Array(layout.size) { y ->
        CharArray(layout[0].size) { x ->
            if(x == 0 || y == 0 || y == layout.size -1 || x == layout[0].size - 1) {
                '.'
            } else {
                val count = surroundingCount(layout, y, x)
                val position = layout[y][x]
                if (position == 'L') {
                    if(count == 0) {
                        '#'
                    } else {
                        'L'
                    }
                } else if(position == '#') {
                    if(count >= 4) {
                        'L'
                    } else {
                        '#'
                    }
                }
                else {
                    '.'
                }
            }
        }
    }

    private fun surroundingCount(layout: Array<CharArray>, y: Int, x: Int): Int {
        var counter = 0
        for (i in -1..1) {
            for(j in -1..1) {
                if(i==0 && j==0) continue
                if(layout[y+i][x+j] == '#') counter++
            }
        }
        return counter
    }
}
