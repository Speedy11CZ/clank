package net.hashsploit.clank.server.medius.test;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusMessageType;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public abstract class MediusPacketHandler {

    protected static final Logger LOGGER = Logger.getLogger(MediusPacketHandler.class.getName());

    private final MediusMessageType requestMessageType;
    private final MediusMessageType responseMessageType;

    public MediusPacketHandler(MediusMessageType requestMessageType, MediusMessageType responseMessageType) {
        this.requestMessageType = requestMessageType;
        this.responseMessageType = responseMessageType;
    }

    public abstract void read(NetInput netInput, MediusClient client) throws IOException;

    public abstract void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException;

    public MediusMessageType getRequestMessageType() {
        return requestMessageType;
    }

    public MediusMessageType getResponseMessageType() {
        return responseMessageType;
    }

    @Override
    public String toString() {
        return "MediusPacketHandler{" +
                "requestMessageType=" + requestMessageType +
                ", responseMessageType=" + responseMessageType +
                '}';
    }
}
