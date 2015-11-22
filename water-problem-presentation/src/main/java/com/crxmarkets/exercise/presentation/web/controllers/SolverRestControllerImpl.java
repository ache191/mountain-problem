package com.crxmarkets.exercise.presentation.web.controllers;

import com.crxmarkets.exercise.api.service.solver.WaterProblemSolverService;
import com.crxmarkets.exercise.presentation.web.controllers.utils.WaterVolumeRequestParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

/**
 * Implementation for SolverRestController
 * @see com.crxmarkets.exercise.presentation.web.controllers.SolverRestController
 * @see com.crxmarkets.exercise.api.service.solver.WaterProblemSolverService
 *
 * @author oleksandr.chekanskyi
 */
@Stateless
public class SolverRestControllerImpl implements SolverRestController {

    private static final Logger logger = LoggerFactory.getLogger(SolverRestControllerImpl.class);

    @EJB
    private WaterProblemSolverService waterProblemSolverService;

    @Override
    public Response getWaterVolume(String surface) {
        logger.info("getWaterVolume called with ?surface: {}", surface);
        if (surface == null || surface.length() <= 0) {
            logger.error("?surface parameter is empty!");
            return Response.status(400).build();
        }
        int result = waterProblemSolverService.getWaterVolume(WaterVolumeRequestParser.parseRequestString(surface));
        return Response.status(200).entity(result).build();
    }
}
