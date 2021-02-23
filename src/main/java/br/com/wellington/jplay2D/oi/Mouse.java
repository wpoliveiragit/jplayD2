package br.com.wellington.jplay2D.oi;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import br.com.wellington.jplay2D.imageProcessing.GameObject;

/**
 * Classe responsável por controlar as ações do mouse.
 */
public class Mouse extends InputBase implements MouseMotionListener, MouseListener {

	private Point mousePosition;

	private InputAction leftButton;
	private InputAction middleButton;
	private InputAction rightButton;

	public static final int BUTTON_LEFT = 1;
	public static final int BUTTON_MIDDLE = 2;
	public static final int BUTTON_RIGHT = 3;

	/**
	 * Construtor de classe. Cria uma instância do mouse posicionada em (x = 0, y =
	 * 0). O comportamento dos botões (direito, meio, esquerdo) é
	 * DECTECT_INITIAL_PRESS_ONLY.
	 */
	public Mouse() {
		mousePosition = new Point(0, 0);

		// Adicione os botões padrão
		leftButton = new InputAction(InputBase.DETECT_INITIAL_PRESS_ONLY);
		middleButton = new InputAction(InputBase.DETECT_INITIAL_PRESS_ONLY);
		rightButton = new InputAction(InputBase.DETECT_INITIAL_PRESS_ONLY);
	}

	/**
	 * Retorna a posição do mouse na tela.
	 * 
	 * @return Point
	 */
	public Point getPosition() {
		return mousePosition;
	}

	/**
	 * Retorna verdadeiro se o botão esquerdo for pressionado, caso contrário,
	 * falso.
	 * 
	 * @return boolean
	 */
	public boolean isLeftButtonPressed() {
		return leftButton.isPressed();
	}

	/**
	 * Retorna verdadeiro se o botão do meio é pressionado; caso contrário, é falso.
	 * 
	 * @return boolean
	 */
	public boolean isMiddleButtonPressed() {
		return middleButton.isPressed();
	}

	/**
	 * Retorna verdadeiro se o botão direito for pressionado, caso contrário, falso.
	 * 
	 * @return boolean
	 */
	public boolean isRightButtonPressed() {
		return rightButton.isPressed();
	}

	/**
	 * Define o comportamento do mouse.
	 * 
	 * @param numberBotton ele representa o botão do mouse e pode ser BOTÃO
	 *                     ESQUERDO, BOTÃO MEIO ou BOTÃO DIREITO.
	 * @param behabior     pode ser DETECT_EVERY_PRESS ou DETECT_INITIAL_PRESS_ONLY.
	 */
	public void setBehavior(int numberBotton, int behavior) {
		switch (numberBotton) {
		case BUTTON_LEFT:
			leftButton.setBehavior(behavior);
			break;
		case BUTTON_MIDDLE:
			middleButton.setBehavior(behavior);
			break;
		case BUTTON_RIGHT:
			rightButton.setBehavior(behavior);
			break;
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			leftButton.press();
			break;
		case MouseEvent.BUTTON2:
			middleButton.press();
			break;
		case MouseEvent.BUTTON3:
			rightButton.press();
			break;
		}
	}

	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			leftButton.release();
			break;
		case MouseEvent.BUTTON2:
			middleButton.release();
			break;
		case MouseEvent.BUTTON3:
			rightButton.release();
			break;
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		mousePosition = e.getPoint();
	}

	public void mouseMoved(MouseEvent e) {
		mousePosition = e.getPoint();
	}

	/**
	 * Retorna verdadeiro se o mouse estiver sobre um objeto; caso contrário, retorna falso.
	 * 
	 * @param obj qualquer GameObject.
	 * @return boolean
	 */

	public boolean isOverObject(GameObject obj) {
		Point min = new Point((int) obj.x, (int) obj.y);
		Point max = new Point((int) (obj.x + obj.width), (int) (obj.y + obj.height));
		return isOverArea(min, max);
	}

	/**
	 * Retorna verdadeiro se o mouse estiver sobre um objeto; caso contrário, retorna falso.
	 * 
	 * @param start ponto inicial da área.
	 * @param end   ponto final da área.
	 * @return boolean
	 */
	public boolean isOverArea(Point start, Point end) {
		return isOverArea(start.x, start.y, end.x, end.y);
	}

	/**
	 * Retorna verdadeiro se o mouse estiver sobre uma área, caso contrário retorna falso
	 * 
	 * @minX minus valor dos eixos X.
	 * @minY minus valor dos eixos Y.
	 * @maxX minus valor dos eixos X.
	 * @maxX minus valor dos eixos Y.
	 * @return boolean
	 */
	public boolean isOverArea(int minX, int minY, int maxX, int maxY) {
		if ((mousePosition.x < minX) || (mousePosition.x > maxX))
			return false;

		if ((mousePosition.y < minY) || (mousePosition.y > maxY))
			return false;

		return true;
	}

}
