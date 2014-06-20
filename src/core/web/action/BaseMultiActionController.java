package core.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.view.RedirectView;

public class BaseMultiActionController extends MultiActionController {
	protected Log logger = LogFactory.getLog(BaseMultiActionController.class);
	protected String templateView;
	protected String mainView;

	public ModelAndView toTemplateView(HttpServletRequest request, String page) {
		request.setAttribute("ContentPage", page);
		return new ModelAndView(this.templateView);
	}

	public ModelAndView toTemplateView(String page) {
		return new ModelAndView(page);
	}

	public ModelAndView toTemplateViewGenSid(HttpServletRequest request, String sid) {
		String page = null;
		try {
			String methodname = getMethodNameResolver().getHandlerMethodName(request);
			page = this.mainView + methodname + ".jsp";
		} catch (NoSuchRequestHandlingMethodException e) {
			e.printStackTrace();
			page = this.mainView + request.getParameter("method") + ".jsp";
		}
		request.setAttribute("ContentPage", page);
		return new ModelAndView(this.templateView);
	}

	public ModelAndView toTemplateView(HttpServletRequest request) {
		String page = null;
		try {
			String methodname = getMethodNameResolver().getHandlerMethodName(request);
			page = this.mainView + methodname + ".jsp";
		} catch (NoSuchRequestHandlingMethodException e) {
			e.printStackTrace();
			page = this.mainView + request.getParameter("method") + ".jsp";
		}
		request.setAttribute("ContentPage", page);
		return new ModelAndView(this.templateView);
	}

	public ModelAndView toTemplateViewByMethodName(HttpServletRequest request, String methodName) {
		String page = this.mainView + methodName + ".jsp";
		request.setAttribute("ContentPage", page);
		return new ModelAndView(this.templateView);
	}

	public ModelAndView toRedirectView(String link) {
		return new ModelAndView(new RedirectView(link));
	}

	public void setTemplateView(String templateView) {
		this.templateView = templateView;
	}

	public void setMainView(String mainView) {
		this.mainView = mainView;
	}
}