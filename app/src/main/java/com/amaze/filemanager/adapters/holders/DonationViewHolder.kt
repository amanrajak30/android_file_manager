
package com.amaze.filemanager.adapters.holders

import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.amaze.filemanager.R

class DonationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @JvmField
    val ROOT_VIEW: LinearLayout = itemView.findViewById(R.id.adapter_donation_root)

    @JvmField
    val TITLE: AppCompatTextView = itemView.findViewById(R.id.adapter_donation_title)

    @JvmField
    val SUMMARY: AppCompatTextView = itemView.findViewById(R.id.adapter_donation_summary)

    @JvmField
    val PRICE: AppCompatTextView = itemView.findViewById(R.id.adapter_donation_price)
}
