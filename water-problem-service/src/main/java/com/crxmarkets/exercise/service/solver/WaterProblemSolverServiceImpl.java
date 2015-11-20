package com.crxmarkets.exercise.service.solver;

import com.crxmarkets.exercise.api.service.solver.WaterProblemSolverService;

import javax.ejb.Stateless;

/**
 * Created by a.chekanskiy@gmail.com on 19.11.15.
 */
@Stateless
public class WaterProblemSolverServiceImpl implements WaterProblemSolverService {
    //TODO add validation
    @Override
    public int getWaterVolume(int[] surface) {
        return calculateVolume(surface);
    }

    private int calculateVolume(int[] surface) {

        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = surface.length - 1;
        int volume = 0;

        while(left < right) {
            if(surface[left] > leftMax) {
                leftMax = surface[left];
            }
            if(surface[right] > rightMax) {
                rightMax = surface[right];
            }
            if(leftMax >= rightMax) {
                volume += rightMax - surface[right];
                right--;
            } else {
                volume += leftMax - surface[left];
                left++;
            }
        }
        return volume;
    }
}
