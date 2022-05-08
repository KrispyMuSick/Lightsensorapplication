package com.krispyproject.lightsensor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krispyproject.lightsensor.model.User



class PostAdapter(val postModel: MutableList<User>): RecyclerView.Adapter<PostViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_post, parent,false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.itemView(postModel[position])
    }

    override fun getItemCount(): Int {
        return postModel.size
    }
}

class PostViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    private val tvTitle2: TextView = itemView.findViewById(R.id.tvTitle2)
    fun itemView(postModel: User){



        //old original
        tvTitle.text = postModel.id
        tvTitle2.text = postModel.intensity


    }


}