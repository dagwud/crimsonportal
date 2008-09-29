/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

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
        
        Thread t = new Thread(gameController);
        t.start();
        
        gameController.getGameState().getGameTime().startTimer();
        
        System.out.println("Loaded");
    }
}
