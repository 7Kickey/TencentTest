package src;

import net.sf.json.JSONObject;

import java.io.*;

/**
 * {
 * echoMsg: "abc123xyz",
 * result: {
 * code: 0,
 * message: "",
 * sessionId: "1615389753503628_6YhDM67Z87t23",
 * randomData: ""
 * }
 * }
 * 要求:
 * 1使用 java 或 golang, 或自己熟悉的其它编程语言, 通过系统或第三方类库提供的 http api, d将 json 数据拉取下来
 * 1.1 将 json 解析到结构体中, 并打印各字段的值.
 * 1.2 将 json 中的 数字, 替换成减号, 并保存在文件中
 */
public class RetMsg implements Serializable {
    private String echoMsg;
    private Result result;

    @Override
    public String toString() {
        return "RetMsg{" +
                "echoMsg='" + echoMsg + '\'' +
                ", result=" + result +
                '}';
    }

    public RetMsg(String echoMsg, Result result) {
        this.echoMsg = echoMsg;
        this.result = result;
    }

    public String getEchoMsg() {
        return echoMsg;
    }

    public void setEchoMsg(String echoMsg) {
        this.echoMsg = echoMsg;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static void main(String[] args) {
        String s = "{" +
                "echoMsg: \"abc123xyz\"," +
                "result: {" +
                "code: 0," +
                "message: \"\"," +
                "sessionId: \"1615389753503628_6YhDM67Z87t23\"," +
                "randomData: \"\"" +
                "}" +
                "}";
        JSONObject jsonObject = JSONObject.fromObject(s);
        String echoMsg = jsonObject.getString("echoMsg").replaceAll("[0-9]", "-");
        JSONObject temp = jsonObject.getJSONObject("result");
        Integer code = temp.getInt("code");
        String message = temp.getString("message").replaceAll("[0-9]", "-");
        String sessionId = temp.getString("sessionId").replaceAll("[0-9]", "-");
        String randomData = temp.getString("randomData").replaceAll("[0-9]", "-");
        RetMsg retMsg = new RetMsg(echoMsg, new Result(code, message, sessionId, randomData));
        try {
            FileOutputStream fos = new FileOutputStream(new File("retMsg.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(retMsg);
            oos.close();
            fos.close();

            FileInputStream fi = new FileInputStream(new File("retMsg.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            RetMsg retMsg1 = (RetMsg) oi.readObject();

            System.out.println(retMsg1.toString());

            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

class Result implements Serializable {
    private Integer code;
    private String message;
    private String sessionId;
    private String randomData;

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", randomData='" + randomData + '\'' +
                '}';
    }

    public Result(Integer code, String message, String sessionId, String randomData) {
        this.code = code;
        this.message = message;
        this.sessionId = sessionId;
        this.randomData = randomData;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getRandomData() {
        return randomData;
    }

    public void setRandomData(String randomData) {
        this.randomData = randomData;
    }
}
