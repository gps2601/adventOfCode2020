package dayfive

import FileLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class BoardingPassTranslatorTest {
    private val boardingPassTranslator = BoardingPassTranslator()
    private val fileLoader = FileLoader()
    private val input = fileLoader.createLinesFrom("dayfive/boarding-passes.txt")

    @Test
    internal fun `example 1`() {
        val result: Seat = boardingPassTranslator.createSeatFromInput("FBFBBFFRLR")

        assertThat(result.row, equalTo(44))
        assertThat(result.column, equalTo(5))
        assertThat(result.id, equalTo(357))
    }

    @Test
    internal fun `example 2`() {
        val result: Seat = boardingPassTranslator.createSeatFromInput("BFFFBBFRRR")

        assertThat(result.row, equalTo(70))
        assertThat(result.column, equalTo(7))
        assertThat(result.id, equalTo(567))
    }

    @Test
    internal fun `example 3`() {
        val result: Seat = boardingPassTranslator.createSeatFromInput("FFFBBBFRRR")

        assertThat(result.row, equalTo(14))
        assertThat(result.column, equalTo(7))
        assertThat(result.id, equalTo(119))
    }

    @Test
    internal fun `example 4`() {
        val result: Seat = boardingPassTranslator.createSeatFromInput("BBFFBBFRLL")

        assertThat(result.row, equalTo(102))
        assertThat(result.column, equalTo(4))
        assertThat(result.id, equalTo(820))
    }

    @Test
    internal fun `part 1`() {
        val seatWithHighestId = input.map { boardingPassTranslator.createSeatFromInput(it) }.maxByOrNull { it.id }

        assertThat(seatWithHighestId?.id, equalTo(866))
    }

    @Test
    internal fun `part 2`() {
        val occupiedSeats = input.map { boardingPassTranslator.createSeatFromInput(it) }
        var myPossibleSeat: Seat? = null
        for (i in 0..128) {
            for(j in 0..7) {
                val targetSeat = boardingPassTranslator.createSeatFromRowCol(i, j)
                if(occupiedSeats.none { it.id == targetSeat.id } && occupiedSeats.filter { it.id == targetSeat.id + 1 || it.id == targetSeat.id -1 }.count() == 2) {
                    myPossibleSeat = targetSeat
                }
            }
        }

        assertThat(myPossibleSeat?.id, equalTo(583))
    }
}
