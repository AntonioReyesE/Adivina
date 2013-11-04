import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Akinator<E extends Comparable <E>> extends JFrame implements MouseListener {
	

	private DesTree<E> tree;
	private NodoDes<E> node;
	private boolean ganar;
	private Image genio, feliz, triste, dialogo,fondo;
	

	public Akinator(DesTree<E> root) {//se tiene que cambiar la direcci�n del file para que sea local
		super();
		this.setTree(root);
		this.getTree().lector("src/memoria");
		this.node = root.getRoot();
		this.ganar = false;
		/////-----Grafico------////
		this.setFocusable(true);
		this.setVisible(true);
		this.setSize(700, 700);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		/////-----Imagenes------////
		this.genio = new ImageIcon("src/genie.png").getImage();
		this.fondo = new ImageIcon("src/cool.jpg").getImage();
		this.dialogo = new ImageIcon("src/talk.png").getImage();
	}

	
	public boolean isGanar() {
		return ganar;
	}


	public void setGanar(boolean ganar) {
		this.ganar = ganar;
	}



	public DesTree<E> getRoot() {
		return getTree();
	}

	public void setRoot(DesTree<E> root) {
		this.setTree(root);
	}


	public NodoDes<E> getNode() {
		return node;
	}


	public void setNode(NodoDes<E> node) {
		this.node = node;
	}


	////////-------------Recorre el arbol------------------------////////////
	public String Recorrer(boolean dir ){
		if(this.node.getNo() == null && this.node.getYes() == null){   //Verifica si ha llegado a una hoja
			System.out.println("Entre a nodo raiz");
			if(/*new JOptionPane().showConfirmDialog(null, this.node.getQuestion()) == 1*/ dir == false){
				System.out.println("entre al if");
				this.getTree().addQuestion(new JOptionPane().showInputDialog("�No se! :( �Cu�l es el animal?"),new JOptionPane().showInputDialog("�Cu�l ser�a la pregunta de si/no que tendr�a que hacer?"),this.node,2);
				this.ganar = true;
				return null;
			}
			else{
				new JOptionPane().showMessageDialog(null, "�Adivin�!");
				this.ganar = true;
				return null;
			}
		}
		else{
			if(dir == true){//Si el ususario menciono que si
				if(this.node.getYes() == null && this.node.getNo() != null){//Si ha llegado a un nodo que tiene un hijo izquierdo
					System.out.println("�No se! :( �Cu�l es el animal?");
					this.getTree().addQuestion(new JOptionPane().showInputDialog("�No se! :( �Cu�l es el animal?"),new JOptionPane().showInputDialog("�Cu�l ser�a la pregunta de si/no que tendr�a que hacer?"),this.node,1);
					this.ganar = true;
					return this.node.getQuestion();
				}
				else{
					this.node = this.node.getYes();
				}
			}
			else{
				if(this.node.getNo() == null && this.node.getYes() != null){//Si ha llegado a un nodo que tiene un hijo derecho
					System.out.println("No se :( me puedes decir la respuesta?");
					this.getTree().addQuestion(new JOptionPane().showInputDialog("�No se! :( �Cu�l es el animal?"),new JOptionPane().showInputDialog("�Cu�l ser�a la pregunta de si/no que tendr�a que hacer?"),this.node,0);
					this.ganar = true;
					return this.node.getQuestion();
				}
				else{
					this.node = this.node.getNo();
				}
			}
		}

		return this.node.getQuestion();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		this.setBackground(Color.WHITE);
        Font fuente = new Font("Monospaced", Font.BOLD, 15);
        g.setFont(fuente);
		g.setColor(Color.BLUE);
		g.drawImage(this.fondo, 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(this.genio, 250, 130,400,500, null);
		g.drawImage(this.dialogo, 120, 20,300,300, null);
		g.drawString(this.node.getQuestion(), 135, 120);
//		if(this.palabra == null){
//			g.drawString("Favor de ingresar una palabra", 150, 300);
//		}
//		else{
//			if(this.aceptacion == false){
//				g.drawString("La palabra ingresada no es aceptada", 150, 300);
//				g.drawImage(this.mal, 200, 350, null);
//			}
//			else{
//				g.drawString("La palabra ingresada es aceptada", 150, 300);
//				g.drawImage(this.bien, 270, 350, null);
//			}
//		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Akinator<String> akinator = new Akinator<String>(new DesTree<String>());
		akinator.repaint();
		while(akinator.ganar == false){
			if(new JOptionPane("Akinator 1.0").showConfirmDialog(null, akinator.node.getQuestion()) == 1){
				System.out.println(akinator.Recorrer(false));
			}
			else{
				System.out.println(akinator.Recorrer(true));
			}
			System.out.println(akinator.getTree().toString());
			akinator.repaint();
		}	
	}


	public DesTree<E> getTree() {
		return tree;
	}


	public void setTree(DesTree<E> tree) {
		this.tree = tree;
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
