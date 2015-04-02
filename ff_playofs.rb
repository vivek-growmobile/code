#!/usr/bin/env ruby

class Division
  attr_accessor :teams

  def standings
    teams.sort do |team1, team2|
      comp = (team2.wins <=> team1.wins)
      comp.zero? ? comp = (team2.division_wins <=> team1.division_wins) : comp
      comp.zero? ? (team2.points <=> team1.points) : comp
    end
  end
end

class Team
  attr_accessor :name, :wins, :division_wins, :points

  def win_pct
    wins / 13
  end

  def label
    "#{name} #{wins}-#{13-wins} (#{division_wins}-#{6-division_wins}) +#{points}"
  end
end

class Match
  attr_accessor :team1, :team2, :team1_odds, :team2_odds, :winner, :loser
end

buckets = {
  "1"  => {},
  "2"  => {},
  "3"  => {},
  "4"  => {},
  "5"  => {},
  "6"  => {},
  "7"  => {},
  "8"  => {},
  "9"  => {},
  "10" => {},
  "11" => {},
  "12" => {}
}

(1..1000).each do |sim|
  vivek = Team.new()
  vivek.name = "Vivek"
  vivek.wins = 7
  vivek.division_wins = 4
  vivek.points = 1234.3 + 131.4


  niyanth = Team.new()
  niyanth.name = "Niyanth"
  niyanth.wins = 7
  niyanth.division_wins = 3
  niyanth.points = 1136.04 + 107.7


  matt = Team.new()
  matt.name = "Matt"
  matt.wins = 4
  matt.division_wins = 2
  matt.points = 964.8 + 70.18


  whitey = Team.new()
  whitey.name = "Whitey"
  whitey.wins = 2
  whitey.division_wins = 1
  whitey.points = 962.96 + 130.64

  nfc_best = Division.new()
  nfc_best.teams = [vivek, niyanth, matt, whitey]

  kyle = Team.new()
  kyle.name = "Kyle"
  kyle.wins = 7
  kyle.division_wins = 2
  kyle.points = 1143.3 + 78.74

  varun = Team.new()
  varun.name = "Varun"
  varun.wins = 6
  varun.division_wins = 3
  varun.points = 1091.34 + 154.98

  nick = Team.new()
  nick.name = "Nick"
  nick.wins = 6
  nick.division_wins = 3
  nick.points = 993.96 + 65.96

  dustin = Team.new()
  dustin.name = "Dustin"
  dustin.wins = 4
  dustin.division_wins = 2
  dustin.points = 1141.7 + 77.20

  sec = Division.new()
  sec.teams = [kyle, varun, nick, dustin]

  ryan = Team.new()
  ryan.name = "Ryan"
  ryan.wins = 9
  ryan.division_wins = 4
  ryan.points = 1234.62 + 101.56

  meher = Team.new()
  meher.name = "Meher"
  meher.wins = 9
  meher.division_wins = 3
  meher.points = 1143.44 + 75.62

  kevin = Team.new()
  kevin.name = "Kevin"
  kevin.wins = 7
  kevin.division_wins = 1
  kevin.points = 1119.38 + 84.96

  andy = Team.new()
  andy.name = "Andy"
  andy.wins = 4
  andy.division_wins = 2
  andy.points = 1182.3 + 89.38

  auto_bye = Division.new()
  auto_bye.teams = [ryan, meher, kevin, andy]

  vivek_niyanth = Match.new()
  vivek_niyanth.team1 = vivek
  vivek_niyanth.team1_odds = 100
  vivek_niyanth.team2 = niyanth
  vivek_niyanth.team2_odds = 0


  matt_whitey = Match.new()
  matt_whitey.team1 = matt
  matt_whitey.team1_odds = 0
  matt_whitey.team2 = whitey
  matt_whitey.team2_odds = 100

  kyle_nick = Match.new()
  kyle_nick.team1 = kyle
  kyle_nick.team1_odds = 100
  kyle_nick.team2 = nick
  kyle_nick.team2_odds = 0

  varun_dustin = Match.new()
  varun_dustin.team1 = varun
  varun_dustin.team1_odds = 100
  varun_dustin.team2 = dustin
  varun_dustin.team2_odds = 0

  meher_ryan = Match.new()
  meher_ryan.team1 = meher
  meher_ryan.team1_odds = 0
  meher_ryan.team2 = ryan
  meher_ryan.team2_odds = 100

  andy_kevin = Match.new()
  andy_kevin.team1 = andy
  andy_kevin.team1_odds = 100
  andy_kevin.team2 = kevin
  andy_kevin.team2_odds = 0

  matchups = [vivek_niyanth, matt_whitey, kyle_nick, varun_dustin, meher_ryan, andy_kevin]

  puts "Playing Week 13..."
  puts "------------"
  random = Random.new()
  matchups.each do |matchup|
    puts "#{matchup.team1.name} (#{matchup.team1_odds}%) Vs. #{matchup.team2.name} (#{matchup.team2_odds}%)"
    r = random.rand(1..100)
    if r <= matchup.team1_odds
      matchup.winner = matchup.team1
      matchup.loser = matchup.team2
    else
      matchup.winner = matchup.team2
      matchup.loser = matchup.team1
    end
    puts "#{matchup.winner.name} beats #{matchup.loser.name}"
    matchup.winner.wins += 1
    matchup.winner.division_wins += 1
  end
  puts "-----------"

  puts "Generating Standings..."
  puts ""
  nfc_best_standings = nfc_best.standings
  sec_standings = sec.standings
  auto_bye_standings = auto_bye.standings
  division_winners = [nfc_best_standings.shift, sec_standings.shift, auto_bye_standings.shift]
  division_winners.sort! do |team1, team2|
    comp = (team2.wins <=> team1.wins)
    comp.zero? ? (team2.points <=> team1.points) : comp
  end

  division_winners.each_with_index do |winner,index=1|
    unless index + 1 == 3
      puts "#{index + 1}: #{winner.label} -- BYE --"
    else
      puts ""
      puts "#{index + 1}: #{winner.label} -- First Round --"
    end

    if buckets["#{index + 1}"]["#{winner.name}"]
      buckets["#{index + 1}"]["#{winner.name}"] = buckets["#{index + 1}"]["#{winner.name}"] + 1
    else
      buckets["#{index + 1}"]["#{winner.name}"] = 1
    end
  end

  wild_card_contenders = [nfc_best_standings, sec_standings, auto_bye_standings].flatten
  wild_card_contenders.sort! do |team1, team2|
    comp = (team2.wins <=> team1.wins)
    comp.zero? ? (team2.points <=> team1.points) : comp
  end

  (4..12).each do |seed|
    wild_card_contender = wild_card_contenders.shift
    if seed < 6
      puts "#{seed}: #{wild_card_contender.label} -- First Round --"
    elsif seed == 6
      puts "#{seed}: #{wild_card_contender.label} -- First Round --"
      puts "-----------------"
    else
      puts "#{seed}: #{wild_card_contender.label} -- OUT --"
    end

    if buckets["#{seed}"]["#{wild_card_contender.name}"]
      buckets["#{seed}"]["#{wild_card_contender.name}"] += 1
    else
      buckets["#{seed}"]["#{wild_card_contender.name}"] = 1
    end
  end
end

puts ""
puts "After 1000 simulations..."
puts "-------------------------"
buckets.each_pair do |seed,bucket|
  str = "#{seed} seed odds: "
  bucket = Hash[bucket.sort_by{|k,v| v}.reverse]
  bucket.each_pair do |name,outcomes|
    odds = (outcomes / 1000.0) * 100
    str += "#{name}: #{odds}% "
  end
  puts str
end
puts ""
