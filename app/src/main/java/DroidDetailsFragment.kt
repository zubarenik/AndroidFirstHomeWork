import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.R


class DroidDetailsFragment(val item: Droid) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.item_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemView = view.findViewById<TextView>(R.id.name)
        itemView.text = item.name

        val color = when (item.state) {
            Droid.STATE_EVEN -> R.color.color_red
            Droid.STATE_ODD -> R.color.color_blue
            else -> R.color.color_black
        }
        itemView.setTextColor(color)
    }
}