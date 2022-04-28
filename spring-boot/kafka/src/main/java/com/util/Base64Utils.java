package com.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Utils {

	// private static byte[] encode(byte[] byteArr) {
	// return Base64.getEncoder().encode(byteArr);
	// }

	public static String encodeToString(byte[] byteArr) {
		byte[] arr = Base64.getEncoder().encode(byteArr);
		return new String(arr, StandardCharsets.UTF_8);
	}

	public static String encodeToString(String str) {
		byte[] byteArr = str.getBytes(StandardCharsets.UTF_8);
		return encodeToString(byteArr);
	}

	// private static byte[] decode(byte[] byteArr) {
	// return Base64.getDecoder().decode(byteArr);
	// }

	// private static byte[] decode(String base64) {
	// byte[] byteArr = base64.getBytes(StandardCharsets.UTF_8);
	// return decode(byteArr);
	// }

	public static String decodeToString(String base64) {
		byte[] byteArr = base64.getBytes(StandardCharsets.UTF_8);
		byteArr = Base64.getDecoder().decode(byteArr);
		return new String(byteArr);
	}

	public static byte[] decodeToByte(String base64) {
		byte[] byteArr = base64.getBytes(StandardCharsets.UTF_8);
		byteArr = Base64.getDecoder().decode(byteArr);
		return byteArr;
	}

	public static String longText() {
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><TDiep><TTChung><PBan>1.0.0</PBan><MNGui>TCT</MNGui><MNNhan>V0309478306</MNNhan><MLTDiep>999</MLTDiep><MTDiep>TCT_MTDiep_001</MTDiep><MTDTChieu>TCTN_MTDiep_001</MTDTChieu></TTChung><DLieu><TBao><MTDiep>999</MTDiep><MNGui>TCT</MNGui><NNhan>2021-10-30T14:00:00</NNhan><TTTNhan>1</TTTNhan><DSLDo><LDo><MTa>Lỗi kỹ thuật phản hồi từ TCT</MTa><MLoi>TCT-001</MLoi></LDo></DSLDo></TBao></DLieu></TDiep>";
		return encodeToString(xml);
	}

}
