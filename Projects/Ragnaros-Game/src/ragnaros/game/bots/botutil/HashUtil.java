/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.bots.botutil;

import java.util.*;
import ragnaros.game.*;
import ragnaros.game.monsters.*;

/**
 *
 * @author Philipp
 */
public class HashUtil
{
    private static boolean initialized = false;
    private static Random rng = new Random(1);
    private static ArrayList<long[]> attackHashes = new ArrayList<long[]>();
    private static ArrayList<long[]> healthHashes = new ArrayList<long[]>();
    private static ArrayList<Long> handHashes = new ArrayList<Long>();
    private static ArrayList<Long> tapHashes = new ArrayList<Long>();
    private static HashMap<Class, Integer> classMap = new HashMap<Class, Integer>();
    private static long[] manaHash;
    private static long healthHash = generateHash();
    
    public static void init()
    {
        if(initialized) return;
        initialized = true;
        manaHash = generateHashes(Mana.values().length);
        
        int index = getIndex(Dog1.class);
        classMap.put(Dog2.class, index);
        classMap.put(Dog3.class, index);
        classMap.put(Dog4.class, index);
        classMap.put(Dog5.class, index);
    }
    
    private static int getIndex(Class clazz)
    {
        int index;
        if(!classMap.containsKey(clazz))
        {
            index = handHashes.size();
            classMap.put(clazz, index);
            handHashes.add(generateHash());
            tapHashes.add(generateHash());
            attackHashes.add(generateHashes(64));
            healthHashes.add(generateHashes(64));
        }
        else index = classMap.get(clazz);
        return index;
    }
    
    public static long getHealthHash(int health)
    {
        return leftRotateHash(healthHash, health);
    }
    
    public static long getMonstersHash(Cards monsters)
    {
        long hash = 0;
        for (int i = 0; i < monsters.size(); i++)
        {
            MonsterCard monster = (MonsterCard)monsters.get(i);
            Class clazz = monster.getClass();
            int attack = monster.getAttackDamage();
            int health = monster.getCurrentLifepoints();
            boolean tapped = monster.isTapped();
            int num = 0;
            for (int j = 0; j < i; j++)
            {
                if(monsters.get(j).getClass().equals(clazz)) num++;
            }
            hash ^= getMonsterHash(clazz, attack, health, tapped, num);
        }
        return hash;
    }
    private static long getMonsterHash(Class clazz, int attack, int health, boolean tapped, int num)
    {
        int index = getIndex(clazz);
        long hash = leftRotateHash(attackHashes.get(index)[num], attack);
        hash ^= leftRotateHash(healthHashes.get(index)[num], health);
        if(tapped) hash ^= leftRotateHash(tapHashes.get(index), num);
        return hash;
    }
    
    public static long getHandHash(Cards hand)
    {
        long hash = 0;
        for (int i = 0; i < hand.size(); i++)
        {
            Card card = hand.get(i);
            Class clazz = card.getClass();
            int num = 0;
            for (int j = 0; j < i; j++)
            {
                if(hand.get(j).getClass().equals(clazz)) num++;
            }
            hash ^= getHandCardHash(clazz, num);
        }
        return hash;
    }
    private static long getHandCardHash(Class clazz, int num)
    {
        return leftRotateHash(handHashes.get(getIndex(clazz)), num);
    }
    
//    public static long getManaPoolHash(ManaPool pool)
//    {
//        Mana[] manaValues = Mana.values();
//        long hash = 0;
//        for (int i = 0; i < manaValues.length; i++)
//        {
//            hash ^= leftRotateHash(manaHash[i], pool.getTotalAmount().getMana(manaValues[i]));
//        }
//        return hash;
//    }
    
    private static long leftRotateHash(long hash, int shift)
    {
        assert 0 <= shift && shift < 64: shift;
        return (hash >>> (64 - shift)) | (hash << shift);
    }
    private static long generateHash()
    {
        return rng.nextLong();
    }
    private static long[] generateHashes(int num)
    {
        long[] hashes = new long[num];
        for (int i = 0; i < num; i++)
        {
            hashes[i] = generateHash();
        }
        return hashes;
    }
}
