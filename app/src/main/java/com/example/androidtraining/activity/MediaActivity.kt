package com.example.androidtraining.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.androidtraining.R
import com.example.androidtraining.RecyclerActivity
import com.example.androidtraining.bottom_navigation.BottomNavigationActivity
import com.example.androidtraining.databinding.ActivityNextBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

private lateinit var binding: ActivityNextBinding

var image_uri: Uri? = null

class NextActivity : AppCompatActivity() {
    @SuppressLint("InflateParams")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar!!
        actionBar.title = "Media"

        binding.btnMedia.setOnClickListener {

            val views: View = layoutInflater.inflate(R.layout.bottom_dialog, null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(views)
            dialog.show()

            val imgCamera = views.findViewById<ImageView>(R.id.imgCamera)
            val imgGallery = views.findViewById<ImageView>(R.id.imgGallery)

            imgCamera.setOnClickListener {

                if (checkCameraPermission()) {
                    captureImage()
                    dialog.dismiss()
                } else {
                    requestCameraPermission()
                    dialog.dismiss()
                }

            }

            imgGallery.setOnClickListener {

                if (checkGalleryPermission()) {
                    pickImage()
                    dialog.dismiss()
                } else {
                    requestStoragePermission()
                    dialog.dismiss()
                }
            }
        }
    }

    private fun checkCameraPermission(): Boolean {
        val resultCamera = ContextCompat.checkSelfPermission(
            this@NextActivity,
            Manifest.permission.CAMERA
        )
        return resultCamera == PackageManager.PERMISSION_GRANTED
    }

    private fun checkGalleryPermission(): Boolean {

        val resultGallery = ContextCompat.checkSelfPermission(
            this@NextActivity,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        return resultGallery == PackageManager.PERMISSION_GRANTED

    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA))
        {
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.CAMERA),100)
        } else
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.CAMERA),100)
    }

    private fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE ))
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),101 )
        } else
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),101 )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Camera Permission Granted", Toast.LENGTH_LONG).show()
                captureImage()
            } else {
                val requestCameraAgain =
                    shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
                if (requestCameraAgain) Toast.makeText(applicationContext,"Camera Permission Denied",Toast.LENGTH_LONG).show()
                else {
                    Toast.makeText(applicationContext,
                        "Camera Permission Denied, Go to settings and enable Permission",Toast.LENGTH_LONG).show()

                    val builder = AlertDialog.Builder(this@NextActivity)
                    builder.setTitle("Permission Required")
                    builder.setMessage("Permission is required to access Camera. " +
                            "\nClick Permit to go to settings and enable Permission")
                    builder.setCancelable(false)
                    builder.setPositiveButton("Permit")
                    { _, _ ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                    builder.setNegativeButton("Cancel")
                    { dialog, _ ->

                        dialog.dismiss()
                    }
                    val alert = builder.create()
                    alert.show()
                }
            }
        } else if (requestCode == 101) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Storage Permission Granted", Toast.LENGTH_LONG)
                    .show()
                pickImage()
            } else {
                val requestStorageAgain =
                    shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
                if (requestStorageAgain) Toast.makeText(applicationContext,"Storage Permission Denied",Toast.LENGTH_LONG).show()
                else {
                    Toast.makeText(this@NextActivity,
                        "Storage Permission Denied, Go to settings and enable Permission",Toast.LENGTH_LONG).show()

                    val builder = AlertDialog.Builder(this@NextActivity)
                    builder.setTitle("Permission Required")
                    builder.setMessage("Permission is required to access Storage. " +
                            "\nClick Permit to go to settings and enable Permission")
                    builder.setCancelable(false)
                    builder.setPositiveButton("Permit")
                    { _, _ ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                    builder.setNegativeButton("Cancel")
                    { dialog, _ ->

                        dialog.dismiss()
                    }
                    val alert = builder.create()
                    alert.show()
                }
            }
        }
    }

    private fun captureImage() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)

        getResult.launch(cameraIntent)

    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                binding.imageView.setImageURI(image_uri)
            }
        }

    private fun pickImage() {

        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        gallery.type = "image/*"

        getImage.launch("image/*")
    }

    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()
    ) {
        binding.imageView.setImageURI(it)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_widget -> {
                val iMA = Intent(this, WidgetActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_RecyclerView -> {
                val iMA = Intent(this, RecyclerActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Dialogs -> {
                val iMA = Intent(this, DialogsActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_CustomListView -> {
                val iMA = Intent(this, NextActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Maps -> {
                val iMA = Intent(this, MapsActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Image -> {
                val iMA = Intent(this, GridActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Posts -> {
                val iMA = Intent(this, PostsActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Tab -> {
                val iMA = Intent(this, TabLayoutViewPager::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_BottomNavigation -> {
                val iMA = Intent(this, BottomNavigationActivity::class.java)
                startActivity(iMA)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}