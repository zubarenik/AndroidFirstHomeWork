import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.R


class DroidAdapter(val data: List<Droid>, val listener: DroidViewHolder.IListener) : RecyclerView.Adapter<DroidViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DroidViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.item_droid, parent, false)

        return DroidViewHolder(layout, listener)
    }

    override fun onBindViewHolder(holder: DroidViewHolder, position: Int) {
        val item = data[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}