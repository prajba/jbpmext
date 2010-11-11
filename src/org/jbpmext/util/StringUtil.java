/**
 * 
 */
package org.jbpmext.util;

/**
 * @author weiht
 *
 */
public class StringUtil {
	private StringUtil() {}
	
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s);
	}
	
	public static boolean strEquals(String s1, String s2) {
		if (s1 == s2) return true;
		if (s1 == null || s2 == null) return false;
		return s1.equals(s2);
	}
	
	public static boolean strEqualsIgnoreCase(String s1, String s2) {
		if (s1 == s2) return true;
		if (s1 == null || s2 == null) return false;
		return s1.equalsIgnoreCase(s2);
	}
}
