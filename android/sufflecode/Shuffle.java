package com.shufflegame.shuffleword;

import java.util.ArrayList;
import java.util.List;

public class Shuffle {

    public String getShuffleWord(String str)
    {
        char []character=str.toCharArray();

        List<Character> list=new ArrayList<Character>();

        for(char ch:character)
        {
            list.add(ch);
        }

        String result="";

        while(list.size()!=0)
        {
            result+=(list.remove((int)(Math.random()*list.size())));
        }

        return result;
    }
}
