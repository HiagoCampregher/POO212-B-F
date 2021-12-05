package moduloBF;
import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	private String conteudoLog = "";
	private POOmonComportamento oponente;
	private Mediador mediador;
	
	public POOmon()
	{
		++qtdAtivacoes;
		this.escreveLog("Log de batalha\nPOOmon: " + this.getNome() + " – " + this.getAmbienteOriginario());//ver se n precisa transformar pra string
	}
	
	@Override
	public int getEnergia()
	{
		this.escreveLog("teste");
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
		int danoAtaqueFinal = danoAtaque;
		if(objAmbiente == this.getAmbiente())
			danoAtaqueFinal -= (0.1 * danoAtaque);
		
		this.setEnergia(this.getEnergia() - danoAtaqueFinal);
		this.escreveLog("Ataque recebido: " + danoAtaque + " - " + objAmbiente + "(-" + danoAtaqueFinal + ")");
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
	
	public POOmonComportamento getOponente()
	{
		return this.oponente;
	}
	
	@Override
	public void informarOponente(POOmonComportamento arg0)
	{
		this.escreveLog("\nOponente: " + arg0.getNome() + " - " + arg0.getAmbienteOriginario());
		
		this.oponente = arg0;
	}
	
	@Override
	public void vitoria()
	{
		++qtdVitorias;
		this.escreveLog("Vitória");
	}
	
	@Override
	public void derrota()
	{
		this.escreveLog("Derrota");
	}
	
	public void escreveLog(String log)
	{
		if (log == null || log.isBlank())
			throw new IllegalArgumentException("Informe alguma coisa para o log.");
		
		this.conteudoLog += (log + "\n");
		
		//if (this.mediador == null)
			//throw new IllegalArgumentException("Informe o mediador.");
		
		try
		{
			//getMediador().getPastaLogs();
			File file = new File("C:\\Users\\Hiago Campregher\\Desktop\\POOmon\\teste.txt");
			file.setWritable(true);
			file.setWritable(true);
			
			FileWriter fw = new FileWriter(file);
			fw.write(conteudoLog);
			fw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("Erro ao gravar o log de batalha.");
		}
	}
}
