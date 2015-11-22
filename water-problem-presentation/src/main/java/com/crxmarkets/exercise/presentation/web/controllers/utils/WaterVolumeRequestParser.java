package com.crxmarkets.exercise.presentation.web.controllers.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Util class that parses input from SolverRestController and converts it to Integer[]
 * @see com.crxmarkets.exercise.presentation.web.controllers.SolverRestController
 *
 * @author oleksand.chekanskyi
 */
public class WaterVolumeRequestParser {
    private WaterVolumeRequestParser() {
    }

    public static Integer[] parseRequestString(String surface) {
        String[] strValues = surface.split(",");
        List<Integer> surfaceList = new LinkedList<>();
        for (String strValue : strValues) {
            surfaceList.add(Integer.valueOf(strValue));
        }
        return surfaceList.toArray(new Integer[surfaceList.size()]);
    }
}
