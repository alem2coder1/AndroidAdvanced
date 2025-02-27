package advancedlab.lab1.viewModel

import androidx.lifecycle.ViewModel

class Lab1ViewModel : ViewModel() {
    var counter: Int = 0

    fun incrementCounter() {
        counter++
    }
}