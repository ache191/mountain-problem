package com.crxmarkets.exercise.presentation.test;

import com.crxmarkets.exercise.api.service.solver.WaterProblemSolverService;
import com.crxmarkets.exercise.service.solver.WaterProblemSolverServiceImpl;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.assertTrue;

/**
 * Test suit for WaterProblemSolverService
 * @see com.crxmarkets.exercise.api.service.solver.WaterProblemSolverService
 *
 * Test is focused on algorithm correctness
 *
 * @author oleksandr.chekanskyi
 */
@RunWith(Arquillian.class)
public class SolverServiceTest {

    @EJB
    private WaterProblemSolverService waterProblemSolverService;

    @Deployment
    public static WebArchive createTestableDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "presentation.war")
                .addClasses(WaterProblemSolverService.class, WaterProblemSolverServiceImpl.class).addAsWebInfResource("beans.xml");
        return war;
    }

    @Test
    public void testServiceCorrectness() {
        Integer[] test1 = {3, 2, 4, 1, 2};
        int result = waterProblemSolverService.getWaterVolume(test1);
        assertTrue("test1 test case should give result of 2, current result: " + result, 2 == result);

        Integer[] test2 = {4, 1, 1, 0, 2, 3};
        int result2 = waterProblemSolverService.getWaterVolume(test2);
        assertTrue("test2 test case should give result of 8, current result: " + result2, 8 == result2);

        Integer[] test3 = {3, 1, 5, 4, 7, 2, 4};
        int result3 = waterProblemSolverService.getWaterVolume(test3);
        assertTrue("test3 test case should give result of 5, current result: " + result3, 5 == result3);

        Integer[] test4 = {1, 2, 3, 4, 1, 2, 0, 3, 1, 3, 0, 6, 2, 1};
        int result4 = waterProblemSolverService.getWaterVolume(test4);
        assertTrue("test4 test case should give result of 18, current result: " + result4, 18 == result4);

        Integer[] test5 = {0, 1, 0, 1, 0, 1, 0, 1, 0};
        int result5 = waterProblemSolverService.getWaterVolume(test5);
        assertTrue("test5 test case should give result of 3, current result: " + result5, 3 == result5);
    }


}
