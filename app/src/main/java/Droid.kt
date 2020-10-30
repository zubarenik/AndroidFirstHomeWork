import java.io.Serializable


class Droid(
        val name: String,
        val state: Int
): Serializable {
    companion object {
        const val STATE_EVEN = 1
        const val STATE_ODD = 0
    }
}