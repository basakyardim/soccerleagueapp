package com.example.soccerleagueapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccerleagueapp.R
import com.example.soccerleagueapp.adapter.LeagueListAdapter
import com.example.soccerleagueapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var viewModel : HomeViewModel
    private val adapter = LeagueListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.getDataFromApi()

        teams_list.layoutManager = LinearLayoutManager(context)
        teams_list.adapter = adapter
        observeData()

        fixture_button.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToFixtureFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeData(){
        viewModel.teams.observe(viewLifecycleOwner, Observer { teams ->
            teams?.let {
                adapter.updateList(teams)
            }

        })

    }


}