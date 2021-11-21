import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import moduloBGame.Ambiente;
import moduloBGame.Mediador;
import moduloBGame.POOmonComportamento;

public class POOmonH implements POOmonComportamento
{
	private Ambiente ambiente;
	private int energiaVital;
	private int qtdVitorias;
	private int maiorEnergiaVital;
	private int qtdAtivacoes;
	private LocalDateTime momentoMaiorEnergiaVital;
	private ImageIO imagem;
	
	public POOmonH(){}
	
	@Override
	public Ambiente getAmbienteOriginario()
	{
		return Ambiente.AGUA;
	}
	
	@Override
	public String getNome()
	{
		return "Fusion";
	}
	
	@Override
	public String getHistoria()
	{
		return "Após uma grande batalha entre 2 POOmons lendários que deu origem aos continentes e mares, Fusion foi criado por Arceus e se tornou responsável e protetor dos mares do mundo.";
	}
	
	@Override
	public int getEnergia()
	{
		return energiaVital;
	}
	
	public void setEnergia(int energiaVital)
	{
		this.energiaVital = energiaVital;
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
	public ImageIO getImagem()
	{
		return imagem;
	}
	
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
			danoAtaque -= 0.1;
		}
		
		this.setEnergia((this.getEnergia() - danoAtaque));
	}
	
	@Override
	public void atacar(POOmonComportamento arg0, Ambiente arg1)
	{
	}
	
	@Override
	public void carregar(int arg0)
	{
	}
	
	@Override
	public void setMediador(Mediador arg0)
	{
	}
}
