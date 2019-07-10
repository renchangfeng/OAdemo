package com.dao.mapper;

import java.util.List;

import com.pojo.PrivilegeCustom;

public interface PrivilegeCustomMapper {
      
	// 查询 根节点，用于所有权限全部加载 
    public PrivilegeCustom searchNodePointInfo();
    
    //查询一级节点数据(以及关联的二级节点)
    public List<PrivilegeCustom> searchAllNodeOnePointInfo();
    
    //查询所有公共权限
    public List<PrivilegeCustom> searchAllPublicPrivilegeInfo();
}
