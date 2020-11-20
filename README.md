# Box And Balls
![Release 1.0](https://img.shields.io/badge/version-v1.0-informational)

Box And Balls or bab, is a software which will help you organize and store your balls.
## First
Enter the number of boxes and the number of balls you wish to process. Remember, if you want the software to accept your values, the number of balls must be between your number of boxes and half your number of boxes. So, if you set 10 boxes, you can have between 5 to 10 balls.

## Second
Select your algorithm. You have four possible choices :
 - Chaining: take a random box and put a ball inside of it. Repeat until there are no more balls.
 - Double choice: take two random boxes (may be the same box), put a ball in the least filled one. If they contain the same number of balls, the first box gets the ball.
 - Open linear addressing: box size is limited to one ball, take a random box, if it is empty put a ball in it, else go to the next one on the list. Repeat until there are no more balls.
 - Open quadratic addressing: box size is limited to one ball, take a random box, if it is empty put a ball in it, else go to the box + 1, if this one is also full go to the box + 4 then box + 9 then box + 16, ... Repeat until there are no more balls.

## Finally
Display your boxes on your screen. You can choose between two display modes, "number" or "shape", number puts numbers in your boxes, shape puts shapes in your boxes. If you chose to use the open linear addressing or open quadratic addressing, you may find some green colored box. This is it was the first empty box encountered to put a ball in it. Also the max value on top of the screen displays the maximum number of boxes visited to place a ball.