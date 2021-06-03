package net.hashsploit.clank.server.medius.objects;

import java.util.HashMap;
import java.util.Map;

public enum MediusPlayerSearchType {
	
	PLAYER_ACCOUNT_ID(0),
	
	PLAYER_ACCOUNT_NAME(1);

	private static final Map<Integer, MediusPlayerSearchType> enumMapping = new HashMap<>();


	private final int value;

	MediusPlayerSearchType(int value) {
		this.value = value;
	}

	public final int getValue() {
		return value;
	}

	public static MediusPlayerSearchType getTypeFromValue(int value) {
		return enumMapping.get(value);
	}

	static {
		for (MediusPlayerSearchType playerSearchType : MediusPlayerSearchType.values()) {
			enumMapping.put(playerSearchType.value, playerSearchType);
		}
	}

}
