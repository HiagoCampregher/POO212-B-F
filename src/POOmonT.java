import java.time.LocalDateTime;
import javax.imageio.ImageIO;

import moduloBGame.Ambiente;
import moduloBGame.Mediador;
import moduloBGame.POOmonComportamento;

public class POOmonT implements POOmonComportamento
{
	private Ambiente ambiente;
	private int energiaVital;
	private int qtdVitorias;
	private int maiorEnergiaVital;
	private int qtdAtivacoes;
	private LocalDateTime momentoMaiorEnergiaVital;
	private ImageIO imagem;
	
	public POOmonT(){}
	
	@Override
	public Ambiente getAmbienteOriginario()
	{
		return Ambiente.TERRA;
	}
	
	@Override
	public String getNome()
	{
		return "Fellion";
	}
	
	@Override
	public String getHistoria()
	{
		return "Um POOmon que vivia calmamente embaixo das montanhas no início do mundo. Com a chegada dos humanos nos vilarejos ao redor das montanhas onde vivia, Fellion decidiu cuidar de todo esse ambiente, incluindo a natureza por si só, os humanos e até outros POOmons que ali começaram a viver. Por isso, Fellion também é considerado um POOmon companheiro e protetor.";
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
		//Fazer validação para retornar a imagem correta
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
	public void atacar(POOmonComportamento inimigo, Ambiente objAmbiente)
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
