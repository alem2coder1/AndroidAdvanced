package advancedlab.lab1.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.advancedlab.R
import com.example.advancedlab.lab1.activity.ForegroundMusicService

class ForegroundServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_foreground_service, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnStartMusic)?.setOnClickListener {
            val intent = Intent(requireContext(), ForegroundMusicService::class.java)
            intent.action = "ACTION_START"
            requireContext().startForegroundService(intent)
        }

        view.findViewById<Button>(R.id.btnPauseMusic)?.setOnClickListener {
            val intent = Intent(requireContext(), ForegroundMusicService::class.java)
            intent.action = "ACTION_PAUSE"
            requireContext().startService(intent)
        }

        view.findViewById<Button>(R.id.btnStopMusic)?.setOnClickListener {
            val intent = Intent(requireContext(), ForegroundMusicService::class.java)
            intent.action = "ACTION_STOP"
            requireContext().startService(intent)
        }
    }
}