StylesManagePanel=Ext.extend(Disco.Ext.CrudPanel,{id:"stylesManagePanel",baseUrl:"stylesManage.java",gridSelModel:"checkbox",fileUpload:true,pageSize:20,cssName:[["\u699b\u6a3f\ue17b","style.css"],["\u7efe\u3223\u58ca","style2.css"],["\u9483\u6fca\u58ca","style3.css"]],cssNameFormat:function(a){if(a=="style.css"){return"\u699b\u6a3f\ue17b"}else{if(a=="style2.css"){return"\u7efe\u3223\u58ca"}else{if(a=="style3.css"){return"\u9483\u6fca\u58ca"}}}},isValid:[["\u935a\ue21c\u6564",1],["\u7ec2\u4f7a\u6564",2]],isValidFormat:function(a){if(a==1){return"\u935a\ue21c\u6564"}else{if(a==2){return"\u7ec2\u4f7a\u6564"}}},bgPicRender:function(a){if(a!=null&&a!=""){return a+'<b><a style="color:green" href="'+a+'" target="_blank">&nbsp\u93cc\u30e7\u6e45</a></b>'}else{return""}},createForm:function(){var a=new Ext.form.FormPanel({frame:true,labelWidth:80,fileUpload:true,labelAlign:"right",defaults:{width:320,xtype:"textfield"},items:[{xtype:"hidden",name:"id"},{fieldLabel:"\u935a\u5d87\u041e",name:"name",emptyText:"\u935a\u5d87\u041e\u6d93\u5d88\u5158\u6d93\u8679\u2516",allowBlank:false,blankText:"\u935a\u5d87\u041e\u6d93\u5d88\u5158\u6d93\u8679\u2516"},{xtype:"combo",name:"cssName",hiddenName:"cssName",fieldLabel:"\u95ab\u590b\u5ae8\u6d93\u5a5a\ue57d",displayField:"title",valueField:"value",allowBlank:false,blankText:"\u6d93\u5a5a\ue57d\u6d93\u5d88\u5158\u6d93\u8679\u2516",store:new Ext.data.SimpleStore({fields:["title","value"],data:this.cssName}),editable:false,mode:"local",triggerAction:"all",emptyText:"\u7487\u70fd\ufffd\u590b\u5ae8..."},{xtype:"combo",name:"isValid",hiddenName:"isValid",fieldLabel:"\u9418\u8235\ufffd\ufffd",displayField:"title",valueField:"value",allowBlank:false,blankText:"\u9418\u8235\ufffd\u4f77\u7b09\u9473\u6212\u8d1f\u7ecc\ufffd",store:new Ext.data.SimpleStore({fields:["title","value"],data:this.isValid}),editable:false,mode:"local",triggerAction:"all",emptyText:"\u7487\u70fd\ufffd\u590b\u5ae8..."},{xtype:"fileuploadfield",emptyText:"\u95ab\u590b\u5ae8\u6d93\u5a41\u7d36\u93c2\u56e6\u6b22",fieldLabel:"\u9473\u5c7e\u6ad9\u9365\u5267\u5896",name:"bgPicPath",buttonText:"\u95ab\u590b\u5ae8",listeners:{fileselected:function(d,b){var e="";var c="";if(b!=null){e=b.split(".")[1];c=e.toUpperCase()}if(e!=""&&e!="undefined"){if(c!="BMP"&&c!="PNG"&&c!="GIF"&&c!="JPG"&&c!="JPEG"){Ext.Msg.alert("\u6dc7\u2103\u4f05\u93bb\u612e\u305a","\u9359\ue047\u5158\u6d93\u5a41\u7d36\u9365\u5267\u5896,\u9365\u5267\u5896\u95c4\u612a\u7c2cbmp,png,gif,jpeg,jpg\u93cd\u714e\u7d21");d.setValue("")}}}}},{xtype:"textarea",fieldLabel:"\u93bb\u5fda\u582a",name:"info",width:330,height:100}]});return a},userRender:function(a){return a?a.trueName:"$!{lang.get('Unknown')}"},createWin:function(){return this.initWin(450,300,"\u68e3\u682d\u3009\u93cd\u5cf0\u7d21\u690b\u5ea2\u7278\u7ee0\uff04\u608a")},storeMapping:["id","name","cssName","createTime","isValid","bgPicPath"],initComponent:function(){this.cm=new Ext.grid.ColumnModel([{header:"\u935a\u5d87\u041e",sortable:true,width:100,dataIndex:"name"},{header:"\u6d93\u5a5a\ue57d",sortable:true,width:100,dataIndex:"cssName",renderer:this.cssNameFormat},{header:"\u9473\u5c7e\u6ad9",sortable:true,width:120,dataIndex:"bgPicPath",renderer:this.bgPicRender},{header:"\u9352\u6d98\u7f13\u93c3\u5815\u68ff",sortable:true,width:50,dataIndex:"createTime",renderer:this.dateRender()},{header:"\u9418\u8235\ufffd\ufffd",sortable:true,width:50,dataIndex:"isValid",renderer:this.isValidFormat}]);StylesManagePanel.superclass.initComponent.call(this)}});