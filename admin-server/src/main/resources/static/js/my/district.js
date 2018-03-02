 $(function () {

    //默认绑定省
    ProviceBind();
    //绑定事件
    $("#province").change( function () {
        cityBind();
        areaBind();
    })
    
    $("#city").change(function () {
        areaBind();
    })
   
   


})


function ProviceBind() {
    //清空下拉数据
    var pro=$("#province").val();
    $.ajax({
        type: "get",
        url: "/districts/province",
        dataType: "JSON",
        async: false,
        success: function (data) {
        	  var select = $("#province");
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
function cityBind() {


    var provice = $("#province").val();
    //判断省份这个下拉框选中的值是否为空
    if (provice == 0) {
        $("#city").html("<option value="+0+">==请选择===</option>");
        return;
    }
    $("#city").html("");
    var str = "<option value="+0+">==请选择===</option>";
    $.ajax({
        type: "get",
        url: "/districts/city/"+provice,
        dataType: "JSON",
        async: false,
        success: function (data) {
            //从服务器获取数据进行绑定
            $.each(data, function (i, item) {
                str += "<option value=" + item.id + ">" + item.name + "</option>";
            })
            //将数据添加到省份这个下拉框里面
            $("#city").append(str);
        },
        error: function () { alert("Error"); }
    });


}

function areaBind() {


    var provice = $("#city").val();
    //判断市这个下拉框选中的值是否为空
    if (provice == 0) {
    	 $("#area").html("<option value="+0+">==请选择===</option>");
        return;
    }
    $("#area").html("");
    var str = "<option value="+0+">==请选择===</option>";
    //将市的ID拿到数据库进行查询，查询出他的下级进行绑定
    $.ajax({
        type: "get",
        url: "/districts/area/"+provice,
        dataType: "JSON",
        async: false,
        success: function (data) {
            //从服务器获取数据进行绑定
            $.each(data, function (i, item) {
                str += "<option value=" + item.id + ">" + item.name + "</option>";
            })
            //将数据添加到省份这个下拉框里面
            $("#area").append(str);
        },
        error: function () { alert("Error"); }
    });
   
}