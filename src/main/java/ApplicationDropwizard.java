import com.codahale.metrics.*;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class ApplicationDropwizard {

    public static void main(String[] args) throws InterruptedException {

        //Creating the Registry
        MetricRegistry metricRegistry = new MetricRegistry();

        //Creating some kinds of metrics
        Meter meter = metricRegistry.meter("meter");
        meter.mark();
        Counter counter = metricRegistry.counter("counter");
        Histogram histogram = metricRegistry.histogram("histogram");
        Timer timer = metricRegistry.timer("timer");

        //Creating Graphite class
        final Graphite graphite = new Graphite(new InetSocketAddress("testgraph.tk", 2003));

        //Reporting metrics
        GraphiteReporter reporter = GraphiteReporter.forRegistry(metricRegistry)
                .prefixedWith("example.local")
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite);

        reporter.start(5, TimeUnit.MILLISECONDS);
        reporter.report();

    }

}
