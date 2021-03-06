package com.example.soccerleagueapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.soccerleagueapp.R
import com.example.soccerleagueapp.adapter.FixtureListAdapter
import com.example.soccerleagueapp.adapter.LeagueListAdapter
import com.example.soccerleagueapp.league.FixtureGenerator
import com.example.soccerleagueapp.league.Match
import com.example.soccerleagueapp.league.Team
import com.example.soccerleagueapp.league.Week
import com.example.soccerleagueapp.model.TeamsModelItem
import com.example.soccerleagueapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_fixture.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_view_pager.*


class FixtureFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    lateinit var leagueTeams: ArrayList<Team>
    lateinit var teamList: ArrayList<TeamsModelItem>
    var emptyWeekTeam = Team("BYE")
    lateinit var matchesOfWeek: List<Week>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fixture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.getDataFromApi()



        observeData()

        if (leagueTeams.size % 2 == 1) {
            leagueTeams.add(emptyWeekTeam)
        }

    }

    private fun observeData() {
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
            matchesOfWeek = fixtureGenerator.createFixture(leagueTeams.size).map { k ->
                Week(k.first, k.second.map { m ->
                    Match(leagueTeams[m.first], leagueTeams[m.second])
                })
            }

            val adapter = FixtureListAdapter(matchesOfWeek)

            fixture_list.layoutManager = LinearLayoutManager(context)
            fixture_list.adapter = adapter
        })


    }


}