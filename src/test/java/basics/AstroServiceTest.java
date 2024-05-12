package basics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AstroServiceTest {

    private final AstroResponse mockResponse =
            new AstroResponse(
                    7,"Success", Arrays.asList(
                            new Assignment("John","Babylon 5"),
                    new Assignment("John Sheridan","Babylon 5"),
                    new Assignment("Susan Ivanova","USS Cerritos"),
                    new Assignment("Beckett Mariner","USS Cerritos"),
                    new Assignment("Brad Boimler","USS Cerritos"),
                    new Assignment("D'Vana Tendi","USS Cerritos"),
                     new Assignment("Ellen Ripley","Nostromo")
            )
            );
    @Mock
    private GateWay<AstroResponse> gateWay;
    @InjectMocks
    private AstroService service;
    @Test
    void testAstroData_usingInjectedMockGateWay() throws IOException, InterruptedException {
        // set the expectation on the mock

        when(gateWay.getResponse())
                .thenReturn(mockResponse);
        //call the method under test
        Map<String,Long> astroData = service.getAstroData();

        //Check the results from the method under test (AssertJ)
        assertThat(astroData)
                .containsEntry("Babylon 5",2L)
                .containsEntry("Nostromo",1L)
                .containsEntry("USS Cerritos",4L);
        //very the stubbed method was called
        verify(gateWay).getResponse();
        verify(gateWay,times(1)).getResponse();

    }


@Test
    void testAstroData_usingRealGateway_withHttpClient() throws IOException, InterruptedException {
        // create an instance of AstroService using real gateway
  service = new AstroService( new AstroGatewayHttpClient());

  //call the method under test

        Map<String,Long> astroData = service.getAstroData();
        astroData.forEach((craft,number)->{
            System.out.println(number +" astronauts board "+ craft);
            assertAll(
                    ()-> assertThat(number).isPositive(),
                    ()-> assertThat(craft).isNotBlank()
            );
        });

    }
    @SuppressWarnings("unchecked")
    @Test
    void testAstroData_usingMockGateway() throws IOException, InterruptedException {
        //create a mock gateway
        GateWay<AstroResponse> mockGateway = mock(GateWay.class);

        //2- Set exceptions on the mock Gateway

        when(mockGateway.getResponse())
                .thenReturn(mockResponse);

       // 3- Create an instance of AstroService using the mock Gateway
       AstroService service = new AstroService(mockGateway);

        // 4- Call the method under test
        Map<String,Long> astroData = service.getAstroData();

        // 5. Check the results from the method under test
        assertThat(astroData)
                .containsEntry("Babylon 5",2L)
                .containsEntry("USS Cerritos",4L)
                .containsEntry("Nostromo",1L)
                .hasSize(3);
        //6- Verify that the mock Gateway method was called
        verify(mockGateway).getResponse();

    }





}