package com.example.soccerleagueapp.league

class FixtureGenerator(teams:Int) {
    var totalWeeks = (teams - 1) * 2

    fun createFixture(teams:Int) : ArrayList<Pair<Int,ArrayList<Pair<Int,Int>>>> {
        var matchesPerWeek = teams / 2
        var weeks = ArrayList<Pair<Int,ArrayList<Pair<Int,Int>>>>()
        for (week in 0 until totalWeeks){
            var matchesOfWeek = ArrayList<Pair<Int,Int>>()
            for (match in 0 until matchesPerWeek){
                var home = (week + match) % (teams - 1)
                var away = (teams - 1 - match + week) % (teams - 1)
                if (match == 0) {
                    away = teams - 1
                }
                matchesOfWeek.add(Pair(home,away))
            }
            weeks.add(Pair(week,matchesOfWeek))
        }
        return weeks
    }
}