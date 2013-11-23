#!/usr/bin/python

#import the graphics library
from graphics import *

#import the wheel class
from wheel import *

#Create the car class
class Car :

    #Car Constuctor
    def __init__(self, wheel1_center, wheel1_radius, 
                 wheel2_center, wheel2_radius, rect_height) :
        self.body = Rectangle (Point(wheel1_center.getX(),wheel1_center.getY() 
                                     - rect_height),
                               Point(wheel2_center.getX(),wheel2_center.getY()))
        self.wheel1 = Wheel (wheel1_center, wheel1_radius, 0.6 * wheel1_radius)
        self.wheel2 = Wheel (wheel2_center, wheel2_radius, 0.6 * wheel2_radius)

    #Draw method
    def draw(self, win) :
        self.body.draw(win)
        self.wheel1.draw(win)
        self.wheel2.draw(win)

    #Undraw method
    def undraw(self) :
        self.body.undraw()
        self.body.undraw()

    #Set color method
    def set_color(self, body_color, wheel_color, tire_color) :
        self.body.setFill(body_color)
        self.wheel1.set_color(wheel_color, tire_color)
        self.wheel2.set_color(wheel_color, tire_color)

    #Move method
    def move(self, dx, dy) :
        self.body.move(dx, dy)
        self.wheel1.move(dx, dy)
        self.wheel2.move(dx, dy)

    #Animate method
    def animate(self, win, dx, dy, steps) :
        if steps > 0 :
            self.move(dx, dy)
            win.after(100, self.animate, win, dx, dy, steps - 1)

#Main method for seeing cars
def main() :
    #Create the window
    new_win = GraphWin('A Car', 700, 300)
    
    #Create a car
    car1 = Car(Point(50, 50), 15, Point(100,50), 15, 40)
    car1.draw(new_win)

    #Color the car
    car1.set_color('black', 'grey', 'pink')

    #Animate the car
    car1.animate(new_win, 1, 0, 400)

    #Run the windows main loop
    new_win.mainloop()

main()
