import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.R


class DroidViewHolder(itemView: View, val listener: IListener) : RecyclerView.ViewHolder(itemView) {
    interface IListener {
        fun onDroidClicked(position: Int)
    }

    private val name: TextView
    private val image: ImageView

    init {
        name = itemView.findViewById(R.id.name)
        image = itemView.findViewById(R.id.image)

        itemView.setOnClickListener {
            listener.onDroidClicked(adapterPosition)
        }
    }

    fun bind(item: Droid) {
        name.text = item.name

        val colorResId = when (item.state) {
            Droid.STATE_EVEN -> R.color.color_red
            Droid.STATE_ODD -> R.color.color_blue
            else -> R.color.color_black
        }
        image.setImageResource(colorResId)
    }
}
