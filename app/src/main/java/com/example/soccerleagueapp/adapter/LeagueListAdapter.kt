package com.example.soccerleagueapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerleagueapp.R
import com.example.soccerleagueapp.model.TeamsModelItem
import kotlinx.android.synthetic.main.teams_list_item_design.view.*

class LeagueListAdapter(val teamList: ArrayList<TeamsModelItem>) : RecyclerView.Adapter<LeagueListAdapter.TeamsViewHolder>() {

    class TeamsViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.teams_list_item_design,parent,false)
        return TeamsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.view.teams_item_name.text = teamList[position].name
    }

    override fun getItemCount(): Int {
        return teamList.size
    }
}