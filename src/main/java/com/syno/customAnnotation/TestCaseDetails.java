package com.syno.customAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.syno.enums.TestCaseCategoryType;

/**
 * @author trivetim
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestCaseDetails {

	public String testCaseID() default "";

	public String testCaseTitle() default "";

	public TestCaseCategoryType[] category();

}