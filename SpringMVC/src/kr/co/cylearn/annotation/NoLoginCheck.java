package kr.co.cylearn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * kr.co.cylearn.annotation
 * NoLoginCheck.java
 *
 * ���� : �α��� üũ ����
 * </pre>
 *
 * @since : 2015. 8. 21.
 * @author : jdh
 * @version : v1.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoLoginCheck {}
