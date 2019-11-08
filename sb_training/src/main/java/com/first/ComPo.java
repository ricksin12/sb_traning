package com.first;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class ComPo implements Serializable {

	private static final long serialVersionUID = -5372249717042135501L;
	private String status;
	private String msg;
	private String msgDet;
	private Map<String, Object> rowMap;
	private List<Map<String, Object>> rowList;
	private String rowString;
	private JSONObject rowJSONObject;
	private String rowNum;
	private String id;
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ComPo() {
	}

	public ComPo(String status) {
		this.status = status;
		if ("-1".equals(status)) {
			this.msg = "Add Failed";
			this.msgDet = "Add Failed";
		} else if ("-2".equals(status)) {
			this.msg = "Update Failed";
			this.msgDet = "Updated Failed";
		} else if ("-2.1".equals(status)) {
			this.msg = "Update Failed";
			this.msgDet = "The data has been modified by other people.";
		} else if ("1".equals(status)) {
			this.msg = "Add Succeed";
			this.msgDet = "Add Success";
		} else if ("2".equals(status)) {
			this.msg = "Update Succeed";
			this.msgDet = "Update Success";
		} else {
			this.msg = "Update Succeed";
			this.msgDet = "Unknow Error, Try again or contract the administrator.";
		}
	}

	public ComPo(Map<String, Object> rowMap) {
		this.rowMap = rowMap;
	}

	public ComPo(String status, List<Map<String, Object>> rowList) {
		this.status = status;
		this.rowList = rowList;
		this.rowNum = String.valueOf(rowList.size());
	}

	public ComPo(List<Map<String, Object>> rowList) {
		this.rowList = rowList;
		this.rowNum = String.valueOf(rowList.size());
	}

	public ComPo(String status, String msg, Map<String, Object> rowMap) {
		this.status = status;
		this.msg = msg;
		this.rowMap = rowMap;
	}

	public ComPo(String status, String msg, List<Map<String, Object>> rowList) {
		this.status = status;
		this.msg = msg;
		this.rowList = rowList;
		this.rowNum = String.valueOf(rowList.size());
	}

	public ComPo(String status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public ComPo(String status, String msg, String msgDet) {
		this.status = status;
		this.msg = msg;
		this.msgDet = msgDet;
	}

	public ComPo(String status, String msg, String msgDet, String id) {
		this.status = status;
		this.msg = msg;
		this.msgDet = msgDet;
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgDet() {
		return this.msgDet;
	}

	public void setMsgDet(String msgDet) {
		this.msgDet = msgDet;
	}

	public Map<String, Object> getRowMap() {
		return rowMap;
	}

	public void setRowMap(Map<String, Object> rowMap) {
		this.rowMap = rowMap;
	}

	public List<Map<String, Object>> getRowList() {
		return rowList;
	}

	public void setRowList(List<Map<String, Object>> rowList) {
		this.rowList = rowList;

	}

	public String getRowString() {
		return rowString;
	}

	public void setRowString(String rowString) throws JSONException {
		this.rowString = rowString;
		if (StringUtils.isNoneBlank(rowString)) {
			this.rowJSONObject = new JSONObject(this.rowString.replace("[", "").replace("]", ""));
		}
	}

	public JSONObject getRowJSONObject() {
		return rowJSONObject;
	}

	public void setRowJSONObject(JSONObject rowJSONObject) {
		this.rowJSONObject = rowJSONObject;
	}

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{\"status\":" + status + ", \"msg\":" + msg + ", \"msgDet\":" + msgDet + ", \"rowString\":" + rowString
				+ ", \"rowNum\":" + rowNum + ", \"id\":" + id + "}";
	}

}
