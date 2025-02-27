package advancedlab.lab1.fragment


import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedlab.R

class ContentProviderFragment : Fragment() {

    private val REQUEST_READ_CALENDAR = 200
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CalendarEventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_content_provider, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewEvents)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CalendarEventsAdapter()
        recyclerView.adapter = adapter

        if (ContextCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.READ_CALENDAR
            ) == PackageManager.PERMISSION_GRANTED) {
            fetchCalendarEvents()
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.READ_CALENDAR), REQUEST_READ_CALENDAR)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_READ_CALENDAR) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                fetchCalendarEvents()
            } else {
                Toast.makeText(requireContext(), "读日历权限被拒绝", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchCalendarEvents() {
        val eventsUri: Uri = CalendarContract.Events.CONTENT_URI
        val projection = arrayOf(
            CalendarContract.Events._ID,
            CalendarContract.Events.TITLE,
            CalendarContract.Events.DTSTART
        )
        val selection = "${CalendarContract.Events.DTSTART} >= ?"
        val selectionArgs = arrayOf(System.currentTimeMillis().toString())
        val sortOrder = CalendarContract.Events.DTSTART + " ASC"

        val cursor: Cursor? = requireContext().contentResolver.query(
            eventsUri,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )

        val eventList = mutableListOf<CalendarEvent>()
        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getLong(it.getColumnIndexOrThrow(CalendarContract.Events._ID))
                val title = it.getString(it.getColumnIndexOrThrow(CalendarContract.Events.TITLE))
                val dtStart = it.getLong(it.getColumnIndexOrThrow(CalendarContract.Events.DTSTART))
                eventList.add(CalendarEvent(id, title, dtStart))
            }
        }
        adapter.submitList(eventList)
    }
}

data class CalendarEvent(val id: Long, val title: String, val dtStart: Long)

class CalendarEventsAdapter : RecyclerView.Adapter<CalendarEventsAdapter.ViewHolder>() {

    private val data = mutableListOf<CalendarEvent>()

    fun submitList(list: List<CalendarEvent>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = data[position]
        holder.bind(event)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: CalendarEvent) {
            itemView.findViewById<android.widget.TextView>(android.R.id.text1).text = event.title
            itemView.findViewById<android.widget.TextView>(android.R.id.text2).text =
                "开始时间: ${event.dtStart}"
        }
    }
}