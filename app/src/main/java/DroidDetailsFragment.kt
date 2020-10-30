import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.R


class DroidDetailsFragment : DialogFragment() {
    companion object {
        const val EXTRAS_DROID = "DROID"

        fun newInstance(droid: Droid): DroidDetailsFragment {
            val extras = Bundle().apply {
                putSerializable(EXTRAS_DROID, droid)
            }

            val fragment = DroidDetailsFragment().apply {
                arguments = extras
            }

            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val droid = droid()
        if (droid != null) {
            view.findViewById<TextView>(R.id.name).text = droid.name

            val stateColor = when (droid.state) {
                Droid.STATE_EVEN -> R.color.color_red
                Droid.STATE_ODD -> R.color.color_blue
                else -> R.color.color_black
            }
        }
    }

    fun droid(): Droid? {
        return arguments?.getSerializable(EXTRAS_DROID) as? Droid
    }
}