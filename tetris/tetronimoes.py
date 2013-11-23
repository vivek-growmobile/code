#!/usr/bin/python

from graphics import *

#A class for blocks
class Block(Rectangle) :
    #The block constructor
    #Takes a point (top left) in sqare coordinates and color
    #Converts to pixel coordinates
    def __init__(self, point, color) :

        #Store the top left point of the block in pixel coordinates
        self.topleft = Point (point.x * 30, point.y * 30)

        #Store the bottom right point of the block in pixel coordinates
        self.bottomright = Point ((point.x + 1) * 30, (point.y + 1) * 30)

        #Invoke the rectangle  constructor with the points
        Rectangle.__init__(self, self.topleft, self.bottomright)

        #Set the color to the input color
        Rectangle.setFill(self, color)

#Main method for testing
def main() :
    #Create a new window
    win = GraphWin('Tetronimoes', 150, 150)

    #Create a block
    block = Block(Point(1,1), 'red')
    
    #Draw the block on the screen
    block.draw(win)

    #Run the window loop
    win.mainloop()

#main()
