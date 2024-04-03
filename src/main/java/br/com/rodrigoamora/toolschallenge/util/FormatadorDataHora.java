package br.com.rodrigoamora.toolschallenge.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatadorDataHora {
	public static String formatarDataHora(String padraoDataHora) {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(padraoDataHora);
		return formatter.format(localDateTime);
	}
}
