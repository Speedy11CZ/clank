package net.hashsploit.clank.server.medius.objects;

import net.hashsploit.clank.server.medius.MediusSerializableObject;
import net.hashsploit.clank.server.medius.test.NetOutput;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum MediusChatMessageType implements MediusSerializableObject {

	BROADCAST(0),
	
	WHISPER(1),
	
	BROADCAST_ACROSS_ENTIRE_UNIVERSE(2),
	
	CLAN_CHAT_TYPE(3),
	
	BUDDY_CHAT_TYPE(4);

	private static final Map<Integer, MediusChatMessageType> ENUM_MAP;

	private final int value;
	
	MediusChatMessageType(int value) {
		this.value = value;
	}

	@Override
	public void serialize(NetOutput netOutput) throws IOException {
		netOutput.writeInt(value);
	}

	static {
        Map<Integer, MediusChatMessageType> map = new ConcurrentHashMap<Integer, MediusChatMessageType>();
        for (MediusChatMessageType instance : MediusChatMessageType.values()) {
            map.put(instance.getValue(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

	public final int getValue() {
		return value;
	}

    public static MediusChatMessageType getByValue(int type) {
    	return ENUM_MAP.get(type);
    }
	
}
