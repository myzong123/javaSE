package pokerapp;

import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author myz
 * @create 2023/2/23-13:40
 */
public class PokerGame {
    //牌盒
    static HashMap<Integer,String> pokerBox = new HashMap<>();
    static List<Integer> list = new ArrayList<>();
    static {
        String[] color = {"♠","♥","♦","♣"};
        String[] number = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
        int serialNumber = 1;
        for(String n : number){
            for(String c : color){
                list.add(serialNumber);
                String poker = c + n;
                pokerBox.put(serialNumber++,poker);
            }
        }
        pokerBox.put(serialNumber++,"小王");
        list.add(serialNumber);
        pokerBox.put(serialNumber,"大王");
        list.add(serialNumber);
        // System.out.println(pokerBox);
    }

    public PokerGame(){
        //洗牌
        Collections.shuffle(list);
        //创建玩家和底牌
        TreeSet<Integer> load = new TreeSet<>();
        TreeSet<Integer> player1 = new TreeSet<>();
        TreeSet<Integer> player2 = new TreeSet<>();
        TreeSet<Integer> player3 = new TreeSet<>();
        //发牌
        for (int i = 0; i < list.size(); i++) {
            if(i > 50){
                load.add(list.get(i));
                continue;
            }
            if(i % 3 == 0){
                player1.add(list.get(i));
            } else if (i % 3 == 1) {
                player2.add(list.get(i));
            }  else {
                player3.add(list.get(i));
            }
        }
        //看牌
        lookPoker("player1",player1);
        lookPoker("player2",player2);
        lookPoker("player3",player3);
        lookPoker("load",load);
    }

    public void lookPoker(String player, TreeSet<Integer> ts){
        StringJoiner sj = new StringJoiner(",","","");
        for(Integer serialNumber : ts){
            sj.add(pokerBox.get(serialNumber));
        }
        System.out.println(player + " : " + sj);
    }
}
