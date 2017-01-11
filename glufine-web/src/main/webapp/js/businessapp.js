$(function(){
	//alert(1);
	var debug = '/glufine-web';
	
	$("#login").click(function(){
		$("input[type='password']").blur(function(){
			var paw=$(this).val()
			//console.info(paw);
		});
		$.post(debug+"/api/findAllBussinessStore.do",{pwd:$("#password").val()},function(data){
			//var json=$.parseJSON(data);
			//获取code码
			var code=data.code;
			if(code==0){				
				$(".business").empty();
				$(".business").append(
					   	"<div class='span4'>"+"<p>商家</p>"+"<select id='sel'> </select>"+"</div>"+
					   	"<div class='span4'>"+"<p>SN</p>"+"<input type='text' id='sn' placeholder='请输入sn'/>"+"</div>"+
					   	"<div class='span3'>"+"<button id='sure'>确认</button>"+"</div>"
					   	);
				var list  = data.data.list;
				//下拉框append div
				for(var i= 0;i<list.length;i++){
					//append 下拉框<option></option>
					$("#sel").append("<option value="+list[i].id+">"+list[i].appId+"</option>");			
				};
				
			
			}else{
				alert("密码输入错误，请重新输入！");
			}
						
		});							
	});
	$("#sn").live("keydown",function(){
//	    alert(1);
		var value = $("#sn").val();
		if(value.length==11){
			var selval=$("#sel option:selected").val(),snval=$("#sn").val();
			$.post("/glufine-web/api/boxRegiest.do",{bnId:selval,sn:snval},function(data){
				var date=new Date();
				var year=date.getFullYear();
				var month=date.getMonth()+1;
				var d=date.getDate();
				var h=date.getHours();
				var m=date.getMinutes();
				var s=date.getSeconds()+1;
				var current=year+"/"+month+"/"+d+"&nbsp;"+h+":"+m+":"+s;
				var text=$("#sel option:selected").text();
				var tr="<tr><td>" +text+"</td><td>" +snval+"</td>" +
				"<td>"+current+"</td><td>成功</td></tr>";
				var trF="<tr><td>" +text+"</td><td>" +snval+"</td>" +
				"<td>"+current+"</td><td>失败</td></tr>";
				if(data.code==0){
					//alert("sucess");
					
					//$("table").append(tr);
					$("table tr:first-child").after(tr);
					
					
				}else{
					$("table tr:first-child").after(trF);
				}
			})
			console.info(value);
			$("#sn").val("");
		}
	  });
	//点击确定，提交给后台
	$("#sure").live("click",function(){
	    //alert(1);
		var selval=$("#sel option:selected").val(),snval=$("#sn").val();
		$.post(debug+"/api/boxRegiest.do",{bnId:selval,sn:snval},function(data){
			
			if(data.code==0){
				alert("sucess");
				
			}else{
				alert("fail");
			}
		})
	  });
	
	
});

//导出到excel
function AutomateExcel(tableid){
	var elTable = $("#tableid"); //要导出的table id。
	var oRangeRef = document.body.createTextRange();
	oRangeRef.moveToElementText(elTable);
	oRangeRef.execCommand("Copy");
	var appExcel = new ActiveXObject("Excel.Application");
	appExcel.Workbooks.Add().Worksheets.Item(1).Paste();
	appExcel.Visible = true;
	appExcel = null;
}





