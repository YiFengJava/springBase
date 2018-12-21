package xyz.yudong520.manageadmin.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 自定义响应结构
 */
//@ApiModel(value="响应对象",description="手机端响应对象R")
public class R {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
//    @ApiModelProperty(value="状态码",name="code",example="200")
    private Integer code;

    // 响应消息
//    @ApiModelProperty(value="状态信息",name="message",example="OK")
    private String message;

    // 响应中的数据
//    @ApiModelProperty(value="具体数据",name="data")
    private Object data;

    public static R build(Integer status, String msg, Object data) {
        return new R(status, msg, data);
    }

    public static R ok(Object data) {
        return new R(data);
    }

    public static R ok() {
        return new R(null);
    }

    public R() {

    }

    public static R build(Integer status, String msg) {
        return new R(status, msg, null);
    }

    public R(Integer status, String msg, Object data) {
        this.code = status;
        this.message = msg;
        this.data = data;
    }

    public R(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer status) {
        this.code = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     * 
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static R formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, R.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("message").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static R format(String json) {
        try {
            return MAPPER.readValue(json, R.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static R formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("message").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
