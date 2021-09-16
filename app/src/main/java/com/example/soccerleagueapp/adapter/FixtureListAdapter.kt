package com.example.soccerleagueapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.soccerleagueapp.R
import com.example.soccerleagueapp.league.Team
import com.example.soccerleagueapp.league.Week
import com.example.soccerleagueapp.model.TeamsModelItem
import kotlinx.android.synthetic.main.fixture_list_item_design.view.*
import kotlinx.android.synthetic.main.fragment_fixture.view.*
import kotlinx.android.synthetic.main.teams_list_item_design.view.*

class FixtureListAdapter(private val matchList: List<Week>) : RecyclerView.Adapter<FixtureListAdapter.TeamsViewHolder>() {

    class TeamsViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.fixture_list_item_design,parent,false)
        return TeamsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        var weekPosition = 4
        holder.view.home_team_name.text = matchList[weekPosition].matches[position].home.teamName
        holder.view.away_team_name.text = matchList[weekPosition].matches[position].away.teamName


        }

    override fun getItemCount(): Int {

        return matchList[0].matches.size

    }


}