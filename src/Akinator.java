
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
		if(dir == true){
			this.node = this.node.getYes();
		}
		else{
			this.node = this.node.getNo();
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
		akinator.tree.addQuestion("Esta es la prueba para agregar preguntas", akinator.node);
		System.out.println(akinator.tree.toString());
		System.out.println(akinator.ClimbDown(false));
	}

}
