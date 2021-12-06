package moduloBF;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import moduloBGame.Ambiente;

public class POOmonA extends POOmon
{
	public POOmonA()
	{
		
	}
	
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
	public Image getImagem()
	{
		String pathImagem;
		
		if (getEnergia() <= 0)
			pathImagem = "images/FenixMorto.png";
		else if (getEnergia() <= 150)
			pathImagem = "images/FenixExausto.png";
		else if (getEnergia() <= 350)
			pathImagem = "images/FenixCansado.png";
		else
			pathImagem = "images/FenixSaudavel.png";
		
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
		
		if (getEnergia() > 300)
		{
			tipoAtaque = "Cruel";
			dano = 225;
			consumo = 150;
		}
		else if (getEnergia() > 99)
		{
			tipoAtaque = "Agressivo";
			dano = 99;
			consumo = 99;
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
