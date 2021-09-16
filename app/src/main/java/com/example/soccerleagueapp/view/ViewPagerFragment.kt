package com.example.soccerleagueapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccerleagueapp.R
import com.example.soccerleagueapp.adapter.FixtureListAdapter
import com.example.soccerleagueapp.adapter.ViewPagerAdapter
import com.example.soccerleagueapp.league.FixtureGenerator
import com.example.soccerleagueapp.league.Match
import com.example.soccerleagueapp.league.Team
import com.example.soccerleagueapp.league.Week
import com.example.soccerleagueapp.model.TeamsModelItem
import com.example.soccerleagueapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_fixture.*
import kotlinx.android.synthetic.main.fragment_view_pager.view.*


class ViewPagerFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    lateinit var leagueTeams: ArrayList<Team>
    lateinit var teamList: ArrayList<TeamsModelItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.getDataFromApi()

        observeData()

    }

    private fun observeData() {
        val fragmentList = ArrayList<Fragment>()

        leagueTeams = ArrayList()
        teamList = ArrayList()
        viewModel.teams.observe(viewLifecycleOwner, Observer { teams ->
            teams?.let {
                teamList.addAll(teams)
                for (team in teamList) {
                    leagueTeams.add(Team(team.name))
                }
            }
            val fixtureGenerator = FixtureGenerator(leagueTeams.size)
            val totWeeks = fixtureGenerator.totalWeeks
            for (i in 1..totWeeks){
                fragmentList.add(FixtureFragment())
            }

            val adapter = ViewPagerAdapter(
                    fragmentList,
                    requireActivity().supportFragmentManager,
                    lifecycle)
            view?.viewPager?.adapter = adapter

        })

    }

}