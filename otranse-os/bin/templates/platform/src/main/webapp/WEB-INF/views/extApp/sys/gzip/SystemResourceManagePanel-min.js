if(typeof Global==="undefined"){Global={}}if(!Global.sytemModuleLoader){Global.sytemModuleLoader=new Ext.tree.TreeLoader({iconCls:"disco-tree-node-icon",url:"resource.java?cmd=getModules",listeners:{beforeload:function(b,a){b.baseParams.id=(a.id.indexOf("root")<0?a.id:"")}}})}if(typeof PermissionList==="undefined"){PermissionList=Ext.extend(BaseGridList,{pagingToolbar:false,storeMapping:["id","name","description"],initComponent:function(){this.cm=new Ext.grid.ColumnModel([{header:"\u93c9\u51ae\u6aba\u935a\ufffd",sortable:true,width:100,dataIndex:"name"},{header:"\u7ee0\ufffd\u6d60\ufffd",id:"description",sortable:true,width:165,dataIndex:"description"}]);PermissionList.superclass.initComponent.call(this)}})}if(typeof RoleList==="undefined"){RoleList=Ext.extend(BaseGridList,{pagingToolbar:false,storeMapping:["id","name","title","description"],initComponent:function(){this.cm=new Ext.grid.ColumnModel([{header:"\u7f02\u682b\u721c",sortable:true,width:80,dataIndex:"name"},{header:"\u935a\u5d87\u041e",sortable:true,width:165,dataIndex:"title"}]);RoleList.superclass.initComponent.call(this)}})}SystemResourceListPanel=Ext.extend(Disco.Ext.CrudPanel,{id:"systemResourceListPanel",showAdd:false,baseUrl:"resource.java",batchRemoveMode:true,pageSize:25,viewWin:{title:"\u74a7\u52ec\u7c2e\u7487\ufe3d\u510f\u93cc\u30e7\u6e45",width:600,height:480},types:[["\u59af\u2033\u6f61\u95c4\u612c\u57d7","MODULE"],["URL\u95c4\u612c\u57d7","URL"],["\u93c2\u89c4\u7876\u95c4\u612c\u57d7","METHOD"],["ACL\u95c4\u612c\u57d7","ACL"]],createViewPanel:function(){if(!this.viewPermissionGrid){this.viewPermissionGrid=new PermissionList({title:"\u93b7\u30e6\u6e41\u7487\u30e8\u796b\u5a67\u612e\u6b91\u93c9\u51ae\u6aba",columnWidth:0.5,height:240,gridForceFit:false,pagingToolbar:false,url:"resource.java?cmd=loadPermission"})}if(!this.viewRoleGrid){this.viewRoleGrid=new RoleList({title:"\u93b7\u30e6\u6e41\u7487\u30e8\u796b\u5a67\u612e\u6b91\u7459\u6395\u58ca",columnWidth:0.5,height:240,pagingToolbar:false,url:"resource.java?cmd=loadRole"})}var a=new Ext.form.FormPanel({frame:true,labelWidth:80,labelAlign:"right",items:[{xtype:"fieldset",title:"\u9369\u70d8\u6e70\u6dc7\u2103\u4f05",height:105,items:[{xtype:"hidden",name:"id"},{xtype:"labelfield",fieldLabel:"\u7ee0\ufffd\u6d60\ufffd",name:"desciption"},{xtype:"labelfield",name:"type",fieldLabel:"\u74a7\u52ec\u7c2e\u7eeb\u8bf2\u7037",renderer:Disco.Ext.Util.comboxRender},{xtype:"labelfield",fieldLabel:"\u74a7\u52ec\u7c2e\u93bb\u5fda\u582a",name:"resStr"}]},{xtype:"fieldset",title:"\u7487\ufe3d\u510f",width:570,height:280,layout:"fit",items:{layout:"hbox",layoutConfig:{align:"stretch"},defaults:{flex:1,border:true},items:[this.viewPermissionGrid,{xtype:"box",width:5},this.viewRoleGrid]}}]});return a},createForm:function(){var a=new Ext.form.FormPanel({frame:true,labelWidth:80,labelAlign:"right",defaults:{width:320,anchor:"-20",xtype:"textfield"},items:[{xtype:"hidden",name:"id"},{fieldLabel:"\u7ee0\ufffd\u6d60\ufffd",name:"desciption"},{xtype:"smartcombo",name:"type",hiddenName:"type",fieldLabel:"\u74a7\u52ec\u7c2e\u7eeb\u8bf2\u7037",displayField:"title",valueField:"id",store:new Ext.data.SimpleStore({fields:["title","id"],data:this.types}),editable:false,mode:"local",triggerAction:"all",emptyText:"\u7487\u70fd\ufffd\u590b\u5ae8..."},{xtype:"textarea",fieldLabel:"\u74a7\u52ec\u7c2e\u93bb\u5fda\u582a",name:"resStr"}]});return a},createWin:function(){return this.initWin(438,200,"\u7eef\u8364\u7cba\u74a7\u52ec\u7c2e\u7ee0\uff04\u608a")},view:function(){var a=SystemResourceListPanel.superclass.view.call(this);if(a){this.viewPermissionGrid.store.removeAll();this.viewPermissionGrid.store.load({params:"id="+this.grid.getSelectionModel().getSelected().get("id")});this.viewRoleGrid.store.removeAll();this.viewRoleGrid.store.load({params:"id="+this.grid.getSelectionModel().getSelected().get("id")})}},importModuleResource:function(){Ext.Ajax.request({waitMsg:"\u59dd\uff45\u6e6a\u7035\u714e\u53c6\u951b\u5c83\ue1ec\u7ecb\u5d85\ufffd\ufffd...",url:this.baseUrl+"?cmd=importModuleResource",params:{pack:this.pack,action:this.action},success:function(){this.refresh()},scope:this})},storeMapping:["id","resStr","type","desciption"],initComponent:function(){this.cm=new Ext.grid.ColumnModel({defaultSortable:true,columns:[{header:"\u74a7\u52ec\u7c2e\u7eeb\u8bf2\u7037",width:50,dataIndex:"type",xtype:"objectcolumn",field:["title","text"]},{header:"\u7ee0\ufffd\u6d60\ufffd",width:100,dataIndex:"desciption"},{header:"\u74a7\u52ec\u7c2e\u93bb\u5fda\u582a",width:300,dataIndex:"resStr"}]});SystemResourceListPanel.superclass.initComponent.call(this)}});SystemResourceManagePanel=function(){this.list=new SystemResourceListPanel({region:"center"});this.tree=new Ext.tree.TreePanel({title:"\u7eef\u8364\u7cba\u59af\u2033\u6f61",region:"west",autoScroll:true,collapseFirst:false,collapsible:true,border:false,split:true,width:250,border:false,margins:"0 2 0 0",tools:[{id:"refresh",handler:function(){this.tree.root.reload()},scope:this}],tools:[{id:"refresh",handler:function(){this.tree.root.reload()},scope:this}],beforeDestroy:function(){if(this._contextMenu){Ext.destroy(this._contextMenu);delete this._contextMenu}delete this.currentNode;this.constructor.prototype.beforeDestroy.call(this)},listeners:{scope:this},root:new Ext.tree.AsyncTreeNode({id:"root",text:"\u93b5\ufffd\u93c8\u590b\u0101\u9367\ufffd",expanded:true,iconCls:"treeroot-icon",loader:Global.sytemModuleLoader})});this.tree.on("click",function(b,a){var c=(b.id!="root"?b.id:"");if(!b.isLeaf()&&b.id!="root"){this.list.pack=b.id;this.list.action=""}else{this.list.pack="";this.list.action=b.id=="root"?"":b.id}Ext.apply(this.list.store.baseParams,{action:this.list.action,pack:this.list.pack});this.list.store.removeAll();this.list.store.load()},this);SystemResourceManagePanel.superclass.constructor.call(this,{id:"newsDirManagePanel",closable:true,autoScroll:true,border:false,layout:"border",items:[this.tree,this.list]})};Ext.extend(SystemResourceManagePanel,Ext.Panel,{getTreeContextMenu:function(){if(!this._contextMenu){this._contextMenu=Ext.create({items:[{scope:this,text:"\u93b5\u5f52\u567a\u9352\u6d98\u7f13\u93c9\u51ae\u6aba",handler:this.batchCreatePermission}]},"menu")}return this._contextMenu},createBatchPermissionPanel:function(){var a=new Ext.grid.ColumnModel({defaultSortable:true,columns:[{header:"\u74a7\u52ec\u7c2e\u7eeb\u8bf2\u7037",width:50,dataIndex:"type",xtype:"objectcolumn",field:["title","text"]},{header:"\u7ee0\ufffd\u6d60\ufffd",width:100,dataIndex:"desciption"},{header:"\u74a7\u52ec\u7c2e\u93bb\u5fda\u582a",width:300,dataIndex:"resStr"}]});return new Ext.grid.GridPanel({cm:a,border:false,viewConfig:{autoFill:true,forceFit:true},store:new Ext.data.JsonStore({data:[],fields:["id","type","desciption","resStr"]})})},beforeDestroy:function(){if(this.batchPermissionPanel){delete this.batchPermissionPanel;Ext.destroy(this.batchPermissionPanel)}SystemResourceManagePanel.superclass.beforeDestroy.call(this)},batchCreatePermission:function(){if(this.currentNode){this.batchPermissionPanel=this.batchPermissionPanel||this.createBatchPermissionPanel();Disco.Ext.Window.show({single:false,title:"\u93b5\u5f52\u567a\u9352\u6d98\u7f13\u93c9\u51ae\u6aba",width:600,height:450,items:this.batchPermissionPanel})}}});