package de.fettlaus.thekraken.events;

public class EventBus extends com.google.common.eventbus.EventBus {
	private static EventBus instance = new EventBus();

	public static EventBus instance() {
		return instance;
	}

	private EventBus() {
	}

}
