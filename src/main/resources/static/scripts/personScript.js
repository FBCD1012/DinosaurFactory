//此处进行一波相关的Ajax的请求操作，将相关的请求发送给后端，然后后端进行细节处理操作
const element=document.getElementById("eggBanner");
const result = document.cookie.match(/0x[a-fA-F0-9]+/g)
const  cardElement=document.getElementById("card" )
cardElement.style.display="none"
function getTheResult(){
    return document.cookie.match(/0x[a-fA-F0-9]+/g)
}
function postTheInfo(){
        axios.get("/getDinosaurInfo",{
            userAdd: result
        },{
            headers : {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(({data})=>{
            if (data.success) {
                alert("获取龙蛋成功 :)")
            }else {
                alert("获取龙蛋失败")
            }
        })
}
