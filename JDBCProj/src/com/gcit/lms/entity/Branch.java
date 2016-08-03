package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;

public class Branch implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -98270711454950637L;

	
	
	private Integer branchId;
	private String branchName;
	private String branchAddres;
	private List <Book> books;
	
	/**
	 * @return the noOfCopies
	 */

	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddres() {
		return branchAddres;
	}
	public void setBranchAddres(String branchAddres) {
		this.branchAddres = branchAddres;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((branchAddres == null) ? 0 : branchAddres.hashCode());
		result = prime * result
				+ ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result
				+ ((branchName == null) ? 0 : branchName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		if (branchAddres == null) {
			if (other.branchAddres != null)
				return false;
		} else if (!branchAddres.equals(other.branchAddres))
			return false;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchName=" + branchName
				+ ", branchAddres=" + branchAddres + "]";
	}
	
	
	


	

}
