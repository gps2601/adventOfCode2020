package day5

class BoardingPassTranslator {

    fun createSeatFrom(input: String): Seat {
        val row = input.substring(0, 7).replace('F', '0').replace('B', '1').toInt(2)
        val column = input.substring(7, 10).replace('L', '0').replace('R', '1').toInt(2)

        return Seat(row, column)
    }
}
