package advancedlab.lab1.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.advancedlab.R

class IntentsFragment : Fragment() {

    private val PICK_IMAGE_REQUEST = 1001
    private var selectedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_intents, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnPickImage)?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        view.findViewById<Button>(R.id.btnShareInstagramStory)?.setOnClickListener {
            selectedImageUri?.let { uri ->
                shareToInstagramStory(uri)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            data?.data?.let {
                selectedImageUri = it
            }
        }
    }

    private fun shareToInstagramStory(imageUri: Uri) {
        val intent = Intent("com.instagram.share.ADD_TO_STORY")
        intent.setDataAndType(imageUri, "image/*")
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        } else {
        }
    }
}