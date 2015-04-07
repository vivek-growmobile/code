#!/usr/bin/python

from graphics import *


#Digital Clock Class
class DigitalClock() :

    #Digital Clock Constructor
    def __init__(self, hour, min, sec) :
        #Store the inputted time
        self._hour = hour
        self._min = min
        self._sec = sec

        #store the time string currently drawn
        self._timestr = ""

        #store the background currently drawn
        self._backstr = ""
        
        #Store an am/pm variable
        self._ampm = "AM"

        #Adjust from military time
        if self._hour > 12 :
            self._hour = self._hour - 12
            self.ampm = "PM"

        #Establish the upper left corner of the clock
        self._pos = Point(30, 30)
    
    #Helper Method for drawing Background
    def draw_background(self, win) :
        #Make the background a rectangle with the pos as the left corner and pos + 300 as the right corner
        background = Rectangle(Point(self._pos.getX(),self._pos.getY()), Point(self._pos.getX() + 300, self._pos.getY() + 300))
        
        #Artistic changes to the background
        #background.setFill('green')
        #background.setBorder('black')

        #Draw the background
        background.draw(win)

        #set the background to what was just drawn
        self._backstr = background

    #Helper Method for drawing time
    def draw_time(self, win) :
        #Make a string representation for each time
        hour_str = str(self._hour)
        if len(hour_str) == 1 :
            hour_str = "0" + hour_str

        min_str = str(self._min)
        if len(min_str) == 1 :
            min_str = "0" + min_str

        sec_str = str(self._sec)
        if len(sec_str) == 1 :
            sec_str = "0" + sec_str

        #Crete a string representation of the time
        time_str = Text(Point(125,125), hour_str + ':' + min_str 
                        + ':' + sec_str + ' ' + self.ampm)
        #time_str.setColor('black')
        
        #Draw the string
        time_str.draw(win)

        #set the string to the clocks current string
        self._timestr = time_str

    
    #The draw method for the digital clock
    def draw(self, win) :
        self.draw_background(win)
        self.draw_time(win)

    #Helper method to change time
    def update_time(self) :
        #if the seconds are at 59
        if self._sec == 59 :
            #Update the seconds to 0 then check minutes
            self._sec = 0

            #if the minutes are at 59
            if self._min == 59 :
                #Update the minutes to 0 and check the hours
                self._min = 0
                
                #If the hours are at 12
                if self. _hour == 12 :
                    #Update the hour to one and toggle the ampm switch
                    self._hour = 1
                    #ampm toggling
                    if self._ampm == "AM" :
                        self._ampm = "PM"
                    else :
                        self._ampm = "PM"
                
                #If the hour isnt at 12 add one to the hour
                else : 
                    self._hour = self._hour + 1
            
            #If the minutes aren't at 59 add one to the minute
            else :
                self._min = self._min + 1

        #If the seconds aren't at 59 add one to the seconds
        else :
            self._sec = self._sec + 1
            

    #Update the clock
    def update(self, win) :
        if not win.isClosed() :
            self._timestr.undraw()
            self.update_time()
            self.draw_time(win)
            win.after(1000, self.update, win)
        
        
def main() :
    
    #A new window
    win = GraphWin("Digital Clock", 300, 300)

    #Create a digital clock object
    clock = DigitalClock(21, 40, 45)

    #Draw the clock
    clock.draw(win)

    clock.update(win)

    win.mainloop()



main()

