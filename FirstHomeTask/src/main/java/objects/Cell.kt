package objects

data class Cell(val number: Int, val color: Int) {
    companion object {
        const val DATA_SIZE_START_VALUE = 100
    }
}
