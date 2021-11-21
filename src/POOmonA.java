import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import moduloBGame.Ambiente;
import moduloBGame.Mediador;
import moduloBGame.POOmonComportamento;

public class POOmonA implements POOmonComportamento
{
	private Ambiente ambiente;
	private int energiaVital;
	private int qtdVitorias;
	private int maiorEnergiaVital;
	private int qtdAtivacoes;
	private LocalDateTime momentoMaiorEnergiaVital;
	private ImageIO imagem;
	
	public POOmonA(){}
	
	@Override
	public Ambiente getAmbienteOriginario()
	{
		return Ambiente.AR;
	}
	
	@Override
	public String getNome()
	{
		return "Fenix";
	}
	
	@Override
	public String getHistoria()
	{
		return "Após ser deserdado do mundo por seu criador Arceus por seu comportamento agressivo, Fenix volta ao mundo natural de uma forma diferente, buscando mostrar sua compaixão e carisma.";
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
