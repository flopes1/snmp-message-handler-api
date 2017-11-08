package com.poli.snmp.service;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.poli.snmp.dao.ISnmpMessageHistoryDao;
import com.poli.snmp.model.SnmpMessage;
import com.poli.snmp.model.SnmpMessageHistory;
import java.io.IOException;
import java.math.BigInteger;
import java.net.*;
import java.nio.charset.Charset;

/**
 * 
 * 
 * @author Filipe Mendes
 *
 */

@Service
@Transactional
public class SnmpMessageHistoryService implements ISnmpMessageHistoryService {

	@Autowired
	private ISnmpMessageHistoryDao snmpMessageDao;

	@Override
	public void addSnmpMessageHistory(SnmpMessageHistory snmpMessage) {
		this.snmpMessageDao.addSnmpMessageHistory(snmpMessage);
	}

	@Override
	public SnmpMessageHistory getLastMessageHistory() {
		return this.snmpMessageDao.getLastMessageHistory();
	}

	@Override
	public List<SnmpMessageHistory> getAllMessagesHistoryDesc() {
		return this.snmpMessageDao.getAllMessagesHistoryDesc();
	}

	@Override
	public String processSnmpMessage(SnmpMessage snmpMessage) throws NumberFormatException, UnknownHostException, IOException {

		String result = "";

		// OID
		// String oid = "1.3.6.1.2.1.1.1.0";
		String[] oidParts = snmpMessage.getObjectId().split("\\.");
		String pubOrPri = snmpMessage.getCommunity(); // "public";
		String oidValue = Integer.toHexString(0x2b);

		for (int i = 2; i < oidParts.length; i++) {
			oidValue += StringUtils.leftPad(Integer.toHexString(Integer.decode(oidParts[i])), 2, "0");
		}

		int oidLength = oidValue.length() / 2;

		String oidType = StringUtils.leftPad(Integer.toHexString(0x06), 2, "0");

		String oidTotal = oidType + StringUtils.leftPad(Integer.toHexString(oidLength), 2, "0") + oidValue;

		// VarBind

		String varBindType = Integer.toHexString(0x30);

		String valueField = StringUtils.leftPad(Integer.toHexString(0x05), 2, "0")
				+ StringUtils.leftPad(Integer.toHexString(0x00), 2, "0");

		String varBindTotal = varBindType + StringUtils.leftPad(Integer.toHexString(oidLength + 2 + 2), 2, "0")
				+ oidTotal + valueField;

		// VarBindList

		String varBindListType = Integer.toHexString(0x30);

		String varBindListLength = StringUtils.leftPad(Integer.toHexString(varBindTotal.length() / 2), 2, "0");

		String varBindListTotal = varBindListType + varBindListLength + varBindTotal;

		// Error Index

		String errorIndex = StringUtils.leftPad(Integer.toHexString(0x02), 2, "0")
				+ StringUtils.leftPad(Integer.toHexString(0x01), 2, "0")
				+ StringUtils.leftPad(Integer.toHexString(0x00), 2, "0");

		// Error

		String error = StringUtils.leftPad(Integer.toHexString(0x02), 2, "0")
				+ StringUtils.leftPad(Integer.toHexString(0x01), 2, "0")
				+ StringUtils.leftPad(Integer.toHexString(0x00), 2, "0");

		// Request ID

		String requestID = StringUtils.leftPad(Integer.toHexString(0x02), 2, "0")
				+ StringUtils.leftPad(Integer.toHexString(0x01), 2, "0")
				+ StringUtils.leftPad(Integer.toHexString(0x01), 2, "0");

		// PDU

		String PDU = Integer.toHexString(0xa0)
				+ StringUtils.leftPad(Integer.toHexString(3 + 3 + 3 + (varBindListTotal.length() / 2)), 2, "0")
				+ requestID + error + errorIndex + varBindListTotal;

		// Community

		String community = StringUtils.leftPad(Integer.toHexString(0x04), 2, "0")
				+ StringUtils.leftPad(Integer.toHexString(toHex(pubOrPri).length() / 2), 2, "0") + toHex(pubOrPri);

		// Version

		String version = StringUtils.leftPad(Integer.toHexString(0x02), 2, "0")
				+ StringUtils.leftPad(Integer.toHexString(0x01), 2, "0")
				+ StringUtils.leftPad(Integer.toHexString(0x00), 2, "0");

		// Message

		String message = Integer.toHexString(0x30)
				+ StringUtils.leftPad(Integer.toHexString(3 + (community.length() / 2) + (PDU.length() / 2)), 2, "0")
				+ version + community + PDU;

		// Primeiro argumento é o nome do host destino
		// InetAddress addr = InetAddress.getByName("127.0.0.1");
		InetAddress addr = InetAddress.getByName(snmpMessage.getTargetIp());

		// int port = Integer.parseInt("161");
		int port = Integer.parseInt(snmpMessage.getTargetPort());

		byte[] msg = hexStringToByteArray(message);

		// Monta o pacote a ser enviado
		DatagramPacket pkg = new DatagramPacket(msg, msg.length, addr, port);

		// Cria o DatagramSocket que será responsável por enviar a mensagem
		DatagramSocket ds = new DatagramSocket();

		// Envia a mensagem
		ds.send(pkg);

		// System.out.println(
		// "Mensagem enviada para: " + addr.getHostAddress() + "\n" + "Porta: "
		// + port + "\n" + "Mensagem: ");

		// Cria o DatagramSocket para aguardar mensagens, neste momento o método
		// fica bloqueando
		// até o recebimente de uma mensagem
		// System.out.println("Ouvindo a porta: " + port);

		// Preparando o buffer de recebimento da mensagem
		byte[] msg2 = new byte[256];

		// Prepara o pacote de dados
		DatagramPacket pkg2 = new DatagramPacket(msg2, msg2.length);

		// Recebimento da mensagem
		ds.receive(pkg2);

		String response = new String(pkg2.getData(), pkg.getLength(), pkg2.getLength());

		// System.out.println("Response: " + response);

		result += response;

		// Fecha o DatagramSocket
		ds.close();

		return result;
	}

	public static byte[] hexStringToByteArray(String s) {

		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int

		i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit

			(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i

					+ 1), 16));
		}
		return data;
	}

	public static String toHex(String arg) {
		return String.format("%x", new BigInteger(1, arg.getBytes(Charset.forName("UTF-8"))));
	}

}
