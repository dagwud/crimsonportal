package crimsonportal.googlecode.com;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import crimsonportal.googlecode.com.ObjectModel.GameController;

/**
 *
 * @author dagwud
 */
public class main 
{
    public static void main(String[] args)
    {
        System.out.println("Loading");

        GameController gameController = new GameController();
        
        Thread gameControlThread = new Thread(gameController);
        gameControlThread.start();
        
        System.out.println("Loaded");
    }
}
