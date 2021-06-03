package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;

import java.io.IOException;
import java.util.List;

public class MediusEndGameReportHandler extends MediusPacketHandler {

    public MediusEndGameReportHandler() {
        super(MediusMessageType.EndGameReport, null);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        //TODO: IMPLEMENT
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {

    }
}
