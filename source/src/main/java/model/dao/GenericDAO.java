package model.dao;

public interface GenericDAO<T, ID> {
	/* Methods */
	
	/* CRUD methods*/
	public void create(T entity);
	
	public T read(ID id);
	
	public void update(T entity);
	
	public void delete(T entity);
	
}
