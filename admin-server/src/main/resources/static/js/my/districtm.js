 $(function () {

    //默认绑定省
//    ProviceMengBind();
    //绑定事件
    $("#provinceMeng").change( function () {
        cityMengBind();
        areaMengBind();
    })
    
    $("#cityMeng").change(function () {
        areaMengBind();
    })
   
   


})


function ProviceMengBind() {
    //清空下拉数据
    var pro=$("#provinceMeng").val();
    $.ajax({
        type: "get",
        url: "/districtms/province",
        dataType: "JSON",
        async: false,
        success: function (data) {
        	  var select = $("#provinceMeng");
              select.append("<option value='0'>==请选择===</option>");
              for(var i=0; i<data.length; i++){
                  var d = data[i];
                  var id = d['id'];
                  var name = d['name'];
                  
                  select.append("<option value='"+ id +"'>" +name+"</option>");
              }
            //从服务器获取数据进行绑定
        },
        error: function () { alert("Error"); }
    });


   
        
}
function cityMengBind() {


    var provice = $("#provinceMeng").val();
    //判断省份这个下拉框选中的值是否为空
    if (provice == 0) {
        $("#cityMeng").html("<option value="+0+">==请选择===</option>");
        return;
    }
    $("#cityMeng").html("");
    var str = "<option value="+0+">==请选择===</option>";
    $.ajax({
        type: "get",
        url: "/districtms/city/"+provice,
        dataType: "JSON",
        async: false,
        success: function (data) {
            //从服务器获取数据进行绑定
            $.each(data, function (i, item) {
                str += "<option value=" + item.id + ">" + item.name + "</option>";
            })
            //将数据添加到省份这个下拉框里面
            $("#cityMeng").append(str);
        },
        error: function () { alert("Error"); }
    });


}

function areaMengBind() {


    var provice = $("#cityMeng").val();
    //判断市这个下拉框选中的值是否为空
    if (provice == 0) {
    	 $("#areaMeng").html("<option value="+0+">==请选择===</option>");
        return;
    }
    $("#areaMeng").html("");
    var str = "<option value="+0+">==请选择===</option>";
    //将市的ID拿到数据库进行查询，查询出他的下级进行绑定
    $.ajax({
        type: "get",
        url: "/districtms/area/"+provice,
        dataType: "JSON",
        async: false,
        success: function (data) {
            //从服务器获取数据进行绑定
            $.each(data, function (i, item) {
                str += "<option value=" + item.id + ">" + item.name + "</option>";
            })
            //将数据添加到省份这个下拉框里面
            $("#areaMeng").append(str);
        },
        error: function () { alert("Error"); }
    });
   
}