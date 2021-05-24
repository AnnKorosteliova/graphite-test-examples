import net.savantly.graphite.CarbonMetric;
import net.savantly.graphite.GraphiteClient;
import net.savantly.graphite.GraphiteClientFactory;
import net.savantly.graphite.impl.SimpleCarbonMetric;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationNetSavantly {

    public static void main(String[] args) throws SocketException, UnknownHostException {

        GraphiteClient client = GraphiteClientFactory.defaultGraphiteClient("testgraph.tk", 2003);

        long epoch = System.currentTimeMillis()/1000;
        CarbonMetric metric = new SimpleCarbonMetric("test.metric", "test", epoch);

        client.saveCarbonMetrics(metric);

        List<CarbonMetric> metrics = new ArrayList<CarbonMetric>();

        for (int i = 0; i < 5; i++) {
            metrics.add(new SimpleCarbonMetric("test.metrics" + i, "test" + i, epoch));
        }

        client.saveCarbonMetrics(metrics);

    }



}
