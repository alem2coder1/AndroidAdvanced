package advancedlab.lab1.fragment



import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.advancedlab.R
import com.example.advancedlab.lab1.activity.AirplaneModeReceiver

class BroadcastReceiverFragment : Fragment() {

    private val airplaneModeReceiver = AirplaneModeReceiver()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_broadcast_receiver, container, false)

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter("android.intent.action.AIRPLANE_MODE")
        requireContext().registerReceiver(airplaneModeReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(airplaneModeReceiver)
    }
}