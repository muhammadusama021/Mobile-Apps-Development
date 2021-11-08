import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.usamacreation.sqlite_task.MainActivity
import com.usamacreation.sqlite_task.R
import com.usamacreation.sqlite_task.ShowRecords


class ItemAdapter(val context: Context, val items: ArrayList<EmpModelClass>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.items_row,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder.tvName.text = item.name
        holder.tvsession.text = item.session
        holder.tvreg.text = item.reg.toString()


        // Updating the background color according to the odd/even positions in list.
        if (position % 2 == 0) {
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorW
                )
            )
        } else {
            holder.llMain.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite))
        }
        holder.ivDelete.setOnClickListener { view ->

            if (context is ShowRecords) {
                context.deleteRecordAlertDialog(item)
            }
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each item to
        val llMain = view.findViewById<LinearLayout>(R.id.llMain)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvsession = view.findViewById<TextView>(R.id.tvsession)
        val tvreg = view.findViewById<TextView>(R.id.tvreg)

        val ivDelete = view.findViewById<ImageView>(R.id.ivDelete)
    }
}