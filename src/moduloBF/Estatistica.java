package moduloBF;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Estatistica implements Serializable
{
	private int qtdVitorias = 0;
	private int maiorEnergiaVital = 0;
	private int qtdAtivacoes = 0;
	private LocalDateTime momentoMaiorEnergiaVital;
	
	public int getQtdVitorias() {
		return qtdVitorias;
	}
	
	public void setQtdVitorias(int qtdVitorias) {
		this.qtdVitorias = qtdVitorias;
	}
	
	public int getMaiorEnergiaVital() {
		return maiorEnergiaVital;
	}
	
	public void setMaiorEnergiaVital(int maiorEnergiaVital) {
		this.maiorEnergiaVital = maiorEnergiaVital;
	}
	
	public int getQtdAtivacoes() {
		return qtdAtivacoes;
	}
	
	public void setQtdAtivacoes(int qtdAtivacoes) {
		this.qtdAtivacoes = qtdAtivacoes;
	}
	
	public LocalDateTime getMomentoMaiorEnergiaVital() {
		return momentoMaiorEnergiaVital;
	}
	
	public void setMomentoMaiorEnergiaVital(LocalDateTime momentoMaiorEnergiaVital) {
		this.momentoMaiorEnergiaVital = momentoMaiorEnergiaVital;
	}
}
