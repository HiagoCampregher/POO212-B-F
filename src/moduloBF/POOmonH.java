package moduloBF;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import moduloBGame.Ambiente;
import moduloBGame.POOmonComportamento;

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
		if (getEnergia() < 80)
		{
			if (arg0 == getAmbienteOriginario())
				getOponente().receberAtaque(36, arg0);
			else
				getOponente().receberAtaque(30, arg0);
		}
		else if (getEnergia() > 140)
		{
			if (arg0 == getAmbienteOriginario())
			{
				this.setEnergia(this.getEnergia() - 96);				
				getOponente().receberAtaque(96, arg0);
			}
			else
			{
				this.setEnergia(this.getEnergia() - 80);				
				getOponente().receberAtaque(80, arg0);
			}
		}		
		else if (getEnergia() > 300)
		{
			if (arg0 == getAmbienteOriginario())
			{
				this.setEnergia(this.getEnergia() - 150);				
				getOponente().receberAtaque(255, arg0);
			}
			else
			{
				this.setEnergia(this.getEnergia() - 150);
				getOponente().receberAtaque(225, arg0);
			}
		}
	}

	@Override
	public void informarOponente(POOmonComportamento arg0) {
		setOponente(arg0);
	}
}
