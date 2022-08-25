package bo;

public class Images {
	private int id;
	private String name;
	private String picture;
	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the picture
	 */
	public final String getPicture() {
		return picture;
	}
	/**
	 * @param picture the picture to set
	 */
	public final void setPicture(String picture) {
		this.picture = picture;
	}
	
	public Images(String name, String picture) {
		this.name = name;
		this.picture = picture;
	}
	public Images(int id, String name, String picture) {
		this(name, picture);
		this.picture = picture;
	}
	
	
	
}
