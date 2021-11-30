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
	public void atacar(POOmonComportamento arg0, Ambiente arg1)
	{
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
	public void atacar(Ambiente arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informarOponente(POOmonComportamento arg0) {
		// TODO Auto-generated method stub
		
	}
}
