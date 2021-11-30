package moduloBF;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import moduloBGame.Ambiente;
import moduloBGame.POOmonComportamento;

public class POOmonT extends POOmon
{
	public POOmonT()
	{
		
	}
	
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
		return "Um POOmon que vivia calmamente embaixo das montanhas no in�cio do mundo. Com a chegada dos humanos nos vilarejos ao redor das montanhas onde vivia, Fellion decidiu cuidar de todo esse ambiente, incluindo a natureza por si s�, os humanos e at� outros POOmons que ali come�aram a viver. Por isso, Fellion tamb�m � considerado um POOmon companheiro e protetor.";
	}

	@Override
	public Image getImagem()
	{
		String pathImagem;
		
		if (getEnergia() <= 0)
			pathImagem = "images/FellionMorto.png";
		else if (getEnergia() <= 150)
			pathImagem = "images/FellionExausto.png";
		else if (getEnergia() <= 350)
			pathImagem = "images/FellionCansado.png";
		else
			pathImagem = "images/FellionSaudavel.png";
		
		Image image = null;
		
		try
		{
			image = ImageIO.read(getClass().getResource(pathImagem));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
	}

	@Override
	public void atacar(Ambiente arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informarOponente(POOmonComportamento arg0) {
		// TODO Auto-generated method stub
		
	}
}
