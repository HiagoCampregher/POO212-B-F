package moduloBF;
import java.awt.Image;
import java.time.LocalDateTime;

import moduloBGame.Ambiente;
import moduloBGame.Mediador;
import moduloBGame.POOmonComportamento;

public abstract class POOmon implements POOmonComportamento
{
	private Ambiente ambiente;
	private int energiaVital = 0;
	private int qtdVitorias = 0;
	private int maiorEnergiaVital = 0;
	private int qtdAtivacoes = 0;
	private LocalDateTime momentoMaiorEnergiaVital;
	private Image imagem;
	private Mediador mediador;
	private String conteudoLog;
	private POOmonComportamento oponente;
	
	public POOmon()
	{
		++qtdAtivacoes;
		
		this.setEnergia(500);
		this.setMediador(mediador);

		this.escreveLog("Log de batalha \n POOmon: " + this.getNome() + " – " + this.getAmbienteOriginario() + "\n");
	}
	
	@Override
	public int getEnergia()
	{
		return energiaVital;
	}
	
	public void setEnergia(int energia) 
	{
		this.energiaVital = energia;

		if (this.getEnergia() > this.maiorEnergiaVital)
		{
			this.maiorEnergiaVital = this.getEnergia();
			this.momentoMaiorEnergiaVital = LocalDateTime.now();
		}
	}
	
	@Override
	public LocalDateTime getMomentoMaiorEnergiaVital()
	{
		return momentoMaiorEnergiaVital;
	}
	
	@Override
	public int getMaiorEnergiaVital()
	{
		return maiorEnergiaVital;
	}
	
	@Override
	public int getQtdActivacoes()
	{
		return qtdAtivacoes;
	}
	
	@Override
	public int getVitorias()
	{
		return qtdVitorias;
	}
	
	@Override
	public abstract Image getImagem();
	
	public Ambiente getAmbiente()
	{
		return ambiente;
	}
	
	@Override
	public boolean estaVivo()
	{
		return getEnergia() > 0;
	}
	
	@Override
	public void receberAtaque(int danoAtaque, Ambiente objAmbiente)
	{
		if(objAmbiente == this.getAmbiente()) {
			danoAtaque -= 0.1;//arrumar aq
		}
		
		this.setEnergia(this.getEnergia() - danoAtaque);
	}
	
	@Override
	public void carregar(int arg0)
	{
		this.escreveLog("Energia recebida: " + arg0);
		
		setEnergia(this.getEnergia() + arg0);
	}
	
	@Override
	public void setMediador(Mediador arg0)
	{
		this.mediador = arg0;
	}
	
	public void setOponente(POOmonComportamento arg0)
	{
		this.oponente = arg0;
	}
	
	public POOmonComportamento getOponente()
	{
		return this.oponente;
	}
	
	@Override
	public void vitoria()
	{
		carregar(500);
		
		++qtdVitorias;
	}
	
	@Override
	public void derrota()
	{
		this.escreveLog("Derrota");
	}
	
	public void escreveLog(String log)
	{
		conteudoLog += (log + "\n");
	}

	public void atacar(POOmonComportamento arg0, Ambiente arg1) {
		// TODO Auto-generated method stub
		
	}
}
