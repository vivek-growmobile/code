class HashMap():
	#Retuns an instance of the class with preallocated space for the give number of objects
	def __init__(self,size,rehash_factor=0.9):
		self.__size = size
		self.__num_elems = 0;
		self.__buckets = [None] * size
		self.__rehash_factor = rehash_factor

	# Stores the given key/value pair in the hash map. 
	# Returns a boolean value indicating success / failure of the operation.
	def set(self,key,value):
		try:
			index = self.__hash_bucket(key)
			if self.__buckets[index] is None:
				self.__buckets[index] = [(key,value)]
			else:
				self.__buckets[index].append((key,value))

			self.__num_elems += 1;	
			if (self.load() > self.__rehash_factor):
				print "load was: {0}, rehash is {1}, rehashing!".format(self.load(), self.__rehash_factor)
				self.__rehash()
			return True
		except:
			return False

	# return the value associated with the given key, or null if no value is set.
	def get(self,key):
		index = self.__hash_bucket(key)
		if self.__buckets[index] is None:
			return None
		else:
			for tup in self.__buckets[index]:
				if tup[0] == key:
					return tup[1]
			return None

	# return a float value representing the load factor
	def load(self):
		return self.__num_elems / float(self.__size)

	def __str__(self):
		return str(self.__buckets)

	# hashes a key to a bucket
	def __hash_bucket(self,key):
		return hash(key) % self.__size

	def __rehash(self):
		new_size = self.__size * 2
		new_buckets = [None] * new_size
		for bucket in self.__buckets:
			if bucket is not None:
				for tup in bucket:
					new_idx = hash(tup[0]) % new_size
					if new_buckets[new_idx] == None:
						new_buckets[new_idx] = [tup]
					else:
						new_buckets[new_idx].append(tup)
		self.__buckets = new_buckets
		self.__size = new_size

def test_main():
	## ALL COMMENTS ASSUME A REHASH FACTOR OF 0.9 ##
	map = HashMap(4)
	#Should be: [None, None, None, [('hi', 1)]]
	map.set("hi",1)
	print map
	#Should be: [None, None, None, [('hi', 1), ('there', 2)]]
	map.set("there",2)
	print map
	#Should be: [None, None, [('you', 3)], [('hi', 1), ('there', 2)]]
	map.set("you",3)
	print map
	#Should Rehash after this
	map.set("user",4)
	print map

	print "getting 'hi' :{0}, should be: 1".format(map.get("hi"))

	print "getting 'blah' :{0}, should be: None".format(map.get("False"))

if __name__ == '__main__':
	test_main()








