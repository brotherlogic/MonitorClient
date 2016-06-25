package com.github.brotherlogic.monitorclient;

public class CommandLineInterface {

	MonitorClient client = new MonitorClient();

	public void handleArguments(String[] args) {
		if (args.length > 0 && args[0].equals("version")) {
			System.out.println(client.getVersion());
		} else if (args.length > 0 && args[0].equals("monitor")) {
			System.out.println(client.getMonitorIPAndHost(args[1], Integer.parseInt(args[2])));
		}
	}

	public static void main(String[] args) {
		CommandLineInterface cli = new CommandLineInterface();
		cli.handleArguments(args);
	}
}
