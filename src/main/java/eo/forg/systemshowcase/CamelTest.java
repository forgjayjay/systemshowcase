package eo.forg.systemshowcase;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelTest {
    public static void main(String[] args) throws Exception {
        try (CamelContext context = new DefaultCamelContext()) {
            
            context.addRoutes(new RouteBuilder() {
                public void configure() {
                    from("direct:start")
                    .to("direct:process"); // Sending message to another Camel route

                    from("direct:process")
                    .log("Received message: ${body}"); // Processing the message in another Camel route
                }
            });

            context.start();

            // Send a message to Camel route
            context.createProducerTemplate().sendBody("direct:start", "Hello, Camel!");

            Thread.sleep(5000);

            context.stop();
        }
    }
}
