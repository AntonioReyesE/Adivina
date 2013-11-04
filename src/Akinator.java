import javax.swing.JOptionPane;


public class Akinator<E extends Comparable <E>> {
	

	private DesTree<E> tree;
	private NodoDes<E> node;
	private boolean ganar;
	

	public Akinator(DesTree<E> root) {//se tiene que cambiar la direcci�n del file para que sea local
		super();
		this.tree = root;
		this.tree.lector("/Users/antonio/Documents/Quinto semestre/Estructura de datos/Workspace 5to Semestre/Adivina/memoria");
		this.node = root.getRoot();
		this.ganar = false;
	}

	
	public boolean isGanar() {
		return ganar;
	}


	public void setGanar(boolean ganar) {
		this.ganar = ganar;
	}



	public DesTree<E> getRoot() {
		return tree;
	}

	public void setRoot(DesTree<E> root) {
		this.tree = root;
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
				this.tree.addQuestion(new JOptionPane().showInputDialog("�No se! :( �Cu�l es el animal?"),new JOptionPane().showInputDialog("�Cu�l ser�a la pregunta de si/no que tendr�a que hacer?"),this.node,2);
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
					this.tree.addQuestion(new JOptionPane().showInputDialog("�No se! :( �Cu�l es el animal?"),new JOptionPane().showInputDialog("�Cu�l ser�a la pregunta de si/no que tendr�a que hacer?"),this.node,1);
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
					this.tree.addQuestion(new JOptionPane().showInputDialog("�No se! :( �Cu�l es el animal?"),new JOptionPane().showInputDialog("�Cu�l ser�a la pregunta de si/no que tendr�a que hacer?"),this.node,0);
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


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Akinator<String> akinator = new Akinator<String>(new DesTree<String>());
		while(akinator.ganar == false){
			if(new JOptionPane().showConfirmDialog(null, akinator.node.getQuestion()) == 1){
				System.out.println(akinator.Recorrer(false));
			}
			else{
				System.out.println(akinator.Recorrer(true));
			}
			System.out.println(akinator.tree.toString());
		}	
	}
}
