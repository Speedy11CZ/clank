package net.hashsploit.clank.server.medius.test.handlers;

import net.hashsploit.clank.Clank;
import net.hashsploit.clank.config.configs.MasConfig;
import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.medius.MediusAuthenticationServer;
import net.hashsploit.clank.server.medius.MediusCallbackStatus;
import net.hashsploit.clank.server.medius.MediusMessageType;
import net.hashsploit.clank.server.medius.objects.*;
import net.hashsploit.clank.server.medius.test.MediusPacket;
import net.hashsploit.clank.server.medius.test.MediusPacketHandler;
import net.hashsploit.clank.server.medius.test.NetInput;
import net.hashsploit.clank.server.medius.test.packets.MediusAccountLoginRequest;
import net.hashsploit.clank.server.medius.test.packets.MediusAccountLoginResponse;
import net.hashsploit.clank.server.rpc.PlayerLoginResponse;
import net.hashsploit.clank.utils.Utils;

import java.io.IOException;
import java.util.List;

public class MediusAccountLoginHandler extends MediusPacketHandler {

    private MediusAccountLoginRequest request;

    public MediusAccountLoginHandler() {
        super(MediusMessageType.AccountLogin, MediusMessageType.AccountLoginResponse);
    }

    @Override
    public void read(NetInput netInput, MediusClient client) throws IOException {
        this.request = new MediusAccountLoginRequest(netInput);
    }

    @Override
    public void write(List<MediusPacket> mediusPackets, MediusClient client) throws IOException {
        String sessionKey = Utils.bytesToStringClean(request.getSessionKey()).toLowerCase() + '\0';

        if (!sessionKey.equals(client.getPlayer().getSessionKey().toLowerCase())) {
            throw new IllegalStateException("Requested login session key does not match the Clients current session key. Login: '" + sessionKey + "' Client: '" + client.getPlayer().getSessionKey().toLowerCase() + "'");
        }

        PlayerLoginResponse rpcResponse = ((MediusAuthenticationServer) client.getServer()).playerLogin(request.getUsername(), request.getPassword(), sessionKey);

        boolean whitelistSuccess = false;
        if (Clank.getInstance().getConfig() instanceof MasConfig) {
            MasConfig masConfig = (MasConfig) Clank.getInstance().getConfig();
            if (!((MasConfig) Clank.getInstance().getConfig()).isWhitelistEnabled()) {
                whitelistSuccess = true;
            }
            else {
                if (masConfig.getWhitelist().containsKey(request.getUsername().toLowerCase())) {
                    if (masConfig.getWhitelist().get(request.getUsername().toLowerCase()).equals(request.getPassword())) {
                        whitelistSuccess = true;
                    }
                }
            }
        }

        MediusCallbackStatus callbackStatus = rpcResponse.getSuccess() && whitelistSuccess ? MediusCallbackStatus.SUCCESS : MediusCallbackStatus.INVALID_PASSWORD;

        // FIXME: bad location
        //final LocationConfig location = client.getServer().getLogicHandler().getLocation();


        String mlsIpAddress = (Clank.getInstance().getConfig() instanceof MasConfig) ? ((MasConfig) Clank.getInstance().getConfig()).getMlsAddress() : Utils.getPublicIpAddress();
        String natIpAddress = (Clank.getInstance().getConfig() instanceof MasConfig) ? ((MasConfig) Clank.getInstance().getConfig()).getNatAddress() : Utils.getPublicIpAddress();

        NetAddress mlsNetAddress = new NetAddress(NetAddressType.NET_ADDRESS_TYPE_EXTERNAL, mlsIpAddress, ((MediusAuthenticationServer) client.getServer()).getMlsPort());
        NetAddress natNetAddress = new NetAddress(NetAddressType.NET_ADDRESS_NAT_SERVICE, natIpAddress, ((MediusAuthenticationServer) client.getServer()).getNatPort());

        NetAddressList netAddressList = new NetAddressList(mlsNetAddress, natNetAddress);
        NetConnectionInfo netConnectionInfo = new NetConnectionInfo(NetConnectionType.NET_CONNECTION_TYPE_CLIENT_SERVER_TCP, netAddressList, 0, new byte[128], request.getSessionKey(), rpcResponse.getMlsToken().getBytes());

        mediusPackets.add(new MediusAccountLoginResponse(request.getMessageID(), callbackStatus, rpcResponse.getAccountId(), MediusAccountType.MEDIUS_CHILD_ACCOUNT, 0, netConnectionInfo));
    }
}
