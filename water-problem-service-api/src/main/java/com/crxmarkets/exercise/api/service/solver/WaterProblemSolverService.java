package com.crxmarkets.exercise.api.service.solver;

/**
 * @author oleksand.chekanskyi
 */
public interface WaterProblemSolverService {

    /**
     * Calculates amount of water after rain in mountains
     *
     * @param surface array of Integer representing surface, each element shows height of hill e.g 3, 2, 4, 1, 2
     * @return volume of water which will surface contain, in example above it will be "2"
     */
    int getWaterVolume(Integer[] surface);
}
