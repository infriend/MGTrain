var oldtabledata={};
function fresh(){
    d = {"auctionid":auctionid}
    $.post("/bidinglist", d, function (res) {
        if (res["success"]==true){
            for (var i = 0; i < res["data"].length; i++){
                if (oldtabledata[res["data"][i].bidingno]==undefined){
                    $("#bidinglist").prepend(
                        "<tr><td>"+ res["data"][i].bidingno+"</td><td>"+ res["data"][i].price+"元</td><td>"
                        + res["data"][i].bidingtime+"</td></tr>"
                    )
                    oldtabledata[res["data"][i].bidingno]=1
                }
            }
        } else{
            layer.msg("获取失败");
        }
    })
}
setInterval("fresh()", 1000)