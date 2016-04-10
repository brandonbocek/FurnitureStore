package model;

import java.util.ArrayList;

public class Search {

	private int start;
	private int end;
	private int numOfResults=0;
	private int numOfPagesNeeded=1;
	private int pageResultMax=10;
	private String origSrch;
	private boolean showNextIcon;
	
	private ArrayList<Product> results;
	
	public Search(){
		results = new ArrayList<Product>();
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getNumOfResults() {
		numOfResults = results.size();
		return numOfResults;
	}

	public void setNumOfResults(int numOfResults) {
		this.numOfResults = numOfResults;
	}

	public int getNumOfPagesNeeded() {
		
		return numOfPagesNeeded;
	}

	public void setNumOfPagesNeeded(int numOfPagesNeeded) {
		this.numOfPagesNeeded = numOfPagesNeeded;
	}

	public ArrayList<Product> getSearchArray() {
		return results;
	}

	public void setSearchArray(ArrayList<Product> results) {
		this.results = results;
	}
	public Product getAResult(int i) {
		return results.get(i);
	}

	public void addAResult(Product product) {
		results.add(product);
	}

	public int getPageResultMax() {
		return pageResultMax;
	}

	public void setPageResultMax(int pageResultMax) {
		this.pageResultMax = pageResultMax;
	}

	public String getOrigSrch() {
		return origSrch;
	}

	public void setOrigSrch(String origSrch) {
		this.origSrch = origSrch;
	}

	public boolean isShowNextIcon() {
		
		if(((numOfPagesNeeded*5)-5)>(start))
			return true;
		
		return false;
	}

	public void setShowNextIcon(boolean showNextIcon) {
		this.showNextIcon = showNextIcon;
	}
	
}
