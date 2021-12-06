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
	private String conteudoLog = "";
	private POOmonComportamento oponente;
	private Mediador mediador;
	private Estatistica estatistica = new Estatistica();
	
	public POOmon()
	{
		
	}
	
	@Override
	public int getEnergia()
	{
		return energiaVital;
	}
	
	public void setEnergia(int energia) 
	{
		this.energiaVital = energia;

		if (energiaVital > estatistica.getMaiorEnergiaVital())
		{
			estatistica.setMaiorEnergiaVital(energiaVital);
			estatistica.setMomentoMaiorEnergiaVital(LocalDateTime.now());
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
		return estatistica.getMomentoMaiorEnergiaVital();
	}
	
	@Override
	public int getMaiorEnergiaVital()
	{
		return estatistica.getMaiorEnergiaVital();
	}
	
	@Override
	public int getQtdActivacoes()
	{
		return estatistica.getQtdAtivacoes();
	}
	
	@Override
	public int getVitorias()
	{
		return estatistica.getQtdVitorias();
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
		
		this.escreveLog("Log de batalha\nPOOmon: " + this.getNome() + " – " + this.getAmbienteOriginario());
		
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
		
		estatistica.setQtdAtivacoes(estatistica.getQtdAtivacoes() + 1);
		estatistica.setQtdVitorias (estatistica.getQtdVitorias () + 1);
		
		this.escreveLog("Vitória");
	}
	
	@Override
	public void derrota()
	{
		GravaDados();
		
		estatistica.setQtdAtivacoes(estatistica.getQtdAtivacoes() + 1);
		this.escreveLog("Derrota");
	}
	
	public void escreveLog(String log)
	{
		if (log == null || log.isBlank())
			throw new IllegalArgumentException("Informe alguma coisa para o log.");
		
		this.conteudoLog += (log + "\n");
		
		try
		{
			//"C:\\Users\\Hiago Campregher\\Desktop\\POOmon\\teste.txt"
			
			File file = new File(mediador.getPastaLogs().toString() + "\\POOmon" + getNome() + ".txt");
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
	     File arq = new File(mediador.getPastaDados().toString() + "\\POOmon" + getNome() + ".dat");
	     
	      try
	      {
	        arq.delete();
	        arq.createNewFile();

	        ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
	        
	        objOutput.writeObject(estatistica);
	        objOutput.close();
	      }
	      catch(IOException erro)
	      {
	          System.out.printf("Erro: %s", erro.getMessage());
	      }
	}
	
	public void LeDados()
	{
	      try
	      {
	        File arq = new File(mediador.getPastaDados().toString() + "\\POOmon" + getNome() + ".dat");
	        if (arq.exists())
	        {
	           ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
	           estatistica = (Estatistica)objInput.readObject();
	           
	           objInput.close();
	        }
	      }
	      catch(IOException erro1)
	      {
	          System.out.printf("Erro: %s", erro1.getMessage());
	      }
	      catch(ClassNotFoundException erro2) {
	          System.out.printf("Erro: %s", erro2.getMessage());
	      }
	}
}
