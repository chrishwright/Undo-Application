package com.wright.undoapp;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author christopherwright
 *
 */
public class ColorGenerator {
    
    private static final List<Color> colors;
    
    static {
        List<Color> colorsTemp = new ArrayList<>();
        colorsTemp.add(Color.RED);
        colorsTemp.add(Color.BLACK);
        colorsTemp.add(Color.BLUE);
        colorsTemp.add(Color.CYAN);
        colorsTemp.add(Color.DARK_GRAY);
        colorsTemp.add(Color.GRAY);
        colorsTemp.add(Color.GREEN);
        colorsTemp.add(Color.LIGHT_GRAY);
        colorsTemp.add(Color.MAGENTA);
        colorsTemp.add(Color.ORANGE);
        colorsTemp.add(Color.ORANGE);
        colorsTemp.add(Color.PINK);
        colorsTemp.add(Color.WHITE);
        colorsTemp.add(Color.YELLOW);
        colors = Collections.unmodifiableList(colorsTemp);
    }
    
    public Color generateRandomColor() {
        Random rand = new Random();
        int random = rand.nextInt(colors.size());
        return colors.get(random);
    }

}
