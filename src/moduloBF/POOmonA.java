package moduloBF;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import moduloBGame.Ambiente;
import moduloBGame.POOmonComportamento;

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

	@Override
	public void atacar(POOmonComportamento arg0, Ambiente arg1) {
		// TODO Auto-generated method stub
		
	}
}
