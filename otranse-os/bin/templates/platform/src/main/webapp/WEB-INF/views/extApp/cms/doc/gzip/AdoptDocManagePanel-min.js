AdoptDocManagePanel=Ext.extend(Disco.Ext.CrudPanel,{gridSelModel:"checkbox",id:"adoptDocManagePanel",baseUrl:"newsDoc.java",autoLoadGridData:true,autoScroll:false,totalPhoto:0,pageSize:20,showAdd:false,showEdit:false,showRemove:false,showView:false,defaultsActions:{create:"save",list:"findAdpotDoc",view:"view",update:"update",remove:"remove"},topicRender:function(b,c,a){return String.format('{1}<b><a style="color:green" href="news.java?cmd=show&id={0}" target="_blank">&nbsp\u93cc\u30e7\u6e45</a></b><br/>',a.id,b)},storeMapping:["id","title","keywords","createDate","author","source","putDate","dir","toBranch","branchDir","state","branchState",{name:"branchDirId",mapping:"branchDir"},{name:"dirId",mapping:"dir"}],initComponent:function(){this.cm=new Ext.grid.ColumnModel([{header:"\u6d93\u5a5a\ue57d",dataIndex:"title",width:250,renderer:this.topicRender},{header:"\u9352\u55da\ue511\u93cd\u5fd5\u6d30",sortable:true,width:90,dataIndex:"branchDir",renderer:this.objectRender("name")},{header:"\u6d63\u6ec6\ufffd\ufffd",sortable:true,width:80,dataIndex:"author"},{header:"\u93be\u626e\u01f9\u93c3\u30e6\u6e61",sortable:true,width:100,dataIndex:"createDate",renderer:this.dateRender()}]);AdoptDocManagePanel.superclass.initComponent.call(this)}});