package com.github.brotherlogic.monitorclient;

public class CommandLineInterface {

	MonitorClient client = new MonitorClient();

	public void handleArguments(String[] args) {
		if (args.length > 0 && args[0].equals("version")) {
			System.out.println(client.getVersion());
		}
	}

	public static void main(String[] args) {
		CommandLineInterface cli = new CommandLineInterface();
		cli.handleArguments(args);
	}
}
