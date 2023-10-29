package com.duongdk.hust.it4785.gmailclone

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EmailAdapter(val emails: ArrayList<EmailModel>): BaseAdapter() {
    override fun getCount(): Int {
        return emails.size
    }

    override fun getItem(p0: Int): Any {
        return emails[p0];
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var viewHolder: MyViewHolder
        var row: View

        if (p1 == null) {
            row = LayoutInflater.from(p2?.context).inflate(R.layout.single_mail_layout, p2, false)
            viewHolder = MyViewHolder()
            viewHolder.sender = row.findViewById<TextView>(R.id.sender)
            viewHolder.title = row.findViewById<TextView>(R.id.mail_title)
            viewHolder.content = row.findViewById<TextView>(R.id.mail_content)
            viewHolder.senderAvatar = row.findViewById<TextView>(R.id.sender_avatar)
            viewHolder.time = row.findViewById<TextView>(R.id.time)
            viewHolder.star = row.findViewById<ImageView>(R.id.imageView)
            row.tag = viewHolder
        } else {
            row = p1
            viewHolder = row.tag as MyViewHolder
        }
        val email = emails[p0]

        viewHolder.sender.setText(email.sender)
        viewHolder.title.setText(email.title)
        viewHolder.content.setText(email.content)
        viewHolder.star.setImageResource(R.drawable.star_favorite_0)
        var isFavorite = false
        viewHolder.star.setOnClickListener {
            if (isFavorite) {
                viewHolder.star.setImageResource(R.drawable.star_favorite_0)
            } else viewHolder.star.setImageResource(R.drawable.star_favorite_1)
            isFavorite = !isFavorite
        }

        // set senderAvatar background color random and senderAvatar text = first letter of sender
        val backgroundColor = generateRandomColor()
        viewHolder.senderAvatar.text = email.sender.substring(0, 1).uppercase()
        viewHolder.senderAvatar.background.setColorFilter(backgroundColor, PorterDuff.Mode.SRC_IN)

        // set time = hour:mins
        viewHolder.time.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(email.timestamp))
        return row
    }
    // Hàm để tạo màu ngẫu nhiên
    private fun generateRandomColor(): Int {
        val red = (0..255).random()
        val green = (0..255).random()
        val blue = (0..255).random()
        return Color.rgb(red, green, blue)
    }
    private class MyViewHolder {
        lateinit var sender: TextView
        lateinit var title: TextView
        lateinit var content: TextView
        lateinit var senderAvatar: TextView
        lateinit var time: TextView
        lateinit var star: ImageView
    }
}