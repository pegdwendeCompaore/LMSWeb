package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.gcit.lms.entity.Branch;
@SuppressWarnings("unchecked")
public class BranchDAO extends BaseDAO{

	public BranchDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addBranch(Branch branch) throws SQLException
		{
			save("insert into tbl_branch (branchName, branchAddress) values (?,?)", new Object []{branch.getBranchName(), branch.getBranchAddres()});
		}
		public Integer addBranchWithId(Branch branch) throws SQLException
		{
			return saveWithID("insert into tbl_branch (branchName, branchAddress) values (?,?)", new Object []{branch.getBranchName(), branch.getBranchAddres()});
		}
		public void updateBranch(Branch branch) throws SQLException {
			
			save("update tbl_Branch set branchName = ?, branchAddress = ? where branchId = ?",
					new Object[] { branch.getBranchName(), branch.getBranchAddres(), branch.getBranchId() });
		}
	
		public void deleteBranch(Branch branch) throws SQLException {
			save("delete from tbl_branch join tbl_book_loans on tbl_book_loans.branchId = tbl_branch.branchId"
					+ "and branchId = ? and tbl_book_loans.dateIn is not null ", new Object[] { branch.getBranchId()});
		}
//		public void addBookCopies(Branch branch) throws SQLException {
//			for(Book b: branch.getBooks()){
//				save("insert into tbl_book_copies (bookId, branchId,noOfCopies) values (?, ?,?)", new Object[] { b.getBookId(), branch.getBranchId(), branch.getNoOfCopies() });
//			}
//		}
		
		public List<Branch> readAllBranch() throws SQLException {
			return read("select * from tbl_branch", null);
		}
	
		
		
		@Override
		public List<Branch> extractData(ResultSet rs) {
			List<Branch> branch = new ArrayList<Branch>();
			BookDAO bdao = new BookDAO(conn);
			try {
				while (rs.next()) {
					Branch b = new Branch();
					b.setBranchId(rs.getInt("branchId"));
					b.setBranchName("branchName");
					b.setBranchAddres("branchAddress");
					b.setBooks(bdao.readFirstLevel(
							"select * from tbl_book where bookId IN (select bookId from tbl_book_copies where branchId = ? and noOfCopies >0))",
							new Object[] {b.getBranchId()}));
					branch.add(b);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return branch;
		}
	
		@Override
		public List<Branch> extractDataFirstLevel(ResultSet rs) {
			List<Branch> branch = new ArrayList<Branch>();
			try {
				while (rs.next()) {
					Branch b = new Branch();
					b.setBranchId(rs.getInt("branchId"));
					b.setBranchName(rs.getString("branchName"));
					branch.add(b);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return branch;
		}

}
