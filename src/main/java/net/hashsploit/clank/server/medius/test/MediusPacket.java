package net.hashsploit.clank.server.medius.test;

import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.logging.Logger;

public abstract class MediusPacket {

    protected static final Logger LOGGER = Logger.getLogger(MediusPacket.class.getName());

    private MediusMessageType packetType;
    private NetInput netInput;

    public MediusPacket(byte[] incomingData) throws IOException {
        short packetType = Utils.bytesToShortLittle(incomingData[0], incomingData[1]);

        for (MediusMessageType mediusMessageType : MediusMessageType.values()) {
            if (mediusMessageType.getShort() == packetType) {
                this.packetType = mediusMessageType;
                break;
            }
        }

        netInput = new NetInput(Arrays.copyOfRange(incomingData, 2, incomingData.length));
    }

    public MediusPacket(MediusMessageType type, byte[] data) throws IOException {
        this.packetType = type;
        this.netInput = new NetInput(data);
    }

    public MediusPacket(MediusMessageType type, NetInput netInput) {
        this.packetType = type;
        this.netInput = netInput;
    }

    public MediusPacket(MediusMessageType type) {
        this.packetType = type;
    }

    public void writePacket(NetOutput netOutput) throws IOException {

    }

    public MediusMessageType getMediusPacketType() {
        return packetType;
    }

    public byte[] toBytes() throws IOException {
        NetOutput netOutput = new NetOutput();
        writePacket(netOutput);
        byte[] netOutputByteArray = netOutput.toByteArray();

        ByteBuffer bb = ByteBuffer.allocate(2 + netOutputByteArray.length);
        bb.put(packetType.getShortByte());
        bb.put(netOutputByteArray);
        bb.flip();
        return bb.array();
    }

    @Override
    public String toString() {
        try {
            return "MediusPacket{" +
                    "packetType=" + packetType +
                    ", data=" + Utils.bytesToHex(toBytes()) +
                    '}';
        } catch (IOException ignored) { }
        return "MediusPacket{" +
                "packetType=" + packetType +
                ", data=null" +
                '}';
    }
}
