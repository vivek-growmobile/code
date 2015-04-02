class Team
  attr_accessor :name, :seed, :vrr

  def label
    "(#{seed}) #{name}"
  end
end

class Match
  attr_accessor :team1, :team2, :team1_odds, :team2_odds, :winner, :loser

  def initialize(team1, team2)
    @team1 = team1
    @team2 = team2
  end

  def label
    "#{team1.label} (#{team1.vrr}%) vs. #{team2.label} (#{team2.vrr}%)"
  end

  def play_match
    puts label
    random = Random.new()
    total_outcomes = self.team1.vrr + self.team2.vrr
    team1_odds = (team1.vrr / total_outcomes) * 100
    team2_odds = (team2.vrr / total_outcomes) * 100
    r = random.rand(1..100)
    if r <= (team1_odds)
      self.winner = self.team1
      self.loser = self.team2
    else
      self.winner = self.team2
      self.loser = self.team1
    end
    puts "#{winner.label} beats #{loser.label}"
  end
end

class Round
  attr_accessor :teams, :matches

  def initialize(teams)
    @teams = teams
    @matches = []
  end

  def set_matches
    sorted = @teams.sort {|team1, team2| team1.seed <=> team2.seed}
    while sorted.count > 0
      self.matches.push(Match.new(sorted.shift,sorted.pop))
    end
  end
end

class Bracket
  attr_accessor :quarters, :semis, :finals
end

ryan = Team.new()
ryan.name = "Ryan"
ryan.seed = 1
ryan.vrr = 65.4

vivek = Team.new()
vivek.name = "Vivek"
vivek.seed = 2
vivek.vrr = 69.2

kyle = Team.new()
kyle.name = "Kyle"
kyle.seed = 3
kyle.vrr = 53.8

meher = Team.new()
meher.name = "Meher"
meher.seed = 4
meher.vrr = 50.0

varun = Team.new()
varun.name = "Varun"
varun.seed = 5
varun.vrr = 61.5

niyanth = Team.new()
niyanth.name = "Niyanth"
niyanth.seed = 6
niyanth.vrr = 57.7

buckets = {
  "Ryan" => {
      "1" => 0,
      "2" => 0,
      "3" => 0,
      "4" => 0,
      "5" => 0,
      "6" => 0
    },
  "Vivek" => {
      "1" => 0,
      "2" => 0,
      "3" => 0,
      "4" => 0,
      "5" => 0,
      "6" => 0
    },
  "Kyle" => {
      "1" => 0,
      "2" => 0,
      "3" => 0,
      "4" => 0,
      "5" => 0,
      "6" => 0
    },
  "Meher" => {
      "1" => 0,
      "2" => 0,
      "3" => 0,
      "4" => 0,
      "5" => 0,
      "6" => 0
    },
  "Varun" => {
      "1" => 0,
      "2" => 0,
      "3" => 0,
      "4" => 0,
      "5" => 0,
      "6" => 0
    },
  "Niyanth" => {
      "1" => 0,
      "2" => 0,
      "3" => 0,
      "4" => 0,
      "5" => 0,
      "6" => 0
    },
}

num_sims = 5000

(1..num_sims).each do |sim|
  playoff_bracket = Bracket.new()
  playoff_bracket.quarters = Round.new([kyle, meher, varun, niyanth])
  playoff_bracket.semis = Round.new([ryan, vivek])
  playoff_bracket.finals = Round.new([])

  puts ""
  puts "Simming Playoffs..."
  puts ""

  puts "Quarterfinals..."
  playoff_bracket.quarters.set_matches
  eliminated = []
  playoff_bracket.quarters.matches.each do |match|
    match.play_match
    # Move teams to next round
    playoff_bracket.semis.teams.push(match.winner)
    eliminated.push(match.loser)
  end
  fifth_place_game = Match.new(eliminated.first, eliminated.last)
  puts ""

  puts "Semifinals..."
  playoff_bracket.semis.set_matches
  eliminated = []
  random = Random.new()
  playoff_bracket.semis.matches.each do |match|
    match.play_match
    # Move teams to next round
    playoff_bracket.finals.teams.push(match.winner)
    eliminated.push(match.loser)
  end
  third_place_game = Match.new(eliminated.first, eliminated.last)
  puts ""

  puts "Finals..."

  puts "Fifth Place Game"
  fifth_place_game.play_match
  puts ""

  puts "Third Place Game.."
  third_place_game.play_match
  puts ""

  playoff_bracket.finals.set_matches
  title_game = playoff_bracket.finals.matches.pop

  puts "Title Game"
  title_game.play_match
  puts "League Champion: #{title_game.winner.label}"
  puts ""

  #Tablulate simulations
  puts "Final Standings"
  puts "----------------"
  puts "1. #{title_game.winner.label}"
  buckets[title_game.winner.name]["1"] += 1
  puts "2. #{title_game.loser.label}"
  buckets[title_game.loser.name]["2"]  += 1

  puts "3. #{third_place_game.winner.label}"
  buckets[third_place_game.winner.name]["3"] += 1
  puts "4. #{third_place_game.loser.label}"
  buckets[third_place_game.loser.name]["4"]  += 1

  puts "5. #{fifth_place_game.winner.label}"
  buckets[fifth_place_game.winner.name]["5"] += 1
  puts "6. #{fifth_place_game.loser.label}"
  buckets[fifth_place_game.loser.name]["6"]  += 1
  puts ""
end

puts "After #{num_sims} sims...."
buckets.each_pair do |team, finishes|
  label = "#{team} : "
  finishes.each do |finish, occurences|
    occ_pct = (occurences / num_sims.to_f) * 100
    label += "#{finish}: #{occ_pct.round(2)}% "
  end
  puts label
end








