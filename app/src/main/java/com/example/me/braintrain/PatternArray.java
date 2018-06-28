package com.example.me.braintrain;

import java.util.ArrayList;
import java.util.Random;


public class PatternArray {
    public ArrayList<Integer> patternArray;
    public Integer nextPattern;
    Random random = new Random();

    public PatternArray() {
        this.patternArray = new ArrayList<Integer>();
        addNextNumber();

    }

    //adds next number in the array
    public void addNextNumber() {
        Integer randomNumber = random.nextInt(4);
        patternArray.add(randomNumber);

    }

    public ArrayList<Integer> getPatternArray() {
        return patternArray;
    }


}
