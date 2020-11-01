import android.graphics.Color
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
            Droid.STATE_EVEN -> convertColor(R.color.color_red)
            Droid.STATE_ODD -> convertColor(R.color.color_blue)
            else -> convertColor()
        }
        itemView.setTextColor(color)
    }

    private fun convertColor(color: Int = 0) : Int{
        return when(color) {
            R.color.color_red -> Color.RED
            R.color.color_blue -> Color.BLUE
            else -> Color.BLACK
        }
    }
}