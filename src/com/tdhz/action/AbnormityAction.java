package com.tdhz.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tdhz.dao.AbnormityDao;
import com.tdhz.pojo.AbnormityInfo;
import com.tdhz.pojo.PerInfo;
import com.tdhz.pojo.Tbcha_channeltg_14;
import com.tdhz.service.AbnormityService;
import com.tdhz.service.PerInfoService;
import com.tdhz.util.PageBean;

@Controller
@Scope("prototype")
public class AbnormityAction {
	private static final Logger log = LoggerFactory.getLogger(AbnormityAction.class);
	public List<Tbcha_channeltg_14> getTblist() {
		return tblist;
	}
	public void setTblist(List<Tbcha_channeltg_14> tblist) {
		this.tblist = tblist;
	}

	@Autowired
	private AbnormityService abnormityService;
	@Autowired
	private AbnormityDao abnormityDao;
	@Autowired
	private PerInfoService perInfoService;
	
	private List<Tbcha_channeltg_14> tblist;
	
	private List<AbnormityInfo> alist = new ArrayList<AbnormityInfo>();
	private AbnormityInfo abnormityInfo;
	private PerInfo perInfo;
	private String onday;
	private PageBean pe; //分页信息
	
	public List<AbnormityInfo> getAlist() {
		return alist;
	}
	public void setAlist(List<AbnormityInfo> alist) {
		this.alist = alist;
	}
	public AbnormityInfo getAbnormityInfo() {
		return abnormityInfo;
	}
	public void setAbnormityInfo(AbnormityInfo abnormityInfo) {
		this.abnormityInfo = abnormityInfo;
	}
	public PerInfo getPerInfo() {
		return perInfo;
	}
	public void setPerInfo(PerInfo perInfo) {
		this.perInfo = perInfo;
	}
	public String getOnday() {
		return onday;
	}
	public void setOnday(String onday) {
		this.onday = onday;
	}
	public PageBean getPe() {
		return pe;
	}
	public void setPe(PageBean pe) {
		this.pe = pe;
	}
	public static void main(String[] args) {
		String DeptName = "全部_建桥学院学生_2009本科_汽车运用";
		DeptName = DeptName.substring(DeptName.indexOf("_",DeptName.indexOf("_")+1) + 1,DeptName.length());
		System.out.println(DeptName);
	}
	//晚归查看(辅导员)
	public String findLaterInfo(){
		//获得HttpServletRequest对象
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpSession session = request.getSession();	
		Integer userid = (Integer) session.getAttribute("userid");
		//定义日期
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if("".equals(onday) ||onday == null){			
			onday=sdf.format(now);
		}		
		List<Object[]> list = abnormityService.findLaterInfoByAss(onday, userid);
		log.info("辅导员id("+userid+")查看晚归信息===============共"+list.size());
		if(list.size()>0){
			for(int i=0;i<list.size();i++){				
				AbnormityInfo abn = new AbnormityInfo();
				abn.setPiname(list.get(i)[1].toString());
				String DeptName = list.get(i)[2].toString();
				DeptName = DeptName.substring(DeptName.indexOf("_",DeptName.indexOf("_")+1) + 1,DeptName.length());
				abn.setDeptname(DeptName);
				abn.setBacktime(list.get(i)[3].toString());
				alist.add(abn);
			}
		}
		
		//定义分页信息
		int page;
        try {
            //当前页数
            page = Integer.valueOf(pe.getPageNum());
        } catch (NumberFormatException e) {
            page = 1;
        }
		 //总数
        int total = alist.size();
        //每页显示数
        int perPage = 10;
        //总页数
        int totalPages = total % perPage == 0 ? total / perPage : total / perPage + 1;
        //本页起始序号
        int beginIndex = (page - 1) * perPage;
        //本页末尾序号的下一个
        int endIndex = beginIndex + perPage;
        if (endIndex > total){
        	endIndex = total;
        }
        
        pe.setTotalNum(total);
        pe.setMaxPage(totalPages);
        pe.setPageSize(perPage);
        pe.setPageNum(page);
        pe.setBeginIndex(beginIndex);
        pe.setEndIndex(endIndex);
		
		return "alist";
	}
	//异常数据统计(辅导员)
	public String findCardAbn(){
		//获得HttpServletRequest对象
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpSession session = request.getSession();	
		Integer userid = (Integer) session.getAttribute("userid");
		//定义日期
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if("".equals(onday) ||onday == null){			
			onday=sdf.format(now);
		}
		List<Object[]> list = abnormityService.findAbnInfoByAss(onday, userid);
		log.info("辅导员id("+userid+")查看异常数据信息===============共"+list.size());
		if(list.size()>0){
			for(int i=0;i<list.size();i++){				
				AbnormityInfo abn = new AbnormityInfo();
				abn.setPiname(list.get(i)[1].toString());
				String DeptName = list.get(i)[2].toString();
				DeptName = DeptName.substring(DeptName.indexOf("_",DeptName.indexOf("_")+1) + 1,DeptName.length());
				abn.setDeptname(DeptName);
				abn.setBacktime(list.get(i)[3].toString());
				abn.setAbn_type((Integer) list.get(i)[4]);
				alist.add(abn);
			}
		}
		
		//定义分页信息
		int page;
        try {
            //当前页数
            page = Integer.valueOf(pe.getPageNum());
        } catch (NumberFormatException e) {
            page = 1;
        }
		 //总数
        int total = alist.size();
        //每页显示数
        int perPage = 10;
        //总页数
        int totalPages = total % perPage == 0 ? total / perPage : total / perPage + 1;
        //本页起始序号
        int beginIndex = (page - 1) * perPage;
        //本页末尾序号的下一个
        int endIndex = beginIndex + perPage;
        if (endIndex > total){
        	endIndex = total;
        }
        
        pe.setTotalNum(total);
        pe.setMaxPage(totalPages);
        pe.setPageSize(perPage);
        pe.setPageNum(page);
        pe.setBeginIndex(beginIndex);
        pe.setEndIndex(endIndex);
		return "abnlist";
	}
	
	//晚归查看(学生处)
	public String findLaterInfoByChief(){
		//获得HttpServletRequest对象
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpSession session = request.getSession();	
		Integer userid = (Integer) session.getAttribute("userid");
		//定义日期
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if("".equals(onday) ||onday == null){			
			onday=sdf.format(now);
		}		
		List<Object[]> list = abnormityService.findLaterInfoByAss(onday, userid);
		log.info("学生处领导id("+userid+")查看晚归信息===============共"+list.size());
		if(list.size()>0){
			for(int i=0;i<list.size();i++){				
				AbnormityInfo abn = new AbnormityInfo();
				abn.setPiname(list.get(i)[1].toString());
				String DeptName = list.get(i)[2].toString();
				DeptName = DeptName.substring(DeptName.indexOf("_",DeptName.indexOf("_")+1) + 1,DeptName.length());
				abn.setDeptname(DeptName);
				abn.setBacktime(list.get(i)[3].toString());
				alist.add(abn);
			}
		}
		
		//定义分页信息
		int page;
        try {
            //当前页数
            page = Integer.valueOf(pe.getPageNum());
        } catch (NumberFormatException e) {
            page = 1;
        }
		 //总数
        int total = alist.size();
        //每页显示数
        int perPage = 10;
        //总页数
        int totalPages = total % perPage == 0 ? total / perPage : total / perPage + 1;
        //本页起始序号
        int beginIndex = (page - 1) * perPage;
        //本页末尾序号的下一个
        int endIndex = beginIndex + perPage;
        if (endIndex > total){
        	endIndex = total;
        }
        
        pe.setTotalNum(total);
        pe.setMaxPage(totalPages);
        pe.setPageSize(perPage);
        pe.setPageNum(page);
        pe.setBeginIndex(beginIndex);
        pe.setEndIndex(endIndex);
		return "xsc_alist";
	}
	
	//异常数据统计(学生处)
		public String findCardAbnByChief(){
			//获得HttpServletRequest对象
			HttpServletRequest request= ServletActionContext.getRequest();
			HttpSession session = request.getSession();	
			Integer userid = (Integer) session.getAttribute("userid");
			//定义日期
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if("".equals(onday) ||onday == null){			
				onday=sdf.format(now);
			}
			List<Object[]> list = abnormityService.findAbnInfoByAss(onday, userid);
			log.info("学生处id("+userid+")查看异常数据信息===============共"+list.size());
			if(list.size()>0){
				for(int i=0;i<list.size();i++){				
					AbnormityInfo abn = new AbnormityInfo();
					abn.setPiname(list.get(i)[1].toString());
					String DeptName = list.get(i)[2].toString();
					DeptName = DeptName.substring(DeptName.indexOf("_",DeptName.indexOf("_")+1) + 1,DeptName.length());
					abn.setDeptname(DeptName);
					abn.setBacktime(list.get(i)[3].toString());
					abn.setAbn_type((Integer) list.get(i)[4]);
					alist.add(abn);
				}
			}
			
			//定义分页信息
			int page;
	        try {
	            //当前页数
	            page = Integer.valueOf(pe.getPageNum());
	        } catch (NumberFormatException e) {
	            page = 1;
	        }
			 //总数
	        int total = alist.size();
	        //每页显示数
	        int perPage = 10;
	        //总页数
	        int totalPages = total % perPage == 0 ? total / perPage : total / perPage + 1;
	        //本页起始序号
	        int beginIndex = (page - 1) * perPage;
	        //本页末尾序号的下一个
	        int endIndex = beginIndex + perPage;
	        if (endIndex > total){
	        	endIndex = total;
	        }
	        
	        pe.setTotalNum(total);
	        pe.setMaxPage(totalPages);
	        pe.setPageSize(perPage);
	        pe.setPageNum(page);
	        pe.setBeginIndex(beginIndex);
	        pe.setEndIndex(endIndex);
			return "xsc_abnlist";
		}

}
