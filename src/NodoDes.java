
public class NodoDes <E extends Comparable<E>> {
	


	private String question;
	private String id;
	private NodoDes<E> yes;
	private NodoDes<E> no;
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NodoDes<E> getYes() {
		return yes;
	}

	public void setYes(NodoDes<E> yes) {
		this.yes = yes;
	}

	public NodoDes<E> getNo() {
		return no;
	}
	
	public void setNo(NodoDes<E> no) {
		this.no = no;
	}

	////------------------Constructores de la clase------------------------------///

	public NodoDes() {
		this.id = null;
		this.no = null;
		this.yes = null;
		this.question = null;
	}
	

	public NodoDes(String question, String id, NodoDes<E> yes, NodoDes<E> no) {
		super();
		this.question = question;
		this.id = id;
		this.yes = yes;
		this.no = no;
	}
	
	public NodoDes(String question, String id) {
		super();
		this.question = question;
		this.id = id;
	}
	
	/////------------------TO String---------------------------------///////////

	@Override
	public String toString() {
		if(yes == null && no == null){
			return ","+id+"@"+question;
		}
		else if(yes == null && no != null){
			return	","+id+"@"+question+no;
		}
		else if(yes != null && no == null){
			return	","+id+"@"+question+yes;
		}
		return	","+id+"@"+question+yes+no;
	}
}
