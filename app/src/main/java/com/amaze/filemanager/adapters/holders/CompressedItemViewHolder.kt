

package com.amaze.filemanager.adapters.holders

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.amaze.filemanager.R
import com.amaze.filemanager.ui.views.ThemedTextView

class CompressedItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // each data item is just a string in this case
    @JvmField
    val pictureIcon: AppCompatImageView = view.findViewById(R.id.picture_icon)

    @JvmField
    val genericIcon: AppCompatImageView = view.findViewById(R.id.generic_icon)

    @JvmField
    val apkIcon: AppCompatImageView = view.findViewById(R.id.apk_icon)

    @JvmField
    val txtTitle: ThemedTextView = view.findViewById(R.id.firstline)

    @JvmField
    val txtDesc: AppCompatTextView = view.findViewById(R.id.secondLine)

    @JvmField
    val date: AppCompatTextView = view.findViewById(R.id.date)

    val perm: AppCompatTextView = view.findViewById(R.id.permis)

    @JvmField
    val rl: View = view.findViewById(R.id.second)

    @JvmField
    val checkImageView: AppCompatImageView = view.findViewById(R.id.check_icon)
}
