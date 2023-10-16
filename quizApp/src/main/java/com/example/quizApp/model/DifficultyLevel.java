package com.example.quizApp.model;

import java.util.ArrayList;

public enum DifficultyLevel {
    EASY, MEDIUM, HARD;
    public static DifficultyLevel getRandomLevel(){
        ArrayList<DifficultyLevel> hs=new ArrayList<>();
        int size=0;
        for(DifficultyLevel d:DifficultyLevel.values()){
            ++size;
            hs.add(d);
        }
        int temp=(int)(Math.random()*1000);
        return hs.get(temp%size);
    }
}
