package kr.co.cylearn.interceptor;

import java.util.Collections;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.cylearn.annotation.NoLoginCheck;
import kr.co.cylearn.model.UserSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

public class LoginInterceptor extends HandlerInterceptorAdapter {


	private final static Logger log = Logger.getLogger(LoginInterceptor.class);

	//인터셉터에서 제외할 uri
	protected Set<String> excludeURI = Collections.emptySet();

	@Override
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response , Object handler) throws ModelAndViewDefiningException{

		log.debug("#################### LoginInterceptor preHandle ####################");

		// session check
		UserSession userSession = (UserSession)WebUtils.getSessionAttribute(request, "userSession");
		String requestURI = request.getRequestURI();

		log.debug("requestURI === " + requestURI);

		//페이지 에외처리
		for (String uri : excludeURI) {
			//log.debug("requestURI" + requestURI);
			//log.debug("uri" + uri);
			if (requestURI.indexOf(uri) > -1) {
				return true;
			}
		}

		//어노테이션으로 로그인필요여부 체크
		//지금은 xml 설정으로만 사용한다
		NoLoginCheck usingAuth = ((HandlerMethod) handler).getMethodAnnotation(NoLoginCheck.class);

		if (usingAuth == null){
			if(userSession == null || userSession.getUserid() == null || userSession.getUserid().equals(""))
			{
				//ModelAndView mav = new ModelAndView("redirect:/errors/common_error");
				ModelAndView mav = new ModelAndView("errors_msg_uri");
				mav.addObject("msg", "로그인 후 이용해주세요");
				mav.addObject("uri", "/login/gate");

				throw new ModelAndViewDefiningException(mav);
			}
		}
		/*if(!userSession.getIsMonitor().equals("Y")){
			if (requestURI.indexOf("/myclass/eduProgressCheck.do") != 0 ){ //진도체크는 확인 안함  , 모니터닝 아이디 확인안함
				///myclass/eduProgressCheck.do
				//중복로그인 체크 2014-11-03 dogbag
				int result =  loginService.checkSessionInfo(request);

				if(result > 0){
					throw new ModelAndViewDefiningException(new ModelAndView("redirect:/login.do?overlap=Y"));
				}
			}
		}*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		closeResource();
	}

	protected void closeResource() {
		try {
		} catch (Exception ignore) {
		}
	}

	public void setExcludeURI(Set<String> excludeURI) {
		this.excludeURI = excludeURI;
	}

	public Set<String> getExcludeURI() {
		return excludeURI;
	}

}
