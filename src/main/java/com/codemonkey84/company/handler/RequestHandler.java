/**
 * 
 */
package com.codemonkey84.company.handler;

import java.util.Map;

import com.codemonkey84.company.domain.Answer;
import com.codemonkey84.company.domain.Validable;

/**
 * @author lenovo
 *
 */
public interface RequestHandler <V extends Validable> {

	Answer process(V value, Map<String, String> urlParams);
}
