#!/usr/bin/python

print "Welcome to Vivek's Hangman!"
print "Player 1 you're up first"

#Prompt for the secret word
secret_word = raw_input("Enter the word to be guessed: ")

print "Ok, player 2 time to guess"

#Determine the max number of attempts
attempts = len (secret_word) * 2
print "The maximum number of tries is: " + str(attempts)

#create a list from letters in the secret word
def generate_list(word) :
    #First generate a list of characters
    char_list = list(word)
    return char_list

#create a boolean list matching the length of another list
def boolean_list(list) :
    bool_list = []
    for element in list :
        bool_list.append(False)
    return bool_list


#get a character list for the guessed word
char_list = generate_list(secret_word)

#get a corresponding boolean list from the chracter list
bool_list = boolean_list(char_list)

#set up a boolean to see if all the letters have been guessed
guessed_word = False

#set up an array for already guessed letters
already_guessed = []

#set up a list representing the game state
game_state = []
for char in secret_word :
    game_state.append("_")

#a function to turn a list of characters into a word
def list_to_string(list) :
    word = ""
    for char in list :
        word += (char + " ")
    return word


#run the game loop

#runs while all the letters haven't been guessed
#and there are still attempts
while not guessed_word and attempts > 0 :
    
    #display the game state
    print "Game State: " + list_to_string(game_state)

    #prompt the user for a letter to guess
    curr_guess = raw_input("Guess a letter, you have " + str(attempts) + " guesses remaining: ")
    
    #check that the guess is valid
    if len(curr_guess) != 1 :
        print "Illegal Guess! Try Again"
        continue

    #a boolean for if the letter has been added to already guessed
    added = False;
    
    #set aside a boolean if the letter has been guessed
    letter_guessed = False

    #if it is the first guess then already guessed is empty so add the guess automatically
    if len(already_guessed) == 0 :
        already_guessed.append(curr_guess)
        added = True
        
    #if it isn't the first guess check through already guessed to see if it has been guessed before
    if not added :
        #check if the letter has been guessed
        for guess in already_guessed :

            #if it has tell the user
            if guess == curr_guess :
                print "This letter has already been guessed"
                letter_guessed = True
                break

    #if the letter has been guessed reprompt the user to guess a letter
    if letter_guessed :
        continue
    #otherwise add the letter to the list of already guessed
    #then decrement the max_attempts by one
    else :
        already_guessed.append(curr_guess)
        attempts = attempts - 1
    
    #set an index for iteration
    i = 0

    #iterate through the dictionary and check if the letter is there
    for letter in char_list :
        #if the guess is correct
        if letter == curr_guess :
            #update that the letter was correctly guessed
            bool_list[i] = True
        i = i + 1
            
    #set up a boolean to see if ther is an unguessed letter
    unguessed_letter = False

    #set another index for iteration
    j = 0

    #iterate through the boolean list and see if there are any unguessed letters
    for bool in bool_list :
        if bool == False :
            unguessed_letter = True
            j = j + 1
        else :
            game_state[j] = char_list[j]
            j = j + 1

    #if there was no unguessed letter then break the game loop
    if not unguessed_letter :
        print "You Win, guessed: " + secret_word
        guessed_word = True
    elif attempts == 0 :
        print "You Lost, word was: " + secret_word

        
        


