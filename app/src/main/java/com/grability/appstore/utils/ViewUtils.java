package com.grability.appstore.utils;

import android.graphics.Color;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by wilson on 10/04/16.
 */
public class ViewUtils {
    private static String[] colors = new String[]{"#009ACD","#0000CD","#9400D3","#DC143C","#F4EB00", "#DE474F", "#575757", "#B247DE", "#6B47DE", "#478BDE", "#47D5DE", "#47D587"};

    public static int getRandomColor(){
        Random randomColor = new Random(Calendar.getInstance().getTimeInMillis() * 999);
        int randomNum = randomColor.nextInt(colors.length);
        return Color.parseColor(colors[randomNum]);
    }

    public static int getRandomArgbColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
