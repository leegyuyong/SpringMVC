package kr.co.cylearn.controller.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.cylearn.annotation.NoLoginCheck;
import kr.co.cylearn.common.base.BaseController;
import kr.co.cylearn.common.util.PageUtil;
import kr.co.cylearn.model.FileForm;
import kr.co.cylearn.model.ResultHashMap;
import kr.co.cylearn.service.BdsService;
import kr.co.cylearn.service.BoardService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * kr.co.cylearn.controller.web
 * BoardController.java
 *
 * ���� : �Խ���
 * ����ڷ��
 * �����ڿ���
 * ���������Խ���
 * LCMS�������
 * �����������
 * ��ڰԽ���
 * CP �Խ���
 * CP �ڷ��
 * ������ -�����Խ���/�����Խ���/�ڷ�Խ���
 * �����׷캰 - �����Խ���/QnA/�ڷ��
 * ���հԽ���

 * </pre>
 *
 * @since : 2015. 7. 10.
 * @author : jdh
 * @version : v1.0
 */
@Controller
@RequestMapping("/board")
public class BoardController extends BaseController {
	private final static Logger log = Logger.getLogger(BoardController.class);

	@Autowired
	BoardService boardService;

	@Autowired
	BdsService bdsService;

	/**
	 * <pre>
	 * ���� : �Խ��� ���
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 10. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 10.
	 * @author : jdh
	 * @param map
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/list/{tabseq}")
	//@NoLoginCheck
	public String list(@PathVariable("tabseq") String tabseq, @RequestParam Map<String, Object> map, ModelMap model, HttpSession session) {

		//map.put("grcode", super.getGrCode(session));

		map.put("tabseq", tabseq);

		ResultHashMap resultModel = boardService.getList(map);

		Map<String, Object> bdsMap = (Map<String, Object>)resultModel.get("bds");

		//�α��� ���� üũ
		if ("Y".equals(bdsMap.get("ISLOGIN_LIST").toString()) && "".equals(super.getUserId(session))) {
			model.addAttribute("msg", "�α��� �� �̿����ּ���");
			model.addAttribute("uri", "/login/gate");
			return "errors_msg_uri";
		}

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAttribute("bds", resultModel.get("bds"));
		model.addAttribute("list", resultModel.getList("list"));
		model.addAttribute("pageUtil", resultModel.get("pageUtil"));
		model.addAttribute("pagingStr", ((PageUtil)resultModel.get("pageUtil")).printPage("goPage"));
		model.addAllAttributes(map);

		return "board_list";
	}

	/**
	 * <pre>
	 * ���� : �Խ��� �� ��ȸ
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 4. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 *
	 * @since : 2015. 6. 4.
	 * @author : jdh
	 * @param map
	 * @param model
	 * @return
	 */
	@RequestMapping("/one/{tabseq}/{seq}")
	public String one(@PathVariable("tabseq") String tabseq, @PathVariable("seq") String seq, @RequestParam Map<String, Object> map, ModelMap model, HttpSession session) {

		map.put("userid", super.getUserId(session));
		map.put("tabseq", tabseq);
		map.put("seq", seq);

		ResultHashMap resultModel = boardService.getOne(map);

		Map<String, Object> bdsMap = (Map<String, Object>)resultModel.get("bds");

		//�α��� ���� üũ
		if ("Y".equals(bdsMap.get("ISLOGIN_DETAIL").toString()) && "".equals(super.getUserId(session))) {
			model.addAttribute("msg", "�α��� �� �̿����ּ���");
			model.addAttribute("uri", "/login/gate");
			return "errors_msg_uri";
		}

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAttribute("bds", resultModel.get("bds"));
		model.addAttribute("map", resultModel.get("map"));
		model.addAttribute("fileList", resultModel.get("fileList"));
		model.addAllAttributes(map);

		return "board_one";
	}


	/**
	 * <pre>
	 * ���� : �Խ��� �Է� ��
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 8. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 8.
	 * @author : jdh
	 * @param map
	 * @param model
	 * @return
	 */
	@RequestMapping("/create_form")
	public String createForm(@RequestParam Map<String, Object> map, ModelMap model) {

		//�Խ���(BDS) ����
		ResultHashMap resultModel = bdsService.getOne(map);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAttribute("bds", resultModel.get("bds"));
		model.addAllAttributes(map);

		return "board_create_form";
	}

	/**
	 * <pre>
	 * ���� : �Խ��� ���
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 15. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 15.
	 * @author : jdh
	 * @param map
	 * @param fileForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/create")
	public String create(@RequestParam Map<String, Object> map, FileForm fileForm, ModelMap model, HttpSession session) {


		map.put("userid", super.getUserId(session));
		map.put("name", super.getName(session));

		ResultHashMap resultModel = boardService.create(map, fileForm);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		//model.addAllAttributes(map);

		return "redirect:/board/list/" + map.get("tabseq");
	}

	/**
	 * <pre>
	 * ���� : �亯 �Է� ��
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 25. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 8. 25.
	 * @author : jdh
	 * @param map
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/create_reply_form")
	public String createReplyForm(@RequestParam Map<String, Object> map, ModelMap model, HttpSession session) {


		map.put("userid", super.getUserId(session));

		ResultHashMap resultModel = boardService.getOne(map);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAttribute("bds", resultModel.get("bds"));
		model.addAttribute("map", resultModel.get("map"));
		model.addAllAttributes(map);

		return "board_create_reply_form";
	}

	/**
	 * <pre>
	 * ���� : �亯 ���
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 8. 25. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 8. 25.
	 * @author : jdh
	 * @param map
	 * @param fileForm
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/create_reply")
	public String createReply(@RequestParam Map<String, Object> map, FileForm fileForm, ModelMap model, HttpSession session) {


		map.put("userid", super.getUserId(session));
		map.put("name", super.getName(session));

		ResultHashMap resultModel = boardService.createReply(map, fileForm);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		return "redirect:/board/list/" + map.get("tabseq");
	}

	/**
	 * <pre>
	 * ���� : �Խ��� ���� ��
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 6. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 6.
	 * @author : jdh
	 * @param map
	 * @param model
	 * @return
	 */
	@RequestMapping("/modify_form")
	public String modifyForm(@RequestParam Map<String, Object> map, ModelMap model, HttpSession session) {


		map.put("userid", super.getUserId(session));

		ResultHashMap resultModel = boardService.getOne(map);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAttribute("bds", resultModel.get("bds"));
		model.addAttribute("map", resultModel.get("map"));
		model.addAttribute("fileList", resultModel.get("fileList"));
		model.addAllAttributes(map);

		return "board_modify_form";
	}

	/**
	 * <pre>
	 * ���� : �Խ��� ����
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 15. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 15.
	 * @author : jdh
	 * @param map
	 * @param fileForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/modify")
	public String modify(@RequestParam Map<String, Object> map, FileForm fileForm, ModelMap model, HttpSession session) {


		map.put("userid", super.getUserId(session));
		map.put("name", super.getName(session));

		ResultHashMap resultModel = boardService.modify(map, fileForm);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		return "redirect:/board/one/" + map.get("tabseq") + "/" + map.get("seq");
	}

	/**
	 * <pre>
	 * ���� : �Խ��� ����
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 15. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 15.
	 * @author : jdh
	 * @param map
	 * @param fileForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/remove")
	public String remove(@RequestParam Map<String, Object> map, FileForm fileForm, ModelMap model, HttpSession session) {


		map.put("userid", super.getUserId(session));
		map.put("name", super.getName(session));

		ResultHashMap resultModel = boardService.remove(map, fileForm);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		//model.addAllAttributes(map);

		return "redirect:/board/list/" + map.get("tabseq");
	}

	/**
	 * <pre>
	 * ���� : ���� ��㳻��
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 10. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 10.
	 * @author : jdh
	 * @param map
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/my_qa_list")
	public String listMyQa(@RequestParam Map<String, Object> map, ModelMap model, HttpSession session) {


		map.put("userid", super.getUserId(session));

		ResultHashMap resultModel = boardService.getListMyQa(map);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAllAttributes(resultModel);
		model.addAllAttributes(map);

		return "board_my_qa_list";
	}

	@RequestMapping("/my_qa_one/{tabseq}/{seq}")
	public String oneMyQa(@PathVariable("tabseq") String tabseq, @PathVariable("seq") String seq, @RequestParam Map<String, Object> map, ModelMap model, HttpSession session) {


		map.put("userid", super.getUserId(session));
		map.put("tabseq", tabseq);
		map.put("seq", seq);

		ResultHashMap resultModel = boardService.getOneMyQa(map);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAttribute("map", resultModel.get("map"));
		model.addAttribute("fileList", resultModel.get("fileList"));
		model.addAllAttributes(map);

		return "board_my_qa_one";
	}

}
