

var elementById = document.getElementById("info");

function getTheDinosaurList(){
    //构建一个搜索的参数值进行操作
    let searchInfo=elementById.value
    axios.post("/searchTheDinosaur",{
        search: searchInfo
    },{
        headers : {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(({data})=>{
        if (data.success) {
            alert("search successful :)")
            window.location.href="/market"
        }else {
            alert("search failed :(")
        }
    })
}