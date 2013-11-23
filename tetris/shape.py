#!/usr/bin/python

from graphics import *

from tetronimoes import *

#A shape class
class Shape :
    
    #Shape constructor
    def __init__ (self, list, color) :
        self.points = list
        self.color = color

    #A draw method
    def draw(self, win) :
        for point in self.points :
            block = Block(point, self.color)
            block.draw(win)

#Subclass for the I Shape
class I_Shape(Shape) :
    
    #I Shape constructor
    def __init__(self, center) :
        coords = [Point(center.x - 2, center.y),
                  Point(center.x - 1, center.y),
                  Point(center.x, center.y),
                  Point(center.x + 1, center.y)]
        Shape.__init__(self, coords, "blue")

#Subclass for the right leaning L shape    
class Rt_L_Shape(Shape) :
    
    #Rt_L Shape constructor
    def __init__(self, center) :
        coords = [Point(center.x - 1, center.y),
                  Point(center.x, center.y),
                  Point(center.x + 1, center.y),
                  Point(center.x + 1, center.y + 1)]
        Shape.__init__(self, coords, "orange")

#Subclass for the left leaning L shape
class Lt_L_Shape(Shape) :

    #Lt_L_Shape constructor
    def __init__(self, center) :
        coords = [Point(center.x - 1, center.y + 1),
                  Point(center.x - 1, center.y),
                  Point(center.x, center.y),
                  Point(center.x + 1, center.y)]
        Shape.__init__(self, coords, 'cyan')

#Subclass for the square
class Square_Shape(Shape) :
    
    #Square Constructor
    def __init__(self, center) :
        coords = [Point(center.x - 1, center.y + 1),
                  Point(center.x - 1, center.y),
                  Point(center.x, center.y),
                  Point(center.x, center.y + 1)]
        Shape.__init__(self, coords, 'red')

#Subclass for the right leaning stack
class Rt_Stack_Shape(Shape) :
    
    #RT_Stack Constructor
    def __init__(self, center) :
        coords = [Point(center.x - 1, center.y + 1),
                  Point(center.x, center.y + 1),
                  Point(center.x, center.y),
                  Point(center.x + 1, center.y)]
        Shape.__init__(self, coords, 'green')

#Subclass for the left leaning stack
class Lt_Stack_Shape(Shape) :
    
    #Lt Stack Constructor
    def __init__(self, center) :
        coords = [Point(center.x - 1, center.y),
                  Point(center.x, center.y),
                  Point(center.x, center.y + 1),
                  Point(center.x + 1, center.y + 1)]
        Shape.__init__(self, coords, 'purple')

#Subclass for the t shape
class T_Shape(Shape) :
    
    #Shape constructor
    def __init__(self, center) :
        coords = [Point(center.x - 1, center.y),
                  Point(center.x, center.y),
                  Point(center.x, center.y + 1),
                  Point(center.x + 1, center.y)]
        Shape.__init__(self, coords, 'yellow')

#Main method for testing I_shape
def main() :
    
    win = GraphWin("Tetronimoes", 200, 150)

    shape = T_Shape(Point(3,1))
    shape.draw(win)

    win.mainloop()

main()
