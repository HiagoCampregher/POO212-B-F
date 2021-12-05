package moduloBF;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import moduloBGame.Ambiente;

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
		return "Um POOmon que vivia calmamente embaixo das montanhas no início do mundo. Com a chegada dos humanos nos vilarejos ao redor das montanhas onde vivia, Fellion decidiu cuidar de todo esse ambiente, incluindo a natureza por si só, os humanos e até outros POOmons que ali começaram a viver. Por isso, Fellion também é considerado um POOmon companheiro e protetor.";
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
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
		return image;
	}

	@Override
	public void atacar(Ambiente arg0)
	{
		String tipoAtaque = "";
		int dano = 0;
		int consumo = 0;
		
		if (getEnergia() > 240)
		{
			tipoAtaque = "Cruel";
			dano = 180;
			consumo = 120;
		}
		else if (getEnergia() > 140)
		{
			tipoAtaque = "Agressivo";
			dano = 80;
			consumo = 80;
		}	
		else
		{
			tipoAtaque = "Básico";
			dano = 30;
		}
			
		this.setEnergia(this.getEnergia() - consumo);				

		int danoExtra = 0;
		if (arg0 == getAmbienteOriginario())
			danoExtra = (int)(0.2 * dano);
			
		getOponente().receberAtaque((dano + danoExtra), arg0);
		
		escreveLog("Ataque efetuado: " + tipoAtaque + " " + dano + "(" + (dano + danoExtra) + ") – " + arg0 + "(-" + consumo + ")");
	}
}
