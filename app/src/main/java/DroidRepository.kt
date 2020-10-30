import java.util.*


class DroidRepository private constructor() {
    companion object {
        const val DATA_SIZE = 100
        val instance by lazy { DroidRepository() }
    }

    private val droidList by lazy { initializeData() }
    fun list() = droidList
    fun item(index: Int) = droidList[index]

    private fun initializeData(): List<Droid> {
        val data = mutableListOf<Droid>()

        for (position in 1 until DATA_SIZE + 1) {
            val name = "$position"
            val state = when (position % 2 == 0) {
                true -> Droid.STATE_EVEN
                false -> Droid.STATE_ODD
            }
            val droid = Droid(name, state)
            data.add(droid)
        }

        return data
    }
}