package kr.co.cylearn.common.util;

/**
 * <pre>
 * kr.co.cylearn.common.util
 * PageUtil.java
 *
 * 설명 : 페이지
 * </pre>
 *
 * @since : 2015. 7. 16.
 * @author : jdh
 * @version : v1.0
 */
public class PageUtil {

	private int pageNo = 0; // 현재 페이지

	private int startRow = 0; // 시작행번호

	private int endRow = 0; // 끝행번호

	private int rowCount = 0; // 총 데이터 갯수

	private int totalPage = 0; // 총 페이지 갯수

	private int pageBlockSize = 0; // 화면에 보여줄 페이지 갯수

	private int startPage = 0; // 화면에 출력될 시작 페이지

	private int endPage = 0; // 화면에 출력될 마지막 페이지

	private int pageList = 0;

	public PageUtil() {
	}

	/**
	 * 페이지 분할 생성자
	 * @param rowCount 데이터 전체 갯수
	 * @param pageSize 한페이지에 출력될 데이터 갯수
	 * @param pageBlockSize 화면에 보여줄 페이지 갯수
	 * @param pageNo 현재 페이지
	 */
	public PageUtil(int rowCount, int pageSize, int pageBlockSize, int pageNo) {
		this.rowCount = rowCount;
		this.pageBlockSize = pageBlockSize;
		this.pageNo = pageNo;

		this.startRow = (pageNo - 1) * pageSize + 1;
		this.endRow = pageNo * pageSize;

		this.totalPage = (int) (Math.ceil((double) rowCount / (double) pageSize));
		this.pageList = (int) (Math.ceil((pageNo - 1) / pageBlockSize)) - 1;
	}

	/**
	 * 페이지 분할 데이터 셋팅
	 * @param rowCount 데이터 전체 갯수
	 * @param pageSize 한페이지에 출력될 데이터 갯수
	 * @param pageBlockSize 화면에 보여줄 페이지 갯수
	 * @param pageNo 현재 페이지
	 */
	public void setPageInfo(int rowCount, int pageSize, int pageBlockSize, int pageNo) {
		this.rowCount = rowCount;
		this.pageBlockSize = pageBlockSize;
		this.pageNo = pageNo;

		this.totalPage = (int) (Math.ceil((double) rowCount / (double) pageSize));
		this.pageList = (int) (Math.ceil((pageNo - 1) / pageBlockSize)) - 1;
	}

	/**
	 * 현재 클릭한 페이지 번호 리턴
	 *
	 * @return
	 */
	public int getPageNo() {
		return this.pageNo;
	}

	/**
	 * 시작행번호 리턴
	 *
	 * @return
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * 끝행번호 리턴
	 *
	 * @return
	 */
	public int getEndRow() {
		return endRow;
	}

	/**
	 * 총 페이지 수 리턴
	 *
	 * @return
	 */
	public int getTotalPage() {
		int pageValue = totalPage;

		if (pageValue == 0) pageValue = 1;

		return pageValue;
	}

	/**
	 * 총 게시물 수 리턴
	 *
	 * @return
	 */
	public int getRowCount() {
		return this.rowCount;
	}

	/**
	 * 출력할 페이지 값 리턴
	 *
	 * @return
	 */
	public int[] getPage() {
		// 화면에 나타날 시작 페이지
		startPage = (pageList + 1) * pageBlockSize;

		// 화면에 나타날 마지막 페이지
		endPage = startPage + pageBlockSize;

		if (endPage > totalPage) endPage = totalPage;

		int[] page = null;

		if (endPage > startPage) page = new int[endPage - startPage];
		else page = new int[0];

		int length = page.length;

		for (int i = 0; i < length; i++) {
			page[i] = startPage + i + 1;
		}

		return page;
	}

	/**
	 * 이전 부분의 마지막 페이지값 리턴
	 *
	 * @return
	 */
	public int getPreviousPartLastPage() {
		return (pageList + 1) * pageBlockSize;
	}

	/**
	 * 다음 부분의 첫페이지 값 리턴
	 *
	 * @return
	 */
	public int getNextPartFirstPage() {
		return ((pageList + 2) * pageBlockSize) + 1;
	}

	/**
	 * 이전 페이지 리턴
	 *
	 * @return
	 */
	public int getPreviousPage() {
		int previousPage = 0;

		if (pageNo != 1) previousPage = pageNo - 1;
		else previousPage = 1;

		return previousPage;
	}

	/**
	 * 다음 페이지 리턴
	 *
	 * @return
	 */
	public int getNextPage() {
		int nextPage = 0;

		if (pageNo == totalPage) nextPage = totalPage;
		else nextPage = pageNo + 1;

		return nextPage;
	}

	/**
	 * 마지막 페이지 값 리턴
	 *
	 * @return
	 */
	public int getLastPage() {
		return totalPage;
	}

	/**
	 * 현재 클릭한 페이지 부분이 마지막 부분인지 체크
	 *
	 * @return
	 */
	public boolean isLastPagePart() {
		boolean isLastPagePart = false;

		if (endPage >= totalPage) isLastPagePart = true;

		return isLastPagePart;
	}

	/**
	 * 현재 클릭한 페이지 부분이 시작 부분인지 체크
	 *
	 * @return
	 */
	public boolean isStartPagePart() {
		boolean isStartPagePart = false;

		if (startPage == 0) isStartPagePart = true;

		return isStartPagePart;
	}

	/**
	 * 이전 페이지가 존재하는지 검사
	 *
	 * @return
	 */
	public boolean isPreviousPage() {
		boolean isPreviousPage = false;

		if (pageNo > 1) isPreviousPage = true;

		return isPreviousPage;
	}

	/**
	 * 다음 페이지가 존재하는지 검사
	 *
	 * @return
	 */
	public boolean isNextPage() {
		boolean isNextPage = false;

		if (pageNo < totalPage) isNextPage = true;

		return isNextPage;
	}

	/**
	 * <pre>
	 * 설명 : 페이지 프린트
	 * 변경이력 :
	 * -----------------------------------------------------------------
	 * 변경일       작성자       변경내용
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 20. jdh          최초 작성
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 20.
	 * @author : jdh
	 * @param impFuncName
	 * @return
	 */
	public String printPage(String impFuncName) {

		// 페이지 분할 데이터 추출
		int[] outPage = getPage();

		if (impFuncName.equals("")) impFuncName = "goPage";

		StringBuffer sb = new StringBuffer();

		sb.append("<div class='paging' align='center'>");
		if (!isStartPagePart()) {
			sb.append("	<div><a href=\"#none\" class=\"first\" title=\"첫페이지\"  onclick=\""+ impFuncName +"('1')\"></a></div>");
			sb.append("	<div><a href=\"#none\" class=\"prev\" title=\"이전페이지\" onclick=\""+ impFuncName +"('"+getPreviousPartLastPage()+"')\"></a></div>");
		}

		for (int i = 0; i < outPage.length; i++) {
			// 현재 클릭한 페이지 일 경우
			if (outPage[i] == getPageNo()) {
				sb.append("	<div><p class='on'>"+outPage[i]+"</p></div>");
			} else {
				sb.append("	<div><p><a href='#none' onclick=\""+ impFuncName +"('"+outPage[i]+"')\" title='페이지 이동'>"+outPage[i]+"</a></p></div>");
			}
		}

		if (!isLastPagePart()) {
			sb.append("	<div><a href='#none' class=\"next\" title=\"다음페이지\" onclick=\""+ impFuncName +"('"+getNextPartFirstPage()+"')\"></a></div>");
			sb.append("	<div><a href='#none' class=\"last\" title=\"마지막페이지\" onclick=\""+ impFuncName +"('"+getLastPage()+"')\"></a></div>");
		}
		sb.append("</div>");

		return sb.toString();
	}


}