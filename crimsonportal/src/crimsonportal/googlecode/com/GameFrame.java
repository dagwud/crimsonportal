/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

import javax.swing.JFrame;

/**
 *
 * @author dagwud
 */
public class GameFrame extends JFrame
{
    public GameFrame(GameCanvas canvas)
    {
        getContentPane().add(canvas);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
