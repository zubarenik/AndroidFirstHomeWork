import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.R


class DroidViewHolder(private val listener: (Droid) -> Unit, itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    private val droid: TextView

    init {
        droid = itemView.findViewById(R.id.item)
    }

    fun bind(item: Droid) {
        droid.text = item.name

        val color = when (item.state) {
            Droid.STATE_EVEN -> convertColor(R.color.color_red)
            Droid.STATE_ODD -> convertColor(R.color.color_blue)
            else -> convertColor()
        }
        droid.setTextColor(color)

        droid.setOnClickListener { listener.invoke(item) }
    }

    private fun convertColor(color: Int = 0) : Int{
        return when(color) {
            R.color.color_red -> Color.RED
            R.color.color_blue -> Color.BLUE
            else -> Color.BLACK
        }
    }
}
