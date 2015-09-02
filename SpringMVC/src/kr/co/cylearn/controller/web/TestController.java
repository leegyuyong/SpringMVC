package kr.co.cylearn.controller.web;

import java.util.HashMap;
import java.util.Map;

import kr.co.cylearn.common.base.BaseController;
import kr.co.cylearn.model.FileForm;
import kr.co.cylearn.model.ResultHashMap;
import kr.co.cylearn.service.TestService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
	private final static Logger log = Logger.getLogger(TestController.class);

	@Autowired
	TestService testService;

	String upfileExt = "";

	int upSizeLimit = 0;

	/**
	 * <pre>
	 * ���� : �׽�Ʈ ��� ��ȸ
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
	@RequestMapping("/list")
	public String list(@RequestParam Map<String, Object> map, ModelMap model) {
		log.debug("model : " + model);
		log.debug("map : " + map.get("subj"));

		ResultHashMap resultModel = testService.getTestList(map);

		if (RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAttribute("list", resultModel.getList("list"));

		return "test_list";
	}

	/**
	 * <pre>
	 * ���� : �׽�Ʈ �� ��ȸ
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
	@RequestMapping("/one")
	public String one(@RequestParam Map<String, Object> map, ModelMap model) {
		ResultHashMap resultModel = testService.getTestOne(map);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAttribute("map", resultModel.get("map"));

		return "test_one";
	}

	/**
	 * <pre>
	 * ���� : �׽�Ʈ �� URI �н� ��ȸ
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
	@RequestMapping("/one/{subj}")
	public String oneURI(@PathVariable("subj") String subj, ModelMap model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subj", subj);

		ResultHashMap resultModel = testService.getTestOne(map);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAttribute("map", resultModel.get("map"));

		return "test_one";
	}

	/**
	 * <pre>
	 * ���� : �׽�Ʈ ��� ��ȸ(JSON)
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
	@RequestMapping("/list_json")
	@ResponseBody
	public ResultHashMap listJson(@RequestParam Map<String, Object> map) {
		return testService.getTestList(map);
	}

	/**
	 * <pre>
	 * ���� : �Խ��� ��� ��ȸ
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
	@RequestMapping("/board_list")
	public String boardList(@RequestParam Map<String, Object> map, ModelMap model) {
		log.debug("model : " + model);
		log.debug("map : " + map.get("subj"));

		ResultHashMap resultModel = testService.getBoardList(map);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAttribute("list", resultModel.getList("list"));
		model.addAllAttributes(map);

		return "test_board_list";
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
	@RequestMapping("/board_one")
	public String boardOne(@RequestParam Map<String, Object> map, ModelMap model) {
		ResultHashMap resultModel = testService.getBoardOne(map);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAttribute("one", resultModel.get("one"));
		model.addAttribute("fileList", resultModel.get("fileList"));
		model.addAllAttributes(map);

		return "test_board_one";
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
	@RequestMapping("/board_create_form")
	public String boardCreateForm(@RequestParam Map<String, Object> map, ModelMap model) {

		model.addAllAttributes(map);

		log.debug("form======================================");
		return "test_board_create_form";
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
	@RequestMapping("/board_insert")
	public String boardInsert(@RequestParam Map<String, Object> map, FileForm fileForm, ModelMap model) {

		ResultHashMap resultModel;

		resultModel = testService.addBoard(map, fileForm);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAllAttributes(map);

		return "redirect:/test/board_list";
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
	@RequestMapping("/board_update_form")
	public String boardUpdateForm(@RequestParam Map<String, Object> map, ModelMap model) {

		ResultHashMap resultModel = testService.getBoardOne(map);

		if (resultModel == null || RESULT_FAIL.equals(resultModel.getResult())) {
			model.addAllAttributes(resultModel);
			return "errors_historyBack";
		}

		model.addAttribute("one", resultModel.get("one"));
		model.addAttribute("fileList", resultModel.get("fileList"));
		model.addAllAttributes(map);

		log.debug("form======================================");
		return "test_board_update_form";
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
	@RequestMapping("/board_update")
	public String boardUpdate(@RequestParam Map<String, Object> map, FileForm fileForm, ModelMap model) {

		ResultHashMap resultModel = testService.UpdateBoard(map);

		return "test_board_update";
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
	@RequestMapping("/board_delete")
	public String boardDelete(@RequestParam Map<String, Object> map, FileForm fileForm, ModelMap model) {

		ResultHashMap resultModel = testService.deleteBoard(map);

		model.addAllAttributes(map);

		return "redirect:/test/board_list";
	}

	@RequestMapping("/top")
	public String top(@RequestParam Map<String, Object> map, ModelMap model) {

		model.addAttribute("txt", "TOP �Դϴ�.");
		return "test_top";
	}

	@RequestMapping("/gnb")
	public String gnb(@RequestParam Map<String, Object> map, ModelMap model) {
		model.addAttribute("txt", "GNB �Դϴ�.");
		return "test_gnb";
	}

	@RequestMapping("/footer")
	public String footer(@RequestParam Map<String, Object> map, ModelMap model) {
		model.addAttribute("txt", "footer �Դϴ�.");
		return "test_footer";
	}
}
