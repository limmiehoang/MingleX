package com.ksv.minglex.service;

public class XSSPrevention {
    public static String escapeHtml(String s) {
		StringBuilder out = new StringBuilder(Math.max(16, s.length()));
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c > 127 || c == '"' || c == '<' || c == '>' || c == '&' || c == '/' || c == 39) {
				out.append("&#");
				out.append((int) c);
				out.append(';');
			} else {
				out.append(c);
			}
		}
		return out.toString();
	}

	public static String escapeJavaScript(String s) {
		StringBuilder out = new StringBuilder(Math.max(16, s.length()));
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			//			This encodes all characters less than 256 except alphanumeric characters
			if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) && c < 256) {
				out.append("\\x");
				out.append(Integer.toHexString((int) c));
			} else {
				out.append(c);
			}
		}
		return out.toString();
	}
}
