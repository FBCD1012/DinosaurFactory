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

// 检查MetaMask是否已安装
if (typeof window.ethereum !== 'undefined') {
    console.log('MetaMask is installed!');

    // 当按钮被点击时，连接到MetaMask
    walletButton.addEventListener('click', function() {
        // 请求用户授权连接到MetaMask
        ethereum.request({ method: 'eth_requestAccounts' })
            .then(function(accounts) {
                // 连接成功，更新按钮显示为钱包地址的前几位
                var shortAddress = accounts[0].substring(0, 6) + '...'; // 只显示前6位
                walletButton.textContent = shortAddress;
            })
            .catch(function(error) {
                // 连接失败，打印错误信息
                console.error(error);
            });
    });

    // 鼠标悬停事件，显示完整地址f
    walletButton.addEventListener('mouseover', function() {
        ethereum.request({ method: 'eth_requestAccounts' })
            .then(function(accounts) {
                walletButton.textContent = accounts[0];
            });
    });

    // 鼠标移出事件，重新显示前几位
    walletButton.addEventListener('mouseout', function() {
        ethereum.request({ method: 'eth_requestAccounts' })
            .then(function(accounts) {
                var shortAddress = accounts[0].substring(0, 6) + '...';
                walletButton.textContent = shortAddress;
            });
    });
} else {
    // MetaMask未安装，显示错误消息或者提示用户安装MetaMask
    console.log('Please install the MetaMask extension!');
}
