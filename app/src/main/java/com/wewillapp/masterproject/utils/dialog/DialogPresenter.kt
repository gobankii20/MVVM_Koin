package com.wewillapp.masterproject.utils.dialog

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.super_rabbit.wheel_picker.OnValueChangeListener
import com.super_rabbit.wheel_picker.WheelPicker
import com.wewillapp.masterproject.R
import com.wewillapp.masterproject.databinding.*
import com.wewillapp.masterproject.utils.extension.getDeviceMetrics
import com.wewillapp.masterproject.utils.imageManagement.setImageView
import com.wewillapp.masterproject.view.adapter.WPWeekDaysPickerAdapter
import java.util.*

class DialogPresenter constructor(
    override var fragmentActivity: FragmentActivity
):DialogFactory(fragmentActivity) {


    fun dialogMessageTwoButton(title: String?, clickCallback: ((Boolean) -> Unit)) {
        val dialog = getDialog()
        dialog.setCanceledOnTouchOutside(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        val binding: DialogTwoButtonBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(fragmentActivity),
                R.layout.dialog_two_button, null, false
            )
        dialog.setContentView(binding.root)
        dialog.window?.attributes!!.width =
            (fragmentActivity.getDeviceMetrics().widthPixels * 0.8).toInt()

        binding.tvText.text = title

        binding.tvOkey.setOnClickListener {
            dialog.dismiss()
            clickCallback.invoke(true)
        }

        binding.tvCancel.setOnClickListener {
            dialog.dismiss()
            clickCallback.invoke(false)
        }

        dialog.show()
    }

    fun dialogMessageNotitle(message: String?, clickCallback: ((Boolean) -> Unit)) {
        val dialog = getDialog()
        dialog.setCanceledOnTouchOutside(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding: DialogMessageNoTitleBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(fragmentActivity),
                R.layout.dialog_message_no_title, null, false
            )
        dialog.setContentView(binding.root)

        dialog.window?.attributes!!.width =
            (fragmentActivity.getDeviceMetrics().widthPixels * 0.8).toInt()
        binding.message = message

        binding.tvOkey.setOnClickListener {
            dialog.dismiss()
            clickCallback.invoke(true)
        }

        dialog.show()
    }

    fun dialogShowImageFullScreen(context: Context, mImageUrl: String) {
        val dialog = getDialog()
        dialog.setCanceledOnTouchOutside(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val binding: CustomImageFullscreenBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(fragmentActivity),
                R.layout.custom_image_fullscreen, null, false
            )
        dialog.setContentView(binding.root)

        binding.ivClose.setOnClickListener {
            dialog.cancel()
        }

        binding.ivShow.setImageView(context, mImageUrl)

        dialog.setCancelable(true)
        dialog.show()
    }

    fun dialogSelectImage(clickCallback: ((Int) -> Unit)) {
        val dialog = getDialog()
        dialog.setCanceledOnTouchOutside(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setGravity(Gravity.BOTTOM)
        val binding: DialogGalleryOrShootingBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(fragmentActivity),
                R.layout.dialog_gallery_or_shooting, null, false
            )
        dialog.setContentView(binding.root)

        binding.btnCamera.setOnClickListener {
            clickCallback.invoke(1)
            dialog.dismiss()
        }

        binding.btnGallery.setOnClickListener {
            clickCallback.invoke(2)
            dialog.dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    fun dialogBottom(
        arrayList: ArrayList<String>,
        mSelectPosition: Int,
        mTitle: String,
        mClickCallBack: (String) -> Unit
    ) {

        val dialog = getDialog()
        dialog.setCanceledOnTouchOutside(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val binding: FragmentDialogBootomBinding = DataBindingUtil.inflate(
            LayoutInflater.from(fragmentActivity),
            R.layout.fragment_dialog_bootom,
            null,
            false
        )
        dialog.setContentView(binding.root)
        dialog.window?.attributes!!.width =
            (fragmentActivity.getDeviceMetrics().widthPixels * 0.8).toInt()

        binding.tvTitleDialog.text = mTitle

        binding.picker.setSelectorRoundedWrapPreferred(false)
        binding.picker.setWheelItemCount(3)
        binding.picker.setMin(0)
        binding.picker.setMax(arrayList.size)
        binding.picker.setAdapter(WPWeekDaysPickerAdapter(arrayList))
        binding.picker.scrollTo(mSelectPosition)

        binding.picker.setOnValueChangeListener(object : OnValueChangeListener {
            override fun onValueChange(picker: WheelPicker, oldVal: String, newVal: String) {
                mClickCallBack.invoke(picker.getCurrentItem())
            }
        })

        binding.txtSelect.setOnClickListener {
            dialog.cancel()
        }

        dialog.setCancelable(true)
        dialog.show()
    }
}
