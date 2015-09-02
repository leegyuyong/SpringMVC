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
 * 설명 : 로그인 체크 안함
 * </pre>
 *
 * @since : 2015. 8. 21.
 * @author : jdh
 * @version : v1.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoLoginCheck {}
