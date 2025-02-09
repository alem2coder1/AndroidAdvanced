package kz.alash.advancedlab.lab1.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kz.alash.advancedlab.R
import kz.alash.advancedlab.lab1.viewModel.Lab1ViewModel

class CounterFragment : Fragment() {
    private lateinit var viewModel: Lab1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_counter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(Lab1ViewModel::class.java)

        val counterText = view.findViewById<TextView>(R.id.counterText)
        val incrementButton = view.findViewById<Button>(R.id.incrementButton)

        counterText.text = viewModel.counter.toString()

        incrementButton.setOnClickListener {
            viewModel.incrementCounter()
            counterText.text = viewModel.counter.toString()
        }
    }
}