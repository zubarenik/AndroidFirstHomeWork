import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.R


class DroidListFragment : Fragment() {
    companion object {
        private const val PORTRAIT_COLUMNS = 3
        private const val LANDSPACE_COLUMNS = 4
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.item_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val droidAdapter = DroidAdapter(DroidRepository.instance.list(), ::listener)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.apply {
            adapter = droidAdapter
            layoutManager = GridLayoutManager(context, getColumns())
        }

        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            DroidRepository.instance.changeList()
            droidAdapter.notifyDataSetChanged()
        }
    }

    private fun listener(item: Droid){
        requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_layout, DroidDetailsFragment(item))
                .addToBackStack(null)
                .commit()
    }

    private fun getColumns() = when (resources.getBoolean(R.bool.is_land)) {
        true -> LANDSPACE_COLUMNS
        false -> PORTRAIT_COLUMNS
    }
}
