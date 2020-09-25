package net.hashsploit.clank.server.pipeline;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.hashsploit.clank.server.DataPacket;
import net.hashsploit.clank.server.MediusClient;
import net.hashsploit.clank.server.RTPacketId;
import net.hashsploit.clank.server.medius.MediusPacket;
import net.hashsploit.clank.server.medius.objects.MediusMessage;
import net.hashsploit.clank.utils.Utils;

/**
 * Handles a server-side channel.
 */
public class TestHandlerMLS extends ChannelInboundHandlerAdapter { // (1)

	private static final Logger logger = Logger.getLogger("");
	private final MediusClient client;

	public TestHandlerMLS(final MediusClient client) {
		super();
		this.client = client;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		logger.fine(ctx.channel().remoteAddress() + ": channel active");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		logger.fine(ctx.channel().remoteAddress() + ": channel inactive");
	}

	
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        
    	logger.fine("======================================================");
    	logger.fine("======================================================");
    	logger.fine("======================================================");
    
    	final ByteBuffer buffer = toNioBuffer((ByteBuf) msg);

		final byte[] data = new byte[buffer.remaining()];
		buffer.get(data);

		logger.finest("TOTAL RAW INCOMING DATA: " + Utils.bytesToHex(data));

		// Get the packets
		List<DataPacket> packets = processData(ctx, data);

		for (DataPacket packet: packets) {
			processSinglePacket(ctx, packet);
		}

    }
    
    private void processSinglePacket(ChannelHandlerContext ctx, DataPacket packet) {
    	// Get the raw data    	
		logger.finest("RAW Single packet: " + Utils.bytesToHex(packet.toBytes()));
		
	    logger.fine("Packet ID: " + packet.getId().toString());
	    logger.fine("Packet ID: " + packet.getId().getByte());
		
	    // Check echo
	    checkEcho(ctx, packet);
	    
	    // Check initial connection TODO: take it out of here!
	    checkInitialConnect(ctx, packet);
	    
	    // Handle cities reconnect TODO: take it out of here!
	    checkForCitiesReconnect(ctx, packet);
	    
	    // Medius packets
	    MediusMessage response = checkMediusPackets(packet);
	    
	    // If there is a response, pass it onto the next handler
	    passOnToHandler(ctx, response);
    }
    
    private void passOnToHandler(ChannelHandlerContext ctx, MediusMessage mm) {
    	if (mm == null) 
    		return;
    	
//    	try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
    	DataPacket packet = new DataPacket(RTPacketId.SERVER_APP, mm.toBytes());

		byte[] finalPayload = packet.toBytes();
		logger.finest("Final payload: " + Utils.bytesToHex(finalPayload));
		ByteBuf msg = Unpooled.copiedBuffer(finalPayload);
		ctx.write(msg);
		ctx.flush();	
    }
    
    
    private MediusMessage checkMediusPackets(DataPacket packet) {
	    // ALL OTHER PACKETS THAT ARE MEDIUS PACKETS
    	MediusMessage mm = null;
	    if (packet.getId().toString().contains("APP")) {
	    	
	    	MediusMessage incomingMessage = new MediusMessage(packet.getPayload());

			logger.fine("Found Medius Packet ID: " + Utils.bytesToHex(incomingMessage.getMediusPacketType().getShortByte()));
			logger.fine("Found Medius Packet ID: " + incomingMessage.getMediusPacketType().toString());
			
			// Detect which medius packet is being parsed
		    MediusPacket mediusPacket = client.getMediusMap().get(incomingMessage.getMediusPacketType());
		    
		    // Process this medius packet
		    mediusPacket.read(incomingMessage);
		    mm = mediusPacket.write(client);	    
	    }
	    return mm;
    }
    
    
    private void checkForCitiesReconnect(ChannelHandlerContext ctx, DataPacket packet) {
		// TODO Auto-generated method stub
		byte[] data = packet.toBytes();

    	if (Utils.bytesToHex(data).equals("0049000108010b00bc29000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")) {
	    	
	    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			try {

				// ID_14
				// byte[] p14 =
				// Utils.hexStringToByteArray("BE2F79946D8FFFCA8D08671D329ACDB89A488F33ABEDD83C278E8C6F4FA68CBA0A66CEEC21EB8EE6C841B725FAA913E3A6982ECAF76B85977C36C1B4538C8850");
				final byte[] p14 = Utils.hexStringToByteArray("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
				outputStream.write(RTPacketId.SERVER_CRYPTKEY_GAME.getByte());
				outputStream.write(Utils.shortToBytesLittle((short) p14.length));
				outputStream.write(p14);

				// ID_07
				// outputStream.write(Utils.hexStringToByteArray("0108D30000010039362E3234322E38382E333600000000"));
				// // ip address to send back to the client
				ByteArrayOutputStream baos = new ByteArrayOutputStream();

				baos.write(Utils.hexStringToByteArray("0108D300000100"));

				byte[] ipAddr = client.getIPAddressAsBytes();
				int numZeros = 16 - client.getIPAddressAsBytes().length;
				String zeroString = new String(new char[numZeros]).replace("\0", "00");
				byte[] zeroTrail = Utils.hexStringToByteArray(zeroString);

				baos.write(ipAddr);
				baos.write(zeroTrail);
				baos.write(Utils.hexStringToByteArray("00"));

				outputStream.write(RTPacketId.SERVER_CONNECT_ACCEPT_TCP.getByte());
				outputStream.write(Utils.shortToBytesLittle((short) baos.size()));
				outputStream.write(baos.toByteArray());

				// ID_1a
				// outputStream.write(Utils.hexStringToByteArray("0100"));
				final byte[] p1a = Utils.hexStringToByteArray("0100");
				outputStream.write(RTPacketId.SERVER_CONNECT_COMPLETE.getByte());
				outputStream.write(Utils.shortToBytesLittle((short) p1a.length));
				outputStream.write(p1a);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			byte[] finalPayload = outputStream.toByteArray();

			logger.fine("Cities re-connect Final payload: " + Utils.bytesToHex(finalPayload));
			ByteBuf msg = Unpooled.copiedBuffer(finalPayload);
			ctx.write(msg); // (1)
			ctx.flush(); // (2)
		}
	}


	public void checkInitialConnect(ChannelHandlerContext ctx, DataPacket packet) {
		byte[] data = packet.toBytes();

	    if (Utils.bytesToHex(data).equals("006b000108010500bc2900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000031330000000000000000000000000000005a657138626b494b77704d6632444f5000")) {
	    	// TODO: Don't hard code the player ID (0x33 in this example)
	    	logger.fine(Utils.bytesToHex(data));
	    	byte[] firstPart = Utils.hexStringToByteArray("07170001081000000100");
			logger.severe(client.getIPAddress());
			byte[] ipAddr = client.getIPAddressAsBytes();
			int numZeros = 16 - client.getIPAddress().length();
			String zeroString = new String(new char[numZeros]).replace("\0", "00");
			byte[] zeroTrail = Utils.hexStringToByteArray(zeroString);
			byte[] lastPart = Utils.hexStringToByteArray("1a02000100");
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			try {
				outputStream.write(firstPart);
				outputStream.write(ipAddr);
				outputStream.write(zeroTrail);
				outputStream.write(lastPart);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			byte[] finalPayload = outputStream.toByteArray();
	    
			logger.fine("Final payload: " + Utils.bytesToHex(finalPayload));
	        ByteBuf msg = Unpooled.copiedBuffer(finalPayload);
	        ctx.write(msg); // (1)
	        ctx.flush(); // (2)
	    }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
    
	private static ByteBuffer toNioBuffer(final ByteBuf buffer) {
		if (buffer.isDirect()) {
			return buffer.nioBuffer();
		}
		final byte[] bytes = new byte[buffer.readableBytes()];
		buffer.getBytes(buffer.readerIndex(), bytes);
		return ByteBuffer.wrap(bytes);
	}

	private static List<DataPacket> processData(ChannelHandlerContext ctx, byte[] data) {
		final List<DataPacket> packets = new ArrayList<DataPacket>();

		int index = 0;

		try {
			while (index < data.length) {
				final byte id = data[index + 0];

				ByteBuffer bb = ByteBuffer.allocate(2);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				bb.put(data[index + 1]);
				bb.put(data[index + 2]);
				short length = bb.getShort(0);

				logger.fine("Length: " + Integer.toString(length));
				byte[] finalData = new byte[length];
				int offset = 0;

				if (length > 0) {
					// ID(1) + Length(2)
					offset += 1 + 2;
				}

				// logger.warning("PLAIN DATA PACKET");
				System.arraycopy(data, index + offset, finalData, 0, finalData.length);

				RTPacketId rtid = null;

				for (RTPacketId p : RTPacketId.values()) {
					if (p.getByte() == id) {
						rtid = p;
						break;
					}
				}

				packets.add(new DataPacket(rtid, finalData));

				index += length + 3;
			}
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}

		return packets;
	}
	
	public void checkEcho(ChannelHandlerContext ctx, DataPacket packet) {
			 if (packet.getId() == RTPacketId.CLIENT_ECHO) {
				// Combine RT id and len
				DataPacket packetResponse = new DataPacket(RTPacketId.CLIENT_ECHO, packet.getPayload());
				byte[] payload = packetResponse.toBytes();
				logger.fine("Final payload: " + Utils.bytesToHex(payload));
				ByteBuf msg = Unpooled.copiedBuffer(payload);
				ctx.write(msg);
				ctx.flush();
		}
	}

}