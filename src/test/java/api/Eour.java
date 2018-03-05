package api;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.redpack.utils.DateUtil;

/**
 * 调用百度汇率api
 * 
 * @author Administrator
 * 
 */
@Service
@Scope
public class Eour {



    /**
     * @param urlAll
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "0d263364faa016e5f06075b69b799087");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 定时任务每天执行调用百度汇率api
     * 
     * @throws JSONException
     */
    public void queryHuiLv() {
//        String httpUrl = "http://apis.baidu.com/apistore/currencyservice/currency";
//        String httpArg = null;
//        Dict dict = new Dict();
//        dict.setType("hui_lv");
//        List<Dict> dictList = dictService.findList(dict);// 查询出数据库表中所有的汇率
//        if (dictList != null && dictList.size() > 0) {
//            for (Dict dt : dictList) {
//                String value = dt.getValue();
//                httpArg = "fromCurrency=" + value + "&toCurrency=CNY&amount=1";
//                if (!"1".equals(value)) {// 表里面目前有个舍客勒 键值是1，所以调用百度接口时无法识别
//                    String data = request(httpUrl, httpArg);
//                    try {
//                        if (data != null && !"".equals(data)) {
//                            Root rt = GsonUtils.json2T(data, Root.class);
//                            RetData retData = rt.getRetData();
//                            dt.setValue(retData.getFromCurrency());// 汇率简称
//                            dt.setLabel(retData.getCurrency());// 值
//                            dt.setUpdateDate(new Date());
//                            dictService.updateHuiLv(dt);
//                            System.out.println(data);
//                            System.out.println(retData);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
    }

    /**
     * 测试用
     * 
     * @param httpUrl
     * @param httpArg
     * @return
     */
    public static String newRequest(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "1f73cfd4f4ffd6fa063f312f1c08eec4");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
//        String httpUrl = "http://apis.baidu.com/apistore/currencyservice/currency";
//        String httpArg = "fromCurrency=EUR&toCurrency=USD&amount=1";
//        String jsonResult = newRequest(httpUrl, httpArg);
//        System.out.println(jsonResult);
        System.out.println(DateUtil.getDayOfWeek());
    }

}