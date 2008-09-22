/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

import java.util.Iterator;

/**
 *
 * @author dagwud
 */
public class main 
{
    public static void main(String[] args) throws Exception
    {
        GameController gameController = new GameController();
        
        System.out.println("Main");
        int loopIteration = 0;
        
        while (true)
        {
            loopIteration = (loopIteration + 1) % maxLoopIteration;
            
            gameController.getGameCanvas().drawLoop();
            
            if ( (loopIteration % 500000) == 0 )
            {
                //Spawn a new enemy:
                gameController.spawnEnemy();
                System.out.println(gameController.gameState.enemies.size() + " enemies");
            }
            
            if ( (loopIteration % 10000) == 0 )
            {
                Iterator<EnemyUnit> i = gameController.gameState.enemies.iterator();
                while (i.hasNext())
                {
                    EnemyUnit e = i.next();
                    e.move();
                    
                    // Check for enemies which have hit a player:
                    Iterator<PlayerUnit> p = gameController.gameState.players.iterator();
                    while (p.hasNext())
                    {
                        if (p.next().location.equals(e.strategy.target))
                        {
                            gameController.gameState.players.remove(e);
                        }
                    }
                }
            }
        }
    }

    static final int maxLoopIteration = Integer.MAX_VALUE;
}
