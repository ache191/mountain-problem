package com.crxmarkets.exercise.presentation.test;

import com.crxmarkets.exercise.api.service.solver.WaterProblemSolverService;
import com.crxmarkets.exercise.presentation.web.ExerciseApplication;
import com.crxmarkets.exercise.presentation.web.controllers.SolverRestController;
import com.crxmarkets.exercise.presentation.web.controllers.SolverRestControllerImpl;
import com.crxmarkets.exercise.presentation.web.controllers.utils.WaterVolumeRequestParser;
import com.crxmarkets.exercise.presentation.web.exceptions.NumberFormatExceptionHandler;
import com.crxmarkets.exercise.service.solver.WaterProblemSolverServiceImpl;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.warp.api.RestContext;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.warp.Activity;
import org.jboss.arquillian.warp.Inspection;
import org.jboss.arquillian.warp.Warp;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.arquillian.warp.servlet.AfterServlet;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.Response;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test suit for SolverRestController
 *
 * @author oleksandr.chekanskyi
 * @see com.crxmarkets.exercise.presentation.web.controllers.SolverRestController
 * <p/>
 * Test is focused on rest semantics correctness
 */
@WarpTest
@RunWith(Arquillian.class)
public class SolverRestControllerImplTest {

    @Deployment
    @OverProtocol("Servlet 3.0")
    public static WebArchive createTestableDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "presentation.war")
                .addClasses(WaterProblemSolverService.class, WaterProblemSolverServiceImpl.class, WaterVolumeRequestParser.class,
                        SolverRestControllerImpl.class, SolverRestController.class, ExerciseApplication.class, NumberFormatExceptionHandler.class).addAsWebInfResource("beans.xml");
        return war;
    }

    @ArquillianResource
    private URL contextPath;

    @BeforeClass
    public static void setUpClass() {
        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
    }

    private SolverRestController controller;

    @Before
    public void setUp() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(contextPath + "api");

        controller = target.proxy(SolverRestController.class);
    }

    @Test
    @RunAsClient
    public void testGetWaterVolume() {
        Warp.initiate(new Activity() {
            @Override
            public void perform() {
                Response response = controller.getWaterVolume("1,0,1");
            }
        }).inspect(new Inspection() {

            private static final long serialVersionUID = 1L;

            @ArquillianResource
            private RestContext restContext;

            @AfterServlet
            public void testGetVolume() {
                assertEquals(HttpMethod.GET, restContext.getHttpRequest().getMethod().name());
                assertEquals(200, restContext.getHttpResponse().getStatusCode());
                assertEquals("application/json", restContext.getHttpResponse().getContentType());
                assertNotNull(restContext.getHttpResponse().getEntity());
                assertEquals(restContext.getHttpResponse().getEntity(), Integer.valueOf(1));
            }
        });
    }

}
