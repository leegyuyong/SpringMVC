package kr.co.cylearn.controller.web;

import java.util.Map;

import kr.co.cylearn.common.util.ErrorUtil;
import kr.co.cylearn.model.FileForm;
import kr.co.cylearn.model.PagingList;
import kr.co.cylearn.model.ResultHashMap;
import kr.co.cylearn.service.SampleService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sample")
public class SampleController {
	private final static Logger log = Logger.getLogger(SampleController.class);

	@Autowired
	SampleService sampleService;

	String upfileExt = "";

	int upSizeLimit = 0;

/*	*//**
	 * <pre>
	 * 설명 : 테스트 목록 조회
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 4. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 *
	 * @since : 2015. 6. 4.
	 * @author : jdh
	 * @param map
	 * @param model
	 * @return
	 *//*
	@RequestMapping("/list")
	public String list(@RequestParam Map<String, Object> map, ModelMap model) {
		log.debug("model : " + model);
		log.debug("map : " + map.get("subj"));

		ResultHashMap resultModel = sampleService.getTestList(map);

		if (resultModel == null || resultModel.getString("resultType").equals("ERROR")) {
			String errorCode = resultModel.getString("errorCode");
			model.addAttribute(errorCode);
			return "errors_historyBack";
		}

		model.addAttribute("list", resultModel.getList("list"));

		return "test_list";
	}

	*//**
	 * <pre>
	 * 설명 : 테스트 상세 조회
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 4. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 *
	 * @since : 2015. 6. 4.
	 * @author : jdh
	 * @param map
	 * @param model
	 * @return
	 *//*
	@RequestMapping("/one")
	public String one(@RequestParam Map<String, Object> map, ModelMap model) {
		ResultHashMap resultModel = sampleService.getTestOne(map);

		if (resultModel == null || resultModel.getString("resultType").equals("ERROR")) {
			String errorCode = resultModel.getString("errorCode");
			model.addAttribute("error", ErrorUtil.getErrorModel(errorCode));

			log.debug("model:" + model.toString());
			return "errors_historyBack";
		}

		model.addAttribute("map", resultModel.get("map"));

		return "test_one";
	}

	*//**
	 * <pre>
	 * 설명 : 테스트 상세 URI 패스 조회
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 4. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 *
	 * @since : 2015. 6. 4.
	 * @author : jdh
	 * @param map
	 * @param model
	 * @return
	 *//*
	@RequestMapping("/one/{subj}")
	public String oneURI(@PathVariable("subj") String subj, ModelMap model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subj", subj);

		ResultHashMap resultModel = sampleService.getTestOne(map);

		if (resultModel == null || resultModel.getString("resultType").equals("ERROR")) {
			String errorCode = resultModel.getString("errorCode");
			model.addAttribute("error", ErrorUtil.getErrorModel(errorCode));

			log.debug("model:" + model.toString());
			return "errors_historyBack";
		}

		model.addAttribute("map", resultModel.get("map"));

		return "test_one";
	}

	*//**
	 * <pre>
	 * 설명 : 테스트 목록 조회(JSON)
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 4. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 *
	 * @since : 2015. 6. 4.
	 * @author : jdh
	 * @param map
	 * @param model
	 * @return
	 *//*
	@RequestMapping("/listJson")
	@ResponseBody
	public Map<String, Object> listJson(@RequestParam Map<String, Object> map, JSONResult resultObject) {
		ResultHashMap resultModel = sampleService.getTestList(map);

		if (resultModel == null || resultModel.getString("resultType").equals("ERROR")) {
			String errorCode = resultModel.getString("errorCode");
			resultObject.put("error", ErrorUtil.getErrorModel(errorCode));
		} else {
			resultObject.put("list", resultModel.getList("list"));
		}
		log.debug("model : " + resultObject.toString());
		return resultObject.getResult();
	}*/



	/**
	 * <pre>
	 * 설명 : 게시판 목록 조회
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 4. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 *
	 * @since : 2015. 6. 4.
	 * @author : jdh
	 * @param map
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{tabseq}_json", method = RequestMethod.GET)
	@ResponseBody
	public PagingList list(@PathVariable int tabseq, @RequestParam Map<String, Object> map) {
		log.debug("map : " + map.get("page"));

		ResultHashMap resultModel = sampleService.getList(tabseq, map);

		PagingList pagingList = new PagingList(map, resultModel.getList("list"));

		return pagingList;
	}

	@RequestMapping(value="/{tabseq}", method = RequestMethod.GET)
	public String listAsHTML(@PathVariable int tabseq, @RequestParam Map<String, Object> map, ModelMap model) {
		log.debug("model : " + model);
		log.debug("map : " + map.get("subj"));

		PagingList pagingList = this.list(tabseq, map);

		/*if (resultModel == null || resultModel.getString("resultType").equals("ERROR")) {
			String errorCode = resultModel.getString("errorCode");
			model.addAttribute(errorCode);
			return "errors_historyBack";
		}*/

		model.addAttribute("list", pagingList.getList());
		model.addAllAttributes(map);

		return "sample_list";
	}

	/**
	 * <pre>
	 * 설명 : 게시판 상세 조회
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 4. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 *
	 * @since : 2015. 6. 4.
	 * @author : jdh
	 * @param map
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{tabseq}/{seq}", method = RequestMethod.GET)
	public String view(@PathVariable int tabseq, @PathVariable int seq, @RequestParam Map<String, Object> map, ModelMap model) {
		ResultHashMap resultModel = sampleService.getBoardOne(map);

		if (resultModel == null || resultModel.getString("resultType").equals("ERROR")) {
			String errorCode = resultModel.getString("errorCode");
			model.addAttribute("error", ErrorUtil.getErrorModel(errorCode));

			log.debug("model:" + model.toString());
			return "errors_historyBack";
		}

		model.addAttribute("one", resultModel.get("one"));
		model.addAttribute("list", resultModel.get("list"));
		model.addAllAttributes(map);

		return "test_board_one";
	}

	@RequestMapping("/board_one")
	public String viewAsHTML(@RequestParam Map<String, Object> map, ModelMap model) {
		ResultHashMap resultModel = sampleService.getBoardOne(map);

		if (resultModel == null || resultModel.getString("resultType").equals("ERROR")) {
			String errorCode = resultModel.getString("errorCode");
			model.addAttribute("error", ErrorUtil.getErrorModel(errorCode));

			log.debug("model:" + model.toString());
			return "errors_historyBack";
		}

		model.addAttribute("one", resultModel.get("one"));
		model.addAttribute("list", resultModel.get("list"));
		model.addAllAttributes(map);

		return "test_board_one";
	}


	/**
	 * <pre>
	 * 설명 : 테스트 입력 폼
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 8. jdh          최초 작성
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


		model.addAllAttributes(map);

		log.debug("form======================================");
		return "board_create_form";
	}

	/**
	 * <pre>
	 * 설명 : 게시판 등록
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 15. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 6. 15.
	 * @author : jdh
	 * @param map
	 * @param fileForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(@RequestParam Map<String, Object> map, FileForm fileForm, ModelMap model) {

		ResultHashMap resultModel;

		resultModel = sampleService.addBoard(map, fileForm);

		if (resultModel == null || resultModel.getString("resultType").equals("ERROR")) {
			String errorCode = resultModel.getString("errorCode");
			model.addAttribute("error", ErrorUtil.getErrorModel(errorCode));

			log.debug("model:" + model.toString());
			return "errors_historyBack";
		}

		model.addAllAttributes(map);

		return "redirect:/test/board_list";
	}

	/**
	 * <pre>
	 * 설명 : 게시판 수정
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 15. jdh          최초 작성
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

		ResultHashMap resultModel = sampleService.UpdateBoard(map);

		return "test_board_update";
	}

	/**
	 * <pre>
	 * 설명 : 게시판 삭제
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 6. 15. jdh          최초 작성
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

		ResultHashMap resultModel = sampleService.deleteBoard(map);

		model.addAllAttributes(map);

		return "redirect:/test/board_list";
	}

	@RequestMapping("/top")
	public String top(@RequestParam Map<String, Object> map, ModelMap model) {
		model.addAttribute("txt", "TOP 입니다.");
		return "test_top";
	}

	@RequestMapping("/gnb")
	public String gnb(@RequestParam Map<String, Object> map, ModelMap model) {
		model.addAttribute("txt", "GNB 입니다.");
		return "test_gnb";
	}

	@RequestMapping("/footer")
	public String footer(@RequestParam Map<String, Object> map, ModelMap model) {
		model.addAttribute("txt", "footer 입니다.");
		return "test_footer";
	}
}
