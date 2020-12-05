package dayfive

class BoardingPassTranslator {

    fun createSeatFromInput(input: String): Seat {
        val row = input.substring(0, 7).replace('F', '0').replace('B', '1').toInt(2)
        val column = input.substring(7, 10).replace('L', '0').replace('R', '1').toInt(2)
        val id = row * 8 + column

        return Seat(id, row, column)
    }

    fun createSeatFromRowCol(row: Int, column: Int): Seat {
        return Seat((row * 8) + column, row, column)
    }
}
