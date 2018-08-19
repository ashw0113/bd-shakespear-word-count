package org.ashw.hortonworks_word_count;

public class CollectorsObject implements Comparable<CollectorsObject>{
	
	private String randomString = null;
	private Integer count =0;
	
	
	public CollectorsObject(String _1, Integer _2) {
		// TODO Auto-generated constructor stub
		
		this.randomString = _1;
		this.count = _2;
	}
	public String getRandomString() {
		return randomString;
	}
	public void setRandomString(String randomString) {
		this.randomString = randomString;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public int compareTo(CollectorsObject obj) {
		// TODO Auto-generated method stub
		
		if(this.count>obj.count) {
			return 1;
		}else if(this.count<obj.count) {
			return -1;
		}else {
		return 0;
		}
	}
	
	public String toString() {
		return randomString+" : "+count;
	}
}
