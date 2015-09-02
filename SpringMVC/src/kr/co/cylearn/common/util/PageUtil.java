package kr.co.cylearn.common.util;

/**
 * <pre>
 * kr.co.cylearn.common.util
 * PageUtil.java
 *
 * ���� : ������
 * </pre>
 *
 * @since : 2015. 7. 16.
 * @author : jdh
 * @version : v1.0
 */
public class PageUtil {

	private int pageNo = 0; // ���� ������

	private int startRow = 0; // �������ȣ

	private int endRow = 0; // �����ȣ

	private int rowCount = 0; // �� ������ ����

	private int totalPage = 0; // �� ������ ����

	private int pageBlockSize = 0; // ȭ�鿡 ������ ������ ����

	private int startPage = 0; // ȭ�鿡 ��µ� ���� ������

	private int endPage = 0; // ȭ�鿡 ��µ� ������ ������

	private int pageList = 0;

	public PageUtil() {
	}

	/**
	 * ������ ���� ������
	 * @param rowCount ������ ��ü ����
	 * @param pageSize ���������� ��µ� ������ ����
	 * @param pageBlockSize ȭ�鿡 ������ ������ ����
	 * @param pageNo ���� ������
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
	 * ������ ���� ������ ����
	 * @param rowCount ������ ��ü ����
	 * @param pageSize ���������� ��µ� ������ ����
	 * @param pageBlockSize ȭ�鿡 ������ ������ ����
	 * @param pageNo ���� ������
	 */
	public void setPageInfo(int rowCount, int pageSize, int pageBlockSize, int pageNo) {
		this.rowCount = rowCount;
		this.pageBlockSize = pageBlockSize;
		this.pageNo = pageNo;

		this.totalPage = (int) (Math.ceil((double) rowCount / (double) pageSize));
		this.pageList = (int) (Math.ceil((pageNo - 1) / pageBlockSize)) - 1;
	}

	/**
	 * ���� Ŭ���� ������ ��ȣ ����
	 *
	 * @return
	 */
	public int getPageNo() {
		return this.pageNo;
	}

	/**
	 * �������ȣ ����
	 *
	 * @return
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * �����ȣ ����
	 *
	 * @return
	 */
	public int getEndRow() {
		return endRow;
	}

	/**
	 * �� ������ �� ����
	 *
	 * @return
	 */
	public int getTotalPage() {
		int pageValue = totalPage;

		if (pageValue == 0) pageValue = 1;

		return pageValue;
	}

	/**
	 * �� �Խù� �� ����
	 *
	 * @return
	 */
	public int getRowCount() {
		return this.rowCount;
	}

	/**
	 * ����� ������ �� ����
	 *
	 * @return
	 */
	public int[] getPage() {
		// ȭ�鿡 ��Ÿ�� ���� ������
		startPage = (pageList + 1) * pageBlockSize;

		// ȭ�鿡 ��Ÿ�� ������ ������
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
	 * ���� �κ��� ������ �������� ����
	 *
	 * @return
	 */
	public int getPreviousPartLastPage() {
		return (pageList + 1) * pageBlockSize;
	}

	/**
	 * ���� �κ��� ù������ �� ����
	 *
	 * @return
	 */
	public int getNextPartFirstPage() {
		return ((pageList + 2) * pageBlockSize) + 1;
	}

	/**
	 * ���� ������ ����
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
	 * ���� ������ ����
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
	 * ������ ������ �� ����
	 *
	 * @return
	 */
	public int getLastPage() {
		return totalPage;
	}

	/**
	 * ���� Ŭ���� ������ �κ��� ������ �κ����� üũ
	 *
	 * @return
	 */
	public boolean isLastPagePart() {
		boolean isLastPagePart = false;

		if (endPage >= totalPage) isLastPagePart = true;

		return isLastPagePart;
	}

	/**
	 * ���� Ŭ���� ������ �κ��� ���� �κ����� üũ
	 *
	 * @return
	 */
	public boolean isStartPagePart() {
		boolean isStartPagePart = false;

		if (startPage == 0) isStartPagePart = true;

		return isStartPagePart;
	}

	/**
	 * ���� �������� �����ϴ��� �˻�
	 *
	 * @return
	 */
	public boolean isPreviousPage() {
		boolean isPreviousPage = false;

		if (pageNo > 1) isPreviousPage = true;

		return isPreviousPage;
	}

	/**
	 * ���� �������� �����ϴ��� �˻�
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
	 * ���� : ������ ����Ʈ
	 * �����̷� :
	 * -----------------------------------------------------------------
	 * ������       �ۼ���       ���泻��
	 * ------------ ------------ ---------------------------------------
	 * 2015. 7. 20. jdh          ���� �ۼ�
	 * -----------------------------------------------------------------
	 * </pre>
	 * @since : 2015. 7. 20.
	 * @author : jdh
	 * @param impFuncName
	 * @return
	 */
	public String printPage(String impFuncName) {

		// ������ ���� ������ ����
		int[] outPage = getPage();

		if (impFuncName.equals("")) impFuncName = "goPage";

		StringBuffer sb = new StringBuffer();

		sb.append("<div class='paging' align='center'>");
		if (!isStartPagePart()) {
			sb.append("	<div><a href=\"#none\" class=\"first\" title=\"ù������\"  onclick=\""+ impFuncName +"('1')\"></a></div>");
			sb.append("	<div><a href=\"#none\" class=\"prev\" title=\"����������\" onclick=\""+ impFuncName +"('"+getPreviousPartLastPage()+"')\"></a></div>");
		}

		for (int i = 0; i < outPage.length; i++) {
			// ���� Ŭ���� ������ �� ���
			if (outPage[i] == getPageNo()) {
				sb.append("	<div><p class='on'>"+outPage[i]+"</p></div>");
			} else {
				sb.append("	<div><p><a href='#none' onclick=\""+ impFuncName +"('"+outPage[i]+"')\" title='������ �̵�'>"+outPage[i]+"</a></p></div>");
			}
		}

		if (!isLastPagePart()) {
			sb.append("	<div><a href='#none' class=\"next\" title=\"����������\" onclick=\""+ impFuncName +"('"+getNextPartFirstPage()+"')\"></a></div>");
			sb.append("	<div><a href='#none' class=\"last\" title=\"������������\" onclick=\""+ impFuncName +"('"+getLastPage()+"')\"></a></div>");
		}
		sb.append("</div>");

		return sb.toString();
	}


}