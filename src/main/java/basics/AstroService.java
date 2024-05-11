package basics;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class AstroService {
    private final GateWay<AstroResponse> gateWay;

    public AstroService(GateWay<AstroResponse> gateWay) {
        this.gateWay = gateWay;
    }



    public  Map<String ,Long> getAstroData() throws IOException, InterruptedException {
        var response =gateWay.getResponse();
        return groupByCraft(response);
    }

    private Map<String, Long> groupByCraft(AstroResponse response) {
        return response.people().stream()
                .collect(Collectors.groupingBy(
                        Assignment::craft,Collectors.counting()
                ));
    }


}
