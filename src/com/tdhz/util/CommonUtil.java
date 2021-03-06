package com.tdhz.util;

import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.tdhz.dto.KqDetailItemDTO;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tdhz.pojo.SNSUserInfo;
import com.tdhz.pojo.WeixinOauth2Token;
import org.springframework.util.CollectionUtils;


public class CommonUtil {
	 private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	 /**
		 * 获取网页授权凭证֤
		 * @author TD-PC
		 *
		 */
		public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
	        WeixinOauth2Token wat = null;
	        // 拼接请求地址ַ
	        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	        requestUrl = requestUrl.replace("APPID", appId);
	        requestUrl = requestUrl.replace("SECRET", appSecret);
	        requestUrl = requestUrl.replace("CODE", code);
	        // 获取网页授权凭证֤
	        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
	        if (null != jsonObject) {
	            try {
	                wat = new WeixinOauth2Token();
	                wat.setAccessToken(jsonObject.getString("access_token"));
	                wat.setExpiresIn(jsonObject.getInt("expires_in"));
	                wat.setRefreshToken(jsonObject.getString("refresh_token"));
	                wat.setOpenId(jsonObject.getString("openid"));
	                wat.setScope(jsonObject.getString("scope"));
	            } catch (Exception e) {
	                wat = null;
	                int errorCode = jsonObject.getInt("errcode");
	                String errorMsg = jsonObject.getString("errmsg");
	                log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
	            }
	        }
	        return wat;
	    }
		/**
		 * 通过网页授权获取用户信息
		 */	
		
	    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
	        SNSUserInfo snsUserInfo = null;
	        //拼接请求地址ַ
	        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
	        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
	        //通过网页授权获取用户信息
	        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

	        if (null != jsonObject) {
	            try {
	                snsUserInfo = new SNSUserInfo();
	             // 用户的标识
	                snsUserInfo.setOpenId(jsonObject.getString("openid"));
	                // 昵称
	                snsUserInfo.setNickname(jsonObject.getString("nickname"));
	                // 性别（1是男性，2是女性，0是未知）
	                snsUserInfo.setSex(jsonObject.getInt("sex"));
	                // 用户所在国家
	                snsUserInfo.setCountry(jsonObject.getString("country"));
	                // 用户所在省份
	                snsUserInfo.setProvince(jsonObject.getString("province"));
	                // 用户所在城市
	                snsUserInfo.setCity(jsonObject.getString("city"));
	                // 用户头像
	                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
	               
	            } catch (Exception e) {
	                snsUserInfo = null;
	                int errorCode = jsonObject.getInt("errcode");
	                String errorMsg = jsonObject.getString("errmsg");
	                log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
	            }
	        }
	        return snsUserInfo;
		}
	 	

	    /**
	     * 发送https请求
	     * 
	     * @param requestUrl �����ַ
	     * @param requestMethod ����ʽ��GET��POST��
	     * @param outputStr �ύ�����
	     * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ)
	     */
	    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
	        JSONObject jsonObject = null;
	        try {
	            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
	            TrustManager[] tm = { new MyX509TrustManager() };
	            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	            sslContext.init(null, tm, new java.security.SecureRandom());
	            //从上述SSLContext对象中得到SSLSocketFactory对象
	            SSLSocketFactory ssf = sslContext.getSocketFactory();

	            URL url = new URL(requestUrl);
	            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	            conn.setSSLSocketFactory(ssf);
	            
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setUseCaches(false);
	            // 设置请求方式（GET/POST）
	            conn.setRequestMethod(requestMethod);

	            // 当outputStr不为null时向输出流写数据
	            if (null != outputStr) {
	                OutputStream outputStream = conn.getOutputStream();
	                // 注意编码格式
	                outputStream.write(outputStr.getBytes("UTF-8"));
	                outputStream.close();
	            }

	            // 从输入流读取返回内容
	            InputStream inputStream = conn.getInputStream();
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String str = null;
	            StringBuffer buffer = new StringBuffer();
	            while ((str = bufferedReader.readLine()) != null) {
	                buffer.append(str);
	            }

	            // 释放资源
	            bufferedReader.close();
	            inputStreamReader.close();
	            inputStream.close();
	            inputStream = null;
	            conn.disconnect();
	            jsonObject = JSONObject.fromObject(buffer.toString());
	        } catch (ConnectException ce) {
	            log.error("连接超时：{}", ce);
	        } catch (Exception e) {
	            log.error("https请求异常：{}", e);
	        }
	        return jsonObject;
	    }

	    
	    /**
	     * URL编码（utf-8）
	     * 
	     * @param source
	     * @return
	     */
	    public static String urlEncodeUTF8(String source) {
	        String result = source;
	        try {
	            result = java.net.URLEncoder.encode(source, "utf-8");
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }


	    public static void appendParam(StringBuilder sql, List<?> vals){
			int size = vals.size();
	    	for(int i = 0; i < size; i++){
				Object v = vals.get(i);
				String split = "";
				if(v instanceof String){
					split = "'";
				}
				if(i == size - 1){
					sql.append( split ).append( v ).append(split);
				}else{
					sql.append( split ).append( v ).append(split).append(", ");
				}
			}

		}

		public static List<KqDetailItemDTO> get(List<Object[]> result) {
			/**
			 * pinfo.piid,")
			 .append(" pinfo.piname, ")
			 .append(" pinfo.credno1, ")
			 .append(" room.roomname as roomname,")
			 .append(" apartment.roomname as apartmentname,")
			 .append(" first_dept.deptname as classname,")
			 .append(" second_dept.deptname as collegename");
			 */
			List<KqDetailItemDTO> items = new ArrayList<>();
			if (!CollectionUtils.isEmpty(result)) {
				for (Object[] vals : result) {
					int i = 0;
					Integer piid = (Integer) vals[i];
					String piname = (String) vals[i++];
					String credno1 = (String) vals[i++];
					String roomname = (String) vals[i++];
					String apartmentname = (String) vals[i++];
					String classname = (String) vals[i++];
					String collegename = (String) vals[i++];
					log.info("查询到的具体信息 piid:{} piname:{} credno1:{} roomname:{} apartmentname:{} classname:{} collegename:{} ",
							new Object[]{piid, piname, credno1, roomname, apartmentname, classname, collegename});

					KqDetailItemDTO itemDTO = new KqDetailItemDTO();
					itemDTO.setRoomName(roomname);
					itemDTO.setClassName(classname);
					itemDTO.setCollegeName(collegename);
					itemDTO.setApartmentName(apartmentname);
					itemDTO.setStudentName(piname);
					itemDTO.setStudentNo(credno1);
					items.add(itemDTO);
				}
			}
			return items;
		}

	public static List<String> getQjLeaveTypes(){
		/**
		 * 个人请假：病假 HOLIDAY_PERSON_ILL = "1";

		 * 个人请假：事假 HOLIDAY_PERSON_ABSENCE = "2";
		 *
		 * 个人请假：其他 HOLIDAY_PERSON_OTHER = "4";
		 *
		 */
		List<String> leaveTypes = new ArrayList<>();
		// 病假
		leaveTypes.add(TypeConstant.HOLIDAY_PERSON_ILL);
		// 事假
		leaveTypes.add(TypeConstant.HOLIDAY_PERSON_ABSENCE);
		// 其他
		leaveTypes.add(TypeConstant.HOLIDAY_PERSON_OTHER);
		return leaveTypes;
	}

	/**
	 * 获取实习的leaveTypes
	 * @return
	 */
	public static List<String> getSxLeaveTypes(){
		List<String> leaveTypes = new ArrayList<>();
		// 个人请假：实习
		leaveTypes.add(TypeConstant.HOLIDAY_PERSON_WORK);
		return leaveTypes;
	}
}
