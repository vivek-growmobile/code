#!/usr/bin/python

import random

class draft_lottery :


	def __init__(self, nms) :
		self.names =  nms
		self.randomized = []
		#self.odds_board = []

	#def odds_board(self):



	def randomize(self) :
		keys = self.names.keys()

		while (len(keys) > 0) :
			choice = random.choice(keys)
			self.randomized.append(choice)
			keys.remove(choice)

	def print_order(self) :
		for i, name in enumerate(self.randomized) :
			print "Pick " + str(len(self.randomized) - i) + ": " + name

def main() : 
	inp = raw_input("Enter Lottery Candidates Followed By Weights (eg. player1:1,player2:2): ")
	pairs = inp.split(',')

	names = {};

	for pair in pairs :
		pair_list = pair.split(':')
		names[pair_list[0]] = float(pair_list[1])


	lotto  =  draft_lottery(names)

	lotto.randomize()

	lotto.print_order()

main()



