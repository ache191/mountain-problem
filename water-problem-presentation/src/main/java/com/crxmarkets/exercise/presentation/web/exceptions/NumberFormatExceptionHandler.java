package com.crxmarkets.exercise.presentation.web.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception handler for NumberFormatException what could appear while parsing input string in SolverRestController
 * @apiNote https://docs.jboss.org/resteasy/docs/2.2.1.GA/userguide/html/ExceptionHandling.html
 * @see com.crxmarkets.exercise.presentation.web.controllers.SolverRestController
 *
 * @author oleksandr.chekanskyi
 */
@Provider
public class NumberFormatExceptionHandler implements ExceptionMapper<NumberFormatException> {
    
    private static final String FORMAT_ERROR_EXCEPTION_MSG = "Please, make request like /getWaterVolume?surface=1,2,3,4";
    
    @Override
    public Response toResponse(NumberFormatException e) {
        return Response.status(401).entity(FORMAT_ERROR_EXCEPTION_MSG).build();
    }
}
