package kr.co.cylearn.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONResult extends ObjectMapper {
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	private String result;
	private String message;

	public JSONResult() {
		this.result = "OK";
		this.message = "";
	}

	public void put(String key, Object value) {
		resultMap.put(key, value);
	}

	/**
	 * result에 모든 데이타 추가
	 * @param mapToAdd result에 추가할 map
	 */
	public void putAll(Map<String, Object> mapToAdd) {
		resultMap.putAll(mapToAdd);
	}

	public Map<String, Object> getResult() {
		resultMap.put("Result", result);
		resultMap.put("Message", message);
		return resultMap;
	}

	public Map<String, Object> getExternalAPIResult(String code, String message, Object data) {
		resultMap.put("code", code);
		resultMap.put("message", message);
		resultMap.put("data", data);
		return resultMap;
	}

	public String getJsonString() throws JsonGenerationException, JsonMappingException, IOException {
		resultMap.put("Result", result);
		resultMap.put("Message", message);
		return this.writeValueAsString(this.resultMap);
	}

	public void setAPIFail(Exception ex) {
		this.result = "FAIL";
		this.message = ex.getMessage();
	}

	public void setAPISuccess(String message) {
		this.result = "OK";
		this.message = message;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof JSONResult)) {
			return false;
		}
		return resultMap.equals(((JSONResult)other).resultMap);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
