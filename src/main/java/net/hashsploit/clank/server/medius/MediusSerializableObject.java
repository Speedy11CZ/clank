package net.hashsploit.clank.server.medius;

import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.NetOutput;

public interface IMediusSerializableObject {
	
    void deserialize(NetInput netInput);

    void serialize(NetOutput netOutput);
	
}
