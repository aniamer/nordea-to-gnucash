package priv.nordea.csv;

public interface INordeaCSVConverter<T,U> {
	public T parseNordeaRow(U row);
}
