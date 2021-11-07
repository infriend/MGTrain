var oldtabledata={};
function fresh(){
    d = {"auctionid":auctionid}
    $.post("/bidinglist", d, function (res) {
        if (res["success"]==true){
            for (var i = 0; i < res["data"].length; i++){
                if (oldtabledata[res["data"][i].price]==undefined){
                    t = Date.parse(res["data"][i].bidingtime);
                    t = new Date(t);
                    t = t.format("yyyy-MM-dd hh:mm:ss");
                    $("#bidinglist").prepend(
                        "<tr><td>"+ res["data"][i].username+"</td><td>"+ res["data"][i].price+"元</td><td>"
                        + t +"</td></tr>"
                    )
                    oldtabledata[res["data"][i].price]=1
                }
            }
        } else{
            layer.msg("获取失败");
        }
    })
}
setInterval("fresh()", 1000)