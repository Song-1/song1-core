package core.bean;

import java.io.Serializable;
import net.sf.json.JSONObject;

public class ResultBean<T> implements Serializable {
	private static final long serialVersionUID = -90763053365837550L;
	private boolean success = false;
	private String message;
	private String code;
	private T valueObject;

	public ResultBean() {
	}

	public ResultBean(boolean success, String code, String message, T valueObject) {
		this.success = success;
		this.message = message;
		this.code = code;
		this.valueObject = valueObject;
	}

	public static ResultBean resultFail(String code, String message, Object valueObject) {
		return new ResultBean(false, code, message, valueObject);
	}

	public static ResultBean resultFail(String code, String message) {
		return new ResultBean(false, code, message, null);
	}

	public static ResultBean resultFail(String message) {
		return new ResultBean(false, null, message, null);
	}

	public static ResultBean resultFail() {
		return new ResultBean(false, null, null, null);
	}

	public static ResultBean resultSuccess(String code, String message, Object valueObject) {
		return new ResultBean(true, code, message, valueObject);
	}

	public static ResultBean resultSuccess(String code, String message) {
		return new ResultBean(true, code, message, null);
	}

	public static ResultBean resultSuccess(String message) {
		return new ResultBean(true, null, message, null);
	}
	public static ResultBean resultSuccess(Object valueObject) {
		return new ResultBean(true, null, null, valueObject);
	}
	public static ResultBean resultSuccess() {
		return new ResultBean(true, null, null, null);
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(T valueObject) {
		this.valueObject = valueObject;
	}

	public String toString() {
		return "ResultBean [success=" + this.success + ", message=" + this.message + ", code=" + this.code
				+ ", valueObject=" + this.valueObject + "]";
	}

	public String toJson() {
		JSONObject jo = new JSONObject();
		jo.put("success", Boolean.valueOf(this.success));
		jo.put("message", this.message);
		jo.put("code", this.code);
		jo.put("valueObject", this.valueObject);
		return jo.toString();
	}
}