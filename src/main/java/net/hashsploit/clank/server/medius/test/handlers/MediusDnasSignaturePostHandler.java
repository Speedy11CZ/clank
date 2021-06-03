package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.MediusDnasSignaturePostRequest;

import java.io.IOException;
import java.util.List;

public class MediusDnasSignaturePostHandler extends MediusPacketHandler {

    private MediusDnasSignaturePostRequest request;

    public MediusDnasSignaturePostHandler() {
        super(MediusMessageType.DnasSignaturePost, null);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusDnasSignaturePostRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {

    }
}
