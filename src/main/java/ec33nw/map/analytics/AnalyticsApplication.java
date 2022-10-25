package ec33nw.map.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ec33nw.map.analytics.impl.GrpcClientStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@SpringBootApplication
public class AnalyticsApplication {

	public static GrpcClientStub client;

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsApplication.class, args);
		String target = "ec33nw-datahandler-service:8980";

		if (args.length > 0) {
			if ("--help".equals(args[0])) {
				System.err.println("Usage: [targetOkteto]");
				System.err.println("");
				System.err.println("  target  The server to connect to. Defaults to " + target);
				System.exit(1);
			}
		}
		
		ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
		client = new GrpcClientStub(channel);

	}

}
