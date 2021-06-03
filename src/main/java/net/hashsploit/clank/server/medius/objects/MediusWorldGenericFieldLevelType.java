package net.hashsploit.clank.server.medius.objects;

import net.hashsploit.clank.server.medius.MediusSerializableObject;
import net.hashsploit.clank.server.medius.test.NetOutput;

import java.io.IOException;

public enum MediusWorldGenericFieldLevelType implements MediusSerializableObject {

    WORLD_GENERIC_FIELD_LEVEL_0(0),
    WORLD_GENERIC_FIELD_LEVEL_1(1),
    WORLD_GENERIC_FIELD_LEVEL_2(1 << 1),
    WORLD_GENERIC_FIELD_LEVEL_3(1 << 2),
    WORLD_GENERIC_FIELD_LEVEL_4(1 << 3),
    WORLD_GENERIC_FIELD_LEVEL_12(1 << 4),
    WORLD_GENERIC_FIELD_LEVEL_123(1 << 5),
    WORLD_GENERIC_FIELD_LEVEL_1234(1 << 6),
    WORLD_GENERIC_FIELD_LEVEL_23(1 << 7),
    WORLD_GENERIC_FIELD_LEVEL_234(1 << 8),
    WORLD_GENERIC_FIELD_LEVEL_34(1 << 9);

    private final int value;

    MediusWorldGenericFieldLevelType(int value) {
        this.value = value;
    }

    @Override
    public void serialize(NetOutput netOutput) throws IOException {
        netOutput.writeInt(value);
    }

    public int getValue() {
        return value;
    }
}
