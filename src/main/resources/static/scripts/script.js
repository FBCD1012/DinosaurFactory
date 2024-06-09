src="https://unpkg.com/axios/dist/axios.min.js"
// collapsible
let coll = document.getElementsByClassName("collapsible");
let i;

for (i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function() {
    this.classList.toggle("active");
    let content = this.nextElementSibling;
    if (content.style.maxHeight){
      content.style.maxHeight = null;
    } else {
      content.style.maxHeight = content.scrollHeight + "px";
    } 
  });
}

// 获取按钮元素
var walletButton = document.getElementById('walletButton');
// 声明一个变量来存储地址值
var userAddress = "";
// 检查MetaMask是否已安装
if (typeof window.ethereum !== 'undefined') {
    console.log('MetaMask is installed!');

    // 当按钮被点击时，连接到MetaMask
    walletButton.addEventListener('click', function() {
        // 请求用户授权连接到MetaMask
        ethereum.request({ method: 'eth_requestAccounts' })
            .then(function(accounts) {
                // 连接成功，更新按钮显示为钱包地址的前几位
                 // 只显示前6位
                walletButton.textContent = accounts[0].substring(0, 6) + '...';
                // 将地址存储在变量中
                userAddress = accounts[0];
                document.cookie=`userAddress=${userAddress}`
                postToTheAddress(userAddress)
                // 添加鼠标悬停事件
                walletButton.addEventListener('mouseover', function() {
                    walletButton.textContent = accounts[0];
                });
                // 添加鼠标移出事件
                walletButton.addEventListener('mouseout', function() {
                    walletButton.textContent = accounts[0].substring(0, 6) + '...';
                });
            })
            .catch(function(error) {
                // 连接失败，打印错误信息
                console.error(error);
            });
    });
} else {
    // MetaMask未安装，显示错误消息或者提示用户安装MetaMask
    console.log('Please install the MetaMask extension!');
}
function postToTheAddress(userAddress){
    axios.post("/address",{
        userAdd: userAddress
    },{
        headers : {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(({data})=>{
        if (data.success) {
            alert("User address obtained successfully :)")
        }else {
            alert("Failed to obtain user address :(")
        }
    })
}
