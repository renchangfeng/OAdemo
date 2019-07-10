package com.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dao.mapper.PrivilegeCustomMapper;
import com.pojo.PrivilegeCustom;
import com.service.IPrivilegeService;

@Service("privilegeService")
public class PrivilegeServiceImpl implements IPrivilegeService {

	@Resource(name="privilegeCustomMapper")
	private PrivilegeCustomMapper pcm;
	
	//查询权限树的根节点，通过关联映射加载所有权限信息
	public PrivilegeCustom searchNodePointInfo() {
		PrivilegeCustom pc = this.pcm.searchNodePointInfo();
		return pc;
	}
	
	//查询一级节点数据(以及关联的二级节点)
    public List<PrivilegeCustom> searchAllNodeOnePointInfo(){
    	List<PrivilegeCustom> pcList = this.pcm.searchAllNodeOnePointInfo();
    	return pcList;
    }

    //查询所有公共权限
    public List<PrivilegeCustom> searchAllPublicPrivilegeInfo(){
    	List<PrivilegeCustom> pc = this.pcm.searchAllPublicPrivilegeInfo();
    	return pc;
    }
}
