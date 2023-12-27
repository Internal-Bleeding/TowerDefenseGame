package gameplay.enemy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;

import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;  
import org.w3c.dom.Node;  
import org.w3c.dom.Element;  

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import gameplay.player.Player;

public class EnemyManager implements Serializable
{
    private ArrayList<Enemy> enemies;
    private HashMap<String, Enemy> enemyTypes;
    //Image here
    private int length;
    private int wave;

    public EnemyManager()
    {
        enemies = new ArrayList<>();
        enemyTypes = new HashMap<String, Enemy>();
        enemyTypes.put("wolf", new Wolf());
        enemyTypes.put("birds", new Bird());
        length = 0;
        wave = 0;
    }

    /*
     * Jelenlegi wave-ek száma
     */
    public int GetWave() {return wave;}

     /**
     * Kitörli az enemy listáját
     */
    public void clear() {
        enemies.clear();
        length = 0;
        wave = 0;
    }

    
    /** 
     * Hozzáadja az enemy a listához
     * @param t a hozzáadandó enemy object-je
     */
    public void add(Enemy e) {
        enemies.add(e);
        length++;
    }

    /*
     * Visszadja a méretet
     */
    public int size() {
        return length;
    }

    /** 
     * vissza adja az enemies listáról hogy üres-e
     * @return boolean true ha a lista üres
     */
    public boolean isEmpty() {
        return enemies.isEmpty();
    }

    public Enemy get(int n) throws IndexOutOfBoundsException
    {
        if (n < length && n >= 0)
            return enemies.get(n);
        else
            throw new IndexOutOfBoundsException();
    }

    /** 
     * Vissza adja  az enemies listát
     * @return List<Enemy> a lista
     */
    public List<Enemy> GetEnemies() {
        return enemies;
    }

    /*
     * Új wave-et indit
     * Csak egyetlen enemy van úgyhogy az egyetlen enemy tipusból
     * ad egyre többet a listába
     */
    public void NewWave()
    {           
        try {
            File file = new File("src/res/waves.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("wave");
            Node node = nodeList.item(wave);
            Element element = (Element) node;

            Set<String> enemySet = enemyTypes.keySet();
            for (String s : enemySet)
            {
                System.out.println(element);
                int enemyCount = Integer.parseInt(element.getElementsByTagName(s).item(0).getTextContent());
                for (int n = 0; n < enemyCount; n++)
                {
                    Class<? extends Enemy> cls = enemyTypes.get(s).getClass();
                    System.out.println(cls + " " + enemyCount);
                    Enemy e = cls.getConstructor().newInstance();
                    e.SetLoc(-n * 50, 100);
                    enemies.add(e);
                    length++;
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        } 
        wave++;
    }

    /*
     * Kitöröl egy enemy-t a listából
     * Amennyiban az n integer valid
     * @param n keresett enemy indexe
     */
    public Enemy remove(int n) throws IndexOutOfBoundsException
    {
        if (n < length && n >= 0)
        {       
            length--;
            return enemies.remove(n);
        }
        else {
            throw new IndexOutOfBoundsException();
        }

    }

    /*
     * Kirajzoló függvénye az összes enemynek
     * (és a wave counter kiirása)
     * @param g A Graphics object amire a kirajzolás megtörténik
     * @return void
     */
    public void draw(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Consolas", Font.BOLD, 20)); //Possible error
        g.drawString("Wave: " + wave, 900, 30);
        for (Enemy enemy : enemies)
        {
            enemy.draw(g);
        }
    }

    /** 
     * Az enemy-ket frissíti minden meghíváskor
     * @param player a játékos objektuma a hp és a money változtatásához
     * @param gameSpeed a sebesség amivel a játék fut
     */
    public void update(float gameSpeed, Player player)
    {
        for (int n = 0; n < enemies.size(); n++)
        {
            if (enemies.get(n).getHealth() <= 0)
            {
                player.AddMoney(enemies.get(n).getMoney());
                enemies.remove(n);
                length--;         
            }
            else if (enemies.get(n).update(gameSpeed))
            {
                player.LoseHealth(1);
                enemies.remove(n);
                length--;
            }

        }
        if (length == 0) {
            NewWave();
        }
    }


}
