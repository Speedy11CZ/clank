package net.hashsploit.clank.server.medius.objects;

import net.hashsploit.clank.server.medius.MediusSerializableObject;
import net.hashsploit.clank.server.medius.test.NetOutput;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public enum MediusAccountType implements MediusSerializableObject {
	
	MEDIUS_CHILD_ACCOUNT(0),
	
	MEDIUS_MASTER_ACCOUNT(1);

	private static final Map<Integer, MediusAccountType> enumMapping = new HashMap<>();

	private final int value;

	MediusAccountType(int value) {
		this.value = value;
	}

	static {
		for (MediusAccountType accountType : MediusAccountType.values()) {
			enumMapping.put(accountType.value, accountType);
		}
	}

	public final int getValue() {
		return value;
	}

	public static MediusAccountType getTypeFromValue(int value) {
		return enumMapping.get(value);
	}

	@Override
	public void serialize(NetOutput netOutput) throws IOException {
		netOutput.writeInt(value);
	}
}
