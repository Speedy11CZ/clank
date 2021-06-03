package net.hashsploit.clank.server.medius;

import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.NetOutput;

import java.io.IOException;

public interface MediusSerializableObject {
	
    default void deserialize(NetInput netInput) throws IOException {

    }

    default void serialize(NetOutput netOutput) throws IOException {

    }
}
