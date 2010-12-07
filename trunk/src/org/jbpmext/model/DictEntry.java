/**
 * 
 */
package org.jbpmext.model;

/**
 * @author weiht
 *
 */
public class DictEntry {
	private Integer id;
	private int usableStatus;
	private String key;
	private Integer intValue;
	private String stringValue;
	private Object value;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getUsableStatus() {
		return usableStatus;
	}
	public void setUsableStatus(int usableStatus) {
		this.usableStatus = usableStatus;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public Integer getIntValue() {
		return intValue;
	}
	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
		this.value = intValue;
	}
	
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
		this.value = stringValue;
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
		if (value != null) {
			stringValue = value.toString();
			try {
				intValue = Integer.valueOf(stringValue);
			} catch (NumberFormatException ex) {
			}
		} else {
			stringValue = null;
			intValue = null;
		}
	}
}
