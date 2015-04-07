#!/usr/bin/python

import urllib
import string

class SocialNetwork() :

	def __init__(self,start,st) :
		self.seed = start
		self.word_set = st

	'''Helper minumum function (NOT USED)'''
	def min3(self, num1, num2, num3) :
		curr_min = num1
		if (num2 < curr_min) :
			curr_min =  num2
		if (num3 < curr_min) :
			curr_min = num3
		return curr_min

	'''Dynamic method for calculating levenshtein distance (NOT USED)'''
	def lev(self, word1, word2) :
		l1 = len(word1)
		l2 = len(word2)

		matrix = []

		#Zero out matrix
		for i in range(l1+1) :
			matrix.append([0]*(l2+1))

		#Make rows 1...l1
		for i in range(1,l1 + 1) :
			matrix[i][0] = i

		#Make columns 1...l2
		for j in range(1,l2 + 1) :
			matrix[0][j] = j

		for i in range (1,l1 + 1) :
			for j in range (1,l2 + 1) :
				if(word1[i-1] == word2[j-1]) :
					matrix[i][j] = matrix[i-1][j-1]
				else : 
					matrix[i][j] = self.min3(matrix[i-1][j] + 1,
										matrix[i][j-1] + 1,
										matrix[i-1][j-1] + 1);

		return matrix[l1][l2]


	'''Finds friends of a word (NOT USED)'''
	def find_friends(self, other_word) :
		friends = set()
		for word in self.word_set :
			if (self.lev(word, other_word) == 1) :
				friends.add(word)

		return friends


	'''Generates all friends by using the reverse of levenshtein'''
	def generate_friends(self, word) :
		friends = set()

		length = len(word)

		characters = set(string.ascii_lowercase)

		for i in range(length) :

			#deletion (not dependent on other characters)
			deletion = word[:i] + word[(i + 1):]
			if deletion in self.word_set :
					friends.add(deletion)
			
			for char in characters :
				if word[i] != char :
					#substitution
					substitution = word[:i] + char + word[(i + 1):]
					if substitution in self.word_set :
						friends.add(substitution)

				#addition
				addition = word[:i] + char + word[i:]
				if addition in self.word_set :
					friends.add(addition)
				
		for char in characters :
			#last character addition
			addition = word + char
			if addition in self.word_set :
				friends.add(addition)


		return friends


	'''BFS Graph algorithm for determining size of network'''
	def network_size(self) :
		seen = set()

		#forward lev
		#exploring = self.find_friends(self.seed)

		#reverse lev
		exploring = self.generate_friends(self.seed)

		
		seen.add(self.seed)


		while len(exploring) > 0 :

			to_explore = set()
			for word in exploring :
				seen.add(word)

				#forward lev
				#friends = self.find_friends(word)

				#reverse lev
				friends = self.generate_friends(word)

				#print friends
				for friend in friends :
					if (friend not in seen) :
						to_explore.add(friend)

			exploring = to_explore



		return len(seen)
	
def main() :
	f = urllib.urlopen('https://raw.github.com/causes/puzzles/master/word_friends/word.list')
	word_set = set(f.read().splitlines())
	net = SocialNetwork("causes", word_set)

	print net.network_size()


main()




