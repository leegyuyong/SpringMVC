package kr.co.cylearn.common.base;

import kr.co.cylearn.dao.CommonDao;
import kr.co.cylearn.service.BdsService;
import kr.co.cylearn.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

	@Autowired
	protected CommonDao commonDao;

	//sqlmap.xml name space define

	//게시판 정보
	protected static final String BDS_SERVICE = BdsService.class.getName();

	//게시판 서비스
	protected static final String BOARD_SERVICE = BoardService.class.getName();

}
