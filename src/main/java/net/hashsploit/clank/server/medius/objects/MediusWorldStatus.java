package net.hashsploit.clank.server.medius.objects;

import net.hashsploit.clank.server.medius.MediusSerializableObject;
import net.hashsploit.clank.server.medius.test.NetOutput;

import java.io.IOException;

public enum MediusWorldStatus implements MediusSerializableObject {

	WORLD_INACTIVE(0),

	WORLD_STAGING(1),

	WORLD_ACTIVE(2),

	WORLD_CLOSED(3),

	WORLD_PENDING_CREATION(4),

	WORLD_PENDING_CONNECT_TO_GAME(5);

	public final int value;

	MediusWorldStatus(int value) {
		this.value = value;
	}

	@Override
	public void serialize(NetOutput netOutput) throws IOException {
		netOutput.writeInt(value);
	}
}
