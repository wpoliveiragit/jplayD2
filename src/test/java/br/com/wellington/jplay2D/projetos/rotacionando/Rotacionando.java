package br.com.wellington.jplay2D.projetos.rotacionando;

import br.com.wellington.jplay2D.GameImage;
import br.com.wellington.jplay2D.Keyboard;
import br.com.wellington.jplay2D.Physics;
import br.com.wellington.jplay2D.Sprite;
import br.com.wellington.jplay2D.Window;
import br.com.wellington.jplay2D.utils.Constantes;

public class Rotacionando implements Constantes {

	public static void main(String[] args) {

		Window win = new Window(800, 600);
		GameImage fundo = new GameImage(ROTACIONANDO_FUNDO);

		Physics fisica = new Physics();
		fisica.createWorld(800, 600);

		Sprite quadrado = new Sprite(ROTACIONANDO_SQUARE);

		fisica.createBodyFromSprite(quadrado, false);

		quadrado.setX(200);
		quadrado.setY(0);

		Sprite ground = new Sprite(ROTACIONANDO_GROUND);
		fisica.createBodyFromSprite(ground, true);

		ground.setY(500);
		ground.setFriction(0);

		Sprite spr = new Sprite(ROTACIONANDO_SPRITE);
		fisica.createBodyFromSprite(spr, true);

		// Lembrando que o método setRotation deve ser chamada antes dos metódos setX
		// e setY.
		// Os metódos setX e setY devem ser chamados mesmo que suas posições sejam
		// 0,0.

		spr.setRotation(Math.PI / 4);
		spr.setX(100);
		spr.setY(100);

		Sprite spr2 = new Sprite(ROTACIONANDO_SPRITE);
		fisica.createBodyFromSprite(spr2, true);

		spr2.setRotation((Math.PI * 135) / 180);
		spr2.setX(300);
		spr2.setY(300);

		// Abaixo são apenas sprites que serão os limites da tela,
		// Dessa forma, a colisão entre o quadrado e os limites são executados.

		Sprite boundX = new Sprite(ROTACIONANDO_LIMITE);
		fisica.createBodyFromSprite(boundX, true);

		Sprite boundY = new Sprite(ROTACIONANDO_LIMITE);
		fisica.createBodyFromSprite(boundY, true);
		boundY.setRotation(Math.PI / 2);
		boundY.setX(-375);
		boundY.setY(300);

		Sprite boundY2 = new Sprite(ROTACIONANDO_LIMITE);
		fisica.createBodyFromSprite(boundY2, true);
		boundY2.setRotation(Math.PI / 2);
		boundY2.setX(375);
		boundY2.setY(300);

		while (true) {

			fundo.draw();
			// Não é necessário agora setar como 'true' no último parâmetro deste
			// metódo, pois os sprites boundX, boundY e boundY2
			// já são encarregados de limitar o quadrado dentro da tela.

			quadrado.applyForceXFromKeyboard(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY, 1, Keyboard.DETECT_EVERY_PRESS,
					false);
			quadrado.applyForceYFromKeyboard(Keyboard.UP_KEY, Keyboard.DOWN_KEY, 50, Keyboard.DETECT_EVERY_PRESS,
					false);
			// Se apertar a tecla ESC, sai da tela inicial.
			if (win.getKeyboard().keyDown(Keyboard.ESCAPE_KEY))
				break;

			fisica.update();
			win.update();
			win.delay(12);
		}
		win.exit();
	}
}
