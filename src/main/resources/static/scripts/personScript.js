//此处进行一波相关的Ajax的请求操作，将相关的请求发送给后端，然后后端进行细节处理操作
src = "https://unpkg.com/axios/dist/axios.min.js"

const element=document.getElementById("eggBanner");
const result = document.cookie.match(/0x[a-fA-F0-9]+/g)
const  cardElement=document.getElementById("card" )
cardElement.style.display="none"
function getTheResult(){
    return document.cookie.match(/0x[a-fA-F0-9]+/g)
}
function postTheInfo(){
    window.location.href='/getTheDinosaurInfo'
}
var  dinosaurId=document.getElementById('dinosaurInfo').value
//异步传递相关参数到网站中进行操作理解
function upLoadTheMarket(){
    document    .cookie = `dinosaurId=${dinosaurId}`
    window.location.href='/insertTheDinosaur'
}
//孵化恐龙ajax请求
function HatchTheDinosaur(elementText){
    axios.post('/hatch',{
        dinosaurEggInfo:elementText
    },{
        headers : {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(({data}) => {
        if (data.success) {
            alert("Hatch the egg:)")
        } else {
            alert("Failed to hatch the egg :(")
        }
    })
}

function getTheDinosaurInfo(){
    window.location.href="/getTheDinosaurInfo"
}


function getTheUploadInfo(dinosaurIndex){
    document.cookie=`dinosaurIndex=${dinosaurIndex}`
    axios.get("/getTheUploadInfo")
}