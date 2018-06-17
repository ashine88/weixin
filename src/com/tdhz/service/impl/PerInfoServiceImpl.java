package com.tdhz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tdhz.dao.PerInfoDao;
import com.tdhz.pojo.Dept;
import com.tdhz.pojo.PerInfo;
import com.tdhz.service.PerInfoService;

@Service
public class PerInfoServiceImpl implements PerInfoService{
	@Resource
	private PerInfoDao perInfoDao;
	@Override
	public PerInfo findById(Integer piid) {
		
		return perInfoDao.findById(piid);
	}
	@Override
	public Dept findpDept(Integer pdept) {
		
		return perInfoDao.findpDept(pdept);
	}
	@Override
	public void updatePer(PerInfo perInfo) {
		perInfoDao.updatePer(perInfo);
		
	}
	@Override
	public PerInfo findOnePerInfo(String piName) {
		List<PerInfo> plist = perInfoDao.findOnePerInfo(piName);		
		PerInfo perInfo = new PerInfo();
		if(plist.size()>0){
			for(int i=0;i<plist.size();i++){
				perInfo = plist.get(i);
			}
		}else{
			perInfo =null;
		}		
		return perInfo;
	}
	@Override
	public Dept findOneDept(String deptName) {
		List<Dept> dlist = perInfoDao.findOneDept(deptName);		
		Dept dept = new Dept();
		if(dlist.size()>0){
			for(int i=0;i<dlist.size();i++){
				dept = dlist.get(i);
			}
		}else{
			dept =null;
		}		
		return dept;
	}

}
