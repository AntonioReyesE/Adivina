import javax.swing.JOptionPane;


public class Akinator<E extends Comparable <E>> {
	

	private DesTree<E> tree;
	private NodoDes<E> node;
	
	public Akinator(DesTree<E> root) {
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
		if(this.node.getNo() == null && this.node == null){
			this.tree.addQuestion(new JOptionPane().showInputDialog("Àcual que es la nueve respuesta?"),new JOptionPane().showInputDialog("Àcual que es la nueva pregunta?"),this.node,2);
			return this.node.getQuestion();
		}
		else{
			if(dir == true){
				if(this.node.getYes() == null){
					System.out.println("No se :( me puedes decir la respuesta?");
					this.tree.addQuestion(new JOptionPane().showInputDialog("Àcual que es la nueve respuesta?"),new JOptionPane().showInputDialog("Àcual que es la nueva pregunta?"),this.node,1);
				}
				else{
					this.node = this.node.getYes();
				}
			}
			else{
				if(this.node.getNo() == null){
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
		//akinator.tree.lector("/Users/antonio/Documents/Quinto semestre/Estructura de datos/Workspace 5to Semestre/Adivina/memoria");
		System.out.println(akinator.tree.toString());
		System.out.println(akinator.ClimbDown(false));
		System.out.println(akinator.ClimbDown(false));
		System.out.println(akinator.ClimbDown(true));
		//akinator.tree.addQuestion("Esta es la prueba para agregar preguntas", akinator.node);
		System.out.println(akinator.tree.toString());
		System.out.println(akinator.ClimbDown(false));
		System.out.println(akinator.ClimbDown(false));
		
	}

}
