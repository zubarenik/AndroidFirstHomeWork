import java.R
import java.util.*


class DroidRepository private constructor() {
    companion object {
        const val DEFAULT_SIZE = 100
        val instance by lazy { DroidRepository() }
    }

    private val droidList by lazy { initializeData() }
    fun list() = droidList
    fun item(index: Int) = droidList[index]

    fun changeList() {
        val position = droidList.size + 1
        val name = "$position"
        val state = when (position % 2 == 0) {
            true -> Droid.STATE_EVEN
            false -> Droid.STATE_ODD
        }
        val newDroid = Droid(name, state)
        droidList.add(newDroid)
    }

    private fun initializeData(): LinkedList<Droid> {
        val data = LinkedList<Droid>()

        for (position in 1 until DEFAULT_SIZE + 1) {
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