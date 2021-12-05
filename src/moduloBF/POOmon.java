package moduloBF;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
		this.escreveLog("Log de batalha\nPOOmon: " + this.getNome() + " – " + this.getAmbienteOriginario());
	}
	
	@Override
	public int getEnergia()
	{
		return energiaVital;
	}
	
	public void setEnergia(int energia) 
	{
		this.energiaVital = energia;

		if (energiaVital > this.maiorEnergiaVital)
		{
			this.maiorEnergiaVital = energiaVital;
			this.momentoMaiorEnergiaVital = LocalDateTime.now();
		}
	}
	
	public void logaEnergia()
	{
		DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatHorario = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		escreveLog("Minha energia vital: " + getEnergia() + " - " + formatData.format(LocalDate.now()) + " - " + formatHorario.format(LocalTime.now()));
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
		
		this.setEnergia(getEnergia() - danoAtaqueFinal);
		this.escreveLog("Ataque recebido: " + danoAtaque + " - " + objAmbiente + "(-" + danoAtaqueFinal + ")");
	}
	
	@Override
	public void carregar(int arg0)
	{
		this.escreveLog("Energia recebida: " + arg0);
		
		setEnergia(this.getEnergia() + arg0);

		logaEnergia();
	}
	
	@Override
	public void setMediador(Mediador arg0)
	{
		if (arg0 == null)
			throw new IllegalArgumentException("Informe um mediador.");
		
		LeDados();
			
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
		
		logaEnergia();
		
		this.oponente = arg0;
	}
	
	@Override
	public void vitoria()
	{
		GravaDados();
		
		++qtdAtivacoes;
		++qtdVitorias;
		this.escreveLog("Vitória");
	}
	
	@Override
	public void derrota()
	{
		GravaDados();
		
		++qtdAtivacoes;
		this.escreveLog("Derrota");
	}
	
	public void escreveLog(String log)
	{
		if (log == null || log.isBlank())
			throw new IllegalArgumentException("Informe alguma coisa para o log.");
		
		this.conteudoLog += (log + "\n");
		
		try
		{
			//mediador.getPastaLogs().toString() + "\\POOmon" + getNome() + ".txt";
			
			File file = new File("C:\\Users\\Hiago Campregher\\Desktop\\POOmon\\teste.txt");
			file.setWritable(true);
			file.setReadable(true);
			
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
	
	public void GravaDados()
	{
	     File arq = new File("POOmon" + getNome() + ".dat");
	     
	      try
	      {
	        arq.delete();
	        arq.createNewFile();

	        ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
	        
	        Estatistica estatisticas = new Estatistica();
	        estatisticas.setMaiorEnergiaVital(maiorEnergiaVital);
	        estatisticas.setMomentoMaiorEnergiaVital(momentoMaiorEnergiaVital);
	        estatisticas.setQtdAtivacoes(qtdAtivacoes);
	        estatisticas.setQtdVitorias(qtdVitorias);
	        
	        objOutput.writeObject(estatisticas);
	        objOutput.close();
	      }
	      catch(IOException erro)
	      {
	          System.out.printf("Erro: %s", erro.getMessage());
	      }
	}
	
	public void LeDados()
	{
	      try {
	        File arq = new File("POOmon" + getNome() + ".dat");
	        if (arq.exists()) {
	           ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
	           Estatistica estatisticas = (Estatistica)objInput.readObject();

	           if (estatisticas != null)
	           {
	        	   this.qtdAtivacoes = estatisticas.getQtdAtivacoes();
	        	   this.qtdVitorias = estatisticas.getQtdVitorias();
	        	   this.maiorEnergiaVital = estatisticas.getMaiorEnergiaVital();
	        	   this.momentoMaiorEnergiaVital = estatisticas.getMomentoMaiorEnergiaVital();
	           }
	        	   
	           objInput.close();
	        }
	      } catch(IOException erro1) {
	          System.out.printf("Erro: %s", erro1.getMessage());
	      } catch(ClassNotFoundException erro2) {
	          System.out.printf("Erro: %s", erro2.getMessage());
	      }
	}
}
