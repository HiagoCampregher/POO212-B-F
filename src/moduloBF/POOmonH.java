package moduloBF;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import moduloBGame.Ambiente;

public class POOmonH extends POOmon
{
	public POOmonH()
	{
	}

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
	public Image getImagem()
	{
		String pathImagem;
		
		if (getEnergia() <= 0)
			pathImagem = "images/FusionMorto.png";
		else if (getEnergia() <= 150)
			pathImagem = "images/FusionExausto.png";
		else if (getEnergia() <= 350)
			pathImagem = "images/FusionCansado.png";
		else
			pathImagem = "images/FusionSaudavel.png";
		
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
