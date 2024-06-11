

var elementById = document.getElementById("searchInfo");


function getTheDinosaurList(){

    axios.post("/searchTheDinosaur",{
        search: elementById.value
    },{
        headers : {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(({data})=>{
        if (data.success) {
            alert("search successful :)")
            var value=elementById.value
            window.location.href=`/getTheDinosaur/${value}`
        }else {
            alert("search failed :(")
        }
    })
}