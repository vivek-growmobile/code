#!/usr/bin/python
player1 = raw_input ("Player 1? ")
player2 = raw_input ("Player 2? ")
if player1 == "rock" :
    if player2 == "rock" :
        print "tie"
    elif player2 == "scissors" :
        print "Player 1 wins!"
    elif player2 == "paper" :
        print "Player 2 wins!"
elif player1 == "scissors" :
    if player2 == "rock" :
        print "Player 2 wins!"
    elif player2 == "paper" :
        print "Player 1 wins"
    elif player2 == "scissors" :
        print "tie"
elif player1 == "paper" :
    if player2 == "rock" :
        print "Player 1 wins!"
    elif player2 == "paper" :
        print "tie"
    elif player2 == "scissors" :
        print "Player 2 wins!"

