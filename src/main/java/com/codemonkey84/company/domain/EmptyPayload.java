/**
 * 
 */
package com.codemonkey84.company.domain;

/**
 * @author lenovo
 *
 */
public class EmptyPayload implements Validable {

	/* (non-Javadoc)
	 * @see com.codemonkey84.company.domain.Validable#isValid()
	 */
	@Override
	public boolean isValid() {
		return true;
	}

}
