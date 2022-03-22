import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import java.util.Scanner;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    private static final int WIDTH_WALL = 28;
    private static final int HEIGHT_WALL = 28;
    private static final int Y = 0;
    private static final int X =0;
    private static final int iX = 28;
    private static final int iY = 28;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()throws Exception
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()throws Exception
    {

        int pacx=0, pacy=0;
        Wall wall = new Wall();
        int withWall = wall.getImage().getWidth();

        Banana banana = new Banana();
        int withBanana = banana.getImage().getWidth();

        BigBall bigBall = new BigBall();
        int withBigBall = bigBall.getImage().getWidth();

        SmallBall smallBall = new SmallBall();
        int withSmallBall = smallBall.getImage().getWidth();

        Cherry cherry = new Cherry();
        int withCherry = cherry.getImage().getWidth();

        Strawberry strawberry = new Strawberry();
        int withStrawberry = strawberry.getImage().getWidth();

        int x,y=Y;

        String texto;
        File doc = new File("C:\\Users\\sessh\\Downloads\\PacmanLevel1.txt");
        Scanner obj = new Scanner(doc);

        while (obj.hasNextLine()) {
            x=X;
            y+=iY;
            texto = obj.nextLine();
            for (char c : texto.toCharArray()) {
                x+=iX;
                switch (c) {
                    case 'X':
                        wall = new Wall();
                        addObject(wall,x,y);
                        break;
                    case 'b':
                        smallBall = new SmallBall();
                        addObject(smallBall,x,y);
                        break;
                    case 'B':
                        bigBall = new BigBall();
                        addObject(bigBall,x,y);
                        break;
                    case 'C':
                        cherry = new Cherry();
                        addObject(cherry,x,y);
                        break;
                    case 'S':
                        strawberry = new Strawberry();
                        addObject(strawberry,x,y);
                        break;
                    case 'P':
                        pacx=x;
                        pacy=y;
                        break;
                }
            }
        }
        PacmanHud hud = new PacmanHud();
        addObject(hud,0,0);

        Pacman pacman = new Pacman(hud);
        addObject(pacman,pacx,pacy);
    }
}
