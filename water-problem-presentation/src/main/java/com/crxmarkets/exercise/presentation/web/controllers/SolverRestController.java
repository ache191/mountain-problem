package com.crxmarkets.exercise.presentation.web.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * REST API Controller to make WaterProblemSolverService be usable for user
 *
 * @author oleksandr.chekanskyi
 * @see com.crxmarkets.exercise.api.service.solver.WaterProblemSolverService
 * Implementation could be find here:
 * @see com.crxmarkets.exercise.presentation.web.controllers.SolverRestControllerImpl
 */
@Path("/solver")
@Consumes("application/json")
@Produces("application/json")
public interface SolverRestController {
    @GET
    @Path("/getWaterVolume")
    Response getWaterVolume(@QueryParam("surface") String surface);
}
