import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pacman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pacman extends Actor
{
     private static final int MAX_COUNTER_MOUTH = 10;
    private static final int MAX_COUNTER_MOVEMENT = 4;
    private static final int OFFSET = 10;
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int RIGHT = 2;
    private static final int LEFT = 3;
    
    private GreenfootImage []images;
    
    private int currentImage;
    private int counterMouth;
    private int offsetX=0;
    private int offsetY=0;
    private int counterMovement;
    private int direction;
    private int score;
    
    private PacmanHud pacmanhud;
    
    public Pacman(PacmanHud pacmanhud)
    {
        images = new GreenfootImage[2];
        images[0] = new GreenfootImage("images/close.png");
        images[1] = new GreenfootImage("images/open.png");
        this.pacmanhud=pacmanhud;
    }
    
    public void act()
    {
        openCloseMouth();
        movePacman();
        checkColissions();
    }
    
    private void checkColissions(){
        Item item = (Item)getOneIntersectingObject(Item.class);
        
        if(item!=null){
            getWorld().removeObject(item);
            score+=item.getScore();
            pacmanhud.setScore(score);
            
            if(getWorld().getObjects(Item.class).isEmpty()){
                offsetX=0;
                offsetY=0;
                getWorld().showText("You WIN this Game!!!",220,60);
            }
        }
    }
    
    private void openCloseMouth()
    {
        counterMouth++;
        if(counterMouth == MAX_COUNTER_MOUTH)
        {
            counterMouth = 0; 
            setImage(images[currentImage]);
            currentImage = (currentImage +1) % images.length;
        }
        
    }
    
    private void movePacman()
    {
        counterMovement++;
        
        if(counterMovement < MAX_COUNTER_MOVEMENT){
            return;
        }
        
        int currentX = getX();
        int currentY = getY();
        
        counterMovement=0;
        handleDirection();
    
        Actor wall = getWallOnTheWay();
        
        if(wall==null)
        {
            setLocation(currentX + offsetX, currentY+offsetY);
        }
    }
    
     private void handleDirection()
    {
        if(Greenfoot.isKeyDown("UP"))
        {
            offsetX=0;
            offsetY=-OFFSET;
            direction=UP;
        }else if(Greenfoot.isKeyDown("DOWN")){
            offsetX=0;
            offsetY=OFFSET;
            direction=DOWN;
        }else if(Greenfoot.isKeyDown("RIGHT"))
        {
            offsetY=0;
            offsetX=OFFSET;
            direction=RIGHT;
        }else if(Greenfoot.isKeyDown("LEFT")){
            offsetY=0;
            offsetX=-OFFSET;
            direction=LEFT;
        }
    }
    
    private Wall getWallOnTheWay()
    {
        Actor wall=null;
        
        switch(direction){
            case UP:
                return (Wall) getOneObjectAtOffset(0,-30,Wall.class);
            case DOWN:
                return (Wall) getOneObjectAtOffset(0,30,Wall.class);
            case RIGHT:
                return (Wall) getOneObjectAtOffset(30,0,Wall.class);
            case LEFT:
                return (Wall) getOneObjectAtOffset(-30,0,Wall.class);
        }
        return null;
    }
}