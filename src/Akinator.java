import javax.swing.JOptionPane;


public class Akinator<E extends Comparable <E>> {
	

	private DesTree<E> tree;
	private NodoDes<E> node;
	
	public Akinator(DesTree<E> root) {//se tiene que cambiar la direcci—n del file para que sea local
		super();
		this.tree = root;
		this.tree.lector("/Users/antonio/Documents/Quinto semestre/Estructura de datos/Workspace 5to Semestre/Adivina/memoria");
		this.node = root.getRoot();
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
	public String ClimbDown(boolean dir ){
		if(this.node.getNo() == null && this.node == null){//Verifica si ha llegado a una hoja
			this.tree.addQuestion(new JOptionPane().showInputDialog("Àcual que es la nueve respuesta?"),new JOptionPane().showInputDialog("Àcual que es la nueva pregunta?"),this.node,2);
			return this.node.getQuestion();
		}
		else{
			if(dir == true){
				if(this.node.getYes() == null && this.node.getNo() != null){//Si ha llegado a un nodo que tiene un hijo izquierdo
					System.out.println("No se :( me puedes decir la respuesta?");
					this.tree.addQuestion(new JOptionPane().showInputDialog("Àcual que es la nueve respuesta?"),new JOptionPane().showInputDialog("Àcual que es la nueva pregunta?"),this.node,1);
				}
				else{
					this.node = this.node.getYes();
				}
			}
			else{
				if(this.node.getNo() == null && this.node.getYes() != null){//Si ha llegado a un nodo que tiene un hijo derecho
					System.out.println("No se :( me puedes decir la respuesta?");
					this.tree.addQuestion(new JOptionPane().showInputDialog("Àcual que es la nueve respuesta?"),new JOptionPane().showInputDialog("Àcual que es la nueva pregunta?"),this.node,0);
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
		System.out.println(akinator.tree.toString());
		
	}

}
