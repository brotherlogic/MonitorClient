package com.github.brotherlogic.monitorclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import discovery.Discovery.Empty;
import discovery.Discovery.RegistryEntry;
import discovery.Discovery.ServiceList;
import discovery.DiscoveryServiceGrpc;
import discovery.DiscoveryServiceGrpc.DiscoveryServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MonitorClient {

	String version;

	protected Properties getProperties(InputStream stream) {
		try {
			Properties props = new Properties();
			props.load(stream);
			return props;
		} catch (IOException e) {
			return null;
		}
	}

	public String getMonitorIPAndHost(String dHost, int dPort) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(dHost, dPort).usePlaintext(true).build();
		DiscoveryServiceBlockingStub blockingStub = DiscoveryServiceGrpc.newBlockingStub(channel);
		try {
			RegistryEntry request = RegistryEntry.newBuilder().setName("monitor").build();
			RegistryEntry entry = blockingStub.discover(request);
			channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);

			return entry.getIp() + ":" + entry.getPort();
		} catch (Exception e) {
			e.printStackTrace();

			try {
				// Call out to the full end poin
				ServiceList list = blockingStub.listAllServices(Empty.newBuilder().build());
				for (RegistryEntry entry : list.getServicesList()) {
					System.out.println("Entry :" + entry);
				}
				channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return "";
	}

	public String getVersion() {
		Properties props = getProperties(MonitorClient.class.getResourceAsStream("properties.txt"));
		return props.getProperty("version");
	}
}
