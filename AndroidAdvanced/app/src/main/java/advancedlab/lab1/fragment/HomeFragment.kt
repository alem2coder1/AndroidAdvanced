package advancedlab.lab1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.advancedlab.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_open_intents).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_intentsFragment)
        }
        view.findViewById<Button>(R.id.btn_open_foreground_service).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_foregroundServiceFragment)
        }
        view.findViewById<Button>(R.id.btn_open_broadcast_receiver).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_broadcastReceiverFragment)
        }
        view.findViewById<Button>(R.id.btn_open_content_provider).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_contentProviderFragment)
        }
    }
}