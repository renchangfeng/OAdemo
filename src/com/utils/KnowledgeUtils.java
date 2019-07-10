package com.utils;

import java.util.List;

import com.pojo.KnowledgeFolderCustom;

public final class KnowledgeUtils {
 
	 //用于生成下载页面的左侧树形菜单
	 public static String createFileDownloadAndDelete(KnowledgeFolderCustom root){
		 StringBuffer str = new StringBuffer();
		 createTreeNodeForFileDownloadAndDelete(root,str);
		 return str.toString();
		 
	 }
	 //生成下载页面左侧的树形控件节点
	 public static void createTreeNodeForFileDownloadAndDelete(KnowledgeFolderCustom treeNode, StringBuffer tree){
		 tree.append("<li id=\"li_" + treeNode.getId() + "\">");
		 if(treeNode.getLevel() == 0){
		  	 tree.append("<IMG src=\"style/images/MenuIcon/FUNC20056.gif\"/>" + treeNode.getFolderName());
		 }else if(treeNode.getLevel() != 0){
			 if(treeNode.getIsLeaf() == 0){
				 tree.append(" <span class=\"folder\"><label for=\"cb_" + treeNode.getId() + "\">" + treeNode.getFolderName() + "</label></span>");
				
			 }else if(treeNode.getIsLeaf() == 1){
				 tree.append("<span class=\"file\"><a href=\"javascript:searchFiles(" + treeNode.getId() + ",'" + treeNode.getFolderName() + "');\">" + treeNode.getFolderName() + "</a></span>");
			 }
		 }
		 
		 //处理子节点
		 List<KnowledgeFolderCustom> children = treeNode.getChildrenList();
		 if(children != null && children.size() > 0){
			 tree.append("<ul>");
			 for(KnowledgeFolderCustom knowledge : children){
				 createTreeNodeForFileDownloadAndDelete(knowledge,tree);
			 }
			 tree.append("</ul>");
		 }
		 tree.append("</li>");
	 }
	 
	//生成左侧上传文件列表页左侧树形菜单
	 public static String createFileUpload(KnowledgeFolderCustom root){
		 StringBuffer str = new StringBuffer();
		 createTreeNodeForFileUpload(root,str);
		 return str.toString();
		 
	 }
	 
	 
	//生成左侧上传文件列表页左侧树形菜单节点
	 public static void createTreeNodeForFileUpload(KnowledgeFolderCustom treeNode, StringBuffer tree){
		 tree.append("<li id=\"li_" + treeNode.getId() + "\">");
		 if(treeNode.getLevel() == 0){
		  	 tree.append("<IMG src=\"style/images/MenuIcon/FUNC20056.gif\"/>" + treeNode.getFolderName());
		 }else if(treeNode.getLevel() != 0){
			 if(treeNode.getIsLeaf() == 0){
				 tree.append(" <span class=\"folder\"><label for=\"cb_" + treeNode.getId() + "\">" + treeNode.getFolderName() + "</label></span>");
				
			 }else if(treeNode.getIsLeaf() == 1){
				 tree.append("<span class=\"file\"><input type=\"radio\" name=\"resourceIdList\" value=\"" + treeNode.getId() + "\" id=\"cb_" + treeNode.getId() + "\" onclick=\"setRadioValue(this.value, '" + treeNode.getFolderName() + "');\"/><label for=\"cb_" + treeNode.getId() + "\">" + treeNode.getFolderName() + "</label></span>");
			 }
		 }
		 
		 //处理子节点
		 List<KnowledgeFolderCustom> children = treeNode.getChildrenList();
		 if(children != null && children.size() > 0){
			 tree.append("<ul>");
			 for(KnowledgeFolderCustom knowledge : children){
				 createTreeNodeForFileUpload(knowledge,tree);
			 }
			 tree.append("</ul>");
		 }
		 tree.append("</li>");
	 }
	 
	//生成创建目录列表页左侧树形菜单
		public static String createTreeForSaveFolder(KnowledgeFolderCustom root){
			StringBuffer tree = new StringBuffer();
			createTreeNodeForSaveFolder(root, tree);
			
			return tree.toString();
		}
		
		//生成上传文件列表页左侧树形菜单节点
		public static void createTreeNodeForSaveFolder(KnowledgeFolderCustom treeNode, StringBuffer tree){
			tree.append("<li id=\"li_" + treeNode.getId() + "\">");
			tree.append("<input type=\"radio\" name=\"resourceIdList\" value=\"" + treeNode.getId() + "\" id=\"cb_" + treeNode.getId() + "\" onclick=\"setRadioValue(this.value,'" + treeNode.getFolderName() + "'," + treeNode.getLevel() + ");\"/><label for=\"cb_" + treeNode.getId() + "\">" + treeNode.getFolderName() + "</label>");
			
			//子节点
			List<KnowledgeFolderCustom> childrenList = treeNode.getChildrenList();
			if(childrenList != null && childrenList.size()>0){
				tree.append("<ul>");
				for(KnowledgeFolderCustom kfc : childrenList){
					createTreeNodeForSaveFolder(kfc, tree);
				}
				tree.append("</ul>");
			}
			tree.append("</li>");
		}
}
