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
		//grpc server ports
		String target = "localhost:8980";
		String targetK8s = "192.168.49.2:30980";
		String targetInnnerK8s = "172.17.0.4:8980";
		String targetOkteto ="35.225.69.73:50051";
		String targetOkteto2 ="ingress-bordaso.cloud.okteto.net:50051";

		if (args.length > 0) {
			if ("--help".equals(args[0])) {
				System.err.println("Usage: [targetOkteto]");
				System.err.println("");
				System.err.println("  target  The server to connect to. Defaults to " + targetOkteto2);
				System.exit(1);
			}
			targetInnnerK8s = args[0];
		}

		System.out.println("updated_______ " + 1);
		
		ManagedChannel channel = ManagedChannelBuilder.forTarget(targetOkteto2).usePlaintext().build();
		client = new GrpcClientStub(channel);

	/*	try {
			channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println(e.getClass().toString() + "______" + e.getMessage());
		}*/
	}

}
