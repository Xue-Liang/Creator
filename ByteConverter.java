package org.xue.socket.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author XueLiang
 *
 * 
 */
public class ByteConverter {
	public static byte[] readBytes(InputStream is, int size) throws IOException {
		byte[] content = new byte[size];
		is.read(content);
		return content;
	}

	public static int readInteger(InputStream is) throws IOException {
		byte[] content = readBytes(is, 4);
		return toInteger(content);
	}

	public static int toInteger(byte[] bs) {
		int value = 0;
		for (int i = 0; i < 4; i++) {
			value += (bs[i] & 0xff) << ((3 - i) << 3);
		}
		return value;
	}

	public static byte[] toBytes(int i) {
		return new byte[] { (byte) (i >> 24 & 0xff), (byte) (i >> 16 & 0xff),
				(byte) (i >> 8 & 0xff), (byte) (i & 0xff) };
	}

	public static byte[] toBytes(long l) {
		return new byte[] { (byte) ((l >> 56) & 0xff),
				(byte) ((l >> 48) & 0xff), (byte) ((l >> 40) & 0xff),
				(byte) ((l >> 32) & 0xff), (byte) ((l >> 24) & 0xff),
				(byte) ((l >> 16) & 0xff), (byte) ((l >> 8) & 0xff),
				(byte) (l & 0xff) };
	}

	public static long toLong(byte[] bs) {
		long value = 0;
		for (int i = 0; i < 8; i++) {
			value += (bs[i] & 0xff) << ((7 - i) << 3);
		}
		return value;
	}
}
