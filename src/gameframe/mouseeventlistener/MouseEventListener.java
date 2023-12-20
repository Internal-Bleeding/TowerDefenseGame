package gameframe.mouseeventlistener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import gameplay.enemy.Enemy;
import gameplay.enemy.EnemyManager;
import gameplay.tower.Tower;
import gameplay.tower.TowerManager;
import shop.ShopPanel;

public class MouseEventListener implements MouseListener
{
    //where initialization occurs:
    //Register for mouse events on blankArea and the panel.
    ShopPanel panel;
    ArrayList<Tower> towers;
    EnemyManager enemies;

    /*
     * Konstruktor a shop panel és a tower-manager megadásával
     */
    public MouseEventListener(ShopPanel panel, ArrayList<Tower> towers, EnemyManager enemies)
    {
        this.panel = panel;
        this.towers = towers;
        this.enemies = enemies;
    }
    /*
    * Tower manager újra beállitása a (new game miatt)
    * Hogy ne kelljen új listenert csinálni új játék esetén
    */
    public void SetTowers(TowerManager manager) {
        this.towers = (ArrayList)manager.GetTowers();
    }

    /*
    * Shop újra beállitása a (new game miatt)
    * Hogy ne kelljen új shop panel-t csinálni új játék esetén
    */
    public void SetShop(ShopPanel panel) {this.panel = panel;}

    /*
     * Egér gomblemnyomás esetén megnézi hogy valamelyik torony lett-e
     * megynomva.
     * Megváltoztatja shop-ban a current tower-t ennek megfelelően
     */
    public void mousePressed(MouseEvent e)
    {     
        System.out.println("X coordinate =" + e.getX());
        System.out.println("Y coordinate = " + e.getY());
        for (Tower tower : towers)
        {
            tower.SetCurrent(false);
        }
        panel.ClearButtons();
        panel.current = null;
        for (Tower tower : towers)
        {
            if (tower.isClicked(e.getX(), e.getY()))
            {
                tower.SetCurrent(true);
                panel.current = tower;
                panel.SetButtons(tower.GetButtons());
                return;
            }
        }
        for (Enemy enemy : enemies.GetEnemies())
        {
            if (enemy.isClicked(e.getX(), e.getY()))
            {
                enemy.SetCurrent(true);
                return;
            }
        }
    }
    /*
     * Csak az implements miatt
     */
    public void mouseReleased(MouseEvent e) {
        //Nothing
    }
    /*
     * Csak az implements miatt
     */
    public void mouseEntered(MouseEvent e) {
        //Nothing
    }
    /*
     * Csak az implements miatt
     */
    public void mouseExited(MouseEvent e) {
        //Nothing
    }
    /*
     * Csak az implements miatt
     */
    public void mouseClicked(MouseEvent e) {
        //Nothing
    }
}
