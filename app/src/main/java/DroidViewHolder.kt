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
            Droid.STATE_EVEN -> R.color.color_red
            Droid.STATE_ODD -> R.color.color_blue
            else -> R.color.color_black
        }
        droid.setTextColor(color)

        droid.setOnClickListener { listener.invoke(item) }
    }
}
