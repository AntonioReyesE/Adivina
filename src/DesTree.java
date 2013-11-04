import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class DesTree <E extends Comparable<E>> {
	
	@Override
	public String toString() {
		return "DesTree [root=" + root.toString() + "]";
	}


	private NodoDes<E> root;
	
	public NodoDes<E> getRoot() {
		return root;
	}

	public void setRoot(NodoDes<E> root) {
		this.root = root;
	}

	public DesTree() {
		this.root = null;
	}
	
	public DesTree(NodoDes<E> root) {
		super();
		this.root = root;
	}

	////////////---------------Insertar en el arbol------------------///////////
	
	public boolean insertD(String dato, String camino){
		NodoDes<E> parent = this.root;
		NodoDes<E> current = parent;
		NodoDes<E> nuevo = new NodoDes<E>(dato, camino);
		int i = 0;
		boolean lado = false;
		if(this.root == null){
			this.root = nuevo;
			return true;
		}
		while(current != null){
			while(i < camino.length()){
				parent = current;
				if(camino.charAt(i) == '-'){
					if(current.getNo() == null){
						parent.setNo(nuevo);
						return true;
					}
					else{
						current = current.getNo();
						lado = false;
					}
				}
				else{
					if(current.getYes() == null){
						parent.setYes(nuevo);
						return true;
					}
					else{
						current = current.getYes();
						lado = true;
					}
				}
				i++;
			}
		}
		if(!lado){
			parent.setNo(nuevo);
		}
		else{
			parent.setYes(nuevo);
		}
		return true;
	}
	
	///////////---------------Construye el arbol a partir de un archivo de texto-------////
	
	public void lector(String archivo){
		try{
			FileReader a = new FileReader(archivo);
			BufferedReader lector = new BufferedReader(a);
			String line;
			String frase;
			String camino;
			String ref;
			while((line = lector.readLine()) != null){
				StringTokenizer st = new StringTokenizer(line, ",");
				while(st.hasMoreTokens()){
					frase = st.nextToken();
					StringTokenizer st2 = new StringTokenizer(frase, "@");
					while(st2.hasMoreTokens()){
						camino = st2.nextToken();
						ref = st2.nextToken();
						this.insertD(ref, camino);
						//System.out.println(ref);
						//System.out.println(camino);
					}
				}
			}
			lector.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	////////////------------Agrega una pregunta--------------------//////////
	public void addQuestion(String r, String q, NodoDes<E> a, int n){
		if(n == 0){///Caso en el que no tiene hoja izquierda
			NodoDes<E> nuevo = new NodoDes<E>(q,a.getId()+"-");
			NodoDes<E> nuevo2 = new NodoDes<E>(r,nuevo.getId()+"+");
			nuevo.setYes(nuevo2);
			a.setNo(nuevo);
		}
		else if(n == 1){///Caso en el que no tiene hoja derecha
			NodoDes<E> nuevo = new NodoDes<E>(q,a.getId()+"+");
			NodoDes<E> nuevo2 = new NodoDes<E>(r,nuevo.getId()+"+");
			nuevo.setYes(nuevo2);
			a.setYes(nuevo);
		}
		else{///Caso en el que el nodo sea una hoja//
			NodoDes<E> nuevo = new NodoDes<E>(a.getQuestion(),a.getId()+"-");
			NodoDes<E> nuevo2 = new NodoDes<E>(r,a.getId()+"+");
			a.setQuestion(q);
			a.setNo(nuevo);
			a.setYes(nuevo2);
		}

	}
}
