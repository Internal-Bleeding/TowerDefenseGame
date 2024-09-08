package gameplay.tower;

import java.awt.Graphics;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import gameplay.enemy.EnemyManager;

public class TowerManager implements Serializable
{
    private ArrayList<Tower> towers;
    public HashMap<String, Tower> towerTypes;
    Tower current;
    private int length;

    public void LoadTowers(int level)
    {
        towers = new ArrayList<>();
        try {
            File file = new File("src/res/level1.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.parse(file);
            document.getDocumentElement().normalize();

            NodeList map = document.getElementsByTagName("towers");
            for(int j = 0; j < map.getLength(); j++)
            {
                Element n = (Element) map.item(j);
                NodeList points = n.getElementsByTagName("tower");
                for(int i = 0; i < points.getLength(); i++)
                {
                    int x = Integer.parseInt(((Element)points.item(i)).getAttribute("x"));
                    int y = Integer.parseInt(((Element)points.item(i)).getAttribute("y"));
                    add(new EmptyTower(x, y));
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
            System.exit(0);
        }
    }

    public TowerManager()
    {
        
        towerTypes = new HashMap<>();
        length = 0;
        towerTypes.put("cannon", new Cannon());
        towerTypes.put("freeze", new FreezeTower());
        LoadTowers(1);
    }

    /*
     * Kitörli az egész listát
     */
    public void clear() {
        towers.clear();
        length = 0;
        current = null;
    }

    
    /** 
     * Hozzáadja a tornyot a listához
     * @param t a hozzáadandó torony object-je
     */
    public void add(Tower t) 
    {
        towers.add(t);
        length++;
    }

    
    /**
     * Kitőrli a tornyot a listából az object alapján ha létezik
     * @param t a keresett torony object-je
     */
    public void deleteTower(Tower t)
    {
        if (towers.remove(t)) {
            length--;
        }

    }
    
    
    /** 
     * Kitőrli a tornyot a listából az index alapján ha létezik
     * @param n a torony index a listában
     */
    public void deleteTower(int n)
    {
        if (towers.remove(n) != null) {
            length--;
        }
    }
    
    /** 
     * Kicseréli a listában a régi tornyot egy új toronyra amennyiben a régi tornyot
     * tartalmazza a lista
     * @param old régi torony
     * @param next új torony
     */
    public void changeTower(Tower old, Tower next) 
    {
        deleteTower(old);
        add(next);
    }
    
    /** 
     * Visszaadja a lista hosszát
     * @return int a towers lista hossza
     */
    public int size() {
        return length;
    }

    
    /** 
     * vissza adja a towers listáról hogy üres-e
     * @return boolean true ha a lista üres
     */
    public boolean isEmpty() {
        return towers.isEmpty();
    }

    
    /** 
     * Megkeresi index alapján és vissza adja a tornyot a listából
     * @param n keresett index
     * @return Tower a megtalált torony
     * @throws IndexOutOfBoundsException ha érvénytelen az index
     */
    public Tower get(int n) throws IndexOutOfBoundsException
    {
        if (n < length && n >= 0)
            return towers.get(n);
        else
            throw new IndexOutOfBoundsException();
    }

    
    
    /** 
     * Vissza adja  a towers listát
     * @return List<Tower> a lista
     */
    public List<Tower> GetTowers() 
    {
        return towers;
    }

    
    /** 
     * Az tornyokatkat frissíti minden meghíváskor
     * @param enemies az Enemymanager ami az ellenfeleket tartalmazza
     * @param gameSpeed a sebesség amivel a játék fut
     */
    public void update(EnemyManager enemies, float gameSpeed) 
    {
        for (Tower tower : towers)
        {
            tower.update(enemies.GetEnemies(), gameSpeed);
        }

    }

    
    /** 
     * @param n
     * @return Tower
     * @throws IndexOutOfBoundsException ha érvénytelen az index
     */
    public Tower remove(int n) throws IndexOutOfBoundsException
    {
        if (n < length && n >= 0)
        {       
            length--;
            return towers.remove(n);
        }
        else {
            throw new IndexOutOfBoundsException();
        }

    }

    public void draw(Graphics g)
    {
        for (Tower tower : towers)
        {          
            tower.draw(g);
        }
    }
}
