/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

/**
 *
 * @author dagwud
 */
public class GameController 
{
    public GameController()
    {
        gameState = new GameState();
        PlayerUnit p = new PlayerUnit(new Location(mapWidth / 2, mapHeight / 2));
        gameCanvas = new GameCanvas(this);
        gameState.players.add(p);
        
        GameFrame frame = new GameFrame(gameCanvas);
        frame.setSize(mapWidth, mapHeight);
    }
    
    public GameState getGameState()
    {
        return gameState;
    }
    
    public GameCanvas getGameCanvas()
    {
        return gameCanvas;
    }
    
    public void spawnEnemy()
    {
        int side = (int) Math.floor(Math.random() * 4);
        int locationX = 100, locationY = 100;
        switch (side)
        {
            case 0:
                // Spawn on left side:
                locationX = 0;
                locationY = (int) (Math.random() * mapWidth);
                break;
            case 1:
                // Spawn on top:
                locationX = (int) (Math.random() * mapWidth);
                locationY = 0;
                break;
            case 2:
                // Spawn on right:
                locationX = mapWidth;
                locationY = (int) (Math.random() * mapHeight);
                break;
            case 3:
                // Spawn on bottom:
                locationX = (int) (Math.random() * mapWidth);
                locationY = mapHeight;
                break;
        }
        
        if (gameState.players.size() > 0)
        {
            EnemyUnit e = new EnemyUnit(1, 1, new Location(locationX, locationY), gameState.players.iterator().next().location);
            gameState.enemies.add(e);
        }
    }
    
    protected GameState gameState;
    protected GameCanvas gameCanvas;
    
    private final int mapWidth = 640, mapHeight = 480;
}
