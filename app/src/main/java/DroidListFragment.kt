import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.R


class DroidListFragment : Fragment() {
    interface IListener {
        fun onDroidClicked(droid: Droid)
    }

    private var listener: IListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = requireActivity() as? IListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
                R.layout.content_list,
                container,
                false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.apply {
            adapter = DroidAdapter(DroidRepository.instance.list(), DroidClickHandler())
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDetach() {
        super.onDetach()

        listener = null
    }

    inner class DroidClickHandler: DroidViewHolder.IListener {
        override fun onDroidClicked(position: Int) {
            val droid = DroidRepository.instance.item(position)

            listener?.onDroidClicked(droid)
        }
    }
}
