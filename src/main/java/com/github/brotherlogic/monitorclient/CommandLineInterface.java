package com.github.brotherlogic.monitorclient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import monitorproto.Monitorproto.Heartbeat;

public class CommandLineInterface {

	MonitorClient client = new MonitorClient();
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

	public void handleArguments(String[] args) {
		if (args.length > 0 && args[0].equals("version")) {
			System.out.println(client.getVersion());
		} else if (args.length > 0 && args[0].equals("monitor")) {
			System.out.println(client.getMonitorIPAndHost(args[1], Integer.parseInt(args[2])));
		} else if (args.length > 0 && args[0].equals("list")) {
			for (Heartbeat beat : client.getHeartbeats(args[1], Integer.parseInt(args[2]))) {
				System.out.println(beat.getEntry().getName() + ": "
						+ formatter.format(new Date(beat.getBeatTime() * 1000L)) + " from " + beat);
			}
		}
	}

	public static void main(String[] args) {
		CommandLineInterface cli = new CommandLineInterface();
		cli.handleArguments(args);
	}
}
