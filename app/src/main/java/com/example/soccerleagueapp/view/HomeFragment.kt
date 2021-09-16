package com.example.soccerleagueapp.view

import android.content.Intent
import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccerleagueapp.R
import com.example.soccerleagueapp.adapter.LeagueListAdapter
import com.example.soccerleagueapp.viewmodel.HomeViewModel
import com.google.android.material.button.MaterialButtonToggleGroup
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
            val action = HomeFragmentDirections.actionHomeFragmentToViewPagerFragment()
            Navigation.findNavController(it).navigate(action)
        }
        theme_button.addOnButtonCheckedListener { group, selectedBtnId, isChecked ->
            val theme = when (selectedBtnId) {
                R.id.dark_mode_button -> AppCompatDelegate.MODE_NIGHT_YES
                R.id.light_mode_button -> AppCompatDelegate.MODE_NIGHT_NO
                else -> AppCompatDelegate.MODE_NIGHT_NO
            }
            AppCompatDelegate.setDefaultNightMode(theme)
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