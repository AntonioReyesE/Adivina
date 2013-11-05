import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Akinator<E extends Comparable <E>> extends JFrame implements ActionListener {
	

	private DesTree<E> tree;
	private NodoDes<E> node;
	private boolean ganar;
	private Image genio, dialogo,fondo;
	private JButton si, no, salir;
	

	public Akinator(DesTree<E> root) {//se tiene que cambiar la direcci�n del file para que sea local
		super();
		this.setTree(root);
		this.getTree().lector("src/memoria.txt");
		this.node = root.getRoot();
		this.ganar = false;
		/////-----Grafico------////
		this.setFocusable(true);
		this.setVisible(true);
		this.setSize(700, 700);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		/////-----Imagenes------////
		this.genio = new ImageIcon("src/genie.png").getImage();
		this.fondo = new ImageIcon("src/cool.jpg").getImage();
		this.dialogo = new ImageIcon("src/talk.png").getImage();
		/////-------Botones--------/////
		this.si = new JButton("SI");
		this.no = new JButton("NO");
		this.salir = new JButton("SALIR");
		this.si.setBounds(0, this.getHeight() - 82, 240, 60);
		this.no.setBounds(240, this.getHeight() - 82, 240, 60);
		this.salir.setBounds(480, this.getHeight() - 82, 220, 60);
		this.add(this.no);
		this.add(this.si);
		this.add(this.salir);
		this.no.addActionListener(this);
		this.si.addActionListener(this);
		this.salir.addActionListener(this);
		this.no.setVisible(true);
		this.si.setVisible(true);
		this.salir.setVisible(true);

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
			if(dir == false){
				this.getTree().addQuestion(new JOptionPane().showInputDialog("No se! :( Cual es el animal?"),new JOptionPane().showInputDialog("Cual seria la pregunta de si/no que tendria que hacer?"),this.node,2);
				this.ganar = true;
				return null;
			}
			else{
				new JOptionPane().showMessageDialog(null, "Adivine! :)");
				this.ganar = true;
				return "Adivine! :)";
			}
		}
		else{
			if(dir == true){//Si el ususario menciono que si
				if(this.node.getYes() == null && this.node.getNo() != null){//Si ha llegado a un nodo que tiene un hijo izquierdo
					System.out.println("No se! :( Cual es el animal?");
					this.getTree().addQuestion(new JOptionPane().showInputDialog("No se! :( Cual es el animal?"),new JOptionPane().showInputDialog("Cual seria la pregunta de si/no que tendria que hacer?"),this.node,1);
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
					this.getTree().addQuestion(new JOptionPane().showInputDialog("No se! :( Cual es el animal?"),new JOptionPane().showInputDialog("Cual seria la pregunta de si/no que tendria que hacer?"),this.node,0);
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
		this.setBackground(Color.DARK_GRAY);
		Font fuente = new Font("Monospaced", Font.BOLD, 15);
		g.setFont(fuente);
		g.setColor(Color.BLUE);
		g.drawImage(this.fondo, 0, 0, this.getWidth(), this.getHeight() -60, null);
		g.drawImage(this.genio, 250, 130,400,500, null);
		g.drawImage(this.dialogo, 120, 20,300,300, null);	
		this.DrawMessage(g);
	}
	
	public void DrawMessage(Graphics g){
		int y = 90;
		int k = 20;
		int w = 0;
		int z = this.node.getQuestion().length();
		for(int i = 0; i < this.node.getQuestion().length(); i++){
				g.drawString(this.node.getQuestion().substring(w, i), 160, y);
				if(i >= k){
					y += 14;
					k += 20;
					w += 20;
				}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Akinator<String> akinator = new Akinator<String>(new DesTree<String>());
		boolean bandera = false;
		while(bandera == false){
			System.out.println("a");
			if(akinator.ganar == true){
				akinator.tree.save();
				System.out.println("b");
				if(new JOptionPane("Deseas volver a jugar?").showConfirmDialog(null, "Desea volver a jugar?") == 0){
					akinator = new Akinator<String>(new DesTree<String>());	
				}
				else{
					bandera = true;
				}
			}
		}
	}


	public DesTree<E> getTree() {
		return tree;
	}


	public void setTree(DesTree<E> tree) {
		this.tree = tree;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.no){
			this.Recorrer(false);
			this.repaint();
		}
		else if(e.getSource() == this.si){
			this.Recorrer(true);
			this.repaint();
		}
		else if(e.getSource() == this.salir){
			this.ganar = true;
			System.exit(0);
		}
	}
}
