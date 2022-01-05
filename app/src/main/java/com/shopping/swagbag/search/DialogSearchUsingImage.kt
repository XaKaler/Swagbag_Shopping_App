package com.shopping.swagbag.search

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.TakePicture
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.shopping.swagbag.R


class DialogSearchUsingImage: DialogFragment(R.layout.dialog_search_using_image), View.OnClickListener {

        private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ success ->
            if(success.resultCode == Activity.RESULT_OK){
                context?.let { ShowToast.short("Get image successfully", it) }
            }else{
                context?.let { ShowToast.short("Get image failed", it) }
            }
            dismiss()
        }

    private val galleryLauncher  = registerForActivityResult(ActivityResultContracts.GetContent()){ _: Uri->
        run {
            context?.let { ShowToast.short("Get image successfully", it) }
            dismiss()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_search_using_image, container, false)

        val cardCamera = view.findViewById<CardView>(R.id.cardCamera)
        val cardGallery = view.findViewById<CardView>(R.id.cardGallery)
        val cancel = view.findViewById<ImageView>(R.id.imgCancel)

        cancel.setOnClickListener(this)
        cardCamera.setOnClickListener(this)
        cardGallery.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.cardCamera -> imageFromCamera()
            R.id.cardGallery -> imageFromGallery()
            R.id.imgCancel -> dismiss()
        }
    }

    private fun imageFromGallery() {
        galleryLauncher.launch("image/*")
    }

    private fun imageFromCamera() {
        if(context?.let { ContextCompat.checkSelfPermission(it, android.Manifest.permission.CAMERA) } == PackageManager.PERMISSION_DENIED){
            activity?.let { ActivityCompat.requestPermissions(it, arrayOf(android.Manifest.permission.CAMERA), 0) }
        }
        else{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            resultLauncher.launch(intent)
        }
    }
}