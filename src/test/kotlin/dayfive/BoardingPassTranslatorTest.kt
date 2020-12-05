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
        val result: Seat = boardingPassTranslator.createSeatFrom("FBFBBFFRLR")

        assertThat(result, equalTo(Seat(44, 5)))
    }

    @Test
    internal fun `example 2`() {
        val result: Seat = boardingPassTranslator.createSeatFrom("BFFFBBFRRR")

        assertThat(result, equalTo(Seat(70, 7)))
    }

    @Test
    internal fun `example 3`() {
        val result: Seat = boardingPassTranslator.createSeatFrom("FFFBBBFRRR")

        assertThat(result, equalTo(Seat(14, 7)))
    }

    @Test
    internal fun `example 4`() {
        val result: Seat = boardingPassTranslator.createSeatFrom("BBFFBBFRLL")

        assertThat(result, equalTo(Seat(102, 4)))
    }

    @Test
    internal fun `part 1`() {
        val seatWithHighestId = input.map { boardingPassTranslator.createSeatFrom(it) }.maxByOrNull { it.id }

        assertThat(seatWithHighestId?.id, equalTo(866))
    }

    @Test
    internal fun `part 2`() {
        val occupiedSeats = input.map { boardingPassTranslator.createSeatFrom(it) }
        var myPossibleSeat: Seat? = null
        for (i in 0..128) {
            for (j in 0..7) {
                val targetSeat = Seat(i, j)
                if (occupiedSeats.none { it == targetSeat } &&
                    occupiedSeats.filter { idsOneEitherSide(it, targetSeat) }.count() == 2) {
                    myPossibleSeat = targetSeat
                }
            }
        }

        assertThat(myPossibleSeat?.id, equalTo(583))
    }

    private fun idsOneEitherSide(it: Seat, targetSeat: Seat) = it.id == targetSeat.id + 1 || it.id == targetSeat.id - 1
}
