import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.R


class DroidAdapter(private val data: List<Droid>, private val listener: (Droid) -> Unit)
    : RecyclerView.Adapter<DroidViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DroidViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.item, parent, false)

        return DroidViewHolder(listener, layout)
    }

    override fun onBindViewHolder(holder: DroidViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount() = data.size
}